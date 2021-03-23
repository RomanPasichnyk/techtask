package digis.techtask.service

import digis.techtask.BaseUnitTest
import digis.techtask.entity.UserEntity
import digis.techtask.exceptions.UserNotFoundException
import digis.techtask.repository.UserRepository
import digis.techtask.service.impl.UserServiceImpl
import spock.lang.Subject
import java.util.stream.Collectors

class UserServiceImplUnitTest extends BaseUnitTest {

    def userRepository = Mock(UserRepository)

    @Subject
    def userService = new UserServiceImpl(userRepository)

    def 'exercise find all users'() {

        given: 'a result from the database'
        def users = random.objects(UserEntity, 5).collect(Collectors.toList())

        when: 'service is called'
        def result = userService.getAllUsers()

        then: 'repository is called'
        1 * userRepository.findAll() >> users

        and: 'result is as expected'
        result == users
    }

    def 'exercise save user'() {

        def userId

        given: 'a valid user'
        def user = random.nextObject(UserEntity)

        when: 'userService is called'
        def result = userService.save(user)

        then: 'repository is called'
        1 * userRepository.save(!null as UserEntity) >> {UserEntity userToSave ->
            with(userToSave) {
                login == user.login
                fullName == user.fullName
                birthDate == user.birthDate
                gender == user.gender
            }
            userId = userToSave.id
        }

        and: 'result is as expected'
        result == userId
    }

    def 'exercise update exiting user'() {

        given: 'a valid user'
        def user = random.nextObject(UserEntity)

        and: 'a result from the database'
        def exitedUser = random.nextObject(UserEntity).tap {id = user.id}

        when: 'printerService is called'
        def result = userService.updateUser(user)

        then: 'check if exists, update, return true'
        1 * userRepository.findById(user.id) >> Optional.of(exitedUser)
        1 * userRepository.save(user) >> {UserEntity printerToUpdate ->
            with(printerToUpdate) {
                id == user.id
                login == user.login
                birthDate == user.birthDate
                fullName == user.fullName
                gender == user.gender
            }
        }
        assert result
    }

    def 'exercise update not exiting user'() {

        given: 'a valid user'
        def user = random.nextObject(UserEntity)

        when: 'printerService is called'
        def result = userService.updateUser(user)

        then: 'check if exists, return false'
        1 * userRepository.findById(user.id) >> Optional.empty()
        assert !result
    }

    def 'exercise find user by id'() {

        given: 'a valid id'
        def id = random.nextLong()

        and: 'a result from the database'
        def user = random.nextObject(UserEntity)

        when: 'service is called'
        def result = userService.getUserById(id)

        then: 'repository is called'
        1 * userRepository.findById(id) >> Optional.of(user)

        and: 'result is as expected'
        result == user
    }

    def 'exercise find user with not existing id'() {

        given: 'a valid id'
        def id = random.nextLong()

        when: 'service is called'
        userService.getUserById(id)

        then: 'repository is called'
        1 * userRepository.findById(id) >> Optional.empty()

        and: 'exception is thrown'
        def ex = thrown(UserNotFoundException)
        ex.message == String.format("User with id %s was not found", id)
    }
}

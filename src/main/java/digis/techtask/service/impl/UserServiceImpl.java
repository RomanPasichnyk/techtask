package digis.techtask.service.impl;

import digis.techtask.entity.UserEntity;
import digis.techtask.exceptions.UserNotFoundException;
import digis.techtask.repository.UserRepository;
import digis.techtask.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Long save(UserEntity userEntity) {
        log.debug("Saving user into database.");
        userRepository.save(userEntity);
        return userEntity.getId();
    }

    @Override
    public List<UserEntity> getAllUsers() {
        log.debug("Retrieving all users from database.");
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(Long id) {
        log.debug("Retrieving user by id: {}.", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));
    }

    @Override
    public boolean updateUser(UserEntity userEntity) {
        Long id = userEntity.getId();

        Optional<UserEntity> currentUser = userRepository.findById(id);
        if (currentUser.isPresent()) {
            log.debug("Updating user by id: {}", id);

            userRepository.save(userEntity);
            return true;
        } else {
            log.warn("User with id: {} doesn't exist in database.", id);
            return false;
        }
    }
}

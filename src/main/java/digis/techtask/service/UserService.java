package digis.techtask.service;

import digis.techtask.entity.UserEntity;
import java.util.List;

public interface UserService {

    Long save(UserEntity userEntity);

    List<UserEntity> getAllUsers();

    UserEntity getUserById(Long id);

    boolean updateUser(UserEntity userEntity);
}

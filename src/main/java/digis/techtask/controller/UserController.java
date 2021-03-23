package digis.techtask.controller;

import digis.techtask.domain.UserDto;
import digis.techtask.entity.UserEntity;
import digis.techtask.service.UserService;
import digis.techtask.utils.ObjectMapperUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final ObjectMapperUtils objectMapperUtils;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = objectMapperUtils.mapAll(userService.getAllUsers(), UserDto.class);
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> addUser(@RequestBody UserDto userDto) {
        Long userId = userService.save(objectMapperUtils.map(userDto, UserEntity.class));
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody UserDto userDto) {
        boolean updateResult = userService.updateUser(objectMapperUtils.map(userDto, UserEntity.class));
        if (updateResult) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("search")
    public ResponseEntity<UserDto> getUserById(
            @RequestParam("id") Long id) {
        UserEntity userEntity = userService.getUserById(id);
        UserDto userDto = objectMapperUtils.map(userEntity, UserDto.class);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}

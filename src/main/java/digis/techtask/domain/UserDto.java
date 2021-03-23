package digis.techtask.domain;

import digis.techtask.enums.GenderEnum;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String login;

    private String fullName;

    private LocalDate birthDate;

    private GenderEnum gender;

}

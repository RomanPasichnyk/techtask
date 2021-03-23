package digis.techtask.entity;

import digis.techtask.enums.GenderEnum;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", length = 40, nullable = false, unique = true)
    private String login;

    @Column(name = "fullName", length = 40, nullable = false)
    private String fullName;

    @Column(name = "birthDate", columnDefinition = "DATE")
    private LocalDate birthDate;

    @Enumerated
    private GenderEnum gender;

}

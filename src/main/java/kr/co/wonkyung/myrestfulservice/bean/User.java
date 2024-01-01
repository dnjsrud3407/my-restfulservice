package kr.co.wonkyung.myrestfulservice.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
@Schema(description = "사용자 도메인 객체")
public class User {

    @Id
    @GeneratedValue
    @Schema(title = "사용자 ID", description = "사용자 ID는 자동 생성됩니다.")
    private int id;

    @Schema(title = "사용자 이름", description = "사용자 이름을 입력합니다.")
    private String name;

    @Schema(title = "사용자 등록일", description = "사용자 등록일을 나타냅니다. 사용자 등록시 설정됩니다.")
    private LocalDate joinDate;

    @Schema(title = "사용자 수정일", description = "사용자 수정일을 나타냅니다. 사용자 수정시 변경됩니다.")
    private LocalDate modifiedDate;

    @Schema(title = "사용자 비밀번호", description = "사용자 비밀번호를 입력합니다.")
    private String password;

    @Schema(title = "사용자 주민번호", description = "사용자 주민번호를 입력합니다.")
    private String ssn;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();


    // == 비즈니스 로직 ==
    public void updateUser(String password) {
        this.password = password;
    }
}

package kr.co.wonkyung.myrestfulservice.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "사용자 생성을 위한 객체")
public class UserCreateForm {

    @NotEmpty(message = "이름을 입력해주세요.")
    @Size(min = 2, message = "이름을 2글자 이상 입력해주세요.")
    @Schema(title = "사용자 이름", description = "사용자 이름을 입력합니다.", example = "김철수")
    private String name;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 20, message = "비밀번호를 8~20 자리로 입력해주세요.")
    @Schema(title = "사용자 비밀번호", description = "사용자 비밀번호를 입력합니다.", example = "11111111")
    private String password;

    @NotEmpty(message = "주민번호를 입력해주세요.")
    @Schema(title = "사용자 주민번호", description = "사용자 주민번호를 입력합니다.", example = "111111-1111111")
    private String ssn;

}

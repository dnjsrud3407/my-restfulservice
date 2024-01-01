package kr.co.wonkyung.myrestfulservice.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "사용자 수정을 위한 객체")
public class UserUpdateForm {

    @Schema(title = "사용자 id", description = "사용자 ID를 파라미터로 설정합니다.", example = "90001")
    private int id;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 20, message = "비밀번호를 8~20 자리로 입력해주세요.")
    @Schema(title = "사용자 비밀번호", description = "사용자 비밀번호를 입력합니다.", example = "11111111")
    private String password;

}

package kr.co.wonkyung.myrestfulservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.wonkyung.myrestfulservice.bean.User;
import kr.co.wonkyung.myrestfulservice.controller.form.UserUpdateForm;
import kr.co.wonkyung.myrestfulservice.dto.UserCntAndListDto;
import kr.co.wonkyung.myrestfulservice.controller.form.UserCreateForm;
import kr.co.wonkyung.myrestfulservice.dto.UserDto;
import kr.co.wonkyung.myrestfulservice.exception.UserNotFoundException;
import kr.co.wonkyung.myrestfulservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "user-controller", description = "User 서비스를 위한 컨트롤러")
public class UserController {

    private final UserService userService;

    /**
     * 모든 사용자, 게시글 조회
     * @return
     */
    @GetMapping
    @Operation(summary = "모든 사용자, 게시글 정보 조회", description = "모든 사용자와 게시글 상세 정보를 조회하는 API<br>사용자 조회 link와 함께 조회됩니다.")
    public ResponseEntity retrieveAllUser(
            @RequestParam(name = "page", required = false, defaultValue = "0") @Parameter(description = "페이지 번호", example = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") @Parameter(description = "페이지 사이즈", example = "10") Integer size) {
        List<UserDto> users = userService.findUsers(page, size);

        for (UserDto userDto : users) {
            String retrieveUserUri = linkTo(methodOn(this.getClass()).retrieveUser(userDto.getUserId())).toString();
            userDto.setLocation(retrieveUserUri);
        }

        UserCntAndListDto result = new UserCntAndListDto();
        result.setCount(users.size());
        result.setUsers(users);

        return ResponseEntity.ok(result);
    }

    /**
     * 사용자 정보와 게시글 조회하기
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "사용자, 게시글 정보 조회", description = "사용자와 게시글 상세 정보를 조회하는 API<br>사용자 전체 조회 link와 함께 조회됩니다.")
    public ResponseEntity retrieveUser(@PathVariable @Parameter(description = "사용자 id", example = "90001") int id) {
        UserDto userDto = userService.findUser(id);

        if(userDto == null) {
            throw new UserNotFoundException("사용자가 존재하지 않습니다. id- " + id);
        }

        String retrieveAllUserUri = linkTo(methodOn(this.getClass()).retrieveAllUser(0, 10)).toString();
        userDto.setLocation(retrieveAllUserUri);

        return ResponseEntity.ok(userDto);
    }

    /**
     * 사용자 생성
     * @param userCreateForm
     * @return
     */
    @PostMapping
    @Operation(summary = "사용자 생성", description = "사용자를 생성하는 API<br>사용자 조회 link가 header에 담겨 응답됩니다.")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserCreateForm userCreateForm) {
        User user = User.builder()
                .name(userCreateForm.getName())
                .joinDate(LocalDate.now())
                .password(userCreateForm.getPassword())
                .ssn(userCreateForm.getSsn())
                .build();
        Integer userId = userService.createUser(user);

        URI location = linkTo(methodOn(this.getClass()).retrieveUser(userId))
                .toUri();

        // responseHeader 에 location 넣는 경우
        return ResponseEntity.created(location).build();
    }

    /**
     * 사용자 수정하기
     * @param userUpdateForm
     * @return
     */
    @PutMapping("/{id}")
    @Operation(summary = "사용자 수정", description = "사용자를 수정하는 API<br>사용자 조회 link가 header에 담겨 응답됩니다.")
    public ResponseEntity modifyUser(@PathVariable @Parameter(description = "사용자 id", example = "90001") int id,
                                     @Valid @RequestBody UserUpdateForm userUpdateForm) {
        User user = User.builder()
                .id(id)
                .modifiedDate(LocalDate.now())
                .password(userUpdateForm.getPassword())
                .build();
        Integer modifiedUserId = userService.modifyUser(user);

        if(modifiedUserId == null) {
            throw new UserNotFoundException("사용자가 존재하지 않습니다. id- " + id);
        }

        URI location = linkTo(methodOn(this.getClass()).retrieveUser(modifiedUserId))
                .toUri();

        // responseHeader 에 location 넣는 경우
        return ResponseEntity.ok().location(location).build();
    }

    /**
     * 사용자와 게시글 삭제하기
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "사용자, 게시글 삭제", description = "사용자와 게시글을 삭제하는 API<br>사용자 전체 조회 link가 header에 담겨 응답됩니다.")
    public ResponseEntity deleteUser(@PathVariable @Parameter(description = "사용자 id", example = "90001") int id) {
        Integer deleteUserId = userService.deleteUser(id);

        if(deleteUserId == null) {
            throw new UserNotFoundException("사용자가 존재하지 않습니다. id- " + id);
        }
        // responseHeader 에 전체보기 URI 전송
        URI location = linkTo(methodOn(this.getClass()).retrieveAllUser(0, 10))
                .toUri();

        return ResponseEntity.noContent().location(location).build();
    }

}

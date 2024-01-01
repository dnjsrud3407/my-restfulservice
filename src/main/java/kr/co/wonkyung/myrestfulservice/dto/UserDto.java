package kr.co.wonkyung.myrestfulservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.co.wonkyung.myrestfulservice.bean.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UserDto {

    @JsonIgnore
    private int userId;
    private String name;
    private LocalDate joinDate;
    private String password;
    private List<PostDto> posts;

    private String location;

    public UserDto(User user) {
        this.userId = user.getId();
        this.name = user.getName();
        this.joinDate = user.getJoinDate();
        this.password = user.getPassword();
        this.posts = user.getPosts().stream()
                .map(post -> new PostDto(post))
                .collect(Collectors.toList());
    }
}

package kr.co.wonkyung.myrestfulservice.dto;

import kr.co.wonkyung.myrestfulservice.bean.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDto {

    private String description;

    public PostDto(Post post) {
        this.description = post.getDescription();
    }
}

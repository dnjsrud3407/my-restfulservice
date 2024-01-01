package kr.co.wonkyung.myrestfulservice.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "게시글 도메인 객체")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(title = "게시글 ID", description = "게시글 ID는 자동 생성됩니다.")
    private int id;

    @Schema(title = "게시글 내용", description = "사용자 내용을 입력합니다.", example = "안녕하세요")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}

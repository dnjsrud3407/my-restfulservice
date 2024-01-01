package kr.co.wonkyung.myrestfulservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "My Restful User Service API 명세서",
                    description = "Spring Boot로 개발하는 RESTful API 명세서 입니다.",
                    version = "v1.0.0")
)
@Configuration
@RequiredArgsConstructor
public class NewSwaggerConfig {

    @Bean
    public GroupedOpenApi customTestOpenAPI() {
        String[] paths = {"/users/**"};

        return GroupedOpenApi.builder()
                .group("User 도메인에 대한 API")
                .pathsToMatch(paths)
                .build();
    }
}

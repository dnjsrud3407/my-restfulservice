package kr.co.wonkyung.myrestfulservice.dto;

import kr.co.wonkyung.myrestfulservice.bean.User;
import lombok.Data;

import java.util.List;

@Data
public class UserCntAndListDto {
    private int count;

    private List<UserDto> users;
}

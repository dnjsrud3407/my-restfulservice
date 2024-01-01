package kr.co.wonkyung.myrestfulservice.service;

import kr.co.wonkyung.myrestfulservice.bean.User;
import kr.co.wonkyung.myrestfulservice.dto.UserDto;
import kr.co.wonkyung.myrestfulservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public List<UserDto> findUsers(int page, int size) {
        List<User> userDtoList = userRepository.findAll(page, size);

        return userDtoList.stream()
                        .map(user -> new UserDto(user))
                        .collect(Collectors.toList());
    }

    public UserDto findUser(int id) {
        User user = userRepository.findById(id);
        UserDto userDto = null;

        if(user != null) {
            userDto = new UserDto(user);
        }

        return userDto;
    }

    @Transactional
    public Integer deleteUser(int id) {
        User user = userRepository.findById(id);

        if(user != null) {
            userRepository.deleteUser(user);
            return id;
        }

        return null;
    }

    @Transactional
    public Integer createUser(User user) {
        userRepository.save(user);

        return user.getId();
    }

    @Transactional
    public Integer modifyUser(User user) {
        User findUser = userRepository.findById(user.getId());

        if(findUser != null) {
            findUser.updateUser(user.getPassword());
            return findUser.getId();
        }

        return null;
    }
}

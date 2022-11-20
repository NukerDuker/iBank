package ru.skillfactory.ibankApi.dto;

import ru.skillfactory.ibankApi.entity.User;

public class User2UserDtoConverter {

    public UserDto convertUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setBalance(user.getBalance());
        return userDto;
    }
}

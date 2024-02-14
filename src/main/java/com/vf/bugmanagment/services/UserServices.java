package com.vf.bugmanagment.services;

import com.vf.bugmanagment.dto.UserDto;
import com.vf.bugmanagment.util.TPage;
import org.springframework.data.domain.Pageable;

public interface UserServices {
    UserDto save(UserDto user);

    UserDto getById(Long id);

    TPage<UserDto> getAllPageable(Pageable pageable);
    UserDto getByUsername(String username);
}

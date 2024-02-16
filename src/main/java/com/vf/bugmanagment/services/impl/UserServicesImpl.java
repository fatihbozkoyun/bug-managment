package com.vf.bugmanagment.services.impl;

import com.vf.bugmanagment.dto.UserDto;
import com.vf.bugmanagment.entity.User;
import com.vf.bugmanagment.repository.UserRepository;
import com.vf.bugmanagment.services.UserServices;
import com.vf.bugmanagment.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServicesImpl implements UserServices {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;


    public UserServicesImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto save(UserDto user) {

        User userObj=modelMapper.map(user, User.class);
        userObj=userRepository.save(userObj);
        user.setId(userObj.getId());
        return user;
    }

    @Override
    public UserDto getById(Long id) {
        User user=userRepository.getOne(id);
        UserDto userDto=modelMapper.map(user,UserDto.class);
        return userDto;
    }

    @Override
    public TPage<UserDto> getAllPageable(Pageable pageable) {
        Page<User> data = userRepository.findAll(pageable);
        TPage<UserDto> respnose = new TPage<UserDto>();
        respnose.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), UserDto[].class)));
        return respnose;
    }

    @Override
    public UserDto getByUsername(String username) {
        User u = userRepository.findByUserName(username);
        return modelMapper.map(u, UserDto.class);
    }

    public List<UserDto> getAll(){
        List<User> data=userRepository.findAll();
        return Arrays.asList(modelMapper.map(data,UserDto[].class));
    }

}

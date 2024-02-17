package com.vf.bugmanagment.services.impl;

import com.vf.bugmanagment.dto.RegistrationRequest;
import com.vf.bugmanagment.dto.UserDto;
import com.vf.bugmanagment.entity.User;
import com.vf.bugmanagment.repository.UserRepository;
import com.vf.bugmanagment.services.UserServices;
import com.vf.bugmanagment.util.TPage;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserServicesImpl implements UserServices {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserServicesImpl(UserRepository userRepository, ModelMapper modelMapper,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
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
    @Transactional
    public Boolean register(RegistrationRequest registrationRequest) {
        try {
            User user = new User();
            user.setEmail(registrationRequest.getEmail());
            user.setNameSurname(registrationRequest.getNameSurname());
            user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
            user.setUserName(registrationRequest.getUsername());
            userRepository.save(user);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error("REGISTRATION=>", e);
            return Boolean.FALSE;
        }
    }

}

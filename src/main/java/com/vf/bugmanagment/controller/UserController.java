package com.vf.bugmanagment.controller;

import com.vf.bugmanagment.dto.UserDto;
import com.vf.bugmanagment.services.impl.UserServicesImpl;
import com.vf.bugmanagment.util.ApiPaths;
import com.vf.bugmanagment.util.TPage;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.UserCtrl.CTRL)
@CrossOrigin
public class UserController {

    private final UserServicesImpl userServicesimpl;

    public UserController(UserServicesImpl userServicesimpl) {
        this.userServicesimpl = userServicesimpl;
    }


    @GetMapping("/pagination")
    public ResponseEntity<TPage<UserDto>> getAllByPagination(Pageable pageable){
        TPage<UserDto> data=userServicesimpl.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAll(){
        List<UserDto> data=userServicesimpl.getAll();
        return ResponseEntity.ok(data);
    }



    @GetMapping("/{id}")
    public  ResponseEntity<UserDto> getById(@PathVariable(value = "id" ,required = true) Long id){
        UserDto user=userServicesimpl.getById(id);
        return ResponseEntity.ok(user);
    }


   @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto user){

        return ResponseEntity.ok(userServicesimpl.save(user));
    }




}

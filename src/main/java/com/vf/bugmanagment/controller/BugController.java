package com.vf.bugmanagment.controller;


import com.vf.bugmanagment.dto.BugDetailDto;
import com.vf.bugmanagment.dto.BugDto;
import com.vf.bugmanagment.entity.BugStatus;
import com.vf.bugmanagment.services.impl.BugServicesImpl;
import com.vf.bugmanagment.util.ApiPaths;
import com.vf.bugmanagment.util.TPage;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.BugCtrl.CTRL)
@CrossOrigin
public class BugController {

    private final BugServicesImpl bugServicesimpl;

    public BugController(BugServicesImpl bugServicesimpl) {
        this.bugServicesimpl = bugServicesimpl;
    }

    @GetMapping("/pagination")
    public ResponseEntity<TPage<BugDto>> getAllByPagination(Pageable pageable){
        TPage<BugDto> data=bugServicesimpl.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BugDto> getById(@PathVariable(value = "id" ,required = true) Long id){
        BugDto bugDto=bugServicesimpl.getById(id);
        return ResponseEntity.ok(bugDto);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<BugDetailDto> getByIdWithDetails(@PathVariable(value = "id",required = true)Long id){
        BugDetailDto bugDetailDto=bugServicesimpl.getByIdWithDetails(id);
        return ResponseEntity.ok(bugDetailDto);
    }

    @PostMapping()
    public ResponseEntity<BugDto> createProject(@RequestBody BugDto bugDto){
        return ResponseEntity.ok(bugServicesimpl.save(bugDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<BugDto> updateProject(@PathVariable(value = "id" ,required = true) Long id, @RequestBody BugDto bugDto){
        return ResponseEntity.ok(bugServicesimpl.update(id, bugDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable (value = "id" ,required = true) Long id ){
        return ResponseEntity.ok(bugServicesimpl.delete(id));
    }

    @GetMapping("/statuses")
    public ResponseEntity<List<BugStatus>> getAll(){
        return ResponseEntity.ok(Arrays.asList(BugStatus.values()));
    }


}

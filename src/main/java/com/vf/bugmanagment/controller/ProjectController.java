package com.vf.bugmanagment.controller;

import com.vf.bugmanagment.dto.ProjectDto;
import com.vf.bugmanagment.services.impl.ProjectServicesImpl;
import com.vf.bugmanagment.util.ApiPaths;
import com.vf.bugmanagment.util.TPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.ProjectCtrl.CTRL)
@Slf4j
@CrossOrigin
public class ProjectController {

    private final ProjectServicesImpl projectServicesimpl;

    public ProjectController(ProjectServicesImpl projectServicesimpl) {
        this.projectServicesimpl = projectServicesimpl;
    }

    @GetMapping("/pagination")
    public ResponseEntity<TPage<ProjectDto>> getAllByPagination(Pageable pageable){
        TPage<ProjectDto> projectAll=projectServicesimpl.getAllPageable(pageable);
        return ResponseEntity.ok(projectAll);
    }

    @GetMapping()
    public ResponseEntity<List<ProjectDto>> getAllProject(){
        List<ProjectDto> data=projectServicesimpl.getAll();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getById(@PathVariable(value = "id" ,required = true) Long id){
        ProjectDto projectDto=projectServicesimpl.getById(id);
        return ResponseEntity.ok(projectDto);
    }
    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto){
        return ResponseEntity.ok(projectServicesimpl.save(projectDto));
    }
    @PutMapping
    public ResponseEntity<ProjectDto> updateProject(@PathVariable(value="id" ,required = true) Long id,@RequestBody ProjectDto projectDto){
        ProjectDto projectDto1=projectServicesimpl.update(id, projectDto);
        return ResponseEntity.ok(projectDto1);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id" ,required = true) Long id){
        return ResponseEntity.ok(projectServicesimpl.delete(id));
    }
}

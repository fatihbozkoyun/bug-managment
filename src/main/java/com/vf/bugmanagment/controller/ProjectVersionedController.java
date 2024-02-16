package com.vf.bugmanagment.controller;

import com.vf.bugmanagment.dto.ProjectDto;
import com.vf.bugmanagment.services.impl.ProjectServicesImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/versioning")
public class ProjectVersionedController {

    private ProjectServicesImpl projectServicesimpl;

    public ProjectVersionedController(ProjectServicesImpl projectServicesimpl) {
        this.projectServicesimpl = projectServicesimpl;
    }

    @GetMapping(value="/{id}" ,params = "version=1")
    public ResponseEntity<ProjectDto> getByIdV1(@PathVariable(value = "id" ,required = true) Long id){
        ProjectDto projectDto=projectServicesimpl.getById(id);
        return ResponseEntity.ok(projectDto);
    }

    @GetMapping(value="/{id}" ,params = "version=2")
    public ResponseEntity<ProjectDto> getByIdV2(@PathVariable(value = "id" ,required = true) Long id){
        ProjectDto projectDto=projectServicesimpl.getById(id);
        return ResponseEntity.ok(projectDto);
    }


}

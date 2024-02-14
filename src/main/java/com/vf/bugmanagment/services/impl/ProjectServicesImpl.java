package com.vf.bugmanagment.services.impl;

import com.vf.bugmanagment.dto.ProjectDto;
import com.vf.bugmanagment.entity.Project;
import com.vf.bugmanagment.repository.ProjectRepository;
import com.vf.bugmanagment.services.ProjectServices;
import com.vf.bugmanagment.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServicesImpl implements ProjectServices {

    private final ProjectRepository projectRepository;

    private final ModelMapper modelMapper;

    public ProjectServicesImpl(ProjectRepository projectRepository, ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProjectDto save(ProjectDto projectDto)  {

        Project project=projectRepository.getByProjectCode(projectDto.getProjectCode());
        if(project !=null)
            throw new IllegalArgumentException("Project is already exists....!");



        return null;
    }

    @Override
    public ProjectDto getById(Long id) {
        return null;
    }

    @Override
    public ProjectDto getByProjectCode(String projectCode) {
        return null;
    }

    @Override
    public List<ProjectDto> getByProjectCodeContains(String projectCode) {
        return null;
    }

    @Override
    public TPage<ProjectDto> getAllPageable(Pageable pageable) {
        return null;
    }

    @Override
    public Boolean delete(ProjectDto projectDto) {
        return null;
    }

    @Override
    public ProjectDto update(Long id, ProjectDto project) {
        return null;
    }
}

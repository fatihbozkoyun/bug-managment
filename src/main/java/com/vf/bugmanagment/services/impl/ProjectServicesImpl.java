package com.vf.bugmanagment.services.impl;

import com.vf.bugmanagment.dto.ProjectDto;
import com.vf.bugmanagment.entity.Project;
import com.vf.bugmanagment.entity.User;
import com.vf.bugmanagment.repository.ProjectRepository;
import com.vf.bugmanagment.repository.UserRepository;
import com.vf.bugmanagment.services.ProjectServices;
import com.vf.bugmanagment.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProjectServicesImpl implements ProjectServices {

    private final ProjectRepository projectRepository;

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    public ProjectServicesImpl(ProjectRepository projectRepository, ModelMapper modelMapper,UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
        this.userRepository=userRepository;
    }

    @Override
    public ProjectDto save(ProjectDto projectDto)  {
         Project projectDb=projectRepository.getByProjectCode(projectDto.getProjectCode());

         if(projectDb !=null)
             throw new IllegalArgumentException("Project Code Already Exist");

         projectDb=modelMapper.map(projectDto,Project.class);
         User user=userRepository.getOne(projectDto.getManagerId());
         projectDb.setManager(user);
         projectDb=projectRepository.save(projectDb);
         projectDto.setId(projectDb.getId());
         return projectDto;

    }

    @Override
    public ProjectDto getById(Long id) {
        Project projectDb=projectRepository.getOne(id);
        ProjectDto projectDto=modelMapper.map(projectDb,ProjectDto.class);
        return projectDto;
    }

    @Override
    public ProjectDto getByProjectCode(String projectCode) {
        Project project=projectRepository.getByProjectCode(projectCode);
        ProjectDto projectDto=modelMapper.map(project,ProjectDto.class);
        return projectDto;
    }

    @Override
    public List<ProjectDto> getByProjectCodeContains(String projectCode) {
        List<Project> projectList=projectRepository.findAll();

        return Arrays.asList(modelMapper.map(projectList,ProjectDto[].class));
    }

    @Override
    public TPage<ProjectDto> getAllPageable(Pageable pageable) {
        Page<Project> data=projectRepository.findAll(pageable);
        TPage<ProjectDto> projectDtoTPage=new TPage<ProjectDto>();
        projectDtoTPage.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), ProjectDto[].class)));

        return projectDtoTPage;
    }

    @Override
    public Boolean delete(ProjectDto projectDto) {
      projectRepository.deleteById(projectDto.getId());
      return Boolean.TRUE;
    }

    @Override
    public ProjectDto update(Long id, ProjectDto project) {
        Project projectDb = projectRepository.getOne(id);
        if (projectDb == null)
            throw new IllegalArgumentException("Project Does Not Exist ID:" + id);

        Project projectCheck = projectRepository.getByProjectCodeAndIdNot(project.getProjectCode(), id);
        if (projectCheck != null)
            throw new IllegalArgumentException("Project Code Already Exist");

        //projectDb.setProjecCode(project.getProjectCode());
        projectDb.setProjectCode(project.getProjectCode());
        projectDb.setProjectName(project.getProjectName());

        projectRepository.save(projectDb);
        return modelMapper.map(projectDb, ProjectDto.class);
    }
}

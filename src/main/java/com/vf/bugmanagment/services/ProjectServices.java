package com.vf.bugmanagment.services;


import com.vf.bugmanagment.dto.ProjectDto;
import com.vf.bugmanagment.util.TPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectServices {

    ProjectDto save(ProjectDto projectDto);

    ProjectDto getById(Long id);

    ProjectDto getByProjectCode(String projectCode);

    List<ProjectDto> getByProjectCodeContains(String projectCode);

    TPage<ProjectDto> getAllPageable(Pageable pageable);

    Boolean delete(ProjectDto projectDto);

    ProjectDto update(Long id, ProjectDto project);
}

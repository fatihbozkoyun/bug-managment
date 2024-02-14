package com.vf.bugmanagment.dto;

import com.vf.bugmanagment.entity.BugStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BugDetailDto {

    private Long id;
    private String description;
    private String details;
    private Date date;
    private BugStatus bugStatus;
    private UserDto assignee;
    private ProjectDto project;

    private List<BugHistoryDto> bugHistories;



}

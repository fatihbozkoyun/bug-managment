package com.vf.bugmanagment.dto;

import com.vf.bugmanagment.entity.BugStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BugUpdateDto {

    private Long id;
    private String description;
    private String details;
    private Date date;
    private BugStatus bugStatus;
    private Long assignee_id;
    private Long project_id;




}

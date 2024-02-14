package com.vf.bugmanagment.dto;

import com.vf.bugmanagment.entity.BugStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BugHistoryDto {

    private Long id;
    private BugDto bug;
    private String description;
    private Date date;
    private BugStatus bugStatus;
    private String details;
    private UserDto assignee;
}

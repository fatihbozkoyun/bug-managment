package com.vf.bugmanagment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "bug")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bug extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description" ,length = 4000)
    private String description;

    @Column(name = "detail" ,length = 4000)
    private String details;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    @Column(name = "bug_status")
    @Enumerated(EnumType.STRING)
    private BugStatus bugStatus;

    @JoinColumn(name = "assignee_user_id")
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    private User assignee;

    @JoinColumn(name = "project_id")
    @ManyToOne(optional = true ,fetch = FetchType.LAZY)
    private Project project;






}

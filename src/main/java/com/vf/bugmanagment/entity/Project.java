package com.vf.bugmanagment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="project")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="project_name")
    private String projectName;

    @Column(name="project_code")
    private String projectCode;


    @JoinColumn(name = "manager_user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User manager;
}

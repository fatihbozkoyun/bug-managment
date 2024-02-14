package com.vf.bugmanagment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "bug_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BugHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "bug_id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Bug bug;

    @Column(name = "description", length = 1000)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    @Column(name = "bug_status")
    @Enumerated(EnumType.STRING)
    private BugStatus bugStatus;

    @Column(name = "details", length = 4000)
    private String details;

    @JoinColumn(name = "assignee_user_id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private User assignee;


}

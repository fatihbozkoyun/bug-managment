package com.vf.bugmanagment.repository;

import com.vf.bugmanagment.entity.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugRepository extends JpaRepository<Bug,Long> {
}

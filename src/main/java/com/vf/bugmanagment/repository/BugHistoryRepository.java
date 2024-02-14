package com.vf.bugmanagment.repository;

import com.vf.bugmanagment.entity.BugHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BugHistoryRepository extends JpaRepository<BugHistory,Long> {

    List<BugHistory> getByBugIdOrderById(Long id);


}

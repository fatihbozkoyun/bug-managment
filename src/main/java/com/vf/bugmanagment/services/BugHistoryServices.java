package com.vf.bugmanagment.services;

import com.vf.bugmanagment.dto.BugHistoryDto;
import com.vf.bugmanagment.entity.Bug;
import com.vf.bugmanagment.util.TPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BugHistoryServices {

    BugHistoryDto save(BugHistoryDto bugHistoryDto);

    BugHistoryDto getById(Long BugHistoryId);

    List<BugHistoryDto> getByBugHistoryId(Long BugId);
    TPage<BugHistoryDto> getAllPageable(Pageable pageable);

    Boolean delete(BugHistoryDto bugHistoryDto);

    void addHistory(Long id, Bug bug);

}

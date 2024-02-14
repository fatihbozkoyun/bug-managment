package com.vf.bugmanagment.services;

import com.vf.bugmanagment.dto.BugDto;
import com.vf.bugmanagment.util.TPage;
import org.springframework.data.domain.Pageable;

public interface BugServices {
    BugDto save(BugDto bugDto);
    BugDto getById(Long id);

    TPage<BugDto> getAllPageable(Pageable pageable);

    Boolean delete(Long BugId);

    BugDto update(Long id,BugDto bugDto);
}

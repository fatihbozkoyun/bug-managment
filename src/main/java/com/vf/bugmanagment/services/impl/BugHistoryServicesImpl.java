package com.vf.bugmanagment.services.impl;

import com.vf.bugmanagment.dto.BugHistoryDto;
import com.vf.bugmanagment.entity.Bug;
import com.vf.bugmanagment.entity.BugHistory;
import com.vf.bugmanagment.repository.BugHistoryRepository;
import com.vf.bugmanagment.services.BugHistoryServices;
import com.vf.bugmanagment.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BugHistoryServicesImpl implements BugHistoryServices {

   private final BugHistoryRepository bugHistoryRepository;
   private final ModelMapper modelMapper;

    public BugHistoryServicesImpl(BugHistoryRepository bugHistoryRepository, ModelMapper modelMapper) {
        this.bugHistoryRepository = bugHistoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BugHistoryDto save(BugHistoryDto bugHistoryDto) {
        BugHistory bugHistory=new BugHistory();
        bugHistory=bugHistoryRepository.save(bugHistory);
        bugHistoryDto.setId(bugHistory.getId());

        return bugHistoryDto;
    }

    @Override
    public BugHistoryDto getById(Long BugHistoryId) {
        BugHistory bugHistory=new BugHistory();
        bugHistory =bugHistoryRepository.getOne(BugHistoryId);
        BugHistoryDto dtos=modelMapper.map(bugHistory,BugHistoryDto.class);

        return dtos;
    }

    @Override
    public List<BugHistoryDto> getByBugHistoryId(Long BugId) {

        return Arrays.asList(modelMapper.map(bugHistoryRepository.getByBugIdOrderById(BugId), BugHistoryDto[].class));


    }

    @Override
    public TPage<BugHistoryDto> getAllPageable(Pageable pageable) {
        Page<BugHistory> data=bugHistoryRepository.findAll(pageable);
        TPage<BugHistoryDto> response=new TPage<>();
        response.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), BugHistoryDto[].class)));

        return response;
    }

    @Override
    public Boolean delete(BugHistoryDto bugHistoryDto) {
        bugHistoryRepository.deleteById(bugHistoryDto.getId());
        return Boolean.TRUE;
    }

    @Override
    public void addHistory(Long id, Bug bug) {
       BugHistory bugHistory=new BugHistory();
       bugHistory.setBug(bug);
       bugHistory.setBugStatus(bug.getBugStatus());
       bugHistory.setDetails(bug.getDetails());
       bugHistory.setDate(bug.getDate());
       bugHistory.setAssignee(bug.getAssignee());
       bugHistory.setDescription(bug.getDescription());
       bugHistoryRepository.save(bugHistory);
    }

}

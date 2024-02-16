package com.vf.bugmanagment.services.impl;

import com.vf.bugmanagment.dto.BugDetailDto;
import com.vf.bugmanagment.dto.BugDto;
import com.vf.bugmanagment.dto.BugHistoryDto;
import com.vf.bugmanagment.dto.BugUpdateDto;
import com.vf.bugmanagment.entity.Bug;
import com.vf.bugmanagment.entity.BugStatus;
import com.vf.bugmanagment.entity.User;
import com.vf.bugmanagment.repository.BugHistoryRepository;
import com.vf.bugmanagment.repository.BugRepository;
import com.vf.bugmanagment.repository.ProjectRepository;
import com.vf.bugmanagment.repository.UserRepository;
import com.vf.bugmanagment.services.BugHistoryServices;
import com.vf.bugmanagment.services.BugServices;
import com.vf.bugmanagment.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class BugServicesImpl implements BugServices {

    private final ModelMapper modelMapper;

    private final BugRepository bugRepository;

    private final BugHistoryRepository bugHistoryRepository;

    private final UserRepository userRepository;

    private final ProjectRepository projectRepository;

    private final BugHistoryServices bugHistoryServices;


    public BugServicesImpl(BugHistoryServices bugHistoryServices,ModelMapper modelMapper,ProjectRepository projectRepository, BugRepository bugRepository, BugHistoryRepository bugHistoryRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.bugRepository = bugRepository;
        this.bugHistoryRepository = bugHistoryRepository;
        this.userRepository = userRepository;
        this.projectRepository=projectRepository;
        this.bugHistoryServices=bugHistoryServices;
    }


    @Override
    public BugDto save(BugDto bugDto) {
        bugDto.setDate(new Date());
        bugDto.setBugStatus(BugStatus.OPEN);
        Bug bugEntity=modelMapper.map(bugDto,Bug.class);

        bugEntity.setProject(projectRepository.getOne(bugDto.getProjectId()));

        bugEntity=bugRepository.save(bugEntity);
        bugDto.setId(bugEntity.getId());
        return bugDto;
    }

    @Transactional
public BugDetailDto update(Long id, BugUpdateDto bug){
        Bug bugDb=bugRepository.getOne(id);
        User user=userRepository.getOne(bug.getAssignee_id());
        bugHistoryServices.addHistory(id,bugDb);
         bugDb.setBugStatus(bug.getBugStatus());
         bugDb.setDate(bug.getDate());
         bugDb.setDescription(bug.getDescription());
         bugDb.setDetails(bug.getDetails());
         bugDb.setAssignee(user);
         bugDb.setProject(projectRepository.getOne(bug.getProject_id()));
         bugRepository.save(bugDb);
    return getByIdWithDetails(id);

}

    public BugDetailDto getByIdWithDetails(Long id) {
        Bug bug = bugRepository.getOne(id);
        BugDetailDto detailDto = modelMapper.map(bug, BugDetailDto.class);
        List<BugHistoryDto> issueHistoryDtos = bugHistoryServices.getByBugHistoryId(bug.getId());
        detailDto.setBugHistories(issueHistoryDtos);
        return detailDto;
    }


    @Override
    public BugDto getById(Long id) {
        Bug bug=bugRepository.getOne(id);
        BugDto bugDto=modelMapper.map(bug,BugDto.class);
        return bugDto;
    }

    @Override
    public TPage<BugDto> getAllPageable(Pageable pageable) {
        Page<Bug> data=bugRepository.findAll(pageable);
        TPage<BugDto> response=new TPage<>();
        response.setStat(data , Arrays.asList(modelMapper.map(data.getContent(),BugDto[].class)));

        return response;
    }

    @Override
    public Boolean delete(Long BugId) {
        bugRepository.deleteById(BugId);
        return true;
    }

    @Override
    public BugDto update(Long id, BugDto bugDto) {
        return null;
    }


}



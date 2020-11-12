package com.csapatsportok.application.service;

import com.csapatsportok.application.info.MatchInfo;
import com.csapatsportok.application.repository.MatchInfoRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class MatchInfoService {

    private MatchInfoRepository matchInfoRepo;

    @Autowired
    public void setMatchInfoRepo(MatchInfoRepository matchInfoRepo) {
        this.matchInfoRepo = matchInfoRepo;
    }

    public List<MatchInfo> getMatchinfoByLeagueName(String name) {
        return matchInfoRepo.getMatchinfoByLeagueName(name);

    }
}

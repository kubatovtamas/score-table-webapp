package com.csapatsportok.application.service;

import com.csapatsportok.application.info.ScorerInfo;
import com.csapatsportok.application.repository.ScorerInfoRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ScorerInfoService {

    private ScorerInfoRepository scorerInfoRepo;

    @Autowired
    public void setScorerInfoRepo(ScorerInfoRepository scorerInfoRepo) {
        this.scorerInfoRepo = scorerInfoRepo;
    }

    public List<ScorerInfo> getScorerInfoByLeagueName(String name) {
        return scorerInfoRepo.getScorerInfoByLeagueName(name);
    }
}

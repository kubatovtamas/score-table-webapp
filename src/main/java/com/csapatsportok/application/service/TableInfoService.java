package com.csapatsportok.application.service;

import com.csapatsportok.application.info.TableInfo;
import com.csapatsportok.application.repository.TableInfoRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class TableInfoService {

    private TableInfoRepository tableInfoRepo;

    @Autowired
    public void setTableInfoRepo(TableInfoRepository tableInfoRepo) {
        this.tableInfoRepo = tableInfoRepo;
    }

    public List<TableInfo> getTableInfoByCountryName(String name) {
        return tableInfoRepo.getTableInfoByCountryName(name);
    }
}

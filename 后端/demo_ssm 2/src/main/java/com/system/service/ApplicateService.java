package com.system.service;

import com.system.domain.ApplicateRecord;

import java.util.List;

public interface ApplicateService {
    String postapplicateaccount(ApplicateRecord applicateRecord);

    List<ApplicateRecord> getList();

    String rebackapplicate(ApplicateRecord applicateRecord);
}

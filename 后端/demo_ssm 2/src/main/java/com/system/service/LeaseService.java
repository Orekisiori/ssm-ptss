package com.system.service;

import com.system.domain.TFlowInfo;
import com.system.domain.TLease;

import java.util.List;

public interface LeaseService {
    void addLease(TLease tLease);

    List<TLease> getLeaseList();

    void delectLeaseBylId(int lId);

    List<TLease> selectKey(String query);

    List<TFlowInfo> selectByfIds();

    void uptadeRow(TLease tLease);
}

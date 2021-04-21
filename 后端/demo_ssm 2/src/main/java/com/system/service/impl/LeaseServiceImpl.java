package com.system.service.impl;

import com.system.domain.TFlowInfo;
import com.system.domain.TLease;
import com.system.domain.TReturn;
import com.system.domain.TWallet;
import com.system.mapper.TFlowInfoMapper;
import com.system.mapper.TLeaseMapper;
import com.system.mapper.TReturnMapper;
import com.system.mapper.TWalletMapper;
import com.system.service.LeaseService;
import com.system.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LeaseServiceImpl implements LeaseService {

    @Autowired
    private TLeaseMapper  tLeaseMapper;

    @Autowired
    private TFlowInfoMapper tFlowInfoMapper;

    @Autowired
    private TReturnMapper tReturnMapper;

    @Autowired
    private TWalletMapper tWalletMapper;

    @Override
    public void addLease(TLease tLease) {
        //租凭的时候修改库存
        TFlowInfo tFlowInfo=tFlowInfoMapper.selectByPrimaryKey(tLease.getfId());
        tFlowInfo.setfStock(tFlowInfo.getfStock()-tLease.getfNumber());
        tFlowInfoMapper.updateStock(tFlowInfo);


        //查询钱包余额
        TWallet WalletList = tWalletMapper.selectByUserId(tLease.getUserId());
        //租凭的时候扣钱包的余额
        TWallet tWallet = new TWallet();
        tWallet.setUserId(tLease.getUserId());
        //INT转换LONG
        tWallet.setBalance(WalletList.getBalance()-Long.valueOf(tLease.getlRent()));
        tWalletMapper.updateByPrimaryKeySelective(tWallet);

        //插入租凭表
        tLease.setlId(MyUtils.getCurrentTimeForId());
        tLeaseMapper.insert(tLease);
    }

    @Override
    public List<TLease> getLeaseList() {
        return tLeaseMapper.getLeaseList();
    }

    @Override
    public void delectLeaseBylId(int lId) {
        //插入归还表
        TReturn tReturn = new TReturn();
        TLease lease=tLeaseMapper.selectByPrimaryKey(lId);
        tReturn.setfId(lease.getfId());
        tReturn.setfNumber(lease.getfNumber());
        tReturn.setrId(MyUtils.getCurrentTimeForId());
        tReturn.setCrateTime(new Date());
        tReturn.setUpdateTime(new Date());
        tReturn.setUserId(lease.getUserId());
        tReturnMapper.insert(tReturn);


        tLeaseMapper.deleteByPrimaryKey(lId);
    }

    @Override
    public List<TLease> selectKey(String query) {
        return tLeaseMapper.selectKey(query);
    }

    /**
     * 根据fids查询商品信息集合
     * @return
     */
    @Override
    public List<TFlowInfo> selectByfIds() {
        List<TLease> leaseList = tLeaseMapper.getLeaseList();
        List<Integer> fIds = new ArrayList<>();
        leaseList.forEach(lease ->{
            fIds.add(lease.getfId());
        });
        return tFlowInfoMapper.selectByfIds(fIds);
    }

    @Override
    public void uptadeRow(TLease tLease) {
        TWallet tWallet = new TWallet();
        //查询钱包余额
        TWallet WalletList = tWalletMapper.selectByUserId(tLease.getUserId());
        //扣钱
        Long money=WalletList.getBalance()-tLease.getlRent();
        tWallet.setBalance(money);
        tWallet.setUserId(tLease.getUserId());
        tWalletMapper.updateByPrimaryKeySelective(tWallet);
    }
}

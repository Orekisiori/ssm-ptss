package com.system.service.impl;

import com.system.domain.TWallet;
import com.system.mapper.TWalletMapper;
import com.system.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private TWalletMapper tWalletMapper;



    @Override
    public TWallet getTwallet(int userId) {
        return tWalletMapper.selectByUserId(userId);
    }

    @Override
    public void updateByPrimaryKeySelective(TWallet tWallet) {
        tWalletMapper.updateByPrimaryKeySelective(tWallet);
    }


}

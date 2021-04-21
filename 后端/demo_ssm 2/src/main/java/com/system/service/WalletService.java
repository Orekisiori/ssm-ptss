package com.system.service;

import com.system.domain.TWallet;

public interface WalletService {
    TWallet getTwallet(int userId);

    void updateByPrimaryKeySelective(TWallet tWallet);
}

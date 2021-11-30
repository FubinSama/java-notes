package com.wfb.learn.framework.ioc

import com.wfb.learn.framework.ioc.AccountDao
import com.wfb.learn.framework.ioc.ItemDao
import com.wfb.learn.framework.ioc.PetStoreService

beans {
    accountDao(AccountDao) {}
    itemDao(ItemDao) {}
    petStore(PetStoreService) {
        accountDao = accountDao
        itemDao = itemDao
    }
}
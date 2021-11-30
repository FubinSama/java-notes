package com.wfb.learn.framework.ioc;

/**
 * <p>
 *  PetStoreService
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-09-29 18:29:01
 */
public class PetStoreService {
    private AccountDao accountDao;
    private ItemDao itemDao;

    // 实例工厂构造方法
    static class PetStoreServiceFactory {
        public PetStoreService createPetStoreService(AccountDao accountDao, ItemDao itemDao) {
            return PetStoreService.createPetStoreService(accountDao, itemDao);
        }
    }

    // 静态工厂构造方法
    public static PetStoreService createPetStoreService(AccountDao accountDao, ItemDao itemDao) {
        return new PetStoreService(accountDao, itemDao);
    }

    // 无参构造器
    public PetStoreService() {}

    // 全参数构造器
    public PetStoreService(AccountDao accountDao, ItemDao itemDao) {
        this.accountDao = accountDao;
        this.itemDao = itemDao;
    }

    // setter器
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    // getter器
    public ItemDao getItemDao() {
        return itemDao;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    @Override
    public String toString() {
        return "PetStoreService{" +
                "accountDao=" + accountDao +
                ", itemDao=" + itemDao +
                '}';
    }
}

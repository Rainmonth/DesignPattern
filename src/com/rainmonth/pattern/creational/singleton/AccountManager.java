package com.rainmonth.pattern.creational.singleton;

/**
 * 单例模式代码实例
 * Created by RandyZhang on 2017/3/20.
 */
public class AccountManager {
    private static AccountManager accountManager = null;
    /**
     * 饿汉式
     */
//    private static AccountManager accountManager = new AccountManager();

    // 账户余额
    private double money = 1000D;

    /**
     * 懒汉式（非线程安全）
     *
     * @return AccountManager
     */
    public static AccountManager getInstance1() {
        if (null == accountManager) {
            accountManager = new AccountManager();
        }
        return accountManager;
    }

    /**
     * 懒汉式（线程安全）
     *
     * @return AccountManager
     */
    public static synchronized AccountManager getInstance2() {
        if (null == accountManager) {
            accountManager = new AccountManager();
        }
        return accountManager;
    }

    /**
     * 懒汉式 双重检查锁 （线程安全）
     *
     * @return AccountManager
     */
    public static AccountManager getInstance3() {
        if (null == accountManager) {
            synchronized (AccountManager.class) {
                if (null == accountManager) {
                    accountManager = new AccountManager();
                }
            }
        }
        return accountManager;
    }

    /**
     * 懒汉式（变种）双重检查锁（变种） （线程安全）
     *
     * @return AccountManager
     */
    public static AccountManager getInstance4() {
        if (null == accountManager) {
            syncInit();
        }
        return accountManager;
    }

    private static synchronized void syncInit() {
        if (null == accountManager) {
            accountManager = new AccountManager();
        }
    }

    /**
     * 静态内部类 线程安全
     *
     * @return AccountManager
     */
    public static AccountManager getInstance5() {
        return AccountManagerHolder.instance;
    }

    /**
     * 单元素枚举 线程安全，支持序列化
     *
     * @return AccountManager
     */
    public static AccountManager getInstance6() {
        return AccountManagerEnum.INSTANCE.getInstance();
    }

    private AccountManager() {

    }

    /**
     * 存款
     *
     * @param depositMoney 存款金额
     */
    public void deposit(double depositMoney) {
        money += depositMoney;
        System.out.println("deposit " + depositMoney + ", Account balance is " + money);
    }

    /**
     * 提现
     *
     * @param withdrawMoney 提现金额
     */
    public void withdraw(double withdrawMoney) {
        money -= withdrawMoney;
        System.out.println("withdraw " + withdrawMoney + ", Account balance is " + money);
    }

    /**
     * 静态内部类实现单例模式
     */
    private static class AccountManagerHolder {
        private static final AccountManager instance = new AccountManager();
    }

    /**
     * 单元素枚举实现单例模式
     */
    public enum AccountManagerEnum {
        INSTANCE;
        private AccountManager instance;

        AccountManagerEnum() {
            instance = new AccountManager();
        }

        public AccountManager getInstance() {
            return instance;
        }
    }
}

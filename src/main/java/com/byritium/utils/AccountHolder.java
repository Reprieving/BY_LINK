package com.byritium.utils;

public class AccountHolder {
    private AccountHolder(){
    }

    private static final ThreadLocal<Long> local = new ThreadLocal<>();

    public static void clear() {
        local.remove();
    }

    public static void set(Long accountId) {
        local.set(accountId);
    }

    public static Long get() {
        return local.get();
    }
}

package com.thoughtworks.main;

public class Account {
    private final int accountId;
    private final int overdraftLimit;
    private int overdraftRemaining;
    private final Currency currency;
    private int balance;

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public int getOverdraftRemaining() {
        return overdraftRemaining;
    }

    public Account(int accountId, int balance, Currency currency, int overdraftLimit) {
        this.accountId = accountId;
        this.balance = balance;
        this.currency = currency;
        this.overdraftLimit = overdraftLimit;
        this.overdraftRemaining = overdraftLimit;
    }
}
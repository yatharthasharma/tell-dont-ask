package com.thoughtworks.main;

import java.util.function.Function;

public class Account {
    private final int accountId;
    private final int overdraftLimit;
    private int overdraftRemaining;
    private final Currency currency;
    private int balance;

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

    private Integer operation(Function<Integer, Integer> operation, Currency depositCurrency, Integer amount) {
        if (currency.equals(depositCurrency)) {
            return operation.apply(amount);
        }
        throw new InvalidCurrencyException("This account does not support international transactions!");
    }

    public int debit(int amount, Currency depositCurrency) {
        return operation(this::debit, depositCurrency, amount);
    }

    private int debit(int amount) {
        if (balance < amount) {
            throw new InsufficientBalanceException("The account has insufficient funds.");
        }
        balance -= amount;
        return balance;
    }

    public int credit(int amount, Currency depositCurrency) {
        return operation(this::credit, depositCurrency, amount);
    }

    private int credit(int amount) {
        balance += amount;
        return balance;
    }
}
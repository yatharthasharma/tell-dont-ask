package com.thoughtworks.main;

public class Account {
    private final int accountId;
    private final int overdraftLimit;
    private int overdraftRemaining;
    private final Currency currency;
    private int balance;

    public Account(int accountId, int balance, Currency currency, int overdraftLimit) {
        if (balance < 0) {
            throw new InsufficientBalanceException("Unable to open an account with a negative balance!");
        }
        if (overdraftLimit < 0) {
            throw new InvalidOverdraftLimitException("Unable to open an account with a negative overdraft limit!");
        }
        this.accountId = accountId;
        this.balance = balance;
        this.currency = currency;
        this.overdraftLimit = overdraftLimit;
        this.overdraftRemaining = overdraftLimit;
    }

    public void currencyChecks(Currency transactionCurrency) {
        if (currency != transactionCurrency) {
            throw new InvalidCurrencyException("This account does not support international transactions!");
        }
    }

    public int debit(int debitAmount, Currency debitCurrency) {
        currencyChecks(debitCurrency);
        if (balance < debitAmount) {
            throw new InsufficientBalanceException("The account has insufficient funds.");
        }
        balance -= debitAmount;
        return balance;
    }

    public int credit(int creditAmount, Currency depositCurrency) {
        currencyChecks(depositCurrency);
        balance += creditAmount;
        return balance;
    }
}
package com.thoughtworks.main;

public class Account {
    private final int accountId;
    private final int overdraftLimit;
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

    public int getOverdraftLimit() {
        return overdraftLimit;
    }

    public Account(int accountId, int balance, Currency currency, int overdraftLimit) {
        if (balance < 0) {
            throw new InsufficientBalanceException("Unable to open an account with a negative balance!");
        }
        this.accountId = accountId;
        this.balance = balance;
        this.currency = currency;
        this.overdraftLimit = overdraftLimit;
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
package com.thoughtworks.main;

public class PaymentService {
    AccountRepository accountRepository;
    public PaymentService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public int debit(int customerId, int amount, Currency depositCurrency) throws InvalidCurrencyException, InsufficientBalanceException {
        Account account = accountRepository.getAccountByCustomer(customerId);
        if (account.getCurrency().equals(depositCurrency)) {
            if (account.getBalance() < amount) {
                throw new InsufficientBalanceException("The account has insufficient funds.");
            }
            account.setBalance(account.getBalance() - amount);
            return account.getBalance();
        }
        throw new InvalidCurrencyException("This account does not support international transactions!");
    }

    public int credit(int customerId, int amount, Currency depositCurrency) throws InvalidCurrencyException {
        Account account = accountRepository.getAccountByCustomer(customerId);
        if (account.getCurrency().equals(depositCurrency)) {
            account.setBalance(account.getBalance() + amount);
            return account.getBalance();
        }
        throw new InvalidCurrencyException("Invalid currency deposited");
    }
}

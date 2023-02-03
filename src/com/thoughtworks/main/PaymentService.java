package com.thoughtworks.main;

public class PaymentService {
    AccountRepository accountRepository;
    public PaymentService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public int debit(int customerId, int amount, Currency depositCurrency) throws InvalidCurrencyException, InsufficientBalanceException {
        Account account = accountRepository.getAccountByCustomer(customerId);
        return account.debit(amount, depositCurrency);
    }

    public int credit(int customerId, int amount, Currency depositCurrency) throws InvalidCurrencyException {
        Account account = accountRepository.getAccountByCustomer(customerId);
        return account.credit(amount, depositCurrency);
    }

}

package com.thoughtworks.test;

import com.thoughtworks.main.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PaymentServiceTest {
    AccountRepository accountRepository = mock(AccountRepository.class);
    PaymentService paymentService = new PaymentService(accountRepository);
    static final int CUSTOMER_ID = 2;

    @Test
    void debitAccountWithSufficientAmount() {
        when(accountRepository.getAccountByCustomer(CUSTOMER_ID)).thenReturn(
                new Account(2, 1000, Currency.GBP, 0)
        );
        assertEquals(paymentService.debit(2, 100, Currency.GBP), 900);
    }

    @Test
    void debitAccountInternationallyThrowsAnException() {
        when(accountRepository.getAccountByCustomer(CUSTOMER_ID)).thenReturn(
                new Account(2, 1000, Currency.GBP, 0)
        );
        assertThrows(InvalidCurrencyException.class, () ->
                paymentService.debit(2, 900, Currency.INR));
    }

    @Test
    void debitAccountWithoutSufficientBalanceThrowsAnException() {
        when(accountRepository.getAccountByCustomer(CUSTOMER_ID)).thenReturn(
                new Account(2, 100, Currency.GBP, 0)
        );
        assertThrows(InsufficientBalanceException.class, () ->
                paymentService.debit(2, 900, Currency.GBP));
    }

    @Test
    void creditAccountWithValidCurrency() {
        when(accountRepository.getAccountByCustomer(CUSTOMER_ID)).thenReturn(
                new Account(2, 1000, Currency.GBP, 0)
        );
        assertEquals(paymentService.credit(2, 100, Currency.GBP), 1100);
    }

    @Test
    void creditAccountWithInvalidCurrencyThrowsException() {
        when(accountRepository.getAccountByCustomer(CUSTOMER_ID)).thenReturn(
                new Account(2, 1000, Currency.GBP, 0)
        );
        assertThrows(InvalidCurrencyException.class, () ->
                paymentService.credit(2, 900, Currency.INR));
    }
}
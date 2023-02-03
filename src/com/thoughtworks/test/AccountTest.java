package com.thoughtworks.test;

import com.thoughtworks.main.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    private static final int ACCOUNT_ID = 1;

    @Test
    void shouldThrowAnInvalidOverdraftLimitExceptionIfAccountCreatedWithNegativeOverdraftLimit() {
        assertThrows(InvalidOverdraftLimitException.class, () -> new Account(
                ACCOUNT_ID, 1000, Currency.GBP, -10
        ));
    }

    @Test
    void shouldThrowAnInsufficientBalanceExceptionIfAccountCreatedWithNegativeBalance() {
        assertThrows(InsufficientBalanceException.class, () -> new Account(
                ACCOUNT_ID, -1000, Currency.GBP, 0
        ));
    }

    @Test
    void shouldThrowAnInvalidCurrencyExceptionWhenTransactingInADifferentCurrency() {
        Account account = new Account(
                ACCOUNT_ID, 1000, Currency.GBP, 0
        );
        assertThrows(InvalidCurrencyException.class, () -> account.currencyChecks(Currency.INR));
    }

    @Test
    void shouldNotThrowAnInvalidCurrencyExceptionWhenTransactingInTheSameCurrency() {
        Account account = new Account(
                ACCOUNT_ID, 1000, Currency.GBP, 0
        );
        assertDoesNotThrow(() -> account.currencyChecks(Currency.GBP));
    }

    @Test
    void creditAccountWithValidCurrency() {
        Account account = new Account(2, 1000, Currency.GBP, 0);
        assertEquals(account.credit(100, Currency.GBP), 1100);
    }

    @Test
    void debitAccountWithSufficientBalance() {
        Account account = new Account(2, 1000, Currency.GBP, 0);
        assertEquals(account.debit(100, Currency.GBP), 900);
    }

    @Test
    void debitAccountWithInsufficientBalance() {
        Account account = new Account(2, 1000, Currency.GBP, 0);
        assertThrows(InsufficientBalanceException.class, () -> account.debit(1100, Currency.GBP));
    }
}

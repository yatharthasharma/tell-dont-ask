# Tell don't ask

## Prerequisites

Java 11

## Tasks

    * Go through the payment orchestrator (PaymentService)
    * Identify accessors from Account that are used to get the state of the object
    * Identify accessors from Account that are used to manipulate its state from outside Account.java class
    * Refactor PaymentService and Account so that the 'state of Account' is managed from within Account itself
    * Bonus task 1 (if you have spare time) - factor in the overdraft limit when you debit/credit the account 
      * Credits should first reduce the overdraft value before increasing the account balance
      * Debits should be taken from the balance, if insufficient balance then from the overdraft limit
    * Bonus task 2 - start accepting different currencies for debit/credit (by using an arbitrary exchange rate) rather than throwing an InvalidCurrencyException
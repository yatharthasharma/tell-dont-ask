package com.thoughtworks.main;

public interface AccountRepository {
    /**
     * Fetch the customer's account from a data store
     *
     * @param customerId use the customer's identification to retrieve details about their account
     * @return Account linked to the customer
     */
    Account getAccountByCustomer(int customerId);
}

package com.drugManagement.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer in the Drug Management System.
 * This class contains information about a customer, including their ID, name, contact information,
 * and purchase history.
 */
public class Customer {
    private String customerID;
    private String name;
    private String contactInfo;
    private List<Purchase> purchaseHistory;

    /**
     * Constructs a Customer with the specified ID, name, and contact information.
     *
     * @param customerID The unique identifier for the customer
     * @param name The name of the customer
     * @param contactInfo The contact information of the customer
     */
    public Customer(String customerID, String name, String contactInfo) {
        this.customerID = customerID;
        this.name = name;
        this.contactInfo = contactInfo;
        this.purchaseHistory = new ArrayList<>();
    }

    /**
     * Gets the customer ID.
     *
     * @return The customer ID
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * Sets the customer ID.
     *
     * @param customerID The new customer ID
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    /**
     * Gets the name of the customer.
     *
     * @return The name of the customer
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the customer.
     *
     * @param name The new name of the customer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the contact information of the customer.
     *
     * @return The contact information of the customer
     */
    public String getContactInfo() {
        return contactInfo;
    }

    /**
     * Sets the contact information of the customer.
     *
     * @param contactInfo The new contact information of the customer
     */
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    /**
     * Gets the purchase history of the customer.
     *
     * @return The list of purchases made by the customer
     */
    public List<Purchase> getPurchaseHistory() {
        return new ArrayList<>(purchaseHistory);
    }

    /**
     * Adds a purchase to the customer's purchase history.
     *
     * @param purchase The purchase to be added
     */
    public void addPurchase(Purchase purchase) {
        purchaseHistory.add(purchase);
    }

    /**
     * Removes a purchase from the customer's purchase history.
     *
     * @param purchase The purchase to be removed
     */
    public void removePurchase(Purchase purchase) {
        purchaseHistory.remove(purchase);
    }

    /**
     * Calculates the total amount spent by the customer.
     *
     * @return The total amount spent
     */
    public double getTotalAmountSpent() {
        return purchaseHistory.stream()
                .mapToDouble(Purchase::getTotalCost)
                .sum();
    }

    /**
     * Returns a string representation of the Customer.
     *
     * @return A string representation of the Customer
     */
    @Override
    public String toString() {
        return "Customer{" +
                "customerID='" + customerID + '\'' +
                ", name='" + name + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", purchaseCount=" + purchaseHistory.size() +
                ", totalAmountSpent=" + getTotalAmountSpent() +
                '}';
    }

    /**
     * Checks if this Customer is equal to another object.
     *
     * @param obj The object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Customer customer = (Customer) obj;
        return customerID.equals(customer.customerID);
    }

    /**
     * Generates a hash code for the Customer.
     *
     * @return The hash code
     */
    @Override
    public int hashCode() {
        return customerID.hashCode();
    }
}
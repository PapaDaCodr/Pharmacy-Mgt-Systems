package com.drugManagement.data;

import java.util.Date;

/**
 * Represents a purchase transaction in the Drug Management System.
 * This class contains information about a single purchase, including the drug purchased,
 * the customer who made the purchase, the quantity, and the date of purchase.
 */
public class Purchase {
    private String purchaseID;
    private Drug drug;
    private Customer customer;
    private int quantity;
    private Date purchaseDate;

    /**
     * Constructs a Purchase with the specified details.
     *
     * @param purchaseID The unique identifier for the purchase
     * @param drug The drug that was purchased
     * @param customer The customer who made the purchase
     * @param quantity The quantity of the drug purchased
     * @param purchaseDate The date when the purchase was made
     */
    public Purchase(String purchaseID, Drug drug, Customer customer, int quantity, Date purchaseDate) {
        this.purchaseID = purchaseID;
        this.drug = drug;
        this.customer = customer;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
    }

    /**
     * Gets the purchase ID.
     *
     * @return The purchase ID
     */
    public String getPurchaseID() {
        return purchaseID;
    }

    /**
     * Sets the purchase ID.
     *
     * @param purchaseID The new purchase ID
     */
    public void setPurchaseID(String purchaseID) {
        this.purchaseID = purchaseID;
    }

    /**
     * Gets the drug that was purchased.
     *
     * @return The purchased drug
     */
    public Drug getDrug() {
        return drug;
    }

    /**
     * Sets the drug that was purchased.
     *
     * @param drug The new drug
     */
    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    /**
     * Gets the customer who made the purchase.
     *
     * @return The customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the customer who made the purchase.
     *
     * @param customer The new customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Gets the quantity of the drug purchased.
     *
     * @return The quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the drug purchased.
     *
     * @param quantity The new quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the date of the purchase.
     *
     * @return The purchase date
     */
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Sets the date of the purchase.
     *
     * @param purchaseDate The new purchase date
     */
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * Calculates the total cost of this purchase.
     *
     * @return The total cost
     */
    public double getTotalCost() {
        return drug.getPrice() * quantity;
    }

    /**
     * Returns a string representation of the Purchase.
     *
     * @return A string representation of the Purchase
     */
    @Override
    public String toString() {
        return "Purchase{" +
                "purchaseID='" + purchaseID + '\'' +
                ", drug=" + drug.getName() +
                ", customer=" + customer.getName() +
                ", quantity=" + quantity +
                ", purchaseDate=" + purchaseDate +
                ", totalCost=" + getTotalCost() +
                '}';
    }
}
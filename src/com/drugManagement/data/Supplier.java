package com.drugManagement.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a supplier in the Drug Management System.
 * This class contains information about a supplier, including their ID, name, contact information,
 * and the list of drugs they supply.
 */
public class Supplier {
    private String supplierID;
    private String name;
    private String contactInfo;
    private List<Drug> suppliedDrugs;

    /**
     * Constructs a Supplier with the specified ID, name, and contact information.
     *
     * @param supplierID The unique identifier for the supplier
     * @param name The name of the supplier
     * @param contactInfo The contact information of the supplier
     */
    public Supplier(String supplierID, String name, String contactInfo) {
        this.supplierID = supplierID;
        this.name = name;
        this.contactInfo = contactInfo;
        this.suppliedDrugs = new ArrayList<>();
    }

    /**
     * Gets the supplier ID.
     *
     * @return The supplier ID
     */
    public String getSupplierID() {
        return supplierID;
    }

    /**
     * Sets the supplier ID.
     *
     * @param supplierID The new supplier ID
     */
    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    /**
     * Gets the name of the supplier.
     *
     * @return The name of the supplier
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the supplier.
     *
     * @param name The new name of the supplier
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the contact information of the supplier.
     *
     * @return The contact information of the supplier
     */
    public String getContactInfo() {
        return contactInfo;
    }

    /**
     * Sets the contact information of the supplier.
     *
     * @param contactInfo The new contact information of the supplier
     */
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    /**
     * Gets the list of drugs supplied by this supplier.
     *
     * @return The list of supplied drugs
     */
    public List<Drug> getSuppliedDrugs() {
        return new ArrayList<>(suppliedDrugs);
    }

    /**
     * Adds a drug to the list of drugs supplied by this supplier.
     *
     * @param drug The drug to be added
     */
    public void addSuppliedDrug(Drug drug) {
        if (!suppliedDrugs.contains(drug)) {
            suppliedDrugs.add(drug);
            drug.addSupplier(this);
        }
    }

    /**
     * Removes a drug from the list of drugs supplied by this supplier.
     *
     * @param drug The drug to be removed
     */
    public void removeSuppliedDrug(Drug drug) {
        suppliedDrugs.remove(drug);
        drug.getSupplier().remove(this);
    }

    /**
     * Returns a string representation of the Supplier.
     *
     * @return A string representation of the Supplier
     */
    @Override
    public String toString() {
        return "Supplier{" +
                "supplierID='" + supplierID + '\'' +
                ", name='" + name + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", suppliedDrugs=" + suppliedDrugs.size() +
                '}';
    }

    /**
     * Checks if this Supplier is equal to another object.
     *
     * @param obj The object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Supplier supplier = (Supplier) obj;
        return supplierID.equals(supplier.supplierID);
    }

    /**
     * Generates a hash code for the Supplier.
     *
     * @return The hash code
     */
    @Override
    public int hashCode() {
        return supplierID.hashCode();
    }
}
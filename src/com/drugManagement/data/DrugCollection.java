package com.drugManagement.data;

import com.drugManagement.data.Drug;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of drugs in the Drug Management System.
 * This class provides methods to add, remove, retrieve, and manage drugs.
 */
public class DrugCollection {
    private List<Drug> drugs;

    /**
     * Constructs a new DrugCollection with an empty list of drugs.
     */
    public DrugCollection() {
        this.drugs = new ArrayList<>();
    }

    /**
     * Adds a new drug to the collection.
     * If a drug with the same ID already exists, it updates the existing drug.
     *
     * @param drug The drug to be added or updated
     */
    public void addDrug(Drug drug) {
        for (int i = 0; i < drugs.size(); i++) {
            if (drugs.get(i).getDrugID().equals(drug.getDrugID())) {
                drugs.set(i, drug);
                return;
            }
        }
        drugs.add(drug);
    }

    /**
     * Removes a drug from the collection based on its ID.
     *
     * @param drugID The ID of the drug to be removed
     * @return true if the drug was found and removed, false otherwise
     */
    public boolean removeDrug(String drugID) {
        return drugs.removeIf(drug -> drug.getDrugID().equals(drugID));
    }

    /**
     * Retrieves a drug from the collection based on its ID.
     *
     * @param drugID The ID of the drug to be retrieved
     * @return The drug with the specified ID, or null if not found
     */
    public Drug getDrug(String drugID) {
        for (Drug drug : drugs) {
            if (drug.getDrugID().equals(drugID)) {
                return drug;
            }
        }
        return null;
    }

    /**
     * Returns a list of all drugs in the collection.
     *
     * @return A list containing all drugs in the collection
     */
    public List<Drug> getAllDrugs() {
        return new ArrayList<>(drugs);
    }

    /**
     * Returns the number of drugs in the collection.
     *
     * @return The number of drugs in the collection
     */
    public int getDrugCount() {
        return drugs.size();
    }

    /**
     * Checks if the collection contains a drug with the specified ID.
     *
     * @param drugID The ID of the drug to check for
     * @return true if a drug with the specified ID exists, false otherwise
     */
    public boolean containsDrug(String drugID) {
        return drugs.stream().anyMatch(drug -> drug.getDrugID().equals(drugID));
    }

    /**
     * Updates the stock of a drug in the collection.
     *
     * @param drugID The ID of the drug to update
     * @param newStock The new stock value
     * @return true if the drug was found and updated, false otherwise
     */
    public boolean updateDrugStock(String drugID, int newStock) {
        Drug drug = getDrug(drugID);
        if (drug != null) {
            drug.setStock(newStock);
            return true;
        }
        return false;
    }

    /**
     * Clears all drugs from the collection.
     */
    public void clearDrugs() {
        drugs.clear();
    }
}
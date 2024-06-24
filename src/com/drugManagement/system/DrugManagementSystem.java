package com.drugManagement.system;

import com.drugManagement.data.*;
import com.drugManagement.algorithms.*;
import com.drugManagement.database.DatabaseManager;
import java.util.*;

public class DrugManagementSystem {
    private List<Drug> drugs;
    private List<Supplier> suppliers;
    private List<Customer> customers;
    private List<Purchase> purchases;

    public DrugManagementSystem() {
        this.drugs = new ArrayList<>();
        this.suppliers = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.purchases = new ArrayList<>();
        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        drugs.addAll(DatabaseManager.loadAllDrugs());
        suppliers.addAll(DatabaseManager.loadAllSuppliers());
        customers.addAll(DatabaseManager.loadAllCustomers());
        purchases.addAll(DatabaseManager.loadAllPurchases());
    }

    public void addDrug(Drug drug) {
        drugs.add(drug);
        DatabaseManager.saveDrug(drug);
    }

    public void removeDrug(String drugID) {
        drugs.removeIf(drug -> drug.getDrugID().equals(drugID));
        // Note: Database removal not implemented in DatabaseManager
    }

    public Drug searchDrugByID(String drugID) {
        return SearchingAlgorithms.binarySearch(drugs, drugID);
    }

    public List<Drug> searchDrugsByName(String name) {
        return SearchingAlgorithms.searchByName(drugs, name);
    }

    public List<Drug> searchDrugsByPriceRange(double minPrice, double maxPrice) {
        return SearchingAlgorithms.searchByPriceRange(drugs, minPrice, maxPrice);
    }

    public List<Drug> searchDrugsBySupplier(String supplierName) {
        return SearchingAlgorithms.searchBySupplier(drugs, supplierName);
    }

    public void sortDrugsByName() {
        SortingAlgorithms.mergeSort(drugs, Comparator.comparing(Drug::getName));
    }

    public void sortDrugsByPrice() {
        SortingAlgorithms.mergeSort(drugs, Comparator.comparing(Drug::getPrice));
    }

    public void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
        DatabaseManager.saveSupplier(supplier);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        DatabaseManager.saveCustomer(customer);
    }

    public void recordPurchase(Purchase purchase) {
        purchases.add(purchase);
        DatabaseManager.savePurchase(purchase);

        // Update drug stock
        Drug drug = purchase.getDrug();
        drug.setStock(drug.getStock() - purchase.getQuantity());
        DatabaseManager.saveDrug(drug);
    }

    public List<Purchase> getPurchaseHistory(Customer customer) {
        return purchases.stream()
                .filter(p -> p.getCustomer().equals(customer))
                .toList();
    }

    public double calculateTotalRevenue() {
        return purchases.stream()
                .mapToDouble(p -> p.getQuantity() * p.getDrug().getPrice())
                .sum();
    }

    public Map<Drug, Integer> getInventoryStatus() {
        Map<Drug, Integer> inventory = new HashMap<>();
        for (Drug drug : drugs) {
            inventory.put(drug, drug.getStock());
        }
        return inventory;
    }

    public List<Drug> getLowStockDrugs(int threshold) {
        return drugs.stream()
                .filter(drug -> drug.getStock() < threshold)
                .toList();
    }

    public void updateDrugInfo(String drugID, Drug updatedDrug) {
        Drug drug = searchDrugByID(drugID);
        if (drug != null) {
            drug.setName(updatedDrug.getName());
            drug.setDrugID(updatedDrug.getDrugID());
            drug.setPrice(updatedDrug.getPrice());
            drug.setStock(updatedDrug.getStock());
            DatabaseManager.saveDrug(drug);
        }
    }

    public List<Drug> getAllDrugs() {
        return new ArrayList<>(drugs);
    }

    public List<Supplier> getAllSuppliers() {
        return new ArrayList<>(suppliers);
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }

    public List<Purchase> getAllPurchases() {
        return new ArrayList<>(purchases);
    }
}
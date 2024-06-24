package com.drugManagement.algorithms;

import com.drugManagement.data.Drug;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Osei Owusu Ansah and Emmanuel Kwarteng
 * **/

public class SearchingAlgorithms {

    /**
     * Performs a linear search on the list of drugs based on drugID.
     *
     * @param drugs The list of drugs to search
     * @param drugID The ID of the drug to find
     * @return The found Drug object, or null if not found
     */
    public static Drug linearSearch(List<Drug> drugs, String drugID) {
        for (Drug drug : drugs) {
            if (drug.getDrugID().equals(drugID)) {
                return drug;
            }
        }
        return null;
    }

    /**
     * Performs a binary search on the list of drugs based on drugID.
     * Note: The list must be sorted by drugID for this to work correctly.
     *
     * @param drugs The sorted list of drugs to search
     * @param drugID The ID of the drug to find
     * @return The found Drug object, or null if not found
     */
    public static Drug binarySearch(List<Drug> drugs, String drugID) {
        int low = 0;
        int high = drugs.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Drug midDrug = drugs.get(mid);
            int comparison = midDrug.getDrugID().compareTo(drugID);

            if (comparison == 0) {
                return midDrug;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return null;
    }

    /**
     * Performs a flexible search on the list of drugs based on a given predicate.
     * This method can be used to search based on any attribute or combination of attributes.
     *
     * @param drugs The list of drugs to search
     * @param predicate The condition that the drug must satisfy
     * @return A list of Drug objects that satisfy the predicate
     */
    public static List<Drug> flexibleSearch(List<Drug> drugs, Predicate<Drug> predicate) {
        return drugs.stream()
                .filter(predicate)
                .toList();
    }

    /**
     * Searches for drugs by name (case-insensitive, partial match).
     *
     * @param drugs The list of drugs to search
     * @param name The name (or part of the name) to search for
     * @return A list of Drug objects that match the name
     */
    public static List<Drug> searchByName(List<Drug> drugs, String name) {
        return flexibleSearch(drugs, drug ->
                drug.getName().toLowerCase().contains(name.toLowerCase()));
    }

    /**
     * Searches for drugs by price range.
     *
     * @param drugs The list of drugs to search
     * @param minPrice The minimum price
     * @param maxPrice The maximum price
     * @return A list of Drug objects within the price range
     */
    public static List<Drug> searchByPriceRange(List<Drug> drugs, double minPrice, double maxPrice) {
        return flexibleSearch(drugs, drug ->
                drug.getPrice() >= minPrice && drug.getPrice() <= maxPrice);
    }

    /**
     * Searches for drugs by supplier name (case-insensitive, partial match).
     *
     * @param drugs The list of drugs to search
     * @param supplierName The name (or part of the name) of the supplier
     * @return A list of Drug objects supplied by the matching supplier(s)
     */
    public static List<Drug> searchBySupplier(List<Drug> drugs, String supplierName) {
        return flexibleSearch(drugs, drug ->
                drug.getSupplier().stream()
                        .anyMatch(supplier -> supplier.getName().toLowerCase().contains(supplierName.toLowerCase())));
    }
}
package com.drugManagement.algorithms;

import com.drugManagement.data.Drug;
import java.util.List;
import java.util.Comparator;

public class SortingAlgorithms {

    /**
     * Sorts the list of drugs using the Quick Sort algorithm.
     * This method is more efficient than Bubble Sort, with an average time complexity of O(n log n).
     *
     * @param drugs The list of drugs to be sorted
     * @param comparator The comparator to determine the sorting criteria
     */
    public static void quickSort(List<Drug> drugs, Comparator<Drug> comparator) {
        quickSort(drugs, 0, drugs.size() - 1, comparator);
    }

    private static void quickSort(List<Drug> drugs, int low, int high, Comparator<Drug> comparator) {
        if (low < high) {
            int partitionIndex = partition(drugs, low, high, comparator);

            quickSort(drugs, low, partitionIndex - 1, comparator);
            quickSort(drugs, partitionIndex + 1, high, comparator);
        }
    }

    private static int partition(List<Drug> drugs, int low, int high, Comparator<Drug> comparator) {
        Drug pivot = drugs.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(drugs.get(j), pivot) <= 0) {
                i++;
                swap(drugs, i, j);
            }
        }

        swap(drugs, i + 1, high);
        return i + 1;
    }

    private static void swap(List<Drug> drugs, int i, int j) {
        Drug temp = drugs.get(i);
        drugs.set(i, drugs.get(j));
        drugs.set(j, temp);
    }

    /**
     * Sorts the list of drugs using the Merge Sort algorithm.
     * This method is efficient, with a time complexity of O(n log n) in all cases.
     *
     * @param drugs The list of drugs to be sorted
     * @param comparator The comparator to determine the sorting criteria
     */
    public static void mergeSort(List<Drug> drugs, Comparator<Drug> comparator) {
        if (drugs.size() <= 1) {
            return;
        }

        int mid = drugs.size() / 2;
        List<Drug> left = drugs.subList(0, mid);
        List<Drug> right = drugs.subList(mid, drugs.size());

        mergeSort(left, comparator);
        mergeSort(right, comparator);

        merge(drugs, left, right, comparator);
    }

    private static void merge(List<Drug> drugs, List<Drug> left, List<Drug> right, Comparator<Drug> comparator) {
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            if (comparator.compare(left.get(i), right.get(j)) <= 0) {
                drugs.set(k++, left.get(i++));
            } else {
                drugs.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) {
            drugs.set(k++, left.get(i++));
        }

        while (j < right.size()) {
            drugs.set(k++, right.get(j++));
        }
    }

    // Usage examples:

    /**
     * Sorts the list of drugs by name using Quick Sort.
     *
     * @param drugs The list of drugs to be sorted
     */
    public static void sortByName(List<Drug> drugs) {
        quickSort(drugs, Comparator.comparing(Drug::getName));
    }

    /**
     * Sorts the list of drugs by code using Merge Sort.
     *
     * @param drugs The list of drugs to be sorted
     */
    public static void sortByCode(List<Drug> drugs) {
        mergeSort(drugs, Comparator.comparing(Drug::getDrugID));
    }

    /**
     * Sorts the list of drugs by price using Quick Sort.
     *
     * @param drugs The list of drugs to be sorted
     */
    public static void sortByPrice(List<Drug> drugs) {
        quickSort(drugs, Comparator.comparingDouble(Drug::getPrice));
    }
}
package com.drugManagement.database;

import com.drugManagement.data.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/drugManagement";
    private static final String USER = "username";
    private static final String PASSWORD = "password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void saveDrug(Drug drug) {
        String sql = "INSERT INTO drugs (drugID, name, price, stock, description) VALUES (?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE name = ?, price = ?, stock = ?, description = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, drug.getDrugID());
            pstmt.setString(2, drug.getName());
            pstmt.setDouble(3, drug.getPrice());
            pstmt.setInt(4, drug.getStock());
            pstmt.setString(6, drug.getName());
            pstmt.setDouble(7, drug.getPrice());
            pstmt.setInt(8, drug.getStock());


            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Drug> loadAllDrugs() {
        List<Drug> drugs = new ArrayList<>();
        String sql = "SELECT * FROM drugs";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Drug drug = new Drug(
                        rs.getString("drugID"),
                        rs.getString("name"),
                        (int) rs.getDouble("price"),
                        rs.getInt("stock")
                );
                drugs.add(drug);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return drugs;
    }

    public static void saveSupplier(Supplier supplier) {
        String sql = "INSERT INTO suppliers (supplierID, name, contactInfo) VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE name = ?, contactInfo = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, supplier.getSupplierID());
            pstmt.setString(2, supplier.getName());
            pstmt.setString(3, supplier.getContactInfo());
            pstmt.setString(4, supplier.getName());
            pstmt.setString(5, supplier.getContactInfo());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Supplier> loadAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM suppliers";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Supplier supplier = new Supplier(
                        rs.getString("supplierID"),
                        rs.getString("name"),
                        rs.getString("contactInfo")
                );
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suppliers;
    }

    public static void saveCustomer(Customer customer) {
        String sql = "INSERT INTO customers (customerID, name, contactInfo) VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE name = ?, contactInfo = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, customer.getCustomerID());
            pstmt.setString(2, customer.getName());
            pstmt.setString(3, customer.getContactInfo());
            pstmt.setString(4, customer.getName());
            pstmt.setString(5, customer.getContactInfo());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Customer> loadAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getString("customerID"),
                        rs.getString("name"),
                        rs.getString("contactInfo")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    public static void savePurchase(Purchase purchase) {
        String sql = "INSERT INTO purchases (purchaseID, drugID, customerID, quantity, purchaseDate) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, purchase.getPurchaseID());
            pstmt.setString(2, purchase.getDrug().getDrugID());
            pstmt.setString(3, purchase.getCustomer().getCustomerID());
            pstmt.setInt(4, purchase.getQuantity());
            pstmt.setDate(5, new java.sql.Date(purchase.getPurchaseDate().getTime()));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Purchase> loadAllPurchases() {
        List<Purchase> purchases = new ArrayList<>();
        String sql = "SELECT * FROM purchases";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Note: This assumes that Drug and Customer objects are already loaded
                // You might need to join tables or load these separately
                Drug drug = new Drug(rs.getString("drugID"), "", 0, 0, 0);
                Customer customer = new Customer(rs.getString("customerID"), "", "");

                Purchase purchase = new Purchase(
                        rs.getString("purchaseID"),
                        drug,
                        customer,
                        rs.getInt("quantity"),
                        rs.getDate("purchaseDate")
                );
                purchases.add(purchase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return purchases;
    }
}
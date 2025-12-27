package com.mphasis.service;

import com.mphasis.dao.PropertyDAO;
import com.mphasis.model.Property;

public class PropertyService {

    Property property = new Property();
    double tax=0;

    private PropertyDAO propertyDAO = new PropertyDAO();

    
    public double calculateTax(int propertyId) throws Exception {

        
        property = propertyDAO.getPropertyById(propertyId);

        if (property == null) {
            System.err.println("Property not found for ID: " + propertyId);
        }

        else {
        if (property.getLocation().equalsIgnoreCase("Y")) {
            tax = (property.getBuiltUpArea() * property.getAgeOfLand() * property.getBasePrice())
                    + (0.5 * property.getBuiltUpArea());
        } else if (property.getLocation().equalsIgnoreCase("N")) {
            tax = property.getBuiltUpArea() * property.getAgeOfLand() * property.getBasePrice();
        }
        }

        
        PropertyDAO.insertTax(propertyId, tax);

        return tax;
    }

    
    public static void displayEqual() {
        System.out.print("+");
        for (int i = 0; i < 80; i++)
            System.out.print("=");
        System.out.println("+");
    }

    public static void displayColumn() {
        System.out.printf("%s%-5s%-18s%-15s%-15s%-12s%-15s%s",
                "|", "ID", "BASE PRICE", "BUILT-UP AREA", "AGE(YEARS)",
                "IN CITY", "PROPERTY TAX", "|");
        System.out.println();
    }
}

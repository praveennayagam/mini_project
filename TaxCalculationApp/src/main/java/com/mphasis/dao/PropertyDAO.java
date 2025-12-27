package com.mphasis.dao;

import java.util.HashMap;
import java.util.Map;
import com.mphasis.model.Property;
import com.mphasis.service.PropertyService;

public class PropertyDAO {

    
    public static Map<Integer, Property> map = new HashMap<>();

    
    public boolean insertProperty(Property property) throws Exception {

        int id = getLastPropertyId() + 1;   
        map.put(id, property);              

        return true;
    }

    
    public int getLastPropertyId() throws Exception {
        if (map.isEmpty())
            return 0;
        return map.keySet().stream().max(Integer::compare).get();
    }

    
    public static void viewAllProperty() throws Exception {

        PropertyService.displayEqual();
        PropertyService.displayColumn();
        PropertyService.displayEqual();

        for (Map.Entry<Integer, Property> entry : map.entrySet()) {

            int id = entry.getKey();
            Property p = entry.getValue();
            double tx = p.getTax();
            String formattedTax = String.format("%.2f", tx);

            System.out.printf("%s%-5s%-18s%-15s%-15s%-12s%-15s%s",
                    "|",
                    id,                      
                    p.getBasePrice(),
                    p.getBuiltUpArea(),
                    p.getAgeOfLand(),
                    p.getLocation(),
                    formattedTax,
                    "|"
            );
            System.out.println();
        }

        PropertyService.displayEqual();
    }

    
    public static boolean insertTax(int id, double tax) throws Exception {
        Property p = map.get(id);
        if (p == null)
            return false;

        p.setTax(tax);
        return true;
    }

   
    public Property getPropertyById(int id) {
        return map.get(id);
    }
}


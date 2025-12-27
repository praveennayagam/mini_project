package com.example.QuizProject.dao;


import com.example.QuizProject.model.Admin;
import com.example.QuizProject.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDAO {

   
    public com.example.QuizProject.model.Admin validate(String username, String password) {
        String sql = "SELECT id, username, password FROM admin WHERE username = ? AND password = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Admin a = new Admin();
                    a.setId(rs.getInt("id"));
                    a.setUsername(rs.getString("username"));
                    a.setPassword(rs.getString("password"));
                    return a;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    
    public boolean updatePassword(String password)
    {
    	String sql = "UPDATE admin set password = ?  WHERE username = 'admin'";
    	
    	try(Connection con = DBConnection.getConnection();
    			PreparedStatement ps = con.prepareStatement(sql))
    	{
    		ps.setString(1, password);
    		
    		int rs = ps.executeUpdate();
    		
    		if(rs > 0)
    		{
    			return true;
    		}
    	}
    	catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	return false;
    }
    
}
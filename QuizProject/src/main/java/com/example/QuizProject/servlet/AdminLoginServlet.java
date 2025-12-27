package com.example.QuizProject.servlet;



import com.example.QuizProject.dao.AdminDAO;
import com.example.QuizProject.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {

    private AdminDAO adminDAO = new AdminDAO();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Admin admin = adminDAO.validate(username, password);

        if (admin != null) {
            
            resp.sendRedirect("adminDashboard.jsp?user=" + java.net.URLEncoder.encode(admin.getUsername(), "UTF-8"));
        } else {
            
            resp.sendRedirect("adminLogin.jsp?error=Invalid+username+or+password");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("adminLogin.jsp");
    }
}
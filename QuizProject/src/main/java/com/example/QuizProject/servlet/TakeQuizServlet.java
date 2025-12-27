package com.example.QuizProject.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.QuizProject.dao.QuizDAO;
import com.example.QuizProject.model.Question;
import com.example.QuizProject.util.DBConnection;

@WebServlet("/takeQuiz")
public class TakeQuizServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        String qidParam = request.getParameter("qid");
        int quizId;

        if (qidParam == null || qidParam.isEmpty()) {
            
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Quiz id is missing");
            return;
        }

        try {
            quizId = Integer.parseInt(qidParam);
        } catch (NumberFormatException e) {
           
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid quiz id");
            return;
        }

       
        try {
            QuizDAO quizDAO = new QuizDAO(DBConnection.getConnection());
            List<Question> questions = quizDAO.getQuestionsByQuizId(quizId);
            System.out.println(questions);

           
            request.setAttribute("questions", questions);
            request.setAttribute("quizId", quizId); 

            
            RequestDispatcher rd = request.getRequestDispatcher("takeQuiz.jsp");
            rd.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }
}

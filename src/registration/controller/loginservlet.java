package registration.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import registration.model.*;
import registration.dao.*;


@WebServlet("/login")
public class loginservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private logindao dao;
    public static String user="";
    public void init() {
        dao = new logindao();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/views/login.jsp");
		dispatcher.forward(request, response);
	}


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        loginbean loginBean = new loginbean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        try {
            if (dao.validate(loginBean)) {
                //HttpSession session = request.getSession();
                // session.setAttribute("username",username);
                //response.sendRedirect("loginsuccess.jsp");
            	user=username;
                RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/views/todo-list.jsp");
        		dispatcher.forward(request, response);
            } else {
                //HttpSession session = request.getSession();
                //session.setAttribute("user", username);
                //response.sendRedirect("login.jsp");
            	RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/views/login.jsp");
         		dispatcher.forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

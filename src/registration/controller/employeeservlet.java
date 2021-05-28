package registration.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import registration.dao.*;
import registration.model.*;

@WebServlet("/register")
public class employeeservlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final int INF = 0;
	private static final int WEB = 0;
       
	private employeedao dao= new employeedao();
	
    public employeeservlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/views/employeeregister.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
       

        employee emp = new employee();
        emp.setusername(username);
        emp.setmobile(mobile);
        emp.setemail(email);
        emp.setpassword(password);

        try {
            dao.registerEmployee(emp);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/views/login.jsp");
		dispatcher.forward(request, response);
	}

}

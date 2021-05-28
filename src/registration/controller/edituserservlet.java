package registration.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import registration.model.employee;
import registration.dao.*;

@WebServlet("/edituser")
public class edituserservlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	edituserdao dao=new edituserdao();
    public edituserservlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/views/edituser.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
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
            dao.editEmployee(emp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/views/edituser.jsp");
		dispatcher.forward(request, response);
	}

}

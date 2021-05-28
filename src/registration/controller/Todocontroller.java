package registration.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import registration.dao.*;
import registration.dao.TodoDaoImpl;
import registration.model.Todo;
import registration.model.notebook;
import registration.controller.loginservlet;
import registration.controller.edituserservlet;


@WebServlet("/")
public class Todocontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoDao todoDAO;
	edituserservlet eu=new edituserservlet();
	notebookservlet note=new notebookservlet();
	notebookdao dao=new notebookdao();
	public void init() {
		todoDAO = new TodoDaoImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/newnotebook":
				showNewNotebookForm(request, response);
				break;
			case "/insert":
				insertTodo(request, response);
				break;
			case "/delete":
				deleteTodo(request, response);
				break;
			case "/deletenotebook":
				deleteNotebook(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/editnotebook":
				showEditNotebookForm(request, response);
				break;
			case "/showdetails":
				showdetail(request, response);
				break;
			case "/update":
				updateTodo(request, response);
				break;
			case "/list":
				listTodo(request, response);
				break;
			case "/edituser":
				eu.doGet(request, response);
				break;
			case "/notebook":
				System.out.println("1st");
				listnotebook(request, response);
				break;
			case "/notify":
				System.out.println("2st");
				listnotification(request, response);
				break;
			default:
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
				dispatcher.forward(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	public void listnotebook(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		List<notebook> listnotebook = dao.selectAllnotebook(loginservlet.user);
		request.setAttribute("listnotebook", listnotebook);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/todo-list.jsp");
		dispatcher.forward(request, response);
	}
	
	public void listnotification(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		List<Todo> listnotification = todoDAO.selectTodaysTodos(loginservlet.user);
		request.setAttribute("listnotification", listnotification);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/todo-list.jsp");
		dispatcher.forward(request, response);
	}

	private void listTodo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		List<Todo> listTodo = todoDAO.selectAllTodos(loginservlet.user);
		request.setAttribute("listTodo", listTodo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/todo-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/todo-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewNotebookForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		notebook existingNotebook = todoDAO.selectNotebook(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/notebook.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showdetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		List<Todo> showdetail = todoDAO.showtododetails(id);
		request.setAttribute("showdetail",showdetail);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/todo-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Todo existingTodo = todoDAO.selectTodo(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/todo-form.jsp");
		request.setAttribute("todo", existingTodo);
		dispatcher.forward(request, response);

	}
	
	private void showEditNotebookForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Todo existingTodo = todoDAO.selectTodo(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/notebook.jsp");
		request.setAttribute("todo", existingTodo);
		dispatcher.forward(request, response);

	}

	private void insertTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException ,NullPointerException{
		
		String title = request.getParameter("title");
		String username = request.getParameter("username");
		String description = request.getParameter("description");
		
		System.out.println("get user");
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		String startDatestr = request.getParameter("startDate");
		LocalDate startDate = LocalDate.parse(startDatestr,df);
		System.out.println("get start"+startDate);
		
		String endDateStr = request.getParameter("endDate");
		LocalDate endDate = LocalDate.parse(endDateStr,df);
		System.out.println("get end"+endDate);
		
		String remainderDateStr = request.getParameter("remainderDate");
		LocalDate remainderDate = LocalDate.parse(remainderDateStr,df);
		System.out.println("get remiander"+remainderDate);
		
		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		String notebook_name = request.getParameter("notebook_name");
		Todo newTodo = new Todo(title, username, description,startDate,endDate,remainderDate, isDone,notebook_name);
		todoDAO.insertTodo(newTodo);
		response.sendRedirect("list");
	}

	private void updateTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		String title = request.getParameter("title");
		String username = request.getParameter("username");
		String description = request.getParameter("description");
		String startDatestr = request.getParameter("startDate");
		LocalDate startDate = LocalDate.parse(startDatestr);
		String endDateStr = request.getParameter("endDate");
		LocalDate endDate = LocalDate.parse(endDateStr);
		String remainderDateStr = request.getParameter("remianderDate");
		LocalDate remainderDate = LocalDate.parse(remainderDateStr);
		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		String notebook_name = request.getParameter("notebook_name");
		Todo updateTodo = new Todo(id, title, username, description, startDate,endDate,remainderDate, isDone,notebook_name);
		
		todoDAO.updateTodo(updateTodo);
		
		response.sendRedirect("list");
	}

	private void deleteTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		todoDAO.deleteTodo(id);
		response.sendRedirect("list");
	}
	
	private void deleteNotebook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		todoDAO.deleteNotebook(id);
		response.sendRedirect("list");
	}
}
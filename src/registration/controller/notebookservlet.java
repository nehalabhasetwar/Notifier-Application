package registration.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import registration.dao.notebookdao;
import registration.model.Todo;
import registration.model.notebook;

/**
 * Servlet implementation class notebookservlet
 */
@WebServlet("/notebookser")
public class notebookservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	notebookdao dao=new notebookdao();
    public notebookservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action = request.getServletPath();
		
		
		try {
		if(action=="/notebook")
			{	
					listnotebook(request,response); 
			}
		else
			{
			  RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/views/todo-list.jsp");
			  dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//doGet(request,response);
		
		String notebookname = request.getParameter("notebookname");
        String username = request.getParameter("username");
       

        notebook note=new notebook();
        note.setNotebook_name(notebookname);
        note.setUsername(username);

        try {
            dao.addNotebook(note);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/views/todo-list.jsp");
		dispatcher.forward(request, response);
	}
	
	
	public void listnotebook(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		//String username = request.getParameter("username");
		List<notebook> listnotebook = dao.selectAllnotebook(loginservlet.user);
		request.setAttribute("listnotebook", listnotebook);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/todo-list.jsp");
		dispatcher.forward(request, response);
	}

}

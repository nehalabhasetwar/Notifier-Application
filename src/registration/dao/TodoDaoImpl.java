package registration.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import registration.model.*;
import registration.utils.JDBCUtils;


public class TodoDaoImpl implements TodoDao {

	private static final String INSERT_TODOS_SQL = "INSERT INTO todos"
			+ "  (title, username, description, startDate,is_done,notebook_name,endDate,remainderDate) VALUES " + " (?,?,?,?,?,?,?,?);";
    private static final String UPDATE_NOTEBOOK = "update notebook set notes_count=notes_count+1 where notebookname = ?;";
	private static final String SELECT_TODO_BY_ID = "select id,title,username,description,startDate,is_done from todos where id =?";
	private static final String SELECT_NOTEBOOK_BY_ID = "select notebookname,username,notes_count,id from notebook where id =?";
	private static final String show_TODO_BY_ID = "select * from todos where id =?";
	private static final String SELECT_ALL_TODOS = "select * from todos where username=?";
	private static final String SELECT_Today_TODOS = "select * from todos where username=?";
	private static final String DELETE_TODO_BY_ID = "delete from todos where id = ?;";
	private static final String DELETE_NOTEBOOK_BY_ID = "delete from notebook where id = ?;";
	private static final String UPDATE_TODO = "update todos set title = ?, username= ?, description =?, startDate =?,endDate=?,remainderDate=?,is_done = ? where id = ?;";

	public TodoDaoImpl() {
	}

	@Override
	public void insertTodo(Todo todo) throws SQLException {
		System.out.println(INSERT_TODOS_SQL);
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TODOS_SQL)) {
			preparedStatement.setString(1, todo.getTitle());
			preparedStatement.setString(2, todo.getUsername());
			preparedStatement.setString(3, todo.getDescription());
			preparedStatement.setDate(4, JDBCUtils.getSQLDate(todo.getStartDate()));
			preparedStatement.setBoolean(5, todo.getStatus());
			preparedStatement.setString(6, todo.getNotebook_name());
			preparedStatement.setDate(7, JDBCUtils.getSQLDate(todo.getEndDate()));
			preparedStatement.setDate(8, JDBCUtils.getSQLDate(todo.getRemainderDate()));
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_NOTEBOOK);) {
			statement.setString(1, todo.getNotebook_name());
			boolean rowUpdated = statement.executeUpdate() > 0;
		}
	}

	@Override
	public Todo selectTodo(long todoId) {
		Todo todo = null;
		// Step 1: Establishing a Connection
		try (Connection connection = JDBCUtils.getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TODO_BY_ID);) {
			preparedStatement.setLong(1, todoId);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				long id = rs.getLong("id");
				String title = rs.getString("title");
				String username = rs.getString("username");
				String description = rs.getString("description");
				LocalDate startDate = rs.getDate("startDate").toLocalDate();
				LocalDate endDate = rs.getDate("endDate").toLocalDate();
				LocalDate remainderDate = rs.getDate("remainderDate").toLocalDate();
				boolean isDone = rs.getBoolean("is_done");
				String notebook_name = rs.getString("notebook_name");
				
				todo = new Todo(id, title, username, description, startDate,endDate,remainderDate, isDone,notebook_name);
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return todo;
	}
	
	@Override
	public notebook selectNotebook(long notebookId) {
		notebook nb = null;
		try (Connection connection = JDBCUtils.getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOTEBOOK_BY_ID);) {
			preparedStatement.setLong(1, notebookId);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				long id = rs.getLong("id");
				String username=rs.getString("username");
				String notebook_name = rs.getString("notebook_name");
				int notes_count=rs.getInt("notes_count");
				nb = new notebook(notebook_name,username, notes_count, id);
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return nb;
	}

	@Override
	public List<Todo> selectAllTodos(String username) {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Todo> todos = new ArrayList<>();

		// Step 1: Establishing a Connection
		try (Connection connection = JDBCUtils.getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TODOS);) {
			preparedStatement.setString(1, username);
			
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				long id = rs.getLong("id");
				String title = rs.getString("title");
				String username1 = rs.getString("username");
				String description = rs.getString("description");
				LocalDate startDate = rs.getDate("startDate").toLocalDate();
				LocalDate endDate = rs.getDate("endDate").toLocalDate();
				LocalDate remainderDate = rs.getDate("remainderDate").toLocalDate();
				boolean isDone = rs.getBoolean("is_done");
				String notebook_name = rs.getString("notebook_name");
				todos.add(new Todo(id, title, username1, description, startDate,endDate,remainderDate, isDone,notebook_name));
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return todos;
	}
	
	@Override
	public List<Todo> selectTodaysTodos(String username) {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Todo> todos = new ArrayList<>();

		// Step 1: Establishing a Connection
		try (Connection connection = JDBCUtils.getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_Today_TODOS);) {
			preparedStatement.setString(1, username);
			
			System.out.println(preparedStatement.toString());
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				long id = rs.getLong("id");
				String title = rs.getString("title");
				String username1 = rs.getString("username");
				String description = rs.getString("description");
				LocalDate startDate = rs.getDate("startDate").toLocalDate();
				LocalDate endDate = rs.getDate("endDate").toLocalDate();
				LocalDate remainderDate = rs.getDate("remainderDate").toLocalDate();
				boolean isDone = rs.getBoolean("is_done");
				String notebook_name = rs.getString("notebook_name");
				System.out.println(remainderDate+" "+LocalDate.now());
				if(remainderDate.compareTo(LocalDate.now())==0)
					todos.add(new Todo(id, title, username1, description, startDate,endDate,remainderDate, isDone,notebook_name));
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return todos;
	}

	@Override
	public boolean deleteTodo(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_TODO_BY_ID);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	@Override
	public boolean deleteNotebook(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_NOTEBOOK_BY_ID);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	@Override
	public List<Todo> showtododetails(int id) throws SQLException {
		List<Todo> todos=new ArrayList<Todo>();
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(show_TODO_BY_ID);) {
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			System.out.println();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				long idx = rs.getLong("id");
				String title = rs.getString("title");
				String username1 = rs.getString("username");
				String description = rs.getString("description");
				LocalDate startDate = rs.getDate("startDate").toLocalDate();
				LocalDate endDate = rs.getDate("endDate").toLocalDate();
				LocalDate remainderDate = rs.getDate("remainderDate").toLocalDate();
				boolean isDone = rs.getBoolean("is_done");
				String notebook_name = rs.getString("notebook_name");
				System.out.println(remainderDate+" "+LocalDate.now());
				todos.add(new Todo(idx, title, username1, description, startDate,endDate,remainderDate, isDone,notebook_name));
			}
		}
		return todos;
	}

	@Override
	public boolean updateTodo(Todo todo) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_TODO);) {
			statement.setString(1, todo.getTitle());
			statement.setString(2, todo.getUsername());
			statement.setString(3, todo.getDescription());
			statement.setDate(4, JDBCUtils.getSQLDate(todo.getStartDate()));
			statement.setDate(5, JDBCUtils.getSQLDate(todo.getEndDate()));
			statement.setDate(6, JDBCUtils.getSQLDate(todo.getRemainderDate()));
			statement.setBoolean(7, todo.getStatus());
			statement.setLong(8, todo.getId());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
}
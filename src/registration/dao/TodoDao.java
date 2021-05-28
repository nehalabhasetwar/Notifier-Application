package registration.dao;

import java.sql.SQLException;
import java.util.List;

import registration.model.*;

public interface TodoDao {

	void insertTodo(Todo todo) throws SQLException;
	Todo selectTodo(long todoId);
	notebook selectNotebook(long notebookId);
	List<Todo> selectAllTodos(String username);
	List<Todo> selectTodaysTodos(String username);
	boolean deleteTodo(int id) throws SQLException;
	boolean deleteNotebook(int id) throws SQLException;
	List<Todo> showtododetails(int id) throws SQLException;
	boolean updateTodo(Todo todo) throws SQLException;

}
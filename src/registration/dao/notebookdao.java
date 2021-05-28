package registration.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import registration.model.Todo;
import registration.model.notebook;
import registration.utils.JDBCUtils;

public class notebookdao {
	
	public static final String SELECT_ALL_TODOS = "select * from notebook where username=?";
	
	public int addNotebook(notebook note) throws ClassNotFoundException {
        String INSERT_USERS_SQL =  "INSERT INTO notebook"
    			+ "  (notebookname, username,notes_count) VALUES " + " (?, ?,?);";

        int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/employee?useSSL=false", "root", "neha1234");

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, note.getNotebook_name());
            preparedStatement.setString(2, note.getUsername());
            preparedStatement.setInt(3,0);
            
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            e.printStackTrace();
        }
        return result;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    
    
    
    public List<notebook> selectAllnotebook(String username) {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<notebook> todos = new ArrayList<notebook>();

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
				String title = rs.getString("notebookname");
				String username1 = rs.getString("username");
				int notes_count=rs.getInt("notes_count");
				todos.add(new notebook(title,username, notes_count, id));
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return todos;
	}

}

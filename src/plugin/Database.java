package plugin;

import java.rmi.ServerException;
import java.sql.*;


public class Database {

		private static final String DATABASE_FILE = "database.sqlite";
		private static final String DATABASE_URL = "jdbc:sqlite:" + DATABASE_FILE;
		private Connection connection;

		public static void initialize() throws ServerException {
			try {
				final String driver = "org.sqlite.JDBC";
				Class.forName(driver);
				
			}
			catch(ClassNotFoundException e) {
				throw new ServerException("Could not load database driver", e);
			}
		}		
		
		public Connection getConnection() {
			return connection;
		}

		public void startTransaction() throws ServerException {
			try {
				assert (connection == null);			
				connection = DriverManager.getConnection(DATABASE_URL);
				connection.setAutoCommit(false);
			}
			catch (SQLException e) {
				throw new ServerException("Could not connect to database. Make sure " + 
					DATABASE_FILE, e);
			}
		}
		
		public boolean inTransaction() {
			return (connection != null);
		}
		
		public void endTransaction(boolean commit) {
			if (connection != null) {		
				try {
					if (commit) {
						connection.commit();
					}
					else {
						connection.rollback();
					}
				}
				catch (SQLException e) {
					System.out.println("Could not end transaction");
					e.printStackTrace();
				}
				finally {
					safeClose(connection);
					connection = null;
				}
			}
		}
		
		public static void safeClose(Connection conn) {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					// ...
				}
			}
		}
		
		public static void safeClose(Statement stmt) {
			if (stmt != null) {
				try {
					stmt.close();
				}
				catch (SQLException e) {
					// ...
				}
			}
		}
		
		public static void safeClose(PreparedStatement stmt) {
			if (stmt != null) {
				try {
					stmt.close();
				}
				catch (SQLException e) {
					// ...
				}
			}
		}
		
		public static void safeClose(ResultSet rs) {
			if (rs != null) {
				try {
					rs.close();
				}
				catch (SQLException e) {
					// ...
				}
			}
		}
		
		
		public static void createDatabaseTables(){
			
		}
		
}



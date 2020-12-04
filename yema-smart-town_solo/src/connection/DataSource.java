package connection;

import java.sql.Connection;

public class DataSource { 
	private static ConnectionPool pool;
	
	public DataSource() {
		pool = new ConnectionPool();
	}
	
	public  Connection giveConnection() { 
		return pool.giveConnection();
	}
	
	public static void returnConnection(Connection c) {
		try {
			pool.returnConnection(c);
		} catch (Exception ee) {}
	}
	
	public void closeAllConnection() {
		try {
			pool.closeAllConnections();
		} catch (Exception e) {}
	}
}

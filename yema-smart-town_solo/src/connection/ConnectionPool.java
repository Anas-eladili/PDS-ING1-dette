package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionPool {
	private ArrayList<Connection> connections = new ArrayList<Connection>();
	private Connection myConnection;
	private PropertiesFileReader file = new PropertiesFileReader();
	// private ArrayList<Connection> usedconnections = new ArrayList<Connection>();
    //private int sizeMax = Integer.valueOf(System.getProperty("my.prop"));

	public ConnectionPool() {
		try {
			file.initJDBC();
			String driver = file.getProperty("driver");

			Class.forName(driver);
			// nombre +=1;

			for (int i = 0; i < 5 ; i++) {
				myConnection = DriverManager.getConnection(file.getProperty("url"), file.getProperty("id"),
						file.getProperty("password"));
				connections.add(myConnection);

			}
		} catch (Exception e) {
			System.out.println("erreur connection");
			e.printStackTrace();

		}
	}

	/**
	 * giveConnection method gives connections to clients whose need it if there
	 * isn't any connection available, they have to wait
	 */
	public synchronized Connection giveConnection() {
		
		
		while (connections.isEmpty()) {

			// throw new RuntimeException("Maximum pool size reached, no available
			// connections!");
			// if(connections.isEmpty()) {
			//System.out.println(" max pool size reached");		
			if(!connections.isEmpty()) {Connection tempConnection = connections.get(0);
			// usedconnections.add(tempConnection);
			//System.out.println(" nb connexions crées:" + connections.size());
			connections.remove(0);
			
			return tempConnection;
			}

		}

		// nombre = connections.size();
		Connection tempConnection = connections.get(0);
		// usedconnections.add(tempConnection);
		//System.out.println(" nb connexions crées:" + connections.size());
		connections.remove(0);
		//if(connections.isEmpty()) {System.out.println(" max pool size reached ");}

		return tempConnection;
	}

	private void sleep(int i) {
		// TODO Auto-generated method stub

	}

	/**
	 * returnConnection method allows to return the connection used by a client
	 * thanks to that, the connection is available again
	 */
	public synchronized void returnConnection(Connection c) {
		connections.add(c);
		// usedconnections.remove(c);
	}

	/**
	 * closeAllConnection method allows to close all the connection used by
	 * different clients
	 */
	public synchronized void closeAllConnections() {
		try {
			for (Connection c : connections) {
				c.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

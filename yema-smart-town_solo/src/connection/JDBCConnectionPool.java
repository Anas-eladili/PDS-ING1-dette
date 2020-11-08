package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class JDBCConnectionPool {
	private ArrayList<Connection> connections = new ArrayList<Connection>();
	private Connection myConnection;
	private PropertiesFileReader file = new PropertiesFileReader();
	// private ArrayList<Connection> usedconnections = new ArrayList<Connection>();
	// private int sizeMax = Integer.valueOf(System.getProperty("my.prop"));

	public JDBCConnectionPool() {
		try {
			file.initJDBC();
			String driver = file.getProperty("driver");

			Class.forName(driver);
			// nombre +=1;

			for (int i = 0; i < 1; i++) {
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
		System.out.println("Veuillez patientez");
		System.out.println(" nb " + connections.size());
		while (connections.isEmpty()) {

			// throw new RuntimeException("Maximum pool size reached, no available
			// connections!");
			// if(connections.isEmpty()) {
			sleep(1500);

		}

		// nombre = connections.size();
		Connection tempConnection = connections.get(0);
		// usedconnections.add(tempConnection);
		System.out.println(" nb connexions cr�es:" + connections.size());
		connections.remove(0);
		System.out.println(" nb connexions restente:" + connections.size());

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

package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JTable;

import common_aqs_client.CommunicationWithServer;
import connection.PropertiesFileReader;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class launchIhm {

	
	private JFrame frame;
	CommunicationWithServer client;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					launchIhm window = new launchIhm();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	public JFrame getFrame() {
		return frame;
	}



	public void setFrame(JFrame frame) {
		this.frame = frame;
	}



	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public launchIhm() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		/*CommunicationWithServer client = new CommunicationWithServer();
    	
   	 PropertiesFileReader serveconfig = new PropertiesFileReader();
   	 serveconfig.initServer();
   	 final int SERVER_PORT = Integer.parseInt(serveconfig.getProperty("serverportClient"));
		final String SERVER_ADDRESS = serveconfig.getProperty("serveraddress");
		client.startConnection(SERVER_ADDRESS, SERVER_PORT);*/
		//client.stopConnection();
		frame = new JFrame();
		frame.setTitle("Bollard and Sensors menu");
		frame.setBounds(100, 100, 711, 529);
		frame.getContentPane().setLayout(null);
		
		JButton SensorB = new JButton("Car Setting");
		SensorB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {CrudCar c;
				CommunicationWithServer client = new CommunicationWithServer();
		    	
			   	 PropertiesFileReader serveconfig = new PropertiesFileReader();
			   	 serveconfig.initServer();
			   	 final int SERVER_PORT = Integer.parseInt(serveconfig.getProperty("serverportClient"));
					final String SERVER_ADDRESS = serveconfig.getProperty("serveraddress");
					client.startConnection(SERVER_ADDRESS, SERVER_PORT);
				
				c = new CrudCar(client);
				frame.setVisible(false);
				c.setVisible(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			}
		});
		SensorB.setBounds(32, 81, 147, 36);
		frame.getContentPane().add(SensorB);
		
		JButton BollardB = new JButton("Bollard Configuration");
		BollardB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				
				try {Crud c;
				CommunicationWithServer client = new CommunicationWithServer();
		    	
			   	 PropertiesFileReader serveconfig = new PropertiesFileReader();
			   	 serveconfig.initServer();
			   	 final int SERVER_PORT = Integer.parseInt(serveconfig.getProperty("serverportClient"));
					final String SERVER_ADDRESS = serveconfig.getProperty("serveraddress");
					client.startConnection(SERVER_ADDRESS, SERVER_PORT);
					c = new Crud(client);
					frame.setVisible(false);
					c.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		BollardB.setBounds(251, 81, 141, 36);
		frame.getContentPane().add(BollardB);
		
		JButton SimulationB = new JButton("Simulation");
		SimulationB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { SimulationIhm c;
				
				CommunicationWithServer client = new CommunicationWithServer();
		    	
			   	 PropertiesFileReader serveconfig = new PropertiesFileReader();
			   	 serveconfig.initServer();
			   	 final int SERVER_PORT = Integer.parseInt(serveconfig.getProperty("serverportClient"));
					final String SERVER_ADDRESS = serveconfig.getProperty("serveraddress");
					try {
						client.startConnection(SERVER_ADDRESS, SERVER_PORT);
						c = new SimulationIhm(client);
						frame.setVisible(false);
						c.setVisible(true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
			}
				
			
		});
		SimulationB.setBounds(151, 147, 120, 36);
		frame.getContentPane().add(SimulationB);
		
		
		/*	client.stopConnection();*/
	}
}

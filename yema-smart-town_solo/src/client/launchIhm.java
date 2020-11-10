package client;

import java.awt.EventQueue;

import javax.swing.JFrame;



import connection.PropertiesFileReader;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;

import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.SystemColor;

public class launchIhm {

	
	private JFrame frmBollardAndTraffic;
	CommunicationWithServer client;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					launchIhm window = new launchIhm();
					window.frmBollardAndTraffic.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	public JFrame getFrame() {
		return frmBollardAndTraffic;
	}



	public void setFrame(JFrame frame) {
		this.frmBollardAndTraffic = frame;
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
		
		frmBollardAndTraffic = new JFrame();
		frmBollardAndTraffic.getContentPane().setBackground(Color.GRAY);
		frmBollardAndTraffic.getContentPane().setFont(new Font("Yu Gothic Medium", Font.BOLD, 12));
		frmBollardAndTraffic.setTitle("Bollard and traffic menu");
		frmBollardAndTraffic.setBounds(100, 100, 464, 273);
		frmBollardAndTraffic.getContentPane().setLayout(null);
		
		JButton SensorB = new JButton("Car Setting");
		SensorB.setForeground(SystemColor.desktop);
		SensorB.setFont(new Font("Yu Gothic Medium", Font.BOLD, 13));
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
				frmBollardAndTraffic.setVisible(false);
				c.setVisible(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			}
		});
		SensorB.setBounds(31, 81, 147, 36);
		frmBollardAndTraffic.getContentPane().add(SensorB);
		
		JButton BollardB = new JButton("Bollard Configuration");
		BollardB.setFont(new Font("Yu Gothic Medium", Font.BOLD, 13));
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
					frmBollardAndTraffic.setVisible(false);
					c.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		BollardB.setBounds(238, 81, 167, 36);
		frmBollardAndTraffic.getContentPane().add(BollardB);
		
		JButton SimulationB = new JButton("Simulation");
		SimulationB.setFont(new Font("Yu Gothic Medium", Font.BOLD, 13));
		SimulationB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				SimulationIhm c;
				
				CommunicationWithServer client = new CommunicationWithServer();
		    	
			   	 PropertiesFileReader serveconfig = new PropertiesFileReader();
			   	 serveconfig.initServer();
			   	 final int SERVER_PORT = Integer.parseInt(serveconfig.getProperty("serverportClient"));
					final String SERVER_ADDRESS = serveconfig.getProperty("serveraddress");
					try {
						client.startConnection(SERVER_ADDRESS, SERVER_PORT);
						c = new SimulationIhm(client);
						frmBollardAndTraffic.setVisible(false);
						c.getFrame().setVisible(true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
			}
				
			
		});
		SimulationB.setBounds(151, 147, 120, 36);
		frmBollardAndTraffic.getContentPane().add(SimulationB);
		
		JLabel lblNewLabel = new JLabel("Bollard and Traffic Menu");
		lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(120, 24, 220, 41);
		frmBollardAndTraffic.getContentPane().add(lblNewLabel);
		
		
		
	}
}

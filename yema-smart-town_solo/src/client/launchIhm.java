package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class launchIhm {

	
	private JFrame frame;
	
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
	 */
	public launchIhm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Bollard and Sensors menu");
		frame.setBounds(100, 100, 711, 529);
		frame.getContentPane().setLayout(null);
		
		JButton SensorB = new JButton("Car Setting");
		SensorB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {CrudCar c;
				c = new CrudCar();
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
				
//launchIhm fen = new launchIhm();
				
				try {Crud c;
					c = new Crud();
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
		SimulationB.setBounds(151, 147, 120, 36);
		frame.getContentPane().add(SimulationB);
		
		
		
	}
}

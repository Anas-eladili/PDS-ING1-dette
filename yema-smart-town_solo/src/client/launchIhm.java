package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class launchIhm {

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
		JFrame frmBollardAndSensors = new JFrame();
		frmBollardAndSensors.setTitle("Bollard and Sensors menu");
		frame = frmBollardAndSensors;
		frmBollardAndSensors.getContentPane().setLayout(null);
		
		JButton SensorB = new JButton("Sensor configuration");
		SensorB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		SensorB.setBounds(32, 81, 147, 36);
		frmBollardAndSensors.getContentPane().add(SensorB);
		
		JButton BollardB = new JButton("Bollard Configuration");
		BollardB.setBounds(251, 81, 141, 36);
		frmBollardAndSensors.getContentPane().add(BollardB);
		
		JButton SimulationB = new JButton("Simulation");
		SimulationB.setBounds(151, 147, 120, 36);
		frmBollardAndSensors.getContentPane().add(SimulationB);
		frame.setBounds(100, 100, 638, 446);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		table = new JTable();
		frame.getContentPane().add(table);
	}
}

package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;

import common.Car;
import common.RetractableBollard;
import rectractable_bollard_vehicule_sensor.SensorOperation;

import javax.swing.JSeparator;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;

public class Bollard_Settings {

	private JFrame frame;
	private JTextField txtway;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bollard_Settings window = new Bollard_Settings();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}  /*int id ; 
	   Boolean way ;
	   RetractableBollard bollard = new RetractableBollard();
	   System.out.println("way : ");
	   way = enter5.nextBoolean();
	   bollard.setWay(way);
	 
	 
	 bollard.setId(1);
	 SensorOperation operation =new  SensorOperation();
	 Car car =new Car();
	 System.out.println("id : ");
	 id = enter3.nextInt();
	 car.setId(id);
	 car.setId(11);
	 car.setIsInTheCity(false);
	 operation.start(bollard,car);
                            */
	/**
	 * Create the application.
	 */
	public Bollard_Settings() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 714, 494);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Launch Simulation ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(233, 220, 177, 58);
		frame.getContentPane().add(btnNewButton);
		
		txtway = new JTextField();
		txtway.setBounds(243, 56, 123, 25);
		frame.getContentPane().add(txtway);
		txtway.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Retour");
		btnNewButton_1.setFont(new Font("Yu Gothic Medium", Font.BOLD, 13));
		btnNewButton_1.setBounds(494, 335, 89, 31);
		frame.getContentPane().add(btnNewButton_1);
	}
}

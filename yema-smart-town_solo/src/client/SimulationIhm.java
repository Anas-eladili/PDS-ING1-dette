package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;

import common.Car;
import common.RetractableBollard;
import common_aqs_client.CommunicationWithServer;
import rectractable_bollard_vehicule_sensor.SensorOperation;

import javax.swing.JSeparator;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class SimulationIhm {

	private JFrame frame;
	private JTextField txtidBollard;
	private JTextField testWayBollard;
	private JTextField textIdCar;
	private JTextField textCarLocation;
	private CommunicationWithServer client ;

	/**
	 * Launch the application.
	 */
	 
	/**
	 * Create the application.
	 */
	public SimulationIhm(CommunicationWithServer client) {
		this.client = client;
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
		btnNewButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					btnSimulationActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(243, 314, 177, 58);
		frame.getContentPane().add(btnNewButton);
		
		txtidBollard = new JTextField();
		txtidBollard.setBounds(145, 75, 123, 25);
		frame.getContentPane().add(txtidBollard);
		txtidBollard.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Retour");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				launchIhm launchIhm = null;
				try {client.stopConnection();

					launchIhm = new launchIhm();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				launchIhm.getFrame().setVisible(true);
				
			
			
			}

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub
				
			}
		});
		btnNewButton_1.setFont(new Font("Yu Gothic Medium", Font.BOLD, 13));
		btnNewButton_1.setBounds(560, 359, 89, 31);
		frame.getContentPane().add(btnNewButton_1);
		
		testWayBollard = new JTextField();
		testWayBollard.setBounds(145, 119, 123, 25);
		frame.getContentPane().add(testWayBollard);
		testWayBollard.setColumns(10);
		
		textIdCar = new JTextField();
		textIdCar.setBounds(145, 170, 123, 25);
		frame.getContentPane().add(textIdCar);
		textIdCar.setColumns(10);
		
		textCarLocation = new JTextField();
		textCarLocation.setBounds(145, 216, 123, 25);
		frame.getContentPane().add(textCarLocation);
		textCarLocation.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Way of bollard ");
		lblNewLabel.setBounds(51, 122, 81, 19);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("id of bollard");
		lblNewLabel_1.setBounds(51, 80, 84, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("car id");
		lblNewLabel_2.setBounds(51, 175, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("car location");
		lblNewLabel_3.setBounds(51, 221, 96, 14);
		frame.getContentPane().add(lblNewLabel_3);
	}
	public void alert(String msg) {
		JOptionPane.showMessageDialog(frame, msg);
	}
	private void btnSimulationActionPerformed(java.awt.event.ActionEvent evt) throws IOException {// GEN-FIRST:event_btnSimulationActionPerformed
		// TODO add your handling code here:
		Integer idBollard = Integer.parseInt(txtidBollard.getText());
		Integer idCar = Integer.parseInt(textIdCar.getText());
		Boolean BollardWay = Boolean.parseBoolean(testWayBollard.getText());
		Boolean CarLocation = Boolean.parseBoolean(textCarLocation.getText());
		
		if ( !BollardWay.toString().isEmpty() && !CarLocation.toString().isEmpty()&& !idCar.toString(idCar).isEmpty()   && !idBollard.toString(idBollard).isEmpty() ){
			
			RetractableBollard bollard = new RetractableBollard();
			 SensorOperation operation =new  SensorOperation();
			Car car =new Car();
			car.setId(idCar);
			bollard.setId(idBollard);
			car.setIsInTheCity(CarLocation);
			   bollard.setWay(BollardWay);
			
			
			
			try {
				operation.start(bollard,car);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		alert("Bollard operation sucess");
		
	}
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
		
		
	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Crud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Crud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Crud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Crud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CommunicationWithServer client = new CommunicationWithServer();
					SimulationIhm window = new SimulationIhm(client);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} 
}

	















package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;

import common.*;
import common.business.Car;
import common.business.RetractableBollard;
import connection.PropertiesFileReader;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import business.SensorOperation;

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
	private JTextField textFieldAlert;
	private JTextField textFieldTraffic;

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

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
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
				MainClient launchIhm = null;
				try {
					

					launchIhm = new MainClient();
					client.stopConnection();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				launchIhm.getFrame().setVisible(true);
				frame.setVisible(false);
			
			
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
		
		textFieldAlert = new JTextField();
		textFieldAlert.setBounds(504, 77, 86, 20);
		frame.getContentPane().add(textFieldAlert);
		textFieldAlert.setColumns(10);
		
		textFieldTraffic = new JTextField();
		textFieldTraffic.setBounds(504, 121, 86, 20);
		frame.getContentPane().add(textFieldTraffic);
		textFieldTraffic.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(416, 80, 46, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setBounds(416, 124, 46, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		JButton btnNewButton_2 = new JButton("Select");
		btnNewButton_2.setBounds(348, 171, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Update");
		btnNewButton_3.setBounds(469, 171, 89, 23);
		frame.getContentPane().add(btnNewButton_3);
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
	private void fetch(CommunicationWithServer client) throws IOException {

		PropertiesFileReader serveconfig = new PropertiesFileReader();
		serveconfig.initServer();

		

		bollardinfo.clear();
		Request req = new Request();

		ConvertJSON converter = new ConvertJSON();
		req.setSource("client");
		req.setOperation_type("select");
		req.setTarget("retractablebollard");
		

		//client.startConnection(SERVER_ADDRESS, SERVER_PORT);
		
		Response resp = new Response();
		try {

			resp = client.sendMessage(req);
			
			ArrayList<String> databollard = resp.getValues();
			ArrayList<RetractableBollard> data = new ArrayList<RetractableBollard>();

			for (int i = 0; i < databollard.size(); i++) {
				data.add(converter.JsonToBollard(databollard.get(i)));

			}

			System.out.println("ok");

			DefaultTableModel model = (DefaultTableModel) tblBollard.getModel();
			for (int i = 0; i < data.size(); i++) {

				Object[] row = new Object[5];
				row[0] = data.get(i).getId();
				row[1] = data.get(i).getAddress();
				row[2] = data.get(i).isActive();
				row[3] = data.get(i).isState();
				row[4] = data.get(i).isWay();
				model.addRow(row);
			}

		}

		

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
			java.util.logging.Logger.getLogger(Frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

	















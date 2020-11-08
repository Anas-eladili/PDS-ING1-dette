/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import common.Car;
import common.ConvertJSON;

import common.Request;
import common.Response;
import common.RetractableBollard;
import common_aqs_client.CommunicationWithServer;
import connection.PropertiesFileReader;

import client.CrudCar;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Color;
import javax.swing.SwingConstants;

/**
 *
 * @author ANAS
 */
public class CrudCar extends javax.swing.JFrame {

    /**
     * Creates new form Crud
     */
	/*private Logger LOGGER;
	@SuppressWarnings("unused")
	private PropertiesFileReader serveconfig;
	 final int SERVER_PORT;
	 final String SERVER_ADDRESS;
	private ClientCommunication client;*/
    
    ArrayList<Car> bollardinfo = new ArrayList<>();
    CommunicationWithServer client = new CommunicationWithServer();
    PropertiesFileReader serveconfig = new PropertiesFileReader();
	

    public CrudCar() throws IOException  {
    	getContentPane().setBackground(new Color(65, 105, 225));
        initComponents();
       
        
        
        lOGGER = Logger.getLogger(CrudCar.class.getName());
		
		serveconfig.initServer();
		
		
		
		
		final int SERVER_PORT = Integer.parseInt(serveconfig.getProperty("serverportClient"));
		final String SERVER_ADDRESS = serveconfig.getProperty("serveraddress");
		
		
		
			
        
		
		fetch(client);
		client.stopConnection();
		
		
    }
    
    
    

    
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	
    	
		
		
    	
    	
        txtId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBollard = new javax.swing.JTable();
        btnSave = new javax.swing.JButton();
        btnSave.setBackground(new Color(0, 0, 128));
        btnSave.setForeground(new Color(0, 0, 128));
        btnUpdate = new javax.swing.JButton();
        btnUpdate.setForeground(new Color(0, 0, 128));
        btnUpdate.setBackground(new Color(0, 0, 128));
        btnDelete1 = new javax.swing.JButton();
        btnDelete1.setForeground(new Color(0, 0, 128));
        btnDelete1.setBackground(new Color(0, 0, 128));
        jLabel6 = new javax.swing.JLabel();
        jLabel6.setForeground(new Color(128, 0, 0));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bollard");
        setResizable(false);

        jLabel1.setFont(new Font("Yu Gothic Medium", Font.BOLD, 13)); // NOI18N
        jLabel1.setText("Id");

        jLabel2.setFont(new Font("Yu Gothic Medium", Font.BOLD, 13)); // NOI18N
        jLabel2.setText("isInTheCity");
        
        jLabel6.setFont(new Font("Yu Gothic Medium", Font.BOLD, 16)); // NOI18N
        jLabel6.setText("Cars Setting");

        tblBollard.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"Id", "isInTheCity"
        	}
        ) {
        	Class[] columnTypes = new Class[] {
        		Integer.class, Boolean.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        });
        tblBollard.setCellSelectionEnabled(true);
        tblBollard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStudentsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBollard);
        if (tblBollard.getColumnModel().getColumnCount() > 0) {
            tblBollard.getColumnModel().getColumn(1).setResizable(false);
        }

        btnSave.setText("Save");
        btnSave.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSave.setIconTextGap(0);
        btnSave.setInheritsPopupMenu(true);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
              
					try {
						btnSaveActionPerformed(evt);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					// TODO Auto-generated catch block
					
				
            }
        });
        

        btnUpdate.setText("Update");
        btnUpdate.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
					btnUpdateActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        btnDelete1.setText("Delete");
        btnDelete1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
            		btnDelete1ActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Car Settings");
        
        txtWay = new JTextField();
        txtWay.setColumns(10);
        
        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
				launchIhm launchIhm = new launchIhm();
				launchIhm.getFrame().setVisible(true);
			}
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(78)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
        					.addGap(18)
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        						.addComponent(txtWay, Alignment.LEADING)
        						.addComponent(txtId, Alignment.LEADING, 188, 188, Short.MAX_VALUE))
        					.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE))
        				.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
        					.addGap(33)))
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 376, GroupLayout.PREFERRED_SIZE)
        					.addGap(161))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
        					.addGap(51)
        					.addComponent(btnDelete1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
        					.addGap(185)
        					.addComponent(btnRetour, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap())))
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap(503, Short.MAX_VALUE)
        			.addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
        			.addGap(303))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(36)
        			.addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
        			.addGap(14)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(29)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
        						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
        						.addComponent(txtWay, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(18)
        					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)))
        			.addGap(58)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnDelete1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnRetour, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(73, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) throws IOException {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        Integer id = Integer.parseInt(txtId.getText()); //txtId.getText().trim();
       
        Boolean isInTheCity = Boolean.parseBoolean(txtWay.getText());
        
        
        if (  !isInTheCity.toString().isEmpty()  && !id.toString(id).isEmpty() ) {
            try {
            	CommunicationWithServer client = new CommunicationWithServer();
            	final int SERVER_PORT = Integer.parseInt(serveconfig.getProperty("serverportClient"));
        		final String SERVER_ADDRESS = serveconfig.getProperty("serveraddress");
           		
           	
        		ConvertJSON converter = new ConvertJSON();
            	
            	
            	Request req = new Request();
        		Car car = new Car();
        		/*bollard.setId(id);
        		bollard.setAddress(Address);
        		bollard.setActive(isActive);
        		bollard.setState(state);
        		bollard.setWay(way);*/
        		req.setSource("client");			
        		req.setOperation_type("selectID");
        		req.setTarget("car");
        		req.setObj(id.toString());
        		
        		
        		client.startConnection(SERVER_ADDRESS, SERVER_PORT);
        		
        		Response resp= new Response();
        		
        			
        		resp = client.sendMessage(req);
        		ArrayList<String> info = resp.getValues();
        		
            	
            	
            	
               
                if (info.isEmpty()) {
                	
                	
                    saveUser(id, isInTheCity, client);
                    DefaultTableModel model = (DefaultTableModel) tblBollard.getModel();
                    Object[] row = new Object[2];
                    row[0] = id;
                    row[1] = isInTheCity;
                    model.addRow(row);
                } else {
                    alert("Please provide a different id number", "Similar id found");
                }

                clear();
                client.stopConnection();
            } catch (Exception ex) {
                Logger.getLogger(CrudCar.class.getName()).log(Level.SEVERE, null, ex);
            }finally {
            }
            
        }  //      else if (!id.matches("^[0-9]{8}$")) {
            //       alert("please provide a valid id number", "Wrong id");
          //      } 
        else {
          alert("please fill in all the details");}
        }
        
    //GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) throws IOException {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
    	Integer id = Integer.parseInt(txtId.getText()); //txtId.getText().trim();
        
        Boolean isInTheCity = Boolean.parseBoolean(txtWay.getText());
          
          if (  !isInTheCity.toString().isEmpty()  && !id.toString(id).isEmpty() ) {
              try {
            	  CommunicationWithServer client = new CommunicationWithServer();
              	final int SERVER_PORT = Integer.parseInt(serveconfig.getProperty("serverportClient"));
          		final String SERVER_ADDRESS = serveconfig.getProperty("serveraddress");
             		
             	
             		
             		
             		
          		ConvertJSON converter = new ConvertJSON();
              	
              	
              	Request req = new Request();
          		Car car = new Car();
          		/*bollard.setId(id);
          		bollard.setAddress(Address);
          		bollard.setActive(isActive);
          		bollard.setState(state);
          		bollard.setWay(way);*/
          		req.setSource("client");			
          		req.setOperation_type("selectID");
          		req.setTarget("car");
          		req.setObj(id.toString());
          		
          		
          		client.startConnection(SERVER_ADDRESS, SERVER_PORT);
          		
          		Response resp= new Response();
          		
          			
          		resp = client.sendMessage(req);
          		ArrayList<String> info = resp.getValues();
                if (!info.isEmpty() ) {
                	/*Integer id1 = Integer.parseInt(txtId.getText());
                    String Address1 = txtAddress.getText().trim();
                    Boolean isActive1 = Boolean.parseBoolean(txtisActive.getText()); 
                    Boolean state1 = Boolean.parseBoolean(txtState.getText());
                    Boolean way1 = Boolean.parseBoolean(txtWay.getText());*/
                	
                	
                    update(id, isInTheCity, client);
                    
                    
                    DefaultTableModel model = (DefaultTableModel) tblBollard.getModel();
                    model.setRowCount(0);                   
                    fetch(client);
                    client.stopConnection();
                    alert("Update was successful");
                    
                } else {
                    alert("There is no such Car", "Update error");
                    clear();
                }

            } catch (Exception ex) {
                Logger.getLogger(CrudCar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            alert("There is nothing to update :(","No row selected");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    //set the values of a row to the textfields
    private void tblStudentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStudentsMouseClicked
        // TODO add your handling code here:
        int i = tblBollard.getSelectedRow();
        TableModel model = tblBollard.getModel();
        txtId.setText(model.getValueAt(i, 0).toString());
        
        txtWay.setText(model.getValueAt(i, 1).toString());
    }//GEN-LAST:event_tblStudentsMouseClicked

    //handles delete button action
   private void btnDelete1ActionPerformed(java.awt.event.ActionEvent evt) throws IOException {//GEN-FIRST:event_btnDelete1ActionPerformed
        // TODO add your handling code here:
        int i = tblBollard.getSelectedRow();
        if (i >= 0) {
            int option = JOptionPane.showConfirmDialog(rootPane,
                    "Are you sure you want to Delete?", "Delete confirmation", JOptionPane.YES_NO_OPTION);
            if (option == 0) {
                TableModel model = tblBollard.getModel();

                String id = model.getValueAt(i, 0).toString();
                if (tblBollard.getSelectedRows().length == 1) {
                    delete(id, client);
                    DefaultTableModel model1 = (DefaultTableModel) tblBollard.getModel();
                    model1.setRowCount(0);
                    fetch(client);
                    clear();
                    client.stopConnection();
                }
            }
        } else {
            alert("Please select a row to delete");
        }
    }//GEN-LAST:event_btnDelete1ActionPerformed

    //method to show an info alert
    public void alert(String msg) {
        JOptionPane.showMessageDialog(rootPane, msg);
    }

    //method to show an error alert
    public void alert(String msg, String title) {
        JOptionPane.showMessageDialog(rootPane, msg, title, JOptionPane.ERROR_MESSAGE);
    }

    //method to save user to the db
    public void saveUser(Integer id, Boolean isInTheCity,CommunicationWithServer client ) throws IOException {
       Car car = new Car();
    	final int SERVER_PORT = Integer.parseInt(serveconfig.getProperty("serverportClient"));
		final String SERVER_ADDRESS = serveconfig.getProperty("serveraddress");
		Request req = new Request();
		ConvertJSON converter = new ConvertJSON();
		car.setId(id);
		car.setIsInTheCity( isInTheCity);
		req.setOperation_type("insert");
		req.setTarget("car");
		req.setSource("");
		req.setObj(converter.CarToJson(car));
		
		client.startConnection(SERVER_ADDRESS, SERVER_PORT);

		client.sendMessage(req);
		client.stopConnection();
		System.out.println("ok");
    }

    //update the db
    public void update(Integer id, Boolean isInTheCity,CommunicationWithServer client) throws IOException {
        try {
        	
        	final int SERVER_PORT = Integer.parseInt(serveconfig.getProperty("serverportClient"));
    		final String SERVER_ADDRESS = serveconfig.getProperty("serveraddress");
    		Car car = new Car();
    		Request req = new Request();
    		ConvertJSON converter = new ConvertJSON();
        	
			car.setId(id);
			car.setIsInTheCity( isInTheCity);
        	req.setOperation_type("update");
    		req.setTarget("car");
    		req.setSource("client");
    		req.setObj(converter.CarToJson(car));
    		
    		client.startConnection(SERVER_ADDRESS, SERVER_PORT);

    		client.sendMessage(req);
    		
    		
            
        	
        	
        	
        	
        	
        	
        } catch (Exception ex) {
            Logger.getLogger(CrudCar.class.getName()).log(Level.SEVERE, null, ex);
        }
       fetch(client);
       client.stopConnection();
    }

    //delete details in the db
    public void delete(String id,CommunicationWithServer client) throws IOException {
        try {
        	
        	final int SERVER_PORT = Integer.parseInt(serveconfig.getProperty("serverportClient"));
    		final String SERVER_ADDRESS = serveconfig.getProperty("serveraddress");
    		Car car = new Car();
    		Request req = new Request();
    		ConvertJSON converter = new ConvertJSON();
        	
			car.setId(Integer.parseInt(id));
        	
        	req.setOperation_type("delete");
    		req.setTarget("car");
    		req.setSource("client");
    		req.setObj(converter.CarToJson(car));
    		
    		client.startConnection(SERVER_ADDRESS, SERVER_PORT);

    		client.sendMessage(req);
        	
            
        } catch (Exception ex) {
            Logger.getLogger(CrudCar.class.getName()).log(Level.SEVERE, null, ex);
        }
       fetch( client);
       client.stopConnection();
       
    }

    //method to clear the txt fields
    private void clear() {
        txtId.setText("");
       
        txtWay.setText("");
    }

    //fetch 
       private void fetch( CommunicationWithServer client) throws IOException  {
    	   
           
          
   		PropertiesFileReader serveconfig = new PropertiesFileReader();
   		serveconfig.initServer();
   		
   		

		
		
		
		
		final int SERVER_PORT = Integer.parseInt(serveconfig.getProperty("serverportClient"));
		final String SERVER_ADDRESS = serveconfig.getProperty("serveraddress");
   		
   	
   		
   		
   		
    	
    	
    	bollardinfo.clear();
    	Request req = new Request();
		
		ConvertJSON converter = new ConvertJSON();
		req.setSource("client");			
		req.setOperation_type("selectAll");
		req.setTarget("car");
		
		
		client.startConnection(SERVER_ADDRESS, SERVER_PORT);
		
		
		
		
		Response resp= new Response();
		try {
			
			resp = client.sendMessage(req);
			
			ArrayList<String> databollard = resp.getValues();
			ArrayList<Car> data = new ArrayList<Car>();
			
			for (int i = 0; i < databollard.size(); i++) {
				data.add(converter.JsonToCar(databollard.get(i)));
				
				
			}
			
			
			
			
			System.out.println("ok");
			
			
			
		
				
			DefaultTableModel model = (DefaultTableModel) tblBollard.getModel();
			for (int i =0 ; i< data.size();i++ ) {
				
			
			
				 Object[] row = new Object[2];
		            row[0] = data.get(i).getId();
		            
		            row[1] = data.get(i).getIsInTheCity();
		            model.addRow(row);
			}
			
		}
	            
	            
			 /*for (RetractableBollard bollard : bollardinfo) {
            Object[] row = new Object[5];
            row[0] = bollard.getId();
            row[1] = bollard.getAddress();
            row[2] = bollard.isActive();
            row[3] = bollard.isState();
            row[4] = bollard.isWay();
            
            model.addRow(row);
            }*/
			
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		}
    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CrudCar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrudCar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrudCar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrudCar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
				new CrudCar().setVisible(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete1;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBollard;
    private javax.swing.JTextField txtId;
    private JTextField txtWay;
	private Logger lOGGER;
}

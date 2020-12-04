package business;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;





import common.ConvertJSON;





import common.Request;
import common.Response;
import common.business.RetractableBollard;
import common.business.VehicleSensor;
public class RequestSensor {
	
	private SensorCommunication communication ; 
	private ConvertJSON converter = new ConvertJSON();
	public RequestSensor(SensorCommunication communication) {
		this.communication = communication;
	}

	
	
	/**
	 * M�thode qui permet de r�cup�rer les configurations �tablient sur bornes
	 * Renvoie un Objet de type retractablebollard
	 */
	public  VehicleSensor getSensor() { 
		Request req = new Request();
		req.setOperation_type("select");
		req.setSource("sensor");
		req.setTarget("vehiclesensor");
			
		try {
			communication.startConnection(communication.getADDRESS(), communication.getPORTAQS());
			Response resp = communication.sendMessage(req);
			VehicleSensor parameter = converter.JsontoVehicleSensor(resp.getValues().get(resp.getValues().size() - 1));
			
			communication.stopConnection();
			return parameter;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	public  RetractableBollard getBollard() { 
		Request req = new Request();
		req.setOperation_type("select");
		req.setSource("sensor");
		req.setTarget("retractablesbollard");
			
		try {
			communication.startConnection(communication.getADDRESS(), communication.getPORTAQS());
			Response resp = communication.sendMessage(req);
			RetractableBollard parameter = converter.JsonToBollard(resp.getValues().get(resp.getValues().size() - 1));
			
			communication.stopConnection();
			return parameter;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	// merthode qui renvoie objet confignbcar donnant le nombre de voiture autoris� dans la ville 
	

	
	

	

				
		
			}
			
		
	
	
	
	
	
		
		
		
		
		
		
		
		
		
		
		
		

	
	
		
	
	
	
		


	
	



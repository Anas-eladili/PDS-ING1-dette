package business;

import java.awt.List;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import business.RequestSensor;

import java.util.* ;


import common.*;
import common.business.Car;
import common.business.RetractableBollard;
import common.business.VehicleSensor;
import client.*;

import connection.PropertiesFileReader;


public class SensorOperation {
	
	private RetractableBollard bollard ;
	private RequestSensor request;
	private VehicleSensor sensor; 
	private PropertiesFileReader file = new PropertiesFileReader();
	private int nbVehicule; 
	private Connection connection;
	private ConvertJSON converter = new ConvertJSON();
	CommunicationWithServer communication ;
	
	

	
	public synchronized void carentry(Car car,CommunicationWithServer communication ) throws IOException {
		 Request request3= new Request();
		 car.setIsInTheCity(true);
		 request3.setOperation_type("insert");
		 request3.setTarget("car"); 
		 request3.setSource("client");  
		 
		 request3.setObj(converter.CarToJson(car));
		 communication.sendMessage(request3);
		 System.out.println("car added successfully");
		 }
	
	public SensorOperation(CommunicationWithServer communication) {
		super();
		this.communication = communication;
	}

	public synchronized void carexit(Car car ,CommunicationWithServer communication) throws IOException {
		 Request request3= new Request();
		 request3.setOperation_type("delete");
		 request3.setTarget("car"); 
		 request3.setSource("client");  
		 
		 request3.setObj(converter.CarToJson(car));
		 communication.sendMessage(request3);
		 System.out.println("car exit successfully");
		}
	public int nbmax(CommunicationWithServer communication) throws IOException {
		Request request1= new Request();
		 request1.setOperation_type("selectnbmax");
		 request1.setTarget("infotraffic"); 
		 request1.setSource("client");
		 
		
		//ArrayList<String> list = communication.sendMessage(request1).getValues();
		
		int nb=Integer.parseInt(communication.sendMessage(request1).getValues().get(0));  
		return nb ;
		}
	public int actualcarnb (CommunicationWithServer communication) throws IOException
	{
		Request request2= new Request();
		 request2.setOperation_type("selectincity");
		 request2.setTarget("car"); 
		 request2.setSource("client");                                                //code pour savoir nb de vehicule 
		 communication.sendMessage(request2);
		 int i = communication.sendMessage(request2).getValues().size();
		 return i ;
		
		
	}
	public Boolean trafficalert(CommunicationWithServer communication) throws IOException
	{Request request3= new Request();
	 request3.setOperation_type("selectalert");
	 request3.setTarget("infotraffic"); 
	 request3.setSource("client");                                               //code pour savoir s'il y a alert ou pas 
	 ArrayList<String> list = communication.sendMessage(request3).getValues();
	 
	 boolean bool1=Boolean.parseBoolean(list.get(0)); 
	 return bool1 ; }
		
		
		
		
		
		public synchronized void start(RetractableBollard bollard , Car car ,CommunicationWithServer communication) throws InterruptedException {
	
		
		

		try {
			
			
			Request request = new Request();
			request.setSource("client");			
			request.setOperation_type("selectID");
			request.setTarget("car");
			request.setObj(Integer.toString(car.getId()));
    		
    		
    		//client.startConnection(SERVER_ADDRESS, SERVER_PORT);
    		
    		Response resp= new Response();
    		
    			
    		resp = communication.sendMessage(request);
    		ArrayList<String> info = resp.getValues();
    		 if (info.isEmpty()) { 
    			 System.out.println("this car isn't registred in the city");
    			 
    		 }else {
		
    		
    		boolean end = true;
			while (end== true) {
				
				if (bollard.isWay()== false && car.getIsInTheCity()==true ) {	// part that treat the cars that go out the city 
					Thread.sleep(5000);
					bollard.setState(true);					
					carexit(car,communication); 
					System.out.println("the car got out of the city successfully");
					bollard.setState(false);
					end= false ;
				}else {
					//code that give the max number of vehicle permited in the city 
					int nbmaxcar = nbmax(communication);
					int actualcarnb = actualcarnb(communication);
					boolean alert = trafficalert(communication);
					if(actualcarnb < nbmaxcar && alert == false) {
						bollard.setState(true);	
						System.out.println("bollard is open right now");
						Request req = new Request();
						car.setIsInTheCity(true);
						req.setObj(converter.CarToJson(car));
						req.setOperation_type("update");
						req.setTarget("car");
						req.setSource("client");
						
						communication.sendMessage(req); 
						System.out.println("vehicle added to the city"); 
						bollard.setState(false);	
						}
					
					else {
						
						System.out.println("vehicle not added to the city "); 
						
						
					        } 
					end = false ;
					}
				
			}
    		 }
						
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
	



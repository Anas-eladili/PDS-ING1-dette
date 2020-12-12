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
import common.business.infotraffic;
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
		 request3.setOperation_type("update");
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
		 request2.setOperation_type("selectnbactuel");
		 request2.setTarget("infotraffic"); 
		 request2.setSource("client");                                                //code pour savoir nb de vehicule 
		 communication.sendMessage(request2);
		 int nb=Integer.parseInt(communication.sendMessage(request2).getValues().get(0)); 
		 return nb ;
		
		
	}
	public void addcar (CommunicationWithServer communication) throws IOException
	{   Response resp = new Response();
		Request request2= new Request();
		 request2.setOperation_type("select");
		 request2.setTarget("infotraffic"); 
		 request2.setSource("client");  //code pour savoir nb de vehicule 
		// request2.setObj(converter.infotrafficToJson(info));
		 resp = communication.sendMessage(request2);
		 infotraffic info = new infotraffic();
		 
			
			ArrayList<String> databollard = resp.getValues();
 			ArrayList<infotraffic> data = new ArrayList<infotraffic>();  

			for (int i = 0; i < databollard.size(); i++) {
				data.add(converter.JsonToinfotraffic(databollard.get(i)));

			}
			for (int i = 0 ; i < data.size() ; i++) {

				
				info.setId(data.get(i).getId());
				info.setAlert(data.get(i).getAlert());
				info.setNbmaxcar(data.get(i).getNbmaxcar());
				info.setNbactuel(data.get(i).getNbactuel());
				
				
			}

		int K = actualcarnb(communication);
		int L = K+1 ;
		
		info.setNbactuel(L);
		
		
		 request2.setOperation_type("update");
		 request2.setTarget("infotraffic"); 
		 request2.setSource("client");  //code pour savoir nb de vehicule 
		 request2.setObj(converter.infotrafficToJson(info));
		 
		 communication.sendMessage(request2);
		 
		}
	
	public void deletecar (CommunicationWithServer communication) throws IOException
	{  Response resp = new Response();
	Request request2= new Request();
	 request2.setOperation_type("select");
	 request2.setTarget("infotraffic"); 
	 request2.setSource("client");  //code pour savoir nb de vehicule 
	// request2.setObj(converter.infotrafficToJson(info));
	 resp = communication.sendMessage(request2);
	 infotraffic info = new infotraffic();
	 
		
		ArrayList<String> databollard = resp.getValues();
		ArrayList<infotraffic> data = new ArrayList<infotraffic>();  

		for (int i = 0; i < databollard.size(); i++) {
			data.add(converter.JsonToinfotraffic(databollard.get(i)));

		}
		for (int i = 0 ; i < data.size() ; i++) {

			
			info.setId(data.get(i).getId());
			info.setAlert(data.get(i).getAlert());
			info.setNbmaxcar(data.get(i).getNbmaxcar());
			info.setNbactuel(data.get(i).getNbactuel());
			
			
		}

	int K = actualcarnb(communication);
	int L = K-1 ;
	
	info.setNbactuel(L);
	
	
	 request2.setOperation_type("update");
	 request2.setTarget("infotraffic"); 
	 request2.setSource("client");  //code pour savoir nb de vehicule 
	 request2.setObj(converter.infotrafficToJson(info));
	 
	 communication.sendMessage(request2);
		}
	
	public Boolean trafficalert(CommunicationWithServer communication) throws IOException
	{
		Request request3= new Request();
	 request3.setOperation_type("selectalert");
	 request3.setTarget("infotraffic"); 
	 request3.setSource("client");                                               //code pour savoir s'il y a alert ou pas 
	 ArrayList<String> list = communication.sendMessage(request3).getValues();
	 
	 boolean bool1=Boolean.parseBoolean(list.get(0)); 
	 return bool1 ; }
		
		
		
		
		
		public synchronized void start(RetractableBollard bollard ,CommunicationWithServer communication, int m) throws InterruptedException {
	
		
		

		try {
		 		
		for (int i= 0 ; i < m; i++) {
    	    
    		boolean end = true;
			while (end== true) {
				
				if (bollard.isWay()== false   ) {	// part that treat the cars that go out the city 
					Thread.sleep(5000);
					bollard.setState(true);					
					deletecar(communication); 
					System.out.println("the car got out of the city successfully");
					bollard.setState(false);
					end= false ;
				}else {
					//code that give the max number of vehicle permited in the city ;
					int nbmaxcar = nbmax(communication);
					int nbactuel = actualcarnb(communication);
					boolean alert = trafficalert(communication);
					if(nbactuel < nbmaxcar && alert == false) {
						bollard.setState(true);	
						//System.out.println("bollard is open right now");
						Request req = new Request();
						addcar(communication);
						System.out.println("vehicle added to the city and there's now  " +(nbactuel+1)+ "  vehicle in the city" ); 
						bollard.setState(false);	
						Thread.sleep(5000);
						}
					
					else if ( nbactuel >= nbmaxcar) {
					
						Thread.sleep(5000);
						System.out.println("Max vehicle number capacity "); 
						
						
					        }
					else if ( alert == true ) {
								
						Thread.sleep(5000);
								System.out.println("Alert pollution "); 
								
								
							        } 
					end = false ;
					}
				
			}
    		 
						
			} }catch (IOException e) {
				e.printStackTrace();
			}
		}
}

	



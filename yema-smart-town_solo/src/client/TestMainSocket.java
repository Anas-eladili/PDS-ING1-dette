package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import business.SensorOperation;
import common.*;
import common.ConvertJSON;
import common.Request;
import common.business.Car;
import common.business.RetractableBollard;
import common.business.VehicleSensor;
import common.business.infotraffic;

import connection.ConnectionPool;
import connection.PropertiesFileReader;

public class TestMainSocket {
	 
	@SuppressWarnings("resource")
	public static void main(String[] args) throws InterruptedException, IOException {
		
		Logger LOGGER = Logger.getLogger(TestMainSocket.class.getName());
		PropertiesFileReader serveconfig = new PropertiesFileReader();
		serveconfig.initServer();
		final int SERVER_PORT = Integer.parseInt(serveconfig.getProperty("serverportClient"));
		final String SERVER_ADDRESS = serveconfig.getProperty("serveraddress");
		CommunicationWithServer client = new CommunicationWithServer();
		client.startConnection(SERVER_ADDRESS, SERVER_PORT);
		
		try {
			
			System.out.println("\n");
			LOGGER.log(Level.INFO, "*** Client Program Beginning ***");
			System.out.println("\n");
			ConvertJSON converter = new ConvertJSON();
			
			/*while(pool.connections.isEmpty()) {
				System.out.println( " wait ");}*/
						
			
			
			
			
			
			
			Request req = new Request();
			
			Scanner enter = new Scanner(System.in);
			Scanner enter2 = new Scanner(System.in);
			Scanner enterName = new Scanner(System.in);
			Scanner enterFin = new Scanner(System.in);
			
			Scanner enter3 = new Scanner(System.in);
			Scanner enter4 = new Scanner(System.in);
			Scanner enter5 = new Scanner(System.in);
			
			Scanner enter6 = new Scanner(System.in);
			Scanner enter7 = new Scanner(System.in);
		
			

		
			
			System.out.println("********************************************************************************");
			System.out.println("************************* WELCOME TO CLIENT MENU *******************************");
			System.out.println("********************************************************************************");
			
			boolean fin = false;
			System.out.print("Please, choose from the menu -> "); String user = enterName.nextLine();
			

			while (!fin) {
				System.out.println(user + ". What do you want to do ? ");
				
				System.out.println("Bollard settings >> 1");
				System.out.println("Sensors setting >> 2 ");
				System.out.println("simulation >> 3 ");
				System.out.println("traffic settings >> 4 ");

				String rep1 = enter.nextLine();
				{
				if (rep1.equals("1")) {
					System.out.println("Select Bollard -> 1");
					System.out.println("Insert Bollard -> 2");
					System.out.println("Update Bollard -> 3");
					System.out.println("Delete Bollard -> 4");

					String rep2 = enter2.nextLine();
				
					if (rep2.equals("1")) {
						System.out.println("Fine, you chose SELECT, this is Users query");
						req.setSource("client");			
						req.setOperation_type("select");
						req.setTarget("retractablebollard");
						
				
						client.sendMessage(req);
						System.out.println("ok");
										
					} else if (rep2.equals("2")) {
						System.out.println(" you chose INSERT, please give us the following information :");
						int id; String address; boolean isActive;boolean state;boolean way;
						RetractableBollard bollard  = new RetractableBollard();
						
						System.out.println("id : ");
						id = enter3.nextInt();
						bollard.setId(id);					
						
						
						System.out.println("address : ");
						address = enter4.nextLine();
						bollard.setAddress(address);
						
						System.out.println("isActive : ");
						isActive = enter5.nextBoolean();
						bollard.setActive(isActive);
						
						System.out.println("state : ");
						state =  enter6.nextBoolean();
						bollard.setState(state);
						
						System.out.println("way :");
						way =  enter7.nextBoolean();
						bollard.setWay(way);
						
							
						req.setOperation_type("insert");
						req.setTarget("retractablebollard");
						req.setSource("");
						req.setObj(converter.BollardToJson(bollard));
						

						client.sendMessage(req);
						System.out.println("ok");
						
					} else if (rep2.equals("3")) {
						System.out.println("Fine, you chose UPDATE, please give us the following information :");
                         RetractableBollard bollard = new RetractableBollard();
                         int id; String address; boolean isActive;boolean state;boolean way;
                         
						System.out.println("id : ");
						id = enter3.nextInt();
						bollard.setId(id);					
						
						
						System.out.println("address : ");
						address = enter4.nextLine();
						bollard.setAddress(address);
						
						System.out.println("isActive : ");
						isActive = enter5.nextBoolean();
						bollard.setActive(isActive);
						
						System.out.println("state : ");
						state =  enter6.nextBoolean();
						bollard.setState(state);
						
						System.out.println("way :");
						way =  enter7.nextBoolean();
						bollard.setWay(way);
						
							
						req.setOperation_type("update");
						req.setTarget("retractablebollard");
						req.setObj(converter.BollardToJson(bollard));
						client.sendMessage(req);
						System.out.println("ok");
						 
					} else if (rep2.equals("4")) {
						System.out.println("Fine, you chose DELETE, please give us the following information :");
                        RetractableBollard bollard = new RetractableBollard();
                        int id; 
                        System.out.println("id : ");
						id = enter3.nextInt();
						bollard.setId(id);	
						
						
						req.setOperation_type("delete");
						req.setTarget("retractablebollard");
						req.setObj(converter.BollardToJson(bollard));
						client.sendMessage(req);
						System.out.println("ok");
						
                        
                        
						
					}
					
					
									
				} else if (rep1.equals("2")) {
					
					
					System.out.println("Select Sensor -> 1");
					System.out.println("Insert Sensor -> 2");
					System.out.println("Update Sensor -> 3");
					System.out.println("Delete Sensor -> 4");

					String rep2 = enter2.nextLine();
				
					if (rep2.equals("1")) {
						System.out.println(" you chose SELECT ");
						req.setSource("client");			
						req.setOperation_type("select");
						req.setTarget("vehiclesensor");
						
				
						client.sendMessage(req);
						System.out.println("ok");
										
					} else if (rep2.equals("2")) {
						System.out.println(" you chose INSERT, please give us the following information :");
						int id; String address; boolean isActive;
						VehicleSensor sensor = new VehicleSensor();
						
						System.out.println("id : ");
						id = enter3.nextInt();
						sensor.setId(id);					
						
						
						System.out.println("address : ");
						address = enter4.nextLine();
						sensor.setAddress(address);
						
						System.out.println("isActive : ");
						isActive = enter5.nextBoolean();
						sensor.setActive(isActive);
						
						
						
							
						req.setOperation_type("insert");
						req.setTarget("vehiclesensor");
						req.setObj(converter.VehicleSensortoJson(sensor));
						

						client.sendMessage(req);
						System.out.println("ok");
						
					} else if (rep2.equals("3")) {
						System.out.println("Fine, you chose UPDATE, please give us the following information :");
						VehicleSensor sensor = new VehicleSensor();
                         int id; String address; boolean isActive;
                         
						System.out.println("id : ");
						id = enter3.nextInt();
						sensor.setId(id);					
						
						
						System.out.println("address : ");
						address = enter4.nextLine();
						sensor.setAddress(address);
						
						System.out.println("isActive : ");
						isActive = enter5.nextBoolean();
						sensor.setActive(isActive);
						
						
							
						req.setOperation_type("update");
						req.setTarget("vehiclesensor");
                        req.setObj(converter.VehicleSensortoJson(sensor));
						

						client.sendMessage(req);
						System.out.println("ok");
						
						 
					} else if (rep2.equals("4")) {
						System.out.println("Fine, you chose DELETE, please give us the following information :");
						VehicleSensor sensor = new VehicleSensor();
                        int id; 
                        System.out.println("id : ");
						id = enter3.nextInt();
						sensor.setId(id);	
						
						
						req.setOperation_type("delete");
						req.setTarget("retractablebollard");
						req.setObj(converter.VehicleSensortoJson(sensor));
						
						client.sendMessage(req);
						System.out.println("ok");
                        
                        
						
					}
					
				} else if (rep1.equals("3")) {
					System.out.println("run the simulation  -> 1");
					String rep2 = enter2.nextLine();
					
					if (rep2.equals("1")) {
						 System.out.println(" simulation running ");
						 int id ; Boolean way ;
						 RetractableBollard bollard = new RetractableBollard();
						 System.out.println("way : ");
						 way = enter5.nextBoolean();
						 bollard.setWay(way);
						 
						 
						 bollard.setId(1);
						 SensorOperation operation =new  SensorOperation(null);
						 Car car =new Car();
						 System.out.println("id : ");
						id = enter3.nextInt();
						car.setId(id);
						 car.setId(11);
						 car.setIsInTheCity(false);
						 operation.start(bollard,car,client);
						 System.out.println("good");}
					
					
					 
				} else if (rep1.equals("4")) {
					System.out.println("Select max car number -> 1");
					System.out.println("Insert  -> 2");
					System.out.println("Update  -> 3");
					System.out.println("Delete  -> 4");
					System.out.println("Show if there's alert  -> 5");

					String rep2 = enter2.nextLine();
				
					if (rep2.equals("1")) {
						System.out.println(" you chose SELECT max car number ");
						Request request1= new Request();
						 request1.setOperation_type("selectnbmax");
						 request1.setTarget("infotraffic"); 
						 request1.setSource("client");
						 
						
						ArrayList<String> list = client.sendMessage(request1).getValues();
						System.out.println(list.get(0));
						
										
					} else if (rep2.equals("2")) {
						System.out.println(" you chose INSERT, please fill up the informations you want :");
						int id; int nbmaxcar;
						boolean alert;
						infotraffic info = new infotraffic();						
						System.out.println("id : ");
						id = enter3.nextInt();
						info.setId(id);					
						
						
						System.out.println("alert : ");
						alert = enter4.nextBoolean();
						info.setAlert(alert);
						
						System.out.println("nbmaxcar : ");
						nbmaxcar = enter5.nextInt();
						info.setNbmaxcar(nbmaxcar);
						
						
						
							
						req.setOperation_type("insert");
						req.setTarget("infotraffic");
						req.setObj(converter.infotrafficToJson(info));
						

						client.sendMessage(req);
						System.out.println("ok");
						
					} else if (rep2.equals("3")) {
						System.out.println("Fine, you chose UPDATE, please give us the following information :");
						int id; int nbmaxcar; boolean alert;
						infotraffic info = new infotraffic();						
						System.out.println("id : ");
						id = enter3.nextInt();
						info.setId(id);					
						
						
						System.out.println("alert : ");
						alert = enter4.nextBoolean();
						info.setAlert(alert);
						
						System.out.println("nbmaxcar : ");
						nbmaxcar = enter5.nextInt();
						info.setNbmaxcar(nbmaxcar);
						
						
							
						req.setOperation_type("update");
						req.setTarget("infotraffic");
						req.setObj(converter.infotrafficToJson(info));
						

						client.sendMessage(req);
						System.out.println("ok");
						
						 
					} else if (rep2.equals("4")) {
						System.out.println("Fine, you chose DELETE, please give us the following information :");
						infotraffic info = new infotraffic();
                        int id; 
                        System.out.println("id : ");
						id = enter3.nextInt();
						info.setId(id);	
						
						
						req.setOperation_type("delete");
						req.setTarget("infotraffic");
						req.setObj(converter.infotrafficToJson(info));
						client.sendMessage(req);
						System.out.println("ok");
						
                        
                        
						
					} else if (rep2.equals("5")) {
						 Request request3= new Request();
						 request3.setOperation_type("selectalert");
						 request3.setTarget("infotraffic"); 
						 request3.setSource("client");                                               //code pour savoir alert ou pas 
						 ArrayList<String> list = client.sendMessage(request3).getValues();
						 System.out.println(list.get(0));
					}
					
					
				}}
				
				System.out.println("Are you finished ? Y/N");
				String r = enterFin.nextLine();
				
				if (r.equals("Y")) {
					fin = true;
					
					
					client.stopConnection();
				}
			}	
						
		} catch (IOException e) {
			e.printStackTrace();
		}	
		System.out.println("\n\n");
		LOGGER.log(Level.INFO, "*** End client program ***");
	}
}

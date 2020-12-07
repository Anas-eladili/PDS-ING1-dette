package tests;



import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import business.SensorOperation;
import client.ClientCommunication;
import client.CommunicationWithServer;
import common.*;
import common.ConvertJSON;
import common.Request;
import common.business.Car;
import common.business.RetractableBollard;
import connection.PropertiesFileReader;


public class TestBollard {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		Logger LOGGER = Logger.getLogger(TestBollard.class.getName());
		PropertiesFileReader serveconfig = new PropertiesFileReader();
		serveconfig.initServer();
		
		final int SERVER_PORT = Integer.parseInt(serveconfig.getProperty("serverportClient"));
		final String SERVER_ADDRESS = serveconfig.getProperty("serveraddress");
		
		 CommunicationWithServer client = new CommunicationWithServer();
		
			client.startConnection(SERVER_ADDRESS, SERVER_PORT);
		
			// TODO Auto-generated catch block
			
			LOGGER.log(Level.WARNING, "Erreur de connexion client");
		
		Yaml yaml = new Yaml(new Constructor(RetractableBollard.class));
		
		InputStream inputStream = yaml.getClass().getClassLoader().getResourceAsStream("ressources/TestBollard.yaml");
		RetractableBollard bollard  =  (RetractableBollard) yaml.load(inputStream);
		ConvertJSON converter = new ConvertJSON();
		
		 
		 bollard.setState(false);
		 bollard.setId(1);
		 SensorOperation operation =new  SensorOperation(client);
		 Car car =new Car();
		 car.setId(10);
		 car.setIsInTheCity(false);
		 operation.start(bollard,car,client);
		 System.out.println("good");
		
		
		
	
		

	
	
	      } 
	}

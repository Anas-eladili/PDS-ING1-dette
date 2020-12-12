package tests;



import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import client.ClientCommunication;
import common.*;
import common.ConvertJSON;

import common.Request;
import common.YamlFileReader;
import common.business.Car;
import common.business.infotraffic;
import connection.PropertiesFileReader;

public class testAddCar {
	
	public static void main(String[] args) throws IOException {
		
		Logger LOGGER = Logger.getLogger(testAddCar.class.getName()); 
		PropertiesFileReader serveconfig = new PropertiesFileReader();
		serveconfig.initServer();
		YamlFileReader simul = new YamlFileReader();
		final int SERVER_PORT = Integer.parseInt(serveconfig.getProperty("serverportClient"));
		final String SERVER_ADDRESS = serveconfig.getProperty("serveraddress");
		
		ClientCommunication client = new ClientCommunication();
		try {
			client.startConnection(SERVER_ADDRESS, SERVER_PORT);
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			LOGGER.log(Level.WARNING, "Erreur de connexion client");
		}
		/*Yaml yaml = new Yaml(new Constructor(Car.class));
	
		InputStream inputStream = yaml.getClass().getClassLoader().getResourceAsStream("ressources/AddCartest.yaml");
		Car car = (Car) yaml.load(inputStream);*/
		ConvertJSON converter = new ConvertJSON();
		infotraffic info = new infotraffic();
		info.setId(1);
		info.setNbactuel(3);
		
		Request req = new Request();
		req.setObj(converter.infotrafficToJson(info));
		
		
		
		
		req.setOperation_type("updatenb");
		req.setTarget("infotraffic");
		req.setSource("clienttest");
		
		client.sendMessage(req); 
		System.out.println("Ok"); 
		
		
	}

}


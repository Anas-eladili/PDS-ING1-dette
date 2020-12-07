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
import connection.PropertiesFileReader;

public class CountCartest {
	
	public static void main(String[] args) throws IOException {
		
		Logger LOGGER = Logger.getLogger(testAddCar.class.getName());
		PropertiesFileReader serveconfig = new PropertiesFileReader();
		serveconfig.initServer();
		
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
		Request request2= new Request();
		 request2.setOperation_type("selectincity");
		 request2.setTarget("car"); 
		 request2.setSource("client");                                                //code pour savoir nb de vehicule 
		 
		 int i = client.sendMessageresp(request2).getValues().size();
		 System.out.println(i);
		System.out.println("Ok"); 
		
		
	}
	}


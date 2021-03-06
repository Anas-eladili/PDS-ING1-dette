package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import common.ConvertJSON;
import common.Request;
import common.Response;
import connection.PropertiesFileReader;


public class CommunicationWithServer {
	private Socket AQSSocket;
	private PrintWriter out;
	private BufferedReader in = null;
	private ConvertJSON converter;
	
	private int SERVER_PORT_AQS;
	private int SERVER_PORT_CLIENT;
	private String SERVER_ADDRESS;
	private PropertiesFileReader serveconfig = new PropertiesFileReader();
	
	public CommunicationWithServer() {
		serveconfig.initServer();
		//SERVER_PORT_AQS = Integer.parseInt(serveconfig.getProperty("serverportAQS"));
		SERVER_PORT_CLIENT = Integer.parseInt(serveconfig.getProperty("serverportClient"));
		SERVER_ADDRESS = serveconfig.getProperty("serveraddress");
	}
	
	public int getPORTAQS() {
		return SERVER_PORT_AQS;
	}

	public int getPORTClient() {
		return SERVER_PORT_CLIENT;
	}
	
	public String getADDRESS() {
		return SERVER_ADDRESS;
	}
	

	public void startConnection(String ip, int port) throws IOException {
		AQSSocket = new Socket(ip, port);
		out = new PrintWriter(AQSSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(AQSSocket.getInputStream()));
		converter = new ConvertJSON();
	}
	
/**
 * sendMessage send the request of the client to the server
 * First, 'req' is translate into a JSON stream thanks to the converter object (ref. ConvertJSON)
 * Secondly, this stream is sending
 * Then, 'resp' is the response of the server, it is converting too, to have an new object : Response
 * To finish, sendMessage display this object 
 */
	public Response sendMessage(Request req) throws IOException {
		
		String jsonRequest = converter.RequestToJson(req); 
		
		out.println(jsonRequest); 
		
		String jsonResponse = in.readLine();
		
		Response resp = converter.JsontoResponse(jsonResponse);
		
		return resp;
	}
	
/**
 * stopConnection is called when aqs doesn't have request anymore
 * connection between server and aqs is closed
 */
	
	public void stopConnection() throws IOException {
		System.out.println("close");
		in.close();
		out.close();
		AQSSocket.close();
	}
}

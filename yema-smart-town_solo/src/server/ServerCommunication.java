package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import com.fasterxml.jackson.databind.ObjectMapper;

import common.ConvertJSON;
import common.Request;
import common.Response;
import connection.DataSource;


public class ServerCommunication {
	protected static DataSource source;

	/**
	 * start establish the connection with clients and sensors
	 * The server is pending a new client or sensor with two threads (for each port)
	 */
	public void start(int portClient, int portSensor) throws IOException {
		source = new DataSource();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException ex) {}
		new ThreadClient(portClient).start();
		new ThreadSensor(portSensor).start();
	}
	@SuppressWarnings("deprecation")
	public void stop(int portClient, int portSensor) throws IOException {
		source = new DataSource();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException ex) {}
		
		

		
	}
	

	/**
	 * 
	 * @author anas
	 * this thread create a new socket which is pending a new client on the client port
	 */
	private static class ThreadClient extends Thread {
		private ServerSocket serverSocketClient;
		private int portClient;

		public ThreadClient(int portClient) {
			this.portClient = portClient;
		}
		public void run() {
			try {
				serverSocketClient = new ServerSocket(portClient);
				System.out.println("Serveur à l'écoute des clients");

				while (true) {
					new CommonThread(serverSocketClient.accept(), source).start();
					
				}
			} catch (IOException | InterruptedException e1) {
				source.closeAllConnection();
				
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @author anas
	 * this thread create a new socket which is pending a new sensor on the sensor port
	 */
	private static class ThreadSensor extends Thread {
		private ServerSocket serverSocketSensor;
		private int portSensor;

		public ThreadSensor(int portSensor) {
			this.portSensor = portSensor;
		}

		public void run() {
			try {
				serverSocketSensor = new ServerSocket(portSensor);
				System.out.println("Serveur à l'écoute des capteurs");
				while (true) {
					new CommonThread(serverSocketSensor.accept(), source).start();
				} 
			} catch (IOException | InterruptedException e) {
				source.closeAllConnection();
				e.printStackTrace();
			}
		}
	}


	/**
	 * this static class CommomThread allow to the server to treat several client or sensor requests
	 * to each client or sensor connection, a thread is starting
	 */
	public static class CommonThread extends Thread {
		private Socket clientSocket;
		private PrintWriter out;
		private BufferedReader in;
		private AtomicBoolean running = new AtomicBoolean(false);
		private Connection connection;
		private Factory factory = new Factory();
		private Boolean flag = false ;

		private ConvertJSON converter = new ConvertJSON();
		private Request req = new Request();

		public CommonThread(Socket socket, DataSource source) throws InterruptedException  {
			this.clientSocket = socket;
			
			
			
			
			
			//System.out.println("test");
		} 

		/**
		 * close properly the thread when run is finish
		 */
		public void interrupt() {
			running.set(false);
			
			
			
		}

		/**
		 * run establish the request treatment 
		 * for common operation, we use factory method in order to minimize the size of this class
		 * run sends the response to the client, it uses converter object to do the conversion between json-request and json-response
		 */
		@SuppressWarnings({ "static-access", "unchecked" })
		public void run()  {
			running.set(true);
			connection = source.giveConnection();
			if(connection== null) {
			while( connection == null) {
				try {
					sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			notifyAll();
			connection = source.giveConnection();}
			while (running.get()) {
				
				try {	
					ObjectMapper mapper = new ObjectMapper();
					out = new PrintWriter(clientSocket.getOutputStream(), true);
					in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					String jsonRequest;

					while ((jsonRequest = in.readLine()) != null) {		
						


						
						String jsonResponse;
						System.out.println("\n");

                          	if (true) {
                          		
							Request req = converter.JsontoRequest(jsonRequest);
							Response resp = new Response();
							System.out.println("\n");
							System.out.println("Treatment of " + req.getSource() + " for a " + req.getOperation_type() + " request \n request : " + jsonRequest);


							if (req.getOperation_type().equals("insert")) {	
								boolean result = factory.getData(req.getTarget()).insert(req.getObj(), connection);
								resp.setResponse_type("insert");
								resp.setResponse_state(result);
								jsonResponse = converter.ResponseToJson(resp); System.out.println(jsonResponse);

								out.println(jsonResponse);
							}

							if (req.getOperation_type().equals("delete")) {	
								boolean result = factory.getData(req.getTarget()).delete(req.getObj(), connection);
								resp.setResponse_type("delete");
								resp.setResponse_state(result);

								jsonResponse = converter.ResponseToJson(resp); System.out.println(jsonResponse);
								out.println(jsonResponse);
							}

							if (req.getOperation_type().equals("update")) {
								boolean result = factory.getData(req.getTarget()).update(req.getObj() , connection);
								resp.setResponse_type("update");
								resp.setResponse_state(result);

								jsonResponse = converter.ResponseToJson(resp); System.out.println(jsonResponse);
								out.println(jsonResponse);
							}

							if (req.getOperation_type().equals("select")) {		
								ArrayList<String> result = factory.getData(req.getTarget()).select(connection);
								resp.setResponse_type("select");
								resp.setResponse_state(true);
								resp.setValues(result);

								jsonResponse = converter.ResponseToJson(resp);  System.out.println(jsonResponse);
								out.println(jsonResponse);
							}

							if (req.getOperation_type().equals("selectID")) {
								ArrayList<String> result = factory.getData(req.getTarget()).selectID(req.getObj(), connection); 
								resp.setResponse_type("selectID");
								resp.setResponse_state(true);
								resp.setValues(result);

								jsonResponse = converter.ResponseToJson(resp); System.out.println(jsonResponse);
								out.println(jsonResponse);
								
							}
							if (req.getOperation_type().equals("selectAll")) {
								ArrayList<String> result = factory.getData(req.getTarget()).selectAll(connection); 
								resp.setResponse_type("selectAll");
								resp.setResponse_state(true);
								resp.setValues(result);

								jsonResponse = converter.ResponseToJson(resp); System.out.println(jsonResponse);
								out.println(jsonResponse);
							}
							if (req.getOperation_type().equals("selectalert")) {
								infotrafficDAO dao = new infotrafficDAO();
								ArrayList<String> result = dao.selectalert(connection);
								resp.setResponse_type("selectalert");
								resp.setResponse_state(true);
								resp.setValues(result);

								jsonResponse = converter.ResponseToJson(resp); System.out.println(jsonResponse);
								out.println(jsonResponse);
							
							
						}if (req.getOperation_type().equals("selectnbmax")) {
							infotrafficDAO dao = new infotrafficDAO();
							ArrayList<String> result = dao.selectnbmax(connection);
							resp.setResponse_type("selectnbmax");
							resp.setResponse_state(true);
							resp.setValues(result);

							jsonResponse = converter.ResponseToJson(resp); System.out.println(jsonResponse);
							out.println(jsonResponse);
						}


							
							
						}


                								}
					System.out.println("------ END of communication -------");
					
					;


					in.close();
					out.close();
					clientSocket.close();
					
					this.currentThread().interrupt(); 
					System.out.println("Thread was interrupted");
				} catch (IOException e) {}	
			} //end while 
			running.set(true);
			source.returnConnection(connection);

		}
	}
}
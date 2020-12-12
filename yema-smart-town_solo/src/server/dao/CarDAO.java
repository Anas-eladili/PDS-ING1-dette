package server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import common.*;
import common.ConvertJSON;
import common.business.Car;


public class CarDAO extends DAO<Car>{
	private ConvertJSON converter = new ConvertJSON();
	@Override
	public boolean insert(String device, Connection connection) {
		
		PreparedStatement preparedStatement = null;
		Car car = converter.JsonToCar(device);

		try {
			preparedStatement = connection.prepareStatement("INSERT INTO car(id, isInTheCity) VALUES(?, ?)");
			
			preparedStatement.setInt(1, car.getId());
			preparedStatement.setBoolean(2, car.getIsInTheCity());
			
			preparedStatement.executeUpdate();
			
			return true; 	
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean delete(String device, Connection connection) {
		PreparedStatement preparedStatement = null;
		Car car  = converter.JsonToCar(device);
try {
			
			preparedStatement = connection.prepareStatement("DELETE FROM car WHERE id = ?");
			
			preparedStatement.setInt(1, car.getId());
			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		return false;
	}}

	@Override
	public boolean update(String device, Connection connection) {
		PreparedStatement preparedStatement = null;
		Car car  = converter.JsonToCar(device);
		try {
			preparedStatement = connection.prepareStatement("UPDATE car SET isInTheCity = ? WHERE id = ?");
			preparedStatement.setInt(2, car.getId());
			
			preparedStatement.setBoolean(1, car.getIsInTheCity());
			
			
			
			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {e.printStackTrace();}
			
		return false;
	}

	@Override
	public ArrayList<String> selectAll(Connection connection) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			Statement myRequest = connection.createStatement();
			ResultSet result = myRequest.executeQuery("SELECT * FROM car  ");
			
			while(result.next()) {
				Car car = new Car();
				
				car.setId(result.getInt(1));
				car.setIsInTheCity(result.getBoolean(2));
				
				
				String json = converter.CarToJson(car);
				list.add(json);
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace(); }
			
		return list;
	}
	@Override
	public ArrayList<String> select(Connection connection) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			Statement myRequest = connection.createStatement();
			ResultSet result = myRequest.executeQuery("SELECT * FROM car  ");
			
			while(result.next()) {
				Car car = new Car();
				
				car.setId(result.getInt(1));
				car.setIsInTheCity(result.getBoolean(2));
				
				
				String json = converter.CarToJson(car);
				list.add(json);
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace(); }
			
		return list;
	}
	public ArrayList<String> selectincity(Connection connection) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			Statement myRequest = connection.createStatement();
			ResultSet result = myRequest.executeQuery("SELECT * FROM car where isInTheCity = true  ");
			
			while(result.next()) {
				
				Car car = new Car();
				car.setId(result.getInt(1));
				car.setIsInTheCity(result.getBoolean(2));
				
				
				String json = converter.CarToJson(car);
				list.add(json);
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace(); }
			
		return list;
	}

	@Override
	public ArrayList<String> selectID(String id, Connection connection) {
		ArrayList<String> list = new ArrayList<String>();
		int idCar = Integer.valueOf(id);

		try {
			Statement myRequest = connection.createStatement();
			ResultSet result = myRequest.executeQuery("SELECT * FROM car WHERE id = " + idCar);

			while(result.next()) {
				Car bollard = new Car();

				bollard.setId(result.getInt(1));
				
				bollard.setIsInTheCity(result.getBoolean(2));
				


				String json = converter.CarToJson(bollard);
				list.add(json);
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
	}
	}

	@Override
	public boolean updatenb(String obj, Connection connection) {
		// TODO Auto-generated method stub
		return false;
	}}



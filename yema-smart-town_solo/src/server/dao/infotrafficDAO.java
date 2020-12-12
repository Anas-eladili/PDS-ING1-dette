package server.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.ConvertJSON;
import common.business.Car;
import common.business.infotraffic;
import common.*;

public class infotrafficDAO extends DAO<infotraffic>{
	private ConvertJSON converter = new ConvertJSON();
	@Override
	public boolean insert(String device, Connection connection) {
		
		PreparedStatement preparedStatement = null;
		infotraffic info = converter.JsonToinfotraffic(device);

		try {
			preparedStatement = connection.prepareStatement("INSERT INTO infotraffic(id, alert, nbmaxcar, nbactuel) VALUES(?, ?,?,?)");
			preparedStatement.setInt(1, info.getId());
			preparedStatement.setBoolean(2, info.getAlert());
			preparedStatement.setInt(3, info.getNbmaxcar());
			preparedStatement.setInt(4, info.getNbactuel());
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
		infotraffic info = converter.JsonToinfotraffic(device);

try {
			
			preparedStatement = connection.prepareStatement("DELETE FROM infotraffic WHERE id = ?");
			
			preparedStatement.setInt(1, info.getId());
			
			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		return false;
	}}

	@Override
	public boolean update(String device, Connection connection) {
		PreparedStatement preparedStatement = null;
		infotraffic info = converter.JsonToinfotraffic(device);
		try {
			preparedStatement = connection.prepareStatement("UPDATE infotraffic SET alert = ?, nbmaxcar = ?, nbactuel = ? WHERE id = ?");
			preparedStatement.setBoolean(1, info.getAlert());
			
			preparedStatement.setInt(2, info.getNbmaxcar());
			preparedStatement.setInt(4, info.getId());
			
			preparedStatement.setInt(3, info.getNbactuel());
			
			
			
			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {e.printStackTrace();}
			
		return false;
	}
	public boolean updatenb(String device, Connection connection) {
		PreparedStatement preparedStatement = null;
		infotraffic info = converter.JsonToinfotraffic(device);
		try {
			preparedStatement = connection.prepareStatement("UPDATE infotraffic SET  nbactuel = ? WHERE id = ?");
		
			preparedStatement.setInt(2, info.getId());
			
			preparedStatement.setInt(1, info.getNbactuel());
			
			
			
			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {e.printStackTrace();}
			
		return false;
	}

	

	@Override
	public ArrayList<String> selectID(String id, Connection connection) {
		ArrayList<String> list = new ArrayList<String>();
		int idinfo = Integer.valueOf(id);

		try {
			Statement myRequest = connection.createStatement();
			ResultSet result = myRequest.executeQuery("SELECT * FROM infotraffic WHERE id = " + idinfo);

			while(result.next()) {
				infotraffic bollard = new infotraffic();
				
				bollard.setId(result.getInt(1));
				
				bollard.setAlert(result.getBoolean(2));
				bollard.setNbmaxcar(result.getInt(3));
				bollard.setNbactuel(result.getInt(4));
				


				String json = converter.infotrafficToJson(bollard);
				list.add(json);
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();}
		return list;
			
	}

	@Override
	public ArrayList<String> select(Connection connection) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			Statement myRequest = connection.createStatement();
			ResultSet result = myRequest.executeQuery("SELECT * FROM infotraffic ");
			
			while(result.next()) {
				infotraffic info = new infotraffic();
				
				info.setId(result.getInt(1));
				info.setAlert(result.getBoolean(2));
				info.setNbmaxcar(result.getInt(3));
				info.setNbactuel(result.getInt(4));
				
				
				
				String json = converter.infotrafficToJson(info);
				list.add(json);
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace(); }
			
		return list;
	}
	public ArrayList<String> selectnbmax(Connection connection) {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		try {
			Statement myRequest = connection.createStatement();
			ResultSet result = myRequest.executeQuery("SELECT nbmaxcar FROM infotraffic");
			
			while(result.next()) {
				int nbmaxcar = result.getInt("nbmaxcar") ;
				
				// info.setNbmaxcar(result.getInt(1));
				
				
				String json = String.valueOf(nbmaxcar);
				list.add(json);
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		
		
	}
		

}
	public ArrayList<String> selectnbactuel(Connection connection) {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		try {
			Statement myRequest = connection.createStatement();
			ResultSet result = myRequest.executeQuery("SELECT nbactuel FROM infotraffic");
			
			while(result.next()) {
				int nbactuel = result.getInt("nbactuel") ;
				
				// info.setNbmaxcar(result.getInt(1));
				
				
				String json = String.valueOf(nbactuel);
				list.add(json);
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		
		
	}
		

}
	public ArrayList<String> selectalert(Connection connection) {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		try {
			Statement myRequest = connection.createStatement();
			ResultSet result = myRequest.executeQuery("SELECT alert FROM infotraffic");
			
			while(result.next()) {
				Boolean  alert = result.getBoolean("alert");
				
				String json = String.valueOf(alert);
				list.add(json);
				
				
				
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		
		
	}
	}
}
	
	
	

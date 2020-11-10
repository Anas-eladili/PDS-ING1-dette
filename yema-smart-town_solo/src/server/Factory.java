package server;

public class Factory {
	@SuppressWarnings("rawtypes")
	public static DAO getData(String target){
		
		
	 	 
		
		 if(target.equals("retractablebollard"))
			  return new BollardDAO();
		
		else if (target.equals("vehiclesensor"))
				return new VehicleSensorDAO();
		
		else if (target.contentEquals("car"))
				return new CarDAO();
		else if (target.contentEquals("infotraffic"))
			return new infotrafficDAO();
		else
				return null; 
	}
}

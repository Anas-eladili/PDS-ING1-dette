package common.business;

public class infotraffic {
	private int id ;
	private Boolean alert ;
	private int nbmaxcar ;
	private int nbactuel ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Boolean getAlert() {
		return alert;
	}
	public int getNbactuel() {
		return nbactuel;
	}
	public void setNbactuel(int nbactuel) {
		this.nbactuel = nbactuel;
	}
	public void setAlert(Boolean alert) {
		this.alert = alert;
	}
	public int getNbmaxcar() {
		return nbmaxcar;
	}
	public void setNbmaxcar(int nbmaxcar) {
		this.nbmaxcar = nbmaxcar;
	}
	

}

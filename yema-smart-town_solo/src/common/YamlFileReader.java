package common;

import java.io.InputStream;
import java.util.ArrayList;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import common.business.Car;


public class YamlFileReader {

	
	
	
	
	

	/**load data to car */
     public Car initCarConfig() { 
		Yaml yaml = new Yaml(new Constructor(Car.class));
		InputStream inputStream =  this.getClass().getClassLoader().getResourceAsStream("ressources/AddCartest");
		
		Car config = (Car) yaml.load(inputStream);
		
		return config; }
}

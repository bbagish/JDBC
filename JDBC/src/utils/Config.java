package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {
	private static Properties config;
	
	static{
		try{
			System.out.println("Opening properties file");
			String filePath="./src/utils/configuration.properties";
			FileInputStream input=new FileInputStream(filePath);
			config=new Properties();
			config.load(input);
			input.close();
		}catch(Exception e){
			e.printStackTrace();
		}	
	}	
	
	public static String getProperty(String key){
		return config.getProperty(key);
	}
	
}
package kaisheng.project.utils;

import java.io.IOException;
import java.util.Properties;

public class Config {
	
	private static Properties pro = new Properties();
	
	static{
		
		try {
			pro.load(Config.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String get(String key){
		return pro.getProperty(key);
	}
	
}

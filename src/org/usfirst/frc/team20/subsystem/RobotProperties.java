package org.usfirst.frc.team20.subsystem;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RobotProperties {

	private static Map<String, Object> properties;
	private static List<PropertyChangeListener> listeners;
	private static PropertyReader reader;
	
	static{
		properties = new HashMap<>();
		listeners = new ArrayList<>();
	}
	
	public static void loadFile(String file) throws FileNotFoundException {
		reader.setReader(new FileReader(file));
		Map.Entry<String, Object> entry;
		while((entry = reader.getNextProperty()) != null){
			properties.put(entry.getKey(), entry.getValue());
		}
	}
	
	public static void registerPropertyChangeListener(PropertyChangeListener listener){
		listeners.add(listener);
	}
	
	public static Object getProperty(String key){
		return properties.get(key);
	}
	
	public static void setProperty(String key, Object property){
		boolean changed = false;
		if(properties.get(key) != property)
			changed = true;
		properties.put(key, property);
		if(changed)
			notifyListeners(key, property);
	}
	
	public static void setProperties(Map<String, Object> properties){
		for(Map.Entry<String, Object> property : properties.entrySet()){
			setProperty(property.getKey(), property.getValue());
		}
	}
	
	public static void setReader(PropertyReader reader){
		RobotProperties.reader = reader;
	}
	
	public static boolean containsAll(String[] keys){
		for(String key : keys){
			if(!properties.containsKey(key))
				return false;
		}
		return true;
	}
	
	private static void notifyListeners(String key, Object property){
		for(PropertyChangeListener listener : listeners){
			listener.propertyChanged(key, property);
		}
		for(PropertyChangeListener listener : listeners){
			properties.putAll(listener.getProperties());
		}
	}
}

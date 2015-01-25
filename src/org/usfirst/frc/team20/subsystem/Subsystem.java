package org.usfirst.frc.team20.subsystem;

import java.util.Map;

public abstract class Subsystem implements PropertyChangeListener{
	
	private Map<String, PropertyChange> listeners;
	
	private interface PropertyChange{
		public void run(Object property);
	}
	
	public void registerPropertyListener(String name, PropertyChange change){
		listeners.put(name, change);
	}
	
	public void propertyChanged(String key, Object value){
		PropertyChange update = listeners.get(key);
		if(update != null)
			update.run(value);
	}
}
package org.usfirst.frc.team20.subsystem;

import java.util.Map;

public interface PropertyChangeListener {

	void propertyChanged(String key, Object property);

	Map<String, Object> getProperties();
}

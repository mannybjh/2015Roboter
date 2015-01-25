package org.usfirst.frc.team20.device;

import java.util.Map;

import org.usfirst.frc.team20.subsystem.PropertyChangeListener;

public abstract class Device implements PropertyChangeListener {

	private String namespace;

	public Device(String namespace) {
		this.namespace = namespace;
	}

	public String getNamespace() {
		return this.namespace;
	}

	@Override public abstract Map<String, Object> getProperties();

	@Override public abstract void propertyChanged(String key, Object property);
}

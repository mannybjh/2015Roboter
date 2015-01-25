package org.usfirst.frc.team20.device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceGroup extends Device {

	private List<Device> devices;

	public DeviceGroup(String namespace) {
		super(namespace);
		devices = new ArrayList<>();
	}

	public void addDevice(Device device) {
		this.devices.add(device);
	}

	@Override public Map<String, Object> getProperties() {
		Map<String, Object> properties = new HashMap<>();
		for (Device device : devices) {
			properties.putAll(device.getProperties());
		}
		return properties;
	}

	@Override public void propertyChanged(String key, Object property) {
		for (Device device : devices)
			device.propertyChanged(key, property);
	}
}

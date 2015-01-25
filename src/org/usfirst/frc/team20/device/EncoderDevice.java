package org.usfirst.frc.team20.device;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.Encoder;

public class EncoderDevice extends Device {

	public static final String DISTANCE_SUFFIX = "Distance";
	public static final String REVERSED_SUFFIX = "Reversed";

	private Encoder encoder;
	private final String DISTANCE_KEY;
	private final String REVERSED_KEY;

	public EncoderDevice(String namespace, Encoder encoder) {
		super(namespace);
		this.encoder = encoder;
		DISTANCE_KEY = namespace + DISTANCE_SUFFIX;
		REVERSED_KEY = namespace + REVERSED_SUFFIX;
	}

	@Override public Map<String, Object> getProperties() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(DISTANCE_KEY, encoder.getDistance());
		properties.put(REVERSED_KEY, encoder.getDirection());
		return properties;
	}

	@Override public void propertyChanged(String key, Object property) {

	}

}

package de.gravitex.test.entity;

import de.gravitex.test.util.TrainActionKey;
import lombok.Data;

@Data
public class Waggon implements RailItem {

	private String waggonNumber;
	
	private String trainId;
	
	private WaggonType waggonType;

	@Override
	public TrainActionKey generateKey(String action) {
		return TrainActionKey.blank();
	}
}
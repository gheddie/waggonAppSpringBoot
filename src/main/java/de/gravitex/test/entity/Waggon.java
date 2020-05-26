package de.gravitex.test.entity;

import java.util.ArrayList;
import java.util.List;

import de.gravitex.test.util.TrainActionKey;
import lombok.Data;

@Data
public class Waggon implements RailItem {
	
	private Long waggonId;

	private String waggonNumber;
	
	private Long trainId;
	
	private WaggonType waggonType;
	
	private int waggonLenght;
	
	private int maximumLoad;
	
	private BrakeType brakeType;
	
	int position;
	
	private List<WaggonDamage> waggonDamages = new ArrayList<WaggonDamage>();

	@Override
	public TrainActionKey generateKey(String action) {
		return TrainActionKey.blank();
	}
}
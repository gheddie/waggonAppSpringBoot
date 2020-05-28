package de.gravitex.test.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@JsonIgnore
	private List<WaggonDamage> waggonDamages = new ArrayList<WaggonDamage>();

	@Override
	public TrainActionKey generateKey(String action) {
		return TrainActionKey.blank();
	}
	
	public int getDamageCount() {
		return waggonDamages.size();
	}
}
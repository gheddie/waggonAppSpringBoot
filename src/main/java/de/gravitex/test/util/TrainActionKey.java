package de.gravitex.test.util;

import lombok.Data;

@Data
public class TrainActionKey {

	private String trainId;
	
	private String waggonNumber;
	
	private String trainAction;
	
	private TrainActionKey() {
		super();
	}
	
	public static TrainActionKey fromValues(String aTrainId, String aWaggonNumber, String aTrainAction) {
		TrainActionKey trainActionKey = new TrainActionKey();
		trainActionKey.setTrainId(aTrainId);
		trainActionKey.setWaggonNumber(aWaggonNumber);
		trainActionKey.setTrainAction(aTrainAction);
		return trainActionKey;
	}

	public static TrainActionKey blank() {
		return TrainActionKey.fromValues(null, null, null);
	}
}
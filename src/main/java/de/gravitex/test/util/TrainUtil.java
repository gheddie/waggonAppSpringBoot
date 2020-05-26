package de.gravitex.test.util;

import java.util.ArrayList;
import java.util.List;

import de.gravitex.test.def.WaggonDefinition;
import de.gravitex.test.entity.Train;
import de.gravitex.test.entity.TrainState;
import de.gravitex.test.entity.Waggon;

public class TrainUtil {

	public static Train createTrain(Long trainId, TrainState trainState, String trainNumber, WaggonDefinition... waggonDefinitions) {
		Train train = new Train();
		train.setTrainId(trainId);
		train.setTrainNumber(trainNumber);
		train.setTrainState(trainState);
		List<Waggon> waggons = new ArrayList<Waggon>();
		for (WaggonDefinition waggonDefinition : waggonDefinitions) {
			waggons.add(createWaggon(waggonDefinition, trainId));
		}
		train.setWaggons(waggons);
		return train;
	}

	private static Waggon createWaggon(WaggonDefinition waggonDefinition, Long trainId) {
		Waggon waggon = new Waggon();
		waggon.setWaggonId(waggonDefinition.getWaggonId());
		waggon.setTrainId(trainId);
		waggon.setWaggonNumber(waggonDefinition.getWaggonNumber());
		waggon.setWaggonType(waggonDefinition.getWaggonType());
		waggon.setWaggonLenght(waggonDefinition.getWaggonLenght());
		waggon.setMaximumLoad(waggonDefinition.getMaximumLoad());
		waggon.setBrakeType(waggonDefinition.getBrakeType());
		
		return waggon;
	}
}
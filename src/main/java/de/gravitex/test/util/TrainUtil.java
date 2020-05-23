package de.gravitex.test.util;

import java.util.ArrayList;
import java.util.List;

import de.gravitex.test.def.WaggonDefinition;
import de.gravitex.test.entity.Train;
import de.gravitex.test.entity.TrainState;
import de.gravitex.test.entity.Waggon;
import de.gravitex.test.entity.WaggonType;

public class TrainUtil {

	public static Train createTrain(TrainState trainState, String trainNumber, WaggonDefinition... waggonDefinitions) {
		Train train = new Train();
		train.setTrainNumber(trainNumber);
		train.setTrainState(trainState);
		List<Waggon> waggons = new ArrayList<Waggon>();
		for (WaggonDefinition waggonDefinition : waggonDefinitions) {
			waggons.add(createWaggon(waggonDefinition.getWaggonType(), waggonDefinition.getWaggonNumber()));
		}
		train.setWaggons(waggons);
		return train;
	}

	private static Waggon createWaggon(WaggonType waggonType, String waggonNumber) {
		Waggon waggon = new Waggon();
		waggon.setWaggonNumber(waggonNumber);
		waggon.setWaggonType(waggonType);
		return waggon;
	}
}
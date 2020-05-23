package de.gravitex.test.bl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.gravitex.test.def.WaggonDefinition;
import de.gravitex.test.entity.Train;
import de.gravitex.test.entity.TrainState;
import de.gravitex.test.entity.Waggon;
import de.gravitex.test.entity.WaggonType;

public class TrainSingleton {

	private static TrainSingleton instance;

	private HashMap<String, Train> trains = new HashMap<String, Train>();

	private TrainSingleton() {
		init();
	}

	private void init() {

		addTrain(createTrain(TrainState.READY_TO_GO, "Train1", WaggonDefinition.fromValues("W100", WaggonType.AVEX),
				WaggonDefinition.fromValues("W101", WaggonType.AVEX),
				WaggonDefinition.fromValues("W102", WaggonType.BVEX)));

		addTrain(createTrain(TrainState.READY_TO_GO, "Train2", WaggonDefinition.fromValues("W200", WaggonType.AVEX),
				WaggonDefinition.fromValues("W201", WaggonType.AVEX),
				WaggonDefinition.fromValues("W202", WaggonType.BVEX),
				WaggonDefinition.fromValues("W203", WaggonType.AVEX),
				WaggonDefinition.fromValues("W204", WaggonType.CVEX)));

		addTrain(createTrain(TrainState.GONE, "Train3", WaggonDefinition.fromValues("W300", WaggonType.AVEX),
				WaggonDefinition.fromValues("W301", WaggonType.CVEX)));
	}

	private void addTrain(Train train) {
		trains.put(train.getTrainNumber(), train);
	}

	public static TrainSingleton getInstance() {
		if (instance == null) {
			instance = new TrainSingleton();
		}
		return instance;
	}

	private Train createTrain(TrainState trainState, String trainNumber, WaggonDefinition... waggonDefinitions) {
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

	private Waggon createWaggon(WaggonType waggonType, String waggonNumber) {
		Waggon waggon = new Waggon();
		waggon.setWaggonNumber(waggonNumber);
		waggon.setWaggonType(waggonType);
		return waggon;
	}

	public List<Train> getTrains() {
		return new ArrayList<Train>(trains.values());
	}

	public Train getTrain(String trainNumber) {
		return trains.get(trainNumber);
	}
}
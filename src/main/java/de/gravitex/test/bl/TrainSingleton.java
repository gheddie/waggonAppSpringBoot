package de.gravitex.test.bl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.gravitex.test.def.WaggonDefinition;
import de.gravitex.test.entity.Train;
import de.gravitex.test.entity.TrainState;
import de.gravitex.test.entity.Waggon;
import de.gravitex.test.entity.WaggonType;
import de.gravitex.test.util.TrainUtil;

public class TrainSingleton {

	private static TrainSingleton instance;

	private HashMap<String, Train> trains = new HashMap<String, Train>();

	private TrainSingleton() {
		init();
	}

	private void init() {

		addTrain(TrainUtil.createTrain(TrainState.READY_TO_GO, "Train1", WaggonDefinition.fromValues("W100", WaggonType.AVEX),
				WaggonDefinition.fromValues("W101", WaggonType.AVEX),
				WaggonDefinition.fromValues("W102", WaggonType.BVEX),
				WaggonDefinition.fromValues("W103", WaggonType.BVEX),
				WaggonDefinition.fromValues("W104", WaggonType.BVEX),
				WaggonDefinition.fromValues("W105", WaggonType.BVEX),
				WaggonDefinition.fromValues("W106", WaggonType.BVEX),
				WaggonDefinition.fromValues("W107", WaggonType.BVEX),
				WaggonDefinition.fromValues("W108", WaggonType.BVEX),
				WaggonDefinition.fromValues("W109", WaggonType.BVEX),
				WaggonDefinition.fromValues("W110", WaggonType.BVEX),
				WaggonDefinition.fromValues("W111", WaggonType.BVEX),
				WaggonDefinition.fromValues("W112", WaggonType.BVEX),
				WaggonDefinition.fromValues("W113", WaggonType.BVEX),
				WaggonDefinition.fromValues("W114", WaggonType.BVEX),
				WaggonDefinition.fromValues("W115", WaggonType.BVEX)));

		addTrain(TrainUtil.createTrain(TrainState.READY_TO_GO, "Train2", WaggonDefinition.fromValues("W200", WaggonType.AVEX),
				WaggonDefinition.fromValues("W201", WaggonType.AVEX),
				WaggonDefinition.fromValues("W202", WaggonType.BVEX),
				WaggonDefinition.fromValues("W203", WaggonType.AVEX),
				WaggonDefinition.fromValues("W204", WaggonType.CVEX)));

		addTrain(TrainUtil.createTrain(TrainState.READY_TO_GO, "Train3", WaggonDefinition.fromValues("W300", WaggonType.AVEX),
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

	public List<Train> getTrains() {
		return new ArrayList<Train>(trains.values());
	}

	public Train getTrain(String trainNumber) {
		return trains.get(trainNumber);
	}
}
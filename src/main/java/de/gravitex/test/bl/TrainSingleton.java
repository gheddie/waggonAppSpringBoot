package de.gravitex.test.bl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.gravitex.test.entity.Train;
import de.gravitex.test.entity.Waggon;

public class TrainSingleton {

	private static TrainSingleton instance;
	
	private HashMap<String, Train> trains = new HashMap<String, Train>();
	
	private TrainSingleton() {
		init();
	}

	private void init() {
		addTrain(createTrain("Train1", "W100", "W101", "W102"));
		addTrain(createTrain("Train2", "W200", "W201", "W202", "W203", "W204"));
		addTrain(createTrain("Train3", "W300", "W301"));
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
	
	private Train createTrain(String trainNumber, String... waggonNumbers) {
		Train t = new Train();
		t.setTrainNumber(trainNumber);
		List<Waggon> waggons = new ArrayList<Waggon>();
		for (String waggonNumber : waggonNumbers) {
			waggons.add(createWaggon(waggonNumber));
		}
		t.setWaggons(waggons);
		return t;
	}
	
	private Waggon createWaggon(String waggonNumber) {
		Waggon w = new Waggon();
		w.setWaggonNumber(waggonNumber);
		return w;
	}

	public List<Train> getTrains() {
		return new ArrayList<Train>(trains.values());
	}

	public Train getTrain(String trainNumber) {
		return trains.get(trainNumber);
	}
}
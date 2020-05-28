package de.gravitex.test.bl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import de.gravitex.test.def.WaggonDefinition;
import de.gravitex.test.entity.BrakeType;
import de.gravitex.test.entity.DamageCode;
import de.gravitex.test.entity.Train;
import de.gravitex.test.entity.TrainState;
import de.gravitex.test.entity.Waggon;
import de.gravitex.test.entity.WaggonDamage;
import de.gravitex.test.entity.WaggonType;
import de.gravitex.test.exception.TrainHandlingException;
import de.gravitex.test.util.TrainUtil;

public class TrainSingleton {

	private static TrainSingleton instance;

	private HashMap<Long, Train> trains = new HashMap<Long, Train>();

	private TrainSingleton() {
		init();
	}

	private void init() {

		addTrain(TrainUtil.createTrain(new Long(1), TrainState.READY_TO_GO, "Train1",
				WaggonDefinition.fromValues(new Long(1), "W100", WaggonType.AVEX, 12, 24, BrakeType.B1),
				WaggonDefinition.fromValues(new Long(2), "W101", WaggonType.AVEX, 0, 0, BrakeType.B2),
				WaggonDefinition.fromValues(new Long(3), "W102", WaggonType.BVEX, 0, 0, null),
				WaggonDefinition.fromValues(new Long(4), "W103", WaggonType.BVEX, 12, 24, null),
				WaggonDefinition.fromValues(new Long(5), "W104", WaggonType.BVEX, 0, 0, BrakeType.B1),
				WaggonDefinition.fromValues(new Long(6), "W105", WaggonType.BVEX, 0, 0, null),
				WaggonDefinition.fromValues(new Long(7), "W106", WaggonType.BVEX, 12, 24, null),
				WaggonDefinition.fromValues(new Long(8), "W107", WaggonType.BVEX, 0, 0, null),
				WaggonDefinition.fromValues(new Long(9), "W108", WaggonType.BVEX, 0, 0, null),
				WaggonDefinition.fromValues(new Long(10), "W109", WaggonType.BVEX, 0, 0, BrakeType.B2),
				WaggonDefinition.fromValues(new Long(11), "W110", WaggonType.BVEX, 12, 24, null),
				WaggonDefinition.fromValues(new Long(12), "W111", WaggonType.BVEX, 0, 0, BrakeType.B1),
				WaggonDefinition.fromValues(new Long(13), "W112", WaggonType.BVEX, 0, 0, null),
				WaggonDefinition.fromValues(new Long(14), "W113", WaggonType.BVEX, 12, 24, null),
				WaggonDefinition.fromValues(new Long(15), "W114", WaggonType.BVEX, 0, 0, BrakeType.B2),
				WaggonDefinition.fromValues(new Long(16), "W115", WaggonType.BVEX, 0, 0, null)));

		addTrain(TrainUtil.createTrain(new Long(2), TrainState.READY_TO_GO, "Train2",
				WaggonDefinition.fromValues(new Long(17), "W200", WaggonType.AVEX, 13, 19, null),
				WaggonDefinition.fromValues(new Long(18), "W201", WaggonType.AVEX, 0, 0, null),
				WaggonDefinition.fromValues(new Long(19), "W202", WaggonType.BVEX, 0, 0, BrakeType.B3),
				WaggonDefinition.fromValues(new Long(20), "W203", WaggonType.AVEX, 27, 6, null),
				WaggonDefinition.fromValues(new Long(21), "W204", WaggonType.CVEX, 0, 0, null)));

		addTrain(TrainUtil.createTrain(new Long(3), TrainState.READY_TO_GO, "Train3",
				WaggonDefinition.fromValues(new Long(22), "W300", WaggonType.AVEX, 0, 0, null),
				WaggonDefinition.fromValues(new Long(23), "W301", WaggonType.CVEX, 0, 0, BrakeType.B3)));
		
		// Train 1
		addWaggonDamage(new Long(6), "damage D1", DamageCode.D1);
		addWaggonDamage(new Long(10), "damage D1", DamageCode.D1);
		addWaggonDamage(new Long(14), "damage D3", DamageCode.D3);
		addWaggonDamage(new Long(14), "damage D1", DamageCode.D1);
		
		// Train 2
		addWaggonDamage(new Long(18), "damage D1", DamageCode.D1);
		addWaggonDamage(new Long(18), "damage D2", DamageCode.D2);
		addWaggonDamage(new Long(18), "damage D3", DamageCode.D3);
		addWaggonDamage(new Long(19), "damage D1", DamageCode.D1);
		addWaggonDamage(new Long(20), "damage D2", DamageCode.D2);
		
		// Train 3
		addWaggonDamage(new Long(22), "damage D4", DamageCode.D4);
	}

	private void addWaggonDamage(Long waggonId, String aDescription, DamageCode damageCode) {
		Waggon waggon = null;
		for (Train train : trains.values()) {
			waggon = train.getWaggon(waggonId);
			if (waggon != null) {
				WaggonDamage waggonDamage = new WaggonDamage();
				waggonDamage.setDescription(aDescription);
				waggonDamage.setDamageCode(damageCode);
				waggon.getWaggonDamages().add(waggonDamage);
			}
		}
	}

	private void addTrain(Train train) {
		trains.put(train.getTrainId(), train);
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

	public Train getTrain(Long trainId) {
		return trains.get(trainId);
	}

	public void checkWaggon(Waggon waggon) throws TrainHandlingException {
		if (waggon.getWaggonNumber().isEmpty()) {
			throw new TrainHandlingException("waggn nummber must not be empty!!");
		}
		if (getAllWaggonNumbers().contains(waggon.getWaggonNumber())) {
			throw new TrainHandlingException("waggon nummber '" + waggon.getWaggonNumber() + "' is already present!!");
		}
	}

	private HashSet<String> getAllWaggonNumbers() {
		HashSet<String> result = new HashSet<String>();
		for (Train train : trains.values()) {
			for (Waggon waggon : train.getWaggons()) {
				result.add(waggon.getWaggonNumber());
			}
		}
		return result;
	}

	public Waggon findWaggon(Long waggonId) {
		System.out.println("finding waggon: " + waggonId);
		for (Train train : trains.values()) {
			for (Waggon waggon : train.getWaggons()) {
				if (waggon.getWaggonId().equals(waggonId)) {
					return waggon;
				}
			}	
		}
		return null;
	}
}
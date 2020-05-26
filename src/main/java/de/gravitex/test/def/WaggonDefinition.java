package de.gravitex.test.def;

import de.gravitex.test.entity.BrakeType;
import de.gravitex.test.entity.WaggonType;
import lombok.Data;

@Data
public class WaggonDefinition {

	private Long waggonId;

	private String waggonNumber;

	private WaggonType waggonType;

	private int waggonLenght;

	private int maximumLoad;

	private BrakeType brakeType;

	private WaggonDefinition() {
		super();
	}

	public static WaggonDefinition fromValues(Long waggonId, String aWaggonNumber, WaggonType aWaggonType,
			int aWaggonLenght, int aMaximumLoad, BrakeType aBrakeType) {

		WaggonDefinition waggonDefinition = new WaggonDefinition();

		waggonDefinition.setWaggonId(waggonId);
		waggonDefinition.setWaggonNumber(aWaggonNumber);
		waggonDefinition.setWaggonType(aWaggonType);
		waggonDefinition.setWaggonLenght(aWaggonLenght);
		waggonDefinition.setMaximumLoad(aMaximumLoad);
		waggonDefinition.setBrakeType(aBrakeType);

		return waggonDefinition;
	}
}
package de.gravitex.test.def;

import de.gravitex.test.entity.WaggonType;
import lombok.Data;

@Data
public class WaggonDefinition {

	private String waggonNumber;
	
	private WaggonType waggonType;
	
	private WaggonDefinition() {
		super();
	}

	public static WaggonDefinition fromValues(String aWaggonNumber, WaggonType aWaggonType) {
		WaggonDefinition waggonDefinition = new WaggonDefinition();
		waggonDefinition.setWaggonNumber(aWaggonNumber);
		waggonDefinition.setWaggonType(aWaggonType);
		return waggonDefinition;
	}
}
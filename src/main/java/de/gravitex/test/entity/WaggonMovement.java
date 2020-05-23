package de.gravitex.test.entity;

import lombok.Data;

@Data
public class WaggonMovement {

	private String movedWaggonNumber;
	
	private String trainId;
	
	private String direction;
}
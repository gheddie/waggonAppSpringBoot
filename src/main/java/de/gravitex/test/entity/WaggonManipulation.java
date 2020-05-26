package de.gravitex.test.entity;

import lombok.Data;

@Data
public class WaggonManipulation {

	private String movedWaggonNumber;
	
	private Long trainId;
	
	private WaggonManipulationType waggonManipulationType;
}
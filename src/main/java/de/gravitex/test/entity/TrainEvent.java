package de.gravitex.test.entity;

import lombok.Data;

@Data
public class TrainEvent {

	private Long trainId;
	
	private String trainAction;
}
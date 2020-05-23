package de.gravitex.test.entity;

import java.util.List;

import lombok.Data;

@Data
public class Train {

	private String trainNumber;
	
	private TrainState trainState;
	
	private List<Waggon> waggons;
}
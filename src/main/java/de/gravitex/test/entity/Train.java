package de.gravitex.test.entity;

import java.util.List;

import lombok.Data;

@Data
public class Train {

	private String trainNumber;
	
	private List<Waggon> waggons;
}
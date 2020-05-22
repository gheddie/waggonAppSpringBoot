package de.gravitex.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.gravitex.test.entity.Train;
import de.gravitex.test.entity.Waggon;
import de.gravitex.test.entity.WaggonMovement;

@RestController
public class WaggonServiceController {

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/moveWaggons", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void moveWaggons(@RequestBody WaggonMovement waggonMovement) {

		System.out.println("movement  [waggon " + waggonMovement.getMovedWaggonNumber() + " moves to "
				+ waggonMovement.getDestinationWaggonNumber() + "]");
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/waggondata", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Train> waggonData() {

		System.out.println("getting waggon data...");

		Train train = new Train();
		train.setTrainNumber("T-XY-987-P-42");

		List<Waggon> waggons = new ArrayList<Waggon>();

		waggons.add(createWaggon("W1"));
		waggons.add(createWaggon("W2"));
		waggons.add(createWaggon("W3"));
		waggons.add(createWaggon("W4"));
		waggons.add(createWaggon("W5"));
		waggons.add(createWaggon("W6"));
		waggons.add(createWaggon("W7"));
		waggons.add(createWaggon("W8"));
		waggons.add(createWaggon("W9"));

		train.setWaggons(waggons);

		System.out.println("succesfully got waggon data...");

		return new ResponseEntity<Train>(train, HttpStatus.OK);
	}
	
	private Waggon createWaggon(String waggonNumber) {
		Waggon w = new Waggon();
		w.setWaggonNumber(waggonNumber);
		return w;
	}
}
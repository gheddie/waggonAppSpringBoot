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
import org.springframework.web.bind.annotation.RequestParam;
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
	@RequestMapping(value = "/traindata", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Train>> trainData() {
		
		List<Train> trains = new ArrayList<Train>();
		
		trains.add(createTrain("100"));
		trains.add(createTrain("200"));
		trains.add(createTrain("300"));
		trains.add(createTrain("400"));
		
		return new ResponseEntity<List<Train>>(trains, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/waggondata", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Train> waggonData(@RequestParam String trainNumber) {

		System.out.println("getting waggon data for train: '"+trainNumber+"'...");

		Train train = new Train();
		train.setTrainNumber(trainNumber);
		List<Waggon> waggons = createTrainWaggons(trainNumber);
		train.setWaggons(waggons);
		System.out.println("succesfully got waggon data...");
		return new ResponseEntity<Train>(train, HttpStatus.OK);
	}

	private List<Waggon> createTrainWaggons(String trainNumber) {
		
		List<Waggon> waggons = new ArrayList<Waggon>();
		
		switch (trainNumber) {
		case "100":
			waggons.add(createWaggon("W100"));
			waggons.add(createWaggon("W101"));
			waggons.add(createWaggon("W102"));
			waggons.add(createWaggon("W103"));
			waggons.add(createWaggon("W104"));
			waggons.add(createWaggon("W105"));
			waggons.add(createWaggon("W106"));
			waggons.add(createWaggon("W107"));
			waggons.add(createWaggon("W108"));			
			break;
		case "200":
			waggons.add(createWaggon("W200"));
			waggons.add(createWaggon("W201"));
			waggons.add(createWaggon("W202"));
			waggons.add(createWaggon("W203"));
			waggons.add(createWaggon("W204"));
			break;
		default:
			waggons.add(createWaggon("W8000"));
			waggons.add(createWaggon("W8001"));
			waggons.add(createWaggon("W8002"));
			waggons.add(createWaggon("W8003"));
			waggons.add(createWaggon("W8004"));
			waggons.add(createWaggon("W8005"));
			waggons.add(createWaggon("W8006"));
			break;
		}

		return waggons;
	}
	
	private Train createTrain(String trainNumber) {
		Train t = new Train();
		t.setTrainNumber(trainNumber);
		return t;
	}
	
	private Waggon createWaggon(String waggonNumber) {
		Waggon w = new Waggon();
		w.setWaggonNumber(waggonNumber);
		return w;
	}
}
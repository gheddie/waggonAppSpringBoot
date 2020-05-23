package de.gravitex.test.controller;

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

import de.gravitex.test.bl.TrainSingleton;
import de.gravitex.test.entity.Train;
import de.gravitex.test.entity.TrainEvent;
import de.gravitex.test.entity.TrainState;
import de.gravitex.test.entity.WaggonMovement;

@RestController
public class WaggonServiceController {
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/changeTrainState", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void changeTrainState(@RequestBody TrainEvent trainEvent) {

		System.out.println(
				"received train action: train " + trainEvent.getTrainId() + ", action: " + trainEvent.getTrainAction());
		switch (trainEvent.getTrainAction()) {
		case "DEPARTURE":
			System.out.println("departing train: " + trainEvent.getTrainId());
			TrainSingleton.getInstance().getTrain(trainEvent.getTrainId()).setTrainState(TrainState.GONE);
			break;
		case "ARRIVAL":
			System.out.println("arriving train: " + trainEvent.getTrainId());
			TrainSingleton.getInstance().getTrain(trainEvent.getTrainId()).setTrainState(TrainState.ARRIVED);
			break;
		}
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/moveWaggons", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void moveWaggons(@RequestBody WaggonMovement waggonMovement) {

		System.out.println("movement  [waggon " + waggonMovement.getMovedWaggonNumber() + " from train "
				+ waggonMovement.getTrainId() + " moves " + waggonMovement.getDirection() + ".]");
		
		switch (waggonMovement.getDirection()) {
		case "UP":
			// forward
			TrainSingleton.getInstance().getTrain(waggonMovement.getTrainId())
					.moveWaggonForward(waggonMovement.getMovedWaggonNumber());
			break;
		case "DOWN":
			// backward
			TrainSingleton.getInstance().getTrain(waggonMovement.getTrainId())
					.moveWaggonBackward(waggonMovement.getMovedWaggonNumber());
			break;
		case "REMOVE":
			TrainSingleton.getInstance().getTrain(waggonMovement.getTrainId())
					.removeWaggon(waggonMovement.getMovedWaggonNumber());
			break;
		case "TOEND":
			TrainSingleton.getInstance().getTrain(waggonMovement.getTrainId())
					.moveWaggonToEnd(waggonMovement.getMovedWaggonNumber());
			break;
		}
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/traindata", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Train>> trainData() {
		
		return new ResponseEntity<List<Train>>(TrainSingleton.getInstance().getTrains(), HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/waggondata", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Train> waggonData(@RequestParam String trainNumber) {

		System.out.println("getting train: '"+trainNumber+"'...");		
		return new ResponseEntity<Train>(TrainSingleton.getInstance().getTrain(trainNumber), HttpStatus.OK);
	}
}
package de.gravitex.test.controller;

import java.util.HashMap;
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
import de.gravitex.test.exception.TrainHandlingException;
import de.gravitex.test.handler.TrainActionHandler;
import de.gravitex.test.util.TrainActionKey;

@RestController
public class WaggonServiceController {
	
	private static final HashMap<TrainActionKey, TrainActionHandler> actionHandlers = new HashMap<TrainActionKey, TrainActionHandler>();
	static {
		actionHandlers.put(TrainActionKey.fromValues("Train1", null, "DEPARTURE"), new TrainActionHandler() {
			@Override
			public void handle() throws TrainHandlingException {
				throw new TrainHandlingException("can not depart train 'Train1'!!");
			}
		});
	}
	
	private static final String RESPONSE_OK = "{result: OK, message: NONE}";
	
	private static final String RESPONSE_FAIL = "{result: FAIL, message: NONE}";
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/changeTrainState", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> changeTrainState(@RequestBody TrainEvent trainEvent) {

		System.out.println(
				"received train action: train " + trainEvent.getTrainId() + ", action: " + trainEvent.getTrainAction());
		Train train = TrainSingleton.getInstance().getTrain(trainEvent.getTrainId());
		try {
			processTrainActionHandler(train.generateKey(trainEvent.getTrainAction()));
			switch (trainEvent.getTrainAction()) {
			case "DEPARTURE":
				System.out.println("departing train: " + trainEvent.getTrainId());
				train.setTrainState(TrainState.GONE);
				break;
			case "ARRIVAL":
				System.out.println("arriving train: " + trainEvent.getTrainId());
				train.setTrainState(TrainState.ARRIVED);
				break;
			}
			System.out.println("changeTrainState --> returning response 200 --> OK...");
			return new ResponseEntity<String>("OK", HttpStatus.OK);
		} catch (TrainHandlingException e) {
			System.out.println("changeTrainState --> returning response 500 --> INTERNAL_SERVER_ERROR...");
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/moveWaggons", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> moveWaggons(@RequestBody WaggonMovement waggonMovement) {

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
		
		return new ResponseEntity<String>(RESPONSE_OK, HttpStatus.OK);
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
	
	private void processTrainActionHandler(TrainActionKey trainActionKey) throws TrainHandlingException {
		TrainActionHandler handler = actionHandlers.get(trainActionKey);
		if (handler != null) {
			handler.handle();
		}
	}
}
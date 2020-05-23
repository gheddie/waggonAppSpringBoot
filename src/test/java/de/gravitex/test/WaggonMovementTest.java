package de.gravitex.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import de.gravitex.test.def.WaggonDefinition;
import de.gravitex.test.entity.Train;
import de.gravitex.test.entity.TrainState;
import de.gravitex.test.entity.WaggonType;
import de.gravitex.test.util.TrainUtil;

public class WaggonMovementTest {

	@Test
	public void testWaggonMovementForward() {
		
		Train train = TrainUtil.createTrain(TrainState.READY_TO_GO, "Train1", WaggonDefinition.fromValues("W100", WaggonType.AVEX),
				WaggonDefinition.fromValues("W101", WaggonType.AVEX),
				WaggonDefinition.fromValues("W102", WaggonType.BVEX),
				WaggonDefinition.fromValues("W103", WaggonType.BVEX),
				WaggonDefinition.fromValues("W104", WaggonType.BVEX));
		assertEquals("W100#W101#W102#W103#W104", train.getWaggonListAsString());
		
		train.moveWaggonForward("W104");
		assertEquals("W100#W101#W102#W104#W103", train.getWaggonListAsString());
		
		train.moveWaggonForward("W104");
		assertEquals("W100#W101#W104#W102#W103", train.getWaggonListAsString());
		
		train.moveWaggonForward("W104");
		assertEquals("W100#W104#W101#W102#W103", train.getWaggonListAsString());
		
		train.moveWaggonForward("W104");
		assertEquals("W104#W100#W101#W102#W103", train.getWaggonListAsString());
		
		// already in front
		train.moveWaggonForward("W104");
		assertEquals("W104#W100#W101#W102#W103", train.getWaggonListAsString());
	}
	
	@Test
	public void testWaggonMovementBackward() {
		
		Train train = TrainUtil.createTrain(TrainState.READY_TO_GO, "Train1", WaggonDefinition.fromValues("W100", WaggonType.AVEX),
				WaggonDefinition.fromValues("W101", WaggonType.AVEX),
				WaggonDefinition.fromValues("W102", WaggonType.BVEX),
				WaggonDefinition.fromValues("W103", WaggonType.BVEX),
				WaggonDefinition.fromValues("W104", WaggonType.BVEX));
		assertEquals("W100#W101#W102#W103#W104", train.getWaggonListAsString());
		
		train.moveWaggonBackward("W100");
		assertEquals("W101#W100#W102#W103#W104", train.getWaggonListAsString());
		
		train.moveWaggonBackward("W100");
		assertEquals("W101#W102#W100#W103#W104", train.getWaggonListAsString());
		
		train.moveWaggonBackward("W100");
		assertEquals("W101#W102#W103#W100#W104", train.getWaggonListAsString());
		
		train.moveWaggonBackward("W100");
		assertEquals("W101#W102#W103#W104#W100", train.getWaggonListAsString());
		
		// already in the back
		train.moveWaggonBackward("W100");
		assertEquals("W101#W102#W103#W104#W100", train.getWaggonListAsString());
	}
	
	@Test
	public void testWaggonMovementInvalidWaggonNumber() {
		
		Train train = TrainUtil.createTrain(TrainState.READY_TO_GO, "Train1", WaggonDefinition.fromValues("W100", WaggonType.AVEX),
				WaggonDefinition.fromValues("W101", WaggonType.AVEX),
				WaggonDefinition.fromValues("W102", WaggonType.BVEX),
				WaggonDefinition.fromValues("W103", WaggonType.BVEX),
				WaggonDefinition.fromValues("W104", WaggonType.BVEX));
		assertEquals("W100#W101#W102#W103#W104", train.getWaggonListAsString());
		
		// not present...
		train.moveWaggonBackward("WXYZ");
		
		// nothing happened...
		assertEquals("W100#W101#W102#W103#W104", train.getWaggonListAsString());
	}
}
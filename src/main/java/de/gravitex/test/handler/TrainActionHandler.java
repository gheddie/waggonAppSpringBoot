package de.gravitex.test.handler;

import de.gravitex.test.exception.TrainHandlingException;

public abstract class TrainActionHandler {

	public abstract void handle() throws TrainHandlingException;
}
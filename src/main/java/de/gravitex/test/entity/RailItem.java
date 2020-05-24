package de.gravitex.test.entity;

import de.gravitex.test.util.TrainActionKey;

public interface RailItem {

	TrainActionKey generateKey(String action);
}
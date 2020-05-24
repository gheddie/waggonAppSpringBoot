package de.gravitex.test.entity;

import java.util.Collections;
import java.util.List;

import de.gravitex.test.util.TrainActionKey;
import lombok.Data;

@Data
public class Train implements RailItem {

	private String trainNumber;
	
	private TrainState trainState;
	
	private List<Waggon> waggons;

	public void moveWaggonForward(String waggonNumber) {
		int index = waggonIndex(waggonNumber);
		if (index == 0 || index == -1) {
			return;
		}
		Collections.swap(waggons, index, index-1);
	}

	public void moveWaggonBackward(String waggonNumber) {
		int index = waggonIndex(waggonNumber);
		if (index == waggons.size() - 1 || index == -1) {
			return;
		}
		Collections.swap(waggons, index, index+1);
	}
	
	public void removeWaggon(String waggonNumber) {
		int index = waggonIndex(waggonNumber);
		if (index == -1) {
			return;
		}
		waggons.remove(index);
	}
	
	public void moveWaggonToEnd(String waggonNumber) {
		int index = waggonIndex(waggonNumber);
		if (index == -1) {
			return;
		}
		Waggon waggonForEnd = waggons.remove(index);
		waggons.add(waggonForEnd);
	}

	private int waggonIndex(String waggonNumber) {
		int index = 0;
		for (Waggon waggon : waggons) {
			if (waggonNumber.equals(waggon.getWaggonNumber())) {
				return index;
			}
			index++;
		}
		return -1;
	}

	public
	String getWaggonListAsString() {
		String result = "";
		int index = 0;
		for (Waggon waggon : waggons) {
			result += waggon.getWaggonNumber();
			if (index < waggons.size() - 1) {
				result += "#";	
			}
			index++;
		}
		return result;
	}

	@Override
	public TrainActionKey generateKey(String action) {
		return TrainActionKey.fromValues(trainNumber, null, action);
	}
}
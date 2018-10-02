package zadatak.zadatak;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import zadatak.zadatak.Main.Direction;
import zadatak.zadatak.Main.Position;

class DrivingModule implements IDrivingModule {
	public static List<String> testMap = new ArrayList<String>();

	public Position go(Direction direction) {
		if (direction.name().toLowerCase().equals(testMap.get(0)) && (testMap.size() == 1)) {
			return Position.END;
		} else if (direction.name().toLowerCase().equals(testMap.get(0))) {
			testMap.remove(0);
			return Position.NORMAL;
		} else {
			return Position.NORMAL_UNCHANGED;
		}
	}

	public void defineMap(Boolean mapWithObstruction) {
		if (mapWithObstruction == false) {
			testMap = new ArrayList<String>(Arrays.asList("down", "down", "right", "right", "down", "down", "down",
					"right", "down", "down", "down", "down", "down", "right", "down", "down", "down"));
		} else {
			testMap = new ArrayList<String>(Arrays.asList("down", "down", "right", "right", "down", "down", "down",
					"right1", "down", "down", "down", "down", "down", "right", "down", "down", "down"));
		}

	}
}

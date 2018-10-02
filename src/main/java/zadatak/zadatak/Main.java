package zadatak.zadatak;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	public enum Position {
		START, // start position „S“
		NORMAL, // some position between start and end
		NORMAL_UNCHANGED, // same as NORMAL, but unchanged compared to previous position of vehicle
		END // end position „E“ }
	}

	public static void main(String[] args) {
		final String obstruction;
		if (args.length != 1) {
			throw new IllegalArgumentException("Invalid argument given!");
		} else {
			obstruction = args[0].toLowerCase();
			if (!obstruction.equals("true") && !obstruction.equals("false")) {
				throw new IllegalArgumentException("Invalid argument given!");
			}
		}
		final DrivingModule drivingModule = new DrivingModule();
		drivingModule.defineMap(Boolean.valueOf(obstruction));
		Position position;
		position = driveFromStartToEnd(drivingModule);
		System.out.println(position);
	}

	public static Position driveFromStartToEnd(DrivingModule drivingModule) {
		final List<Direction> returnPath = new ArrayList<Direction>();
		final List<Direction> directions = new ArrayList<Direction>(Arrays.asList(Direction.values()));

		Position currentPosition = Position.START;
		boolean returnFlag = false;
		while (currentPosition != Position.END) {
			for (int i = 0; i < directions.size(); i++) {
				if (returnPath.size() > 0) {
					// skip if direction is opposite of last used direction
					if (directions.get(i) == returnPath.get(returnPath.size() - 1)) {
						continue;
					}
				}

				final Position nextPosition = drivingModule.go(directions.get(i));

				// start position
				if ((currentPosition == Position.START) && (nextPosition == Position.NORMAL)) {
					currentPosition = nextPosition;
					returnPath.add(0, getOppositeDirection(directions.get(i)));
					returnFlag = false;
					break;
				}

				// in between or end position
				if (((nextPosition == Position.NORMAL) && (directions.get(i) != returnPath.get(returnPath.size() - 1)))
						|| (nextPosition == Position.END)) {
					currentPosition = nextPosition;
					returnPath.add(0, getOppositeDirection(directions.get(i)));
					returnFlag = false;
					break;
				}

				// change return flag for every wrong direction
				if (nextPosition == Position.NORMAL_UNCHANGED) {
					returnFlag = true;
				}
			}

			// if return flag is true break while loop
			if (returnFlag) {
				break;
			}

		}

		// return vehicle to start position if return flag has value true
		if (returnFlag) {
			for (final Direction direction : returnPath) {
				currentPosition = drivingModule.go(direction);
				// for testing purpose
				currentPosition = Position.START;
			}
		}

		return currentPosition;

	}

	public static Direction getOppositeDirection(Direction direction) {

		switch (direction) {
		case UP:
			return Direction.DOWN;
		case DOWN:
			return Direction.UP;
		case RIGHT:
			return Direction.LEFT;
		case LEFT:
			return Direction.RIGHT;
		default:
			throw new IllegalArgumentException("Invalid argument given!");
		}
	}
}

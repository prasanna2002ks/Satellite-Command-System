import java.util.logging.Level;
import java.util.logging.Logger;

class Satellite {
    String orientation = "North";
    String solarPanels = "Inactive";
    int dataCollected = 0;

    // Logger for logging events
	Logger LOGGER = Logger.getLogger(Satellite.class.getName());

    void rotate(String direction) {
        if (direction.equals("North") || direction.equals("South") || direction.equals("East") || direction.equals("West")) {
            orientation = direction;
            LOGGER.log(Level.INFO, "Satellite orientation set to " + direction);
        } else {
            LOGGER.log(Level.WARNING, "Invalid direction. Use North, South, East, or West.");
        }
    }

    void activatePanels() {
        if (solarPanels.equals("Inactive")) {
            solarPanels = "Active";
            LOGGER.log(Level.INFO, "Solar panels activated.");
        } else {
            LOGGER.log(Level.WARNING, "Solar panels are already active.");
        }
    }

    void deactivatePanels() {
        if (solarPanels.equals("Active")) {
            solarPanels = "Inactive";
            LOGGER.log(Level.INFO, "Solar panels deactivated.");
        } else {
            LOGGER.log(Level.WARNING, "Solar panels are already inactive.");
        }
    }

    void collectData() {
        if (solarPanels.equals("Active")) {
            dataCollected += 10;
            LOGGER.log(Level.INFO, "Data collected: " + dataCollected);
        } else {
            LOGGER.log(Level.WARNING, "Cannot collect data. Solar panels are inactive.");
        }
    }
}

class SatelliteCommandSystem {
    public static void main(String[] args) {
        Satellite satellite = new Satellite();
		
        satellite.rotate("South");
        satellite.activatePanels();
        satellite.collectData();
    }
}
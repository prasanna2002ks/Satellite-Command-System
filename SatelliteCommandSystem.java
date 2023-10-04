import java.util.logging.Level;
import java.util.logging.Logger;

class Satellite {
    String orientation = "North";
    String solarPanels = "Inactive";
    int dataCollected = 0;

    // Logger for logging events
    Logger LOGGER = Logger.getLogger(Satellite.class.getName());

    void rotate(String direction) {
        try {
            if (direction.equals("North") || direction.equals("South") || direction.equals("East") || direction.equals("West")) {
                orientation = direction;
                LOGGER.log(Level.INFO, "Satellite orientation set to " + direction);
            } else {
                throw new IllegalArgumentException("Invalid direction. Use North, South, East, or West.");
            }
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

    void activatePanels() {
        try {
            if (solarPanels.equals("Inactive")) {
                solarPanels = "Active";
                LOGGER.log(Level.INFO, "Solar panels activated.");
            } else {
                throw new IllegalStateException("Solar panels are already active.");
            }
        } catch (IllegalStateException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

    void deactivatePanels() {
        try {
            if (solarPanels.equals("Active")) {
                solarPanels = "Inactive";
                LOGGER.log(Level.INFO, "Solar panels deactivated.");
            } else {
                throw new IllegalStateException("Solar panels are already inactive.");
            }
        } catch (IllegalStateException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

    void collectData() {
        try {
            if (solarPanels.equals("Active")) {
                dataCollected += 10;
                LOGGER.log(Level.INFO, "Data collected: " + dataCollected);
            } else {
                throw new IllegalStateException("Cannot collect data. Solar panels are inactive.");
            }
        } catch (IllegalStateException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }
}

class SatelliteCommandSystem {
    public static void main(String[] args) {
        Satellite satellite = new Satellite();
        try {
            satellite.rotate("South");
            satellite.activatePanels();
            satellite.collectData();
            satellite.activatePanels(); // Try activating panels again (should trigger an exception)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

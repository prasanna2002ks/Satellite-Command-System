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
            if (direction.equalsIgnoreCase("North") || direction.equalsIgnoreCase("South")
                    || direction.equalsIgnoreCase("East") || direction.equalsIgnoreCase("West")) {
                orientation = direction;
                System.out.println("\n");
                LOGGER.info("\nSatellite orientation set to " + direction);
            } else {
                throw new IllegalArgumentException("!!!Invalid direction. Use North, South, East, or West.!!!");
            }
        } catch (IllegalArgumentException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    void activatePanels() {
        try {
            if (solarPanels.equals("Inactive")) {
                solarPanels = "Active";
                System.out.println("\n");
                LOGGER.info("\nSolar panels activated.");
            } else {
                throw new IllegalStateException("!!!Solar panels are already active.!!!");
            }
        } catch (IllegalStateException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    void deactivatePanels() {
        try {
            if (solarPanels.equals("Active")) {
                solarPanels = "Inactive";
                System.out.println("\n");
                LOGGER.info("\nSolar panels deactivated.");
            } else {
                throw new IllegalStateException("!!!Solar panels are already inactive.!!!");
            }
        } catch (IllegalStateException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    void collectData() {
        try {
			if(solarPanels.equals("Active") && orientation.equalsIgnoreCase("North")) {
				dataCollected += 20;
                System.out.println("\n");
                LOGGER.info("\nData collected : 20");
			} else if (solarPanels.equals("Active")) {
                dataCollected += 10;
                System.out.println("\n");
                LOGGER.info("\nData collected : 10");
            } else{
                throw new IllegalStateException("!!!Cannot collect data. Solar panels are inactive.!!!");
            }
        } catch (IllegalStateException e) {
            LOGGER.warning(e.getMessage());
        }
    }
}
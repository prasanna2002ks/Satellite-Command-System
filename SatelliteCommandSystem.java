import java.util.Scanner;
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
            if (direction.equalsIgnoreCase("North") || direction.equalsIgnoreCase("South") || direction.equalsIgnoreCase("East") || direction.equalsIgnoreCase("West")) {
                orientation = direction;
				System.out.println("\n");
				//LOGGER.log(Level.INFO, "Satellite orientation set to " + direction);
                LOGGER.info("\nSatellite orientation set to " + direction);
            } else {
                throw new IllegalArgumentException("!!!Invalid direction. Use North, South, East, or West.!!!");
            }
        } catch (IllegalArgumentException e) {
			//LOGGER.log(Level.WARNING, e.getMessage());
            LOGGER.warning(e.getMessage());  //shorthand code for above line.
        }
    }

    void activatePanels() {
        try {
            if (solarPanels.equals("Inactive")) {
                solarPanels = "Active";
				System.out.println("\n");
                //LOGGER.log(Level.INFO, "\nSolar panels activated.");
				LOGGER.info("\nSolar panels activated.");
            } else {
                throw new IllegalStateException("!!!Solar panels are already active.!!!");
            }
        } catch (IllegalStateException e) {
            //LOGGER.log(Level.WARNING, e.getMessage());
			LOGGER.warning(e.getMessage());
        }
    }

    void deactivatePanels() {
        try {
            if (solarPanels.equals("Active")) {
                solarPanels = "Inactive";
				System.out.println("\n");
                //LOGGER.log(Level.INFO, "\nSolar panels deactivated.");
				LOGGER.info("\nSolar panels deactivated.");
            } else {
                throw new IllegalStateException("!!!Solar panels are already inactive.!!!");
            }
        } catch (IllegalStateException e) {
            //LOGGER.log(Level.WARNING, e.getMessage());
			LOGGER.warning(e.getMessage());
        }
    }

    void collectData() {
        try {
            if (solarPanels.equals("Active")) {
                dataCollected += 10;
				System.out.println("\n");
                //LOGGER.log(Level.INFO, "\nData collected : 10");
				LOGGER.info("\nData collected : 10");
            } else {
                throw new IllegalStateException("!!!Cannot collect data. Solar panels are inactive.!!!");
            }
        } catch (IllegalStateException e) {
            //LOGGER.log(Level.WARNING, e.getMessage());
			LOGGER.warning(e.getMessage());
        }
    }
}

class SatelliteCommandSystem {
    public static void main(String[] args) {
        Satellite satellite = new Satellite();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\nSatellite Command System");
        System.out.println("\nInitial state:");
        printSatelliteState(satellite);

        while (true) {
            System.out.println("\nEnter a command (rotate, activatePanels, deactivatePanels, collectData, exit):");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                executeCommand(satellite, input);
            } catch (IllegalArgumentException e) {
                System.out.println("!!!Invalid command. Please enter a valid command.!!!");
            }

            printSatelliteState(satellite);
        }
    }

    private static void executeCommand(Satellite satellite, String command) {
		Scanner scanner = new Scanner(System.in);
		switch(command.toLowerCase()) {
			case "rotate":
				System.out.print("Enter Direction to Rotate : ");
				String direction = scanner.nextLine();
				satellite.rotate(direction);
				break;
			case "activatepanels":
				satellite.activatePanels();
				break;
			case "deactivatepanels":
				satellite.deactivatePanels();
				break;
			case "collectdata":
				satellite.collectData();
				break;
			default:
				throw new IllegalArgumentException("!!!Invalid command.!!!");
		}
    }

    private static void printSatelliteState(Satellite satellite) {
        System.out.println("\nSatellite state:");
        System.out.println("Orientation: " + satellite.orientation);
        System.out.println("Solar Panels: " + satellite.solarPanels);
        System.out.println("Data Collected: " + satellite.dataCollected);
    }
}
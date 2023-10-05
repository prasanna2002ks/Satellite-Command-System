import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

// Command interface
interface Command {
    void execute();
}

// Child command classes
class RotateCommand implements Command {
    private Satellite satellite;
    private String direction;

    public RotateCommand(Satellite satellite, String direction) {
        this.satellite = satellite;
        this.direction = direction;
    }

    @Override
    public void execute() {
        satellite.rotate(direction);
    }
}

class ActivatePanelsCommand implements Command {
    private Satellite satellite;

    public ActivatePanelsCommand(Satellite satellite) {
        this.satellite = satellite;
    }

    @Override
    public void execute() {
        satellite.activatePanels();
    }
}

class DeactivatePanelsCommand implements Command {
    private Satellite satellite;

    public DeactivatePanelsCommand(Satellite satellite) {
        this.satellite = satellite;
    }

    @Override
    public void execute() {
        satellite.deactivatePanels();
    }
}

class CollectDataCommand implements Command {
    private Satellite satellite;

    public CollectDataCommand(Satellite satellite) {
        this.satellite = satellite;
    }

    @Override
    public void execute() {
        satellite.collectData();
    }
}

/*class ToggleSatellite implements Command {
	private Satellite satellite;
	
	public ToggleSatellite(Satellite satellite){
		this.satellite = satellite;
	}
	
	@Override
	public void execute() {
		if(satellite.solarPanels.equals("Active")){
			satellite.deactivatePanels();
		} else {
			satellite.activatePanels();
		}
	}
	
}*/

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

class SatelliteCommandSystem2 {
    public static void main(String[] args) {
        Satellite satellite = new Satellite();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\nSatellite Command System");
        System.out.println("\nInitial state:");
        printSatelliteState(satellite);

        while (true) {
            System.out.println("\nEnter a command (rotate, activatePanels, deactivatePanels, collectData, Status, Toggle ,reset, exit):");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                Command command = createCommand(satellite, input);
				if(command!=null){
					command.execute();
				}
            } catch (IllegalArgumentException e) {
                System.out.println("!!!Invalid command. Please enter a valid command.!!!");
            }

            printSatelliteState(satellite);
        }
    }
	
	public static void ToggleSatellite(Satellite satellite) {
		if(satellite.solarPanels.equals("Active")){
			satellite.deactivatePanels();
		} else {
			satellite.activatePanels();
		}
	}
	
	public static void reset(Satellite satellite){
		satellite.orientation = "North";
		satellite.solarPanels = "Inactive";
		satellite.dataCollected = 0;
	}

    private static Command createCommand(Satellite satellite, String command) {
        Scanner scanner = new Scanner(System.in);
        switch (command.toLowerCase()) {
            case "rotate":
                System.out.print("Enter Direction to Rotate : ");
                String direction = scanner.nextLine();
                return new RotateCommand(satellite, direction);
            case "activatepanels":
                return new ActivatePanelsCommand(satellite);
            case "deactivatepanels":
                return new DeactivatePanelsCommand(satellite);
            case "collectdata":
                return new CollectDataCommand(satellite);
			case "status":
				printSatelliteState(satellite);
				return null;
			case "toggle":
				ToggleSatellite(satellite);
				return null;
			case "reset":
				reset(satellite);
				printSatelliteState(satellite);
				return null;
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
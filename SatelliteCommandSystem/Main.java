import java.util.Scanner;

class Main {
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
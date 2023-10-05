# Satellite-Command-System

Here's an explanation of the code line by line:

**import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;**
These lines import necessary classes and packages for the program. Scanner is used for user input, and Logger is used for logging messages

**interface Command {
    void execute();
}**
Here, we define a Command interface. This interface will be implemented by concrete command classes to encapsulate different satellite operations.

**class RotateCommand implements Command {
    // ...
}**
These lines define a concrete command class RotateCommand, which implements the Command interface. This class encapsulates the "rotate" operation.

**class ActivatePanelsCommand implements Command {
    // ...
}**
This class, ActivatePanelsCommand, encapsulates the "activatePanels" operation.

**class DeactivatePanelsCommand implements Command {
    // ...
}**
This class, DeactivatePanelsCommand, encapsulates the "deactivatePanels" operation.

**class CollectDataCommand implements Command {
    // ...
}**
This class, CollectDataCommand, encapsulates the "collectData" operation.

**class Satellite {
    // ...
}**
Here, we define the Satellite class, which represents the satellite with its properties and operations.

**class SatelliteCommandSystem {
    // ...
}**
This class, SatelliteCommandSystem, is the main class of the program and contains the main method where the program execution begins.

**public static void main(String[] args) {
    // ...
}**
The main method is the entry point of the program. It initializes a Satellite object, sets up user input with a Scanner, and starts the command loop.

**System.out.println("\n\nSatellite Command System");
System.out.println("\nInitial state:");
printSatelliteState(satellite);
**
These lines print a header and the initial state of the satellite.

**while (true) {
    // ...
}**
This is the main command loop that keeps running until the user enters "exit."

**System.out.println("\nEnter a command (rotate, activatePanels, deactivatePanels, collectData, exit):");
String input = scanner.nextLine();**
Here, it prompts the user to enter a command and reads the input.

**if (input.equalsIgnoreCase("exit")) {
    break;
}**
If the user enters "exit," the loop terminates, and the program ends.

**try {
    Command command = createCommand(satellite, input);
    command.execute();
} catch (IllegalArgumentException e) {
    System.out.println("!!!Invalid command. Please enter a valid command.!!!");
}
**
In this block, it tries to create a command based on the user input and execute it. If an invalid command is entered, it catches the IllegalArgumentException and displays an error message.

**private static Command createCommand(Satellite satellite, String command) {
    Scanner scanner = new Scanner(System.in);
    switch (command.toLowerCase()) {
        // ...
    }
}**
The createCommand method takes the user input and returns an appropriate command object based on the input. It handles different commands like "rotate," "activatePanels," "deactivatePanels," and "collectData."

**private static void printSatelliteState(Satellite satellite) {
    System.out.println("\nSatellite state:");
    System.out.println("Orientation: " + satellite.orientation);
    System.out.println("Solar Panels: " + satellite.solarPanels);
    System.out.println("Data Collected: " + satellite.dataCollected);
}**
This method is responsible for printing the current state of the satellite, including its orientation, solar panel status, and data collected. It helps in displaying the satellite's state to the user.


Command Design Pattern:
          * The Command Pattern encapsulates a request as an object, thereby allowing for parameterization of clients with queues, requests, and operations.
In this case, we can create command objects for different satellite operations: RotateCommand, ActivatePanelsCommand, DeactivatePanelsCommand, and CollectDataCommand.
          * This pattern decouples the sender (user) from the receiver (Satellite) and allows you to extend the operations easily.

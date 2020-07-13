package sample;

import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;

public class Action extends Thread
{
    int numberOfWorkers = 3, numberOfTrucks = 1, numberOfRepetitions, conveyorBeltMaxSize, conveyorBeltCapacity, truckCapacity;
    Employee[] workers;
    Truck[] trucks;
    ConveyorBelt conveyorBelt;
    GridPane grid;

    public Action(int numberOfRepetitions, int conveyorBeltMaxSize, int conveyorBeltCapacity, int truckCapacity, GridPane grid)
    {
        super("Action");
        this.numberOfRepetitions = numberOfRepetitions;
        this.conveyorBeltMaxSize = conveyorBeltMaxSize;
        this.conveyorBeltCapacity = conveyorBeltCapacity;
        this.truckCapacity = truckCapacity;
        this.grid = grid;

        workers = new Employee[numberOfWorkers];
        trucks = new Truck[numberOfTrucks];
        conveyorBelt = new ConveyorBelt(conveyorBeltMaxSize, conveyorBeltCapacity, grid);
    }

    public void run()
    {
        if(numberOfRepetitions <= 0 || conveyorBeltMaxSize <=0 || conveyorBeltCapacity <= 0 || truckCapacity <= 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Wprowadzono niepoprawne dane!");
            alert.showAndWait();
        }

        for (int i=0;i<numberOfWorkers;i++) workers[i] = new Employee(i+1, numberOfRepetitions, conveyorBelt, grid);

        for (int i=0;i<numberOfTrucks;i++) trucks[i] = new Truck(truckCapacity, numberOfRepetitions*numberOfWorkers/numberOfTrucks, conveyorBelt, grid);

        for (int i=0;i<numberOfWorkers;i++) workers[i].start();

        for (int i=0;i<numberOfTrucks;i++) trucks[i].start();

        try
        {
            for (int i=0;i<numberOfWorkers;i++) workers[i].join();

            for (int i=0;i<numberOfTrucks;i++) trucks[i].join();
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println("End of program.");
    }
}
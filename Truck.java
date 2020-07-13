package sample;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Truck extends Thread
{
    private int truckNumber, capacity, currentLoading, numberOfRepetitions, rowIndex;
    public ConveyorBelt conveyorBelt;
    Brick brick;
    GridPane grid;

    public Truck(int capacity, int numberOfRepetitions, ConveyorBelt conveyorBelt, GridPane grid)
    {
        super("Truck-");
        this.truckNumber = 1;
        this.capacity = capacity;
        this.currentLoading = 0;
        this.numberOfRepetitions = numberOfRepetitions;
        this.conveyorBelt = conveyorBelt;
        this.grid = grid;
        this.rowIndex = 1;
    }

    public void run()
    {
        for (int i=0;i<numberOfRepetitions;i++)
        {
            load();
        }
    }

    public void load()
    {
        try
        {
            brick = conveyorBelt.take();
        }catch(InterruptedException e)
        {
            e.printStackTrace();
        }

        if(currentLoading + brick.getWeight() > capacity)
        {
            System.out.println("The truck is full.");
            truckNumber++;
            currentLoading = 0;
        }

        currentLoading += brick.getWeight();
        System.out.println("To " + getName() + truckNumber + " was loaded: " + currentLoading);

        String pom = String.valueOf(brick.getWeight());
        String pomNr = String.valueOf(truckNumber);
        Label pom2 = new Label("        "+pom+"        ");
        pom2.setStyle("-fx-border-color: black");
        Label pomNrL;
        if(truckNumber<10) pomNrL = new Label("          "+pomNr+"            ");
        else pomNrL = new Label("          "+pomNr+"          ");
        pomNrL.setStyle("-fx-border-color: black");

        table(pom2, 4);
        table(pomNrL, 5);

        rowIndex++;
    }

    public void table(Label pom2, int columnIndex)
    {
        GridPane.setConstraints(pom2, columnIndex, rowIndex);
        Platform.runLater(new Runnable() {
            @Override
            public void run()
            {
                grid.getChildren().addAll(pom2);
            }
        });
    }
}
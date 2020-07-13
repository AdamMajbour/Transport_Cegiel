package sample;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.util.LinkedList;
import java.util.Queue;

public class ConveyorBelt
{
    private Queue<Brick> conveyorBelt = new LinkedList<>();
    private int capacity, maxLoad, currentLoading, rowIndex;
    GridPane grid;

    public ConveyorBelt(int capacity, int maxLoad, GridPane grid)
    {
        this.capacity = capacity;
        this.maxLoad = maxLoad;
        this.currentLoading = 0;
        this.grid = grid;
        this.rowIndex = 1;
    }

    public synchronized void put(Brick cegla) throws InterruptedException
    {
        while (conveyorBelt.size()==capacity || currentLoading + cegla.getWeight() > maxLoad)
        {
            wait();
        }
        conveyorBelt.add(cegla);
        currentLoading += cegla.getWeight();

        String pom = String.valueOf(cegla.getWeight());
        Label pom2 = new Label("    "+pom+"     ");
        pom2.setStyle("-fx-border-color: black");

        table(pom2, 3);

        rowIndex++;

        System.out.println("Brick was added to the Conveyor Belt. Weight: " + cegla.getWeight());
        System.out.println("Actual Conveyor Belt:");
        for(Brick brickTest : this.conveyorBelt)
            System.out.println(brickTest);

        notify();
    }

    public synchronized Brick take() throws InterruptedException
    {
        while (conveyorBelt.isEmpty())
        {
            wait();
        }
        Brick item = conveyorBelt.remove();
        currentLoading -= item.getWeight();

        System.out.println("Brick was removed from the Conveyor Belt. Weight: " + item.getWeight());
        System.out.println("Actual Conveyor Belt:");
        for(Brick brickTest : this.conveyorBelt)
            System.out.println(brickTest);

        notify();

        return item;
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
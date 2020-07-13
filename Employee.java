package sample;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.util.Random;

public class Employee extends Thread
{
    private int numberOfRepetitions, rowIndex;
    private Brick brick;
    public ConveyorBelt conveyorBelt;
    private Random brickWeight;
    GridPane grid;

    public Employee(int employeeId, int numberOfRepetitions, ConveyorBelt conveyorBelt, GridPane grid)
    {
        super("P"+employeeId);
        this.numberOfRepetitions = numberOfRepetitions;
        this.conveyorBelt = conveyorBelt;
        brickWeight = new Random();
        this.grid = grid;
        this.rowIndex = 1;
    }

    public void run()
    {
        for(int i=0;i<numberOfRepetitions;i++)
        {
            brickProduction();

            try
            {
                conveyorBelt.put(brick);
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void brickProduction()
    {
        brick = new Brick(brickWeight.nextInt(3)+1);

        try {
            sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String pom = String.valueOf(brick.getWeight());
        Label pom2 = new Label(" "+pom+" ");
        pom2.setStyle("-fx-border-color: black");

        if(getName().equals("P1")) table(pom2, 0);
        else if(getName().equals("P2")) table(pom2, 1);
        else if(getName().equals("P3")) table(pom2, 2);

        rowIndex++;

        System.out.println(getName()+" produced brick. Weight: " + brick.getWeight());
    }

    public void table(Label pom2, int columnIndex)
    {
        GridPane.setConstraints(pom2, columnIndex, rowIndex);
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                grid.getChildren().addAll(pom2);
            }
        });
    }
}
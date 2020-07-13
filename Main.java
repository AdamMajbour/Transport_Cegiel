package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    Action action;
    Button button;
    GridPane grid;
    Group root;
    Scene scene;
    Text text0, text1, text2, text3, text4, text5, legenda;
    TextField TFtruckCapacity, TFconvSize, TFconvCapacity, TFlp;
    Label P1label, P2label, P3label, tasmaLabel, ZaladunekLabel, nrCiezLabel;
    VBox propertiesVB, textFieldsVB, tableKeyVB, signatureVB, tableVB;
    ScrollBar sc;

    @Override
    public void start(Stage window)
    {
        window.setTitle("Transport cegieł");
        window.setWidth(550);
        window.setHeight(450);

        creatingElements();
        creatingGrid();
        creatingVboxes();
        creatingScrollBar();

        root = new Group(propertiesVB, textFieldsVB, tableKeyVB, signatureVB, tableVB);
        scene = new Scene(root, 500, 500, Color.ALICEBLUE);
        root.getChildren().add(sc);

        button.setOnAction(event -> {
            grid.getChildren().clear();
            grid.getChildren().addAll(P1label,P2label,P3label,tasmaLabel,ZaladunekLabel,nrCiezLabel);
            try
            {
                if(Integer.parseInt(TFlp.getText())<=0 || Integer.parseInt(TFconvSize.getText())<=0 ||
                    Integer.parseInt(TFconvCapacity.getText())<=0 || Integer.parseInt(TFtruckCapacity.getText())<=0)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Wprowadzono niepoprawne dane!");
                    alert.setContentText("Trzeba wprowadzić wszystkie dane i muszą być dodatnie.");
                    alert.showAndWait();
                    Platform.exit();
                }

                action = new Action(Integer.parseInt(TFlp.getText()), Integer.parseInt(TFconvSize.getText()),
                        Integer.parseInt(TFconvCapacity.getText()), Integer.parseInt(TFtruckCapacity.getText()), grid);
            }catch (NumberFormatException e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Wprowadzono niepoprawne dane!");
                alert.setContentText("Trzeba wprowadzić wszystkie dane i muszą być dodatnie.");
                alert.showAndWait();
                Platform.exit();
            }
            action.start();
        });

        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void creatingElements()
    {
        text0 = new Text("Wykonawca: Adam Majbour-Majek\nGrupa: WCY18IY5S1");
        text0.setX(320);
        text0.setY(430);

        text1 = new Text("Właściwości:");
        text1.setX(320);
        text1.setY(15);
        text1.setFont(Font.font(null, FontWeight.BOLD, 12));

        text2 = new Text("Ładowność ciężarówki = ");
        text2.setX(320);
        text2.setY(30);

        text3 = new Text("Pojemność taśmy = ");
        text3.setX(320);
        text3.setY(60);

        text4 = new Text("Udźwig taśmy = ");
        text4.setX(320);
        text4.setY(90);

        text5 = new Text("Liczba powtórzeń = ");
        text5.setX(320);
        text5.setY(120);

        TFtruckCapacity = new TextField();
        TFtruckCapacity.setLayoutX(470);
        TFtruckCapacity.setLayoutY(15);
        TFtruckCapacity.setPrefWidth(50);

        TFconvSize = new TextField();
        TFconvSize.setLayoutX(470);
        TFconvSize.setLayoutY(45);
        TFconvSize.setPrefWidth(50);

        TFconvCapacity = new TextField();
        TFconvCapacity.setLayoutX(470);
        TFconvCapacity.setLayoutY(75);
        TFconvCapacity.setPrefWidth(50);

        TFlp = new TextField();
        TFlp.setLayoutX(470);
        TFlp.setLayoutY(105);
        TFlp.setPrefWidth(50);

        P1label = new Label("P1");
        P1label.setStyle("-fx-border-color: black; -fx-font-weight: bold");
        P2label = new Label("P2");
        P2label.setStyle("-fx-border-color: black; -fx-font-weight: bold");
        P3label = new Label("P3");
        P3label.setStyle("-fx-border-color: black; -fx-font-weight: bold");
        tasmaLabel = new Label("Taśma");
        tasmaLabel.setStyle("-fx-border-color: black; -fx-font-weight: bold");
        ZaladunekLabel = new Label("Załadunek");
        ZaladunekLabel.setStyle("-fx-border-color: black; -fx-font-weight: bold");
        nrCiezLabel = new Label("Nr ciężarówki");
        nrCiezLabel.setStyle("-fx-border-color: black; -fx-font-weight: bold");

        button = new Button("Start");
        button.relocate(430, 135);
        button.setPrefWidth(100);

        legenda = new Text("Legenda:\nP1,P2,P3 - wagi cegieł wyprodukowanych\nprzez danego pracownika\nTaśma - cegły o danej wadze, które\naktualnie znajdują się na taśmie\n" +
                "W ciężarówce - wagi cegieł, które\nznajdują się w ciężarówce\nNr ciężarówki - numer ciężarówki, do\nktórej ładowane są cegły");
        legenda.setX(320);
        legenda.setY(220);
    }

    public void creatingGrid()
    {
        grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(0);
        grid.setHgap(0);
        GridPane.setConstraints(P1label,0, 0);
        GridPane.setConstraints(P2label,1, 0);
        GridPane.setConstraints(P3label,2, 0);
        GridPane.setConstraints(tasmaLabel,3, 0);
        GridPane.setConstraints(ZaladunekLabel,4, 0);
        GridPane.setConstraints(nrCiezLabel,5, 0);

        grid.getChildren().addAll(P1label,P2label,P3label,tasmaLabel,ZaladunekLabel,nrCiezLabel);
    }

    public void creatingVboxes()
    {
        propertiesVB = new VBox(text1, text2, text3, text4, text5, button);
        propertiesVB.setSpacing(14);
        propertiesVB.setPadding(new Insets(10));

        textFieldsVB = new VBox(TFtruckCapacity, TFconvSize, TFconvCapacity, TFlp);
        textFieldsVB.setSpacing(5);
        textFieldsVB.setPadding(new Insets(37));
        textFieldsVB.setLayoutX(110);

        tableKeyVB = new VBox(legenda);
        tableKeyVB.setPadding(new Insets(10));
        tableKeyVB.setLayoutY(190);

        signatureVB = new VBox(text0);
        signatureVB.setPadding(new Insets(10));
        signatureVB.setLayoutY(360);

        tableVB = new VBox(grid);
        tableVB.setPadding(new Insets(5));
        tableVB.setLayoutX(250);
    }

    public void creatingScrollBar()
    {
        sc = new ScrollBar();
        sc.setLayoutX(520);
        sc.setMin(0);
        sc.setOrientation(Orientation.VERTICAL);
        sc.setPrefHeight(410);
        sc.setMax(5500);
        sc.valueProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                tableVB.setLayoutY(-newValue.doubleValue());
            }
        });
    }
}
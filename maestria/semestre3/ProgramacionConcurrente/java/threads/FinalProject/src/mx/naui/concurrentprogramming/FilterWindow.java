package mx.naui.concurrentprogramming;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.image.ImageView;

/**
 *
 * @author humberto
 */
public class FilterWindow {
  private static final ImageView pic = new ImageView();
  // TODO hacer que se cree una instancia de esta clase en lugar de solo statics?
  public static void display(String title, String message, Filter filter) {
    Stage window = new Stage();
    //Block events to other window
    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle(title);
    window.setMinWidth(250);
    
    Label label = new Label();
    label.setText(message);
    Button closeButton = new Button("Close");
    closeButton.setOnAction(e->window.close());
    
    final ComboBox filterComboBox = new ComboBox();
    filterComboBox.getItems().addAll(
        "Identity",
        "Sharpen",
        "Box Blur",
        "Gaussian Blur",
        "Edge Enhance",
        "Edge Detect",
        "Edge Detect 2",
        "Edge Detect 3",
        "Edge Emboss"
    );
    filterComboBox.setValue("Identity");
    
    TextField division = new TextField();
    division.setMaxWidth(4);
    
    TextField offset = new TextField();
    offset.setMaxWidth(4);
    
    GridPane leftMenu = new GridPane();
    leftMenu.setVgap(4);
    leftMenu.setHgap(10);
    leftMenu.setPadding(new Insets(5, 5, 5, 5));
    leftMenu.add(new Label("Filter: "), 0, 0);
    leftMenu.add(filterComboBox, 1, 0);
    leftMenu.add(new Label("Division: "), 0, 1);
    leftMenu.add(division, 1, 1);
    leftMenu.add(new Label("Offset: "), 0, 1);
    leftMenu.add(division, 1, 1);
    
    
    pic.setFitHeight(300);
    pic.setPreserveRatio(true);
    
    VBox vbox = new VBox();
    vbox.setMinWidth(400);
    vbox.setAlignment(Pos.CENTER);
    vbox.setSpacing(10);
    vbox.setPadding(new Insets(0, 10, 0, 10));
    vbox.getChildren().add(pic);
    
    BorderPane borderPane = new BorderPane();
    borderPane.setLeft(leftMenu);
    borderPane.setRight(vbox);
    
    //Display window and wait for it to be closed before returning
    Scene scene = new Scene(borderPane);
    window.setScene(scene);
    window.showAndWait();
  }
}

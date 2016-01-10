package mx.naui.concurrentprogramming;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.*;

/**
 *
 * @author humberto
 */
public class FinalProject extends Application {

  private static final Logger logger = LogManager.getLogger(FinalProject.class);
  private Stage window;
  private final VBox vbox = new VBox();
  private final ImageView pic = new ImageView();
  private final Label name = new Label();
  private Desktop desktop = Desktop.getDesktop();

  @Override
  public void start(Stage primaryStage) {
    window = primaryStage;
    window.setTitle("Final Project");
    window.setOnCloseRequest(e -> {
      e.consume(); // In other words, hey Java, we've got it from here
      closeProgram();
    });

    Scene scene = new Scene(new VBox(), 500, 400);
    scene.setFill(Color.OLDLACE);

    name.setFont(new Font("Verdana Bold", 22));
    pic.setFitHeight(300);
    pic.setPreserveRatio(true);

    MenuBar menuBar = new MenuBar();
    vbox.setAlignment(Pos.CENTER);
    vbox.setSpacing(10);
    vbox.setPadding(new Insets(0, 10, 0, 10));
    vbox.getChildren().addAll(name, pic);

    // --- Menu File
    Menu menuFile = new Menu("File");

    MenuItem menuOpen = new MenuItem("Open");
    menuOpen.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
    menuOpen.setOnAction(menuLoadEventListener);

    MenuItem menuClear = new MenuItem("Clear");
    menuClear.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
    menuClear.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        vbox.setVisible(false);
        pic.setImage(null);
        name.setText(null);
      }
    });

    MenuItem menuExit = new MenuItem("Exit");
    menuExit.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
    menuExit.setOnAction(e -> closeProgram());

    menuFile.getItems().addAll(menuOpen, menuClear, new SeparatorMenuItem(), menuExit);

    
    // --- Menu Edit
    Menu menuEdit = new Menu("Edit");
    Menu menuFilter = new Menu("Picture Filter");
    //Picture Effect menu
    final ToggleGroup groupFilter = new ToggleGroup();
    RadioMenuItem itemFilter = new RadioMenuItem("Identity");
    itemFilter.setUserData(null);
    itemFilter.setToggleGroup(groupFilter);
    menuFilter.getItems().add(itemFilter);

    menuEdit.getItems().addAll(menuFilter);
    
    
    // --- Menu View
    Menu menuView = new Menu("View");
    CheckMenuItem titleView = createMenuItem("Title", name);
    CheckMenuItem picView = createMenuItem("Picture", pic);
    picView.setDisable(true);
    menuView.getItems().addAll(titleView, picView);
    

    menuBar.getMenus().addAll(menuFile, menuEdit, menuView);

    ((VBox) scene.getRoot()).getChildren().addAll(menuBar, vbox);

    window.setScene(scene);

    window.show();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

  public void closeProgram() {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Confirm close");
    alert.setHeaderText("You are about to close the application.");
    alert.setContentText("Are you sure you want to continue?");

    ButtonType buttonTypeClose = ButtonType.CLOSE;
    ButtonType buttonTypeCancel = ButtonType.CANCEL;

    alert.getButtonTypes().setAll(buttonTypeCancel, buttonTypeClose);

    Optional<ButtonType> answer = alert.showAndWait();

    if (answer.get() == ButtonType.CLOSE) {
      logger.debug("Closed");
      System.exit(0);
    }
  }

  private static CheckMenuItem createMenuItem(String title, final Node node) {
    CheckMenuItem cmi = new CheckMenuItem(title);
    cmi.setSelected(true);
    cmi.selectedProperty().addListener(new ChangeListener<Boolean>() {
      public void changed(ObservableValue ov,
              Boolean old_val, Boolean new_val) {
        node.setVisible(new_val);
      }
    });
    return cmi;
  }

  EventHandler<ActionEvent> menuLoadEventListener
          = new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent t) {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("View Pictures");
      fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
      //Set extension filter
      FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
      FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
      fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

      //Show open file dialog
      File file = fileChooser.showOpenDialog(null);

      try {
        BufferedImage bufferedImage = ImageIO.read(file);
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        pic.setImage(image);
        name.setText(file.getName());
        vbox.setVisible(true);
      } catch (IOException ex) {
        logger.error(ex);
      }

    }
  };

}

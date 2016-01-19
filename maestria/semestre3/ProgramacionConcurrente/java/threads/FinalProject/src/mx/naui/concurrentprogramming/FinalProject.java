package mx.naui.concurrentprogramming;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
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
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 *
 * @author humberto
 */
public class FinalProject extends Application {

  static {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
  }

  private static final Logger logger = LogManager.getLogger(FinalProject.class);
  private Stage window;
  private Scene mainScene;
  private final VBox vbox = new VBox();
  private final ImageView pic = new ImageView();
  private Splitter filter;
  private final Label name = new Label();

  @Override
  public void start(Stage primaryStage) {
    window = primaryStage;
    window.setTitle("Final Project");
    window.setOnCloseRequest(e -> {
      e.consume(); // In other words, hey Java, we've got it from here
      closeProgram();
    });

    mainScene = new Scene(new VBox(), 700, 450);
    mainScene.setFill(Color.OLDLACE);

    name.setFont(new Font("Verdana Bold", 22));
    pic.setFitHeight(350);
    pic.setPreserveRatio(true);

    MenuBar menuBar = new MenuBar();
    vbox.setAlignment(Pos.CENTER);
    vbox.setSpacing(10);
    vbox.setPadding(new Insets(0, 10, 0, 10));
    vbox.getChildren().addAll(name, pic);

    // --- Menu Edit
    Menu menuEdit = new Menu("Edit");
    MenuItem menuFilter = new MenuItem("Picture Filter");
    menuFilter.setAccelerator(KeyCombination.keyCombination("Ctrl+F"));
    menuFilter.setDisable(true);
    menuFilter.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {
        openFilterWindow();
      }
    });

    menuEdit.getItems().add(menuFilter);

    // --- Menu File
    Menu menuFile = new Menu("File");

    MenuItem menuOpen = new MenuItem("Open");
    menuOpen.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
    //menuOpen.setOnAction(menuLoadEventListener);
    menuOpen.setOnAction(new EventHandler<ActionEvent>() {
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

        filter = new Splitter(file.getPath());
        Image image = SwingFXUtils.toFXImage(toBufferedImage(filter.getImage()), null);
        pic.setImage(image);
        name.setText(file.getName());
        vbox.setVisible(true);
        menuFilter.setDisable(false);
      }
    });

    MenuItem menuClear = new MenuItem("Clear");
    menuClear.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
    menuClear.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        vbox.setVisible(false);
        pic.setImage(null);
        name.setText(null);
        filter = null;
        menuFilter.setDisable(true);
      }
    });

    MenuItem menuExit = new MenuItem("Exit");
    menuExit.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
    menuExit.setOnAction(e -> closeProgram());

    menuFile.getItems().addAll(menuOpen, menuClear, new SeparatorMenuItem(), menuExit);

    // --- Menu View
    Menu menuView = new Menu("View");
    CheckMenuItem titleView = createMenuItem("Name", name);
    CheckMenuItem picView = createMenuItem("Picture", pic);
    picView.setDisable(true);
    menuView.getItems().addAll(titleView, picView);

    menuBar.getMenus().addAll(menuFile, menuEdit, menuView);

    ((VBox) mainScene.getRoot()).getChildren().addAll(menuBar, vbox);

    window.setScene(mainScene);

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

  public BufferedImage toBufferedImage(Mat matrix) {
    int type = BufferedImage.TYPE_BYTE_GRAY;
    if (matrix.channels() > 1) {
      type = BufferedImage.TYPE_3BYTE_BGR;
    }
    int bufferSize = matrix.channels() * matrix.cols() * matrix.rows();
    byte[] buffer = new byte[bufferSize];
    matrix.get(0, 0, buffer); // get all the pixels
    BufferedImage image = new BufferedImage(matrix.cols(), matrix.
            rows(), type);
    final byte[] targetPixels = ((DataBufferByte) image.getRaster().
            getDataBuffer()).getData();
    System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);
    return image;
  }

  /*EventHandler<ActionEvent> menuLoadEventListener
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

      //try {
        filter = new Filter(file.getPath());
        //BufferedImage bufferedImage = ImageIO.read(file);
        Image image = SwingFXUtils.toFXImage(toBufferedImage(filter.getImage()), null);
        pic.setImage(image);
        name.setText(file.getName());
        vbox.setVisible(true);
      //} catch (IOException ex) {
      //  logger.error(ex);
      //}
    }
  };*/
  public void openFilterWindow() {
    FilterWindow.display("Filter Selection", "Select a filter", filter);
  }
}

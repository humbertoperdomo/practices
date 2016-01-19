package mx.naui.concurrentprogramming;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.*;
import org.opencv.core.Mat;

/**
 *
 * @author humberto
 */
public class FilterWindow {

  private static final Logger logger = LogManager.getLogger(FilterWindow.class);
  private static final ImageView pic = new ImageView();

  public static void display(String title, String message, Splitter filter) {
    Stage window = new Stage();
    //Block events to other window
    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle(title);
    window.setMinWidth(250);

    final ComboBox<Kernel> filterComboBox = new ComboBox();
    filterComboBox.getItems().addAll(Kernel.values());
    filterComboBox.setValue(Kernel.IDENTITY);

    NumberTextField divisor = new NumberTextField("1", 2);
    divisor.setMaxWidth(40);

    NumberTextField offset = new NumberTextField("0", 2);
    offset.setMaxWidth(40);

    Button applyButton = new Button("Apply");
    applyButton.setDefaultButton(true);
    applyButton.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {
        filter.setDivisor(Double.parseDouble(divisor.getText()));
        filter.setOffset(Double.parseDouble(offset.getText()));
        filter.setSelectedFilter(filterComboBox.getValue().getIndex());
        filter.setNSlices(10);
        filter.doSomething();
        displayImage(filter.getOutImage());
        logger.debug("Filter applied!");
      }
    });

    GridPane leftMenu = new GridPane();
    leftMenu.setVgap(4);
    leftMenu.setHgap(10);
    leftMenu.setPadding(new Insets(5, 5, 5, 5));
    leftMenu.add(new Label("Filter: "), 0, 0);
    leftMenu.add(filterComboBox, 1, 0);
    leftMenu.add(new Label("Division: "), 0, 1);
    leftMenu.add(divisor, 1, 1);
    leftMenu.add(new Label("Offset: "), 0, 2);
    leftMenu.add(offset, 1, 2);
    leftMenu.add(applyButton, 1, 3);

    pic.setFitHeight(300);
    pic.setPreserveRatio(true);
    displayImage(filter.getOutImage());

    final ContextMenu cm = new ContextMenu();
    MenuItem cmItem = new MenuItem("Save Image");
    cmItem.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        logger.debug(pic.getId());
        File file = fileChooser.showSaveDialog(window);
        if (file != null) {
          try {
            ImageIO.write(SwingFXUtils.fromFXImage(pic.getImage(),
                    null), "png", file);
          } catch (IOException ex) {
            System.out.println(ex.getMessage());
          }
        }
      }
    }
    );
    cm.getItems().add(cmItem);

    pic.addEventHandler(MouseEvent.MOUSE_CLICKED,
            new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        if (e.getButton() == MouseButton.SECONDARY) {
          cm.show(pic, e.getScreenX(), e.getScreenY());
        }
      }
    });

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

  private static void displayImage(Mat mat) {
    Image image = SwingFXUtils.toFXImage(toBufferedImage(mat), null);
    pic.setImage(image);
  }

  private static BufferedImage toBufferedImage(Mat matrix) {
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
}

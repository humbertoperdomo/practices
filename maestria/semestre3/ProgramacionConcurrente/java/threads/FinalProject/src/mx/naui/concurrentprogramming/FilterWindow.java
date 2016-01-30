package mx.naui.concurrentprogramming;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.*;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 *
 * @author humberto
 */
public class FilterWindow {

  private static final Logger logger = LogManager.getLogger(FilterWindow.class);
  private static final ImageView pic = new ImageView();

  public static void display(String title, String message, Splitter splitter) {
    Stage window = new Stage();
    //Block events to other window
    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle(title);
    window.setMinWidth(250);


    NumberTextField divisor = new NumberTextField("1", 2);
    divisor.setMaxWidth(40);

    NumberTextField offset = new NumberTextField("0", 2);
    offset.setMaxWidth(40);

    final CheckBox preserveChanges = new CheckBox();
    preserveChanges.setSelected(false);
    preserveChanges.selectedProperty().addListener(new ChangeListener<Boolean>() {
        public void changed(ObservableValue<? extends Boolean> ov,
            Boolean old_val, Boolean new_val) {
                splitter.setPreservingChanges(new_val);
        }
    });

    Slider slices = new Slider(1.0, 40.0, 1.0);
    slices.setShowTickLabels(true);
    slices.setMajorTickUnit(1);
    slices.setMinorTickCount(1);
    slices.setBlockIncrement(1);
    slices.setDisable(((splitter.getImage().cols() >= 300) && (splitter.getImage().rows() >= 300)) ? false : true);
    slices.valueProperty().addListener(new ChangeListener<Number>() {
      public void changed(ObservableValue<? extends Number> ov,
              Number old_val, Number new_val) {
        splitter.setNSlices(new_val.intValue());
      }
    });

    final Slider hue = new Slider(0.0, 255.0, 0.0);
    //hue.setShowTickLabels(true);
    hue.setMajorTickUnit(1);
    hue.setMinorTickCount(1);
    hue.setBlockIncrement(15);
    hue.setDisable(splitter.getSelectedFilter() != Kernel.HSV);
    
    hue.valueProperty().addListener(new ChangeListener<Number>() {
      public void changed(ObservableValue<? extends Number> ov,
              Number old_val, Number new_val) {
        logger.debug("Starting change of hue");
        splitter.changeHSV(0, 
                (old_val.intValue() > new_val.intValue()) ? (Math.abs(old_val.intValue() - new_val.intValue()) * -1) : Math.abs(new_val.intValue() - old_val.intValue()));
        logger.debug("Hue changed");
        displayImage(splitter.getOutImage()); 
      }
    });

    Slider saturation = new Slider(0.0, 255.0, 0.0);
    //saturation.setShowTickLabels(true);
    saturation.setMajorTickUnit(1);
    saturation.setMinorTickCount(1);
    saturation.setBlockIncrement(15);
    saturation.setDisable(splitter.getSelectedFilter() != Kernel.HSV);
    
    saturation.valueProperty().addListener(new ChangeListener<Number>() {
      public void changed(ObservableValue<? extends Number> ov,
              Number old_val, Number new_val) {
        logger.debug("Starting change of saturation");
        splitter.changeHSV(1, 
                (old_val.intValue() > new_val.intValue()) ? (Math.abs(old_val.intValue() - new_val.intValue()) * -1) : Math.abs(new_val.intValue() - old_val.intValue()));
        logger.debug("Saturation changed");
        displayImage(splitter.getOutImage());
      }
    });

    Slider value = new Slider(0.0, 255.0, 0.0);
    //value.setShowTickLabels(true);
    value.setMajorTickUnit(1);
    value.setMinorTickCount(1);
    value.setBlockIncrement(15);
    value.setDisable(splitter.getSelectedFilter() != Kernel.HSV);
    
    value.valueProperty().addListener(new ChangeListener<Number>() {
      public void changed(ObservableValue<? extends Number> ov,
              Number old_val, Number new_val) {
        logger.debug("Starting change of value");
        splitter.changeHSV(2, 
                (old_val.intValue() > new_val.intValue()) ? (Math.abs(old_val.intValue() - new_val.intValue()) * -1) : Math.abs(new_val.intValue() - old_val.intValue()));
        logger.debug("Value changed");
        displayImage(splitter.getOutImage());
      }
    });

    final ComboBox<Kernel> filterComboBox = new ComboBox();
    filterComboBox.getItems().addAll(Kernel.values());
    filterComboBox.setValue(Kernel.IDENTITY);
    filterComboBox.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {
        if (filterComboBox.getValue() != Kernel.HSV){
          hue.setDisable(true);
          saturation.setDisable(true);
          value.setDisable(true);
        } else {
          hue.setDisable(false);
          hue.setValue(0.0);
          saturation.setDisable(false);
          saturation.setValue(0.0);
          value.setDisable(false);
          value.setValue(0.0);
        }
      }
    });
    
    final Button applyButton = new Button("Apply");
    applyButton.setDefaultButton(true);
    applyButton.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {
        splitter.setDivisor(Double.parseDouble(divisor.getText()));
        splitter.setOffset(Double.parseDouble(offset.getText()));
        splitter.setSelectedFilter(filterComboBox.getValue());
        //splitter.setPreservingChanges(preserveChanges.isSelected());
        splitter.setNSlices(slices.isDisable() ? 1 : (int) slices.getValue());
        logger.debug("Starting Filter");
        splitter.doSomething();
        logger.debug("Filter applied!");
        displayImage(splitter.getOutImage());
        if (filterComboBox.getValue() == Kernel.HSV) {
          preserveChanges.setSelected(true);
          hue.setValue(0.0);
          saturation.setValue(0.0);
          value.setValue(0.0);
        }
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
    leftMenu.add(new Label("Preserve changes: "), 0, 3);
    leftMenu.add(preserveChanges, 1, 3);
    leftMenu.add(new Label("Slides: "), 0, 4);
    leftMenu.add(slices, 1, 4);
    leftMenu.add(applyButton, 1, 5);
    leftMenu.add(new Label("Hue: "), 0, 6);
    leftMenu.add(hue, 1, 6);
    leftMenu.add(new Label("Saturation: "), 0, 7);
    leftMenu.add(saturation, 1, 7);
    leftMenu.add(new Label("Value: "), 0, 8);
    leftMenu.add(value, 1, 8);

    pic.setFitHeight(300);
    pic.setPreserveRatio(true);
    displayImage(splitter.getOutImage());

    final ContextMenu cm = new ContextMenu();
    MenuItem cmItem = new MenuItem("Save Image");
    cmItem.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        logger.debug(pic.getId());
        File file = fileChooser.showSaveDialog(window);
        if (file != null) {
          Imgcodecs.imwrite(file.getPath(), splitter.getOutImage());
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
    scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent evt) {
        if (evt.getCode().equals(KeyCode.ESCAPE)) {
          window.close();
        }
      }
    });
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

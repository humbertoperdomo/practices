package mx.naui.concurrentprogramming;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;

/**
 *
 * @author humberto
 */
public class Splitter {

  private static final Logger logger = LogManager.getLogger(Splitter.class);
  private String imagePath;
  private Mat image;
  private Mat outImage;
  private int nSlices;
  private int selectedFilter;
  private double divisor = 1.0;
  private double offset = 0.0;
  private ExecutorService executor;

  Splitter(String path) {
    this(path, 1);
  }

  Splitter(String path, int nSlices) {
    this(path, nSlices, 0);
  }

  Splitter(String imagePath, int nSlices, int selectedFilter) {
    this.imagePath = imagePath;
    image = Imgcodecs.imread(imagePath);
    outImage = image.clone();
    this.nSlices = nSlices;
    this.selectedFilter = selectedFilter;
  }

  public Mat getImage() {
    return image;
  }

  public Mat getOutImage() {
    return outImage;
  }

  public int getNSlices() {
    return nSlices;
  }

  public void setNSlices(int nSlices) {
    this.nSlices = nSlices;
  }

  public int getSelectedFilter() {
    return selectedFilter;
  }

  public void setSelectedFilter(int selectedFilter) {
    this.selectedFilter = selectedFilter;
  }

  public double getDivisor() {
    return divisor;
  }

  public void setDivisor(double divisor) {
    this.divisor = divisor;
  }

  public double getOffset() {
    return offset;
  }

  public void setOffset(double offset) {
    this.offset = offset;
  }

  public void doSomething() {
    int columnSlices = (int) Math.ceil(Math.sqrt(nSlices));
    int rowSlices = nSlices / columnSlices;
    int orphanSlice = nSlices % columnSlices;
    int sliceWidth = image.cols() / columnSlices;
    int sliceHeight = image.rows() / (orphanSlice == 0 ? rowSlices : (rowSlices + 1));

    if (nSlices == 1) {
      filter(image, Kernel.at(selectedFilter).getKernel(), 1, divisor, offset).copyTo(outImage);
    } else {
      logger.debug("rowSlices = {}, columnSlices = {}, orphanSlice = {}, sliceWidth = {}", rowSlices, columnSlices, orphanSlice, sliceWidth);
      executor = Executors.newFixedThreadPool((nSlices > 1) ? nSlices / 2 : 1);
      for (int y = 0; y < rowSlices; ++y) {
        for (int x = 0; x < columnSlices; ++x) {
          executor.submit(new Filter(outImage, 
            splitMat(image,
              ((x * sliceWidth) - ((x > 0) ? 1 : 0)), // decide if x starts before in order to avoid blank lines
              ((y * sliceHeight) - ((y > 0) ? 1 : 0)), // decide if y starts before in order to avoid blank lines
              (sliceWidth + (((x + 1) < columnSlices && (x > 0)) ? 2 : 1)), // decide width considering the starting x
              (sliceHeight + ((orphanSlice > 0 || (y + 1) < rowSlices || y > 0) ? (((orphanSlice > 0 && y > 0) || ((y + 1) < rowSlices && y > 0)) ? 2 : 1) : 0))), 
            Kernel.at(selectedFilter).getKernel(), 1, divisor, offset, x, y, 
            (x > 0) ? 1 : 0, (y > 0) ? 1 : 0, sliceWidth, sliceHeight));
          /*
          new Mat(filter(
                  splitMat(image,
                          ((x * sliceWidth) - ((x > 0) ? 1 : 0)), // decide if x starts before in order to avoid blank lines
                          ((y * sliceHeight) - ((y > 0) ? 1 : 0)), // decide if y starts before in order to avoid blank lines
                          (sliceWidth + (((x + 1) < columnSlices && (x > 0)) ? 2 : 1)), // decide width considering the starting x
                          (sliceHeight + ((orphanSlice > 0 || (y + 1) < rowSlices || y > 0) ? (((orphanSlice > 0 && y > 0) || ((y + 1) < rowSlices && y > 0)) ? 2 : 1) : 0))),
                  Kernel.at(selectedFilter).getKernel(), 1, divisor, offset),
                  (new Rect((x > 0) ? 1 : 0, (y > 0) ? 1 : 0, sliceWidth, sliceHeight))
          )
                  .copyTo(outImage.submat(new Rect(x * sliceWidth, y * sliceHeight, sliceWidth, sliceHeight)));
          */
        }
      }

      if (orphanSlice > 0) {
        int orphanWidth = image.cols() / orphanSlice;
        for (int x = 0; x < orphanSlice; ++x) {
          executor.submit(new Filter(outImage, 
            splitMat(image,
              ((x * orphanWidth) - ((x > 0) ? 1 : 0)),
              ((rowSlices * sliceHeight) - 1),
              (orphanWidth + (((x + 1) < orphanSlice && (x > 0)) ? 2 : (orphanSlice > 1 ? 1 : 0))),
              (sliceHeight + 1)),
            Kernel.at(selectedFilter).getKernel(), 1, divisor, offset, x, 
            rowSlices, (x > 0) ? 1 : 0, 1, orphanWidth, sliceHeight));
          /*
          new Mat(filter(
                  splitMat(image,
                          ((x * orphanWidth) - ((x > 0) ? 1 : 0)),
                          ((rowSlices * sliceHeight) - 1),
                          (orphanWidth + (((x + 1) < orphanSlice && (x > 0)) ? 2 : (orphanSlice > 1 ? 1 : 0))),
                          (sliceHeight + 1)),
                  Kernel.at(selectedFilter).getKernel(), 1, divisor, offset), //new Random().nextInt(9) instead selectedFilter 
                  (new Rect((x > 0) ? 1 : 0, 1, orphanWidth, sliceHeight))
          )
                  .copyTo(outImage.submat(new Rect(x * orphanWidth, rowSlices * sliceHeight, orphanWidth, sliceHeight)));
          */
        }
      }
      executor.shutdown();
      try {
        executor.awaitTermination(30, TimeUnit.SECONDS);
      } catch (InterruptedException ex) {
        logger.error(ex);
      }
    }
  }

  public Mat splitMat(Mat inputMat, int x, int y, int w, int h) {
    logger.debug("x = {}, y = {}, w = {}, h = {}", x, y, w, h);
    convertRGBToHSV(new double[]{74, 111, 193});
    Mat subImage = new Mat(inputMat, new Rect(x, y, w, h));
    return subImage;
  }

  public Mat filter(Mat inputMat, double[] kernel, int kernelSize, double divisor, double offset) {
    Mat om = inputMat.clone();
    double channels[] = new double[om.channels()];

    if (!om.empty()) {
      for (int ix = 0; ix < inputMat.cols(); ix++) {
        for (int iy = 0; iy < inputMat.rows(); iy++) {
          // initializes channels array values to 0.0
          for (int i = 0; i < channels.length; i++) {
            channels[i] = 0.0;
          }

          for (int kx = -kernelSize; kx <= kernelSize; kx++) {
            for (int ky = -kernelSize; ky <= kernelSize; ky++) {
              for (int l = 0; l < om.channels(); l++) {

                channels[l] += (kernel[(kx + kernelSize) + (ky + kernelSize) * (2 * kernelSize + 1)] / divisor)
                        * checkPixel(inputMat, ix + kx, iy + ky, l) + offset;
              }
            }
          }

          for (int l = 0; l < om.channels(); l++) {
            channels[l] = (channels[l] > 255.0) ? 255.0 : ((channels[l] < 0.0) ? 0.0 : channels[l]);
          }

          om.put(iy, ix, channels);
        }
      }

    }
    return om;
  }

  public static double checkPixel(Mat inputMat, int x, int y, int l) {
    if ((x < 0) || (x >= inputMat.cols()) || (y < 0) || (y >= inputMat.rows())) {
      return 0.0;
    }

    return inputMat.get(y, x)[l];
  }
  
  public void convertRGBToHSV(double[] pixel) {
    double minRGB, maxRGB;
    double r = pixel[2], g = pixel[1], b = pixel[0];
    double hue = 0.0;
    double saturation;
    double value;
    double delta;
    
    logger.debug("red = {}, green = {}, blue = {}", r, g, b);
    
    minRGB = Math.min(r, Math.min(g, b));
    maxRGB = Math.max(r, Math.max(g, b));
    
    logger.debug("minRGB = {}, maxRGB = {}", minRGB, maxRGB);
    
    delta = maxRGB - minRGB;
    value = maxRGB;                // Value (Brightness).
    
    logger.debug("delta = {}, value = {}", delta, value);
    
    // Black-gray-white
    if (maxRGB != 0) {             // Make sure it's not pure black.
      saturation = delta / maxRGB; // Saturation.
      logger.debug("saturation = {}", saturation);
      double angleToUnit = 1.0 / (6.0 * delta); // Make the Hues between 0.0 to 1.0 instead of 6.0
      logger.debug("angleToUnit = {}", angleToUnit);
      int rComparison = Double.compare(maxRGB, r);
      int gComparison = Double.compare(maxRGB, g);
      int bComparison = Double.compare(maxRGB, b);
      logger.debug("rComparison = {}, gComparison = {}, bComparison = {}", rComparison, gComparison, bComparison);
      
      if (rComparison == 0) {        // between yellow and magenta.
        hue = (g - b) * angleToUnit;
      } else if (gComparison == 0) { // between cyan and yellow.
        hue = (2.0 / 6.0) + (b - r) * angleToUnit;
      } else if(bComparison == 0) {  // between magenta and cyan.
        hue = (4.0 / 6.0) + ( r - g ) * angleToUnit;
      }
      
      logger.debug("hue = {}", hue);
      
      // Wrap outlier Hues around the circle.
      if (hue < 0.0)
          hue += 1.0;
      if (hue >= 1.0)
          hue -= 1.0;
    } else {
      // color is pure Black.
      saturation = 0;
      hue = 0;	// undefined hue
    }
    
    // Convert from floats to 8-bit integers.
    int bHue = (int)(0.5 + hue * 255.0);
    int bSaturation = (int)(0.5 + saturation * 255.0);
    int bValue = (int)(0.5 + value * 255.0);
    
    // Clip the values to make sure it fits within the 8bits.
    if (bHue > 255)
      bHue = 255;
    if (bHue < 0)
      bHue = 0;
    if (bSaturation > 255)
      bSaturation = 255;
    if (bSaturation < 0)
      bSaturation = 0;
    if (bValue > 255)
      bValue = 255;
    if (bValue < 0)
      bValue = 0;
    
    logger.debug("hue = {}, saturation = {}, value = {}", bHue, bSaturation, bValue);
  }
}

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
  public static final double DBL_EPSILON = 2.220446049250313E-16d;
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
      if (selectedFilter > 0)
        filter(image, Kernel.at(selectedFilter).getKernel(), 1, divisor, offset).copyTo(outImage);
      else
        filterHSV(image).copyTo(outImage);
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
    
    double[][] asd = {{134, 143, 186}, {136, 145, 188}, {131, 140, 183}, {141, 150, 193},
      {143, 150, 193}, {146, 153, 196}, {135, 142, 185}, {144, 151, 194},
      {135, 141, 186}, {151, 149, 191}, {140, 146, 189}, {143, 152, 202},
      {138, 135, 180}, {135, 152, 195}, {144, 146, 187}, {153, 153, 183}};
    for(int i = 0; i < asd.length; i++) {
      convertRGBToHSV(asd[i]);
    }
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

  public Mat filterHSV(Mat inputMat) {
    Mat om = inputMat.clone();

    if (!om.empty()) {
      for (int ix = 0; ix < inputMat.cols(); ix++) {
        for (int iy = 0; iy < inputMat.rows(); iy++) {
          om.put(iy, ix, RGB2HSV(inputMat.get(iy, ix)));
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
  
  public double[] convertRGBToHSV(double[] pixel) {
    double[] channels = {0.0, 0.0, 0.0};
    double r = pixel[2] / 255.0, g = pixel[1] / 255.0, b = pixel[0] / 255.0;
    double minRGB;
    double hue = 0.0;
    double saturation;
    double value;
    double delta;
    
    logger.info("r = {}, g = {}, b = {}", r, g, b);
    
    minRGB = Math.min(r, Math.min(g, b));
    value = Math.max(r, Math.max(g, b));
    
    logger.info("minRGB = {}, value = {}", minRGB, value);
    
    delta = value - minRGB;
    
    logger.info("delta = {}", delta);
    
    // Black-gray-white
    if (Double.compare(value, 0.0) != 0) {             // Make sure it's not pure black.
      saturation = delta / value; // Saturation.
      logger.info("saturation = {}", saturation);
      double angleToUnit = 1.0 / (6.0 * delta); // Make the Hues between 0.0 to 1.0 instead of 6.0
      logger.info("angleToUnit = {}", angleToUnit);
      int rComparison = Double.compare(value, r);
      int gComparison = Double.compare(value, g);
      int bComparison = Double.compare(value, b);
      logger.info("rComparison = {}, gComparison = {}, bComparison = {}", rComparison, gComparison, bComparison);
      if (rComparison == 0) {        // between yellow and magenta.
        hue = (Math.toRadians(60.0)*(g - b))/delta; 
      } else if (gComparison == 0) { // between cyan and yellow.
        hue = (Math.toRadians(120.0)+Math.toRadians(60.0)*(b - r))/delta;
      } else if(bComparison == 0) {  // between magenta and cyan.
        hue = (Math.toRadians(240.0)+Math.toRadians(60.0)*(r - g))/delta; 
      }
      
      logger.info("hue = {}", hue);
      
      // Wrap outlier Hues around the circle.
      if (hue < 0.0)
          hue += 360; //1.0;
      //if (hue >= 1.0)
      //    hue -= 1.0;
    } else {
      // color is pure Black.
      saturation = 0;
      hue = 0;	// undefined hue
    }
    
    // Convert from floats to 8-bit integers.
    int bHue = ((int)((hue / 2))); // * 255.0); // hue
    int bSaturation = (int)(0.5 + saturation * 255.0); // saturation
    int bValue = (int)(0.5 + value * 255.0); // value
    
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
    
    channels[0] = (double)bHue;        // Blue
    channels[1] = (double)bSaturation; // Green
    channels[2] = (double)bValue;      // Red
    
    logger.info("hue = {}, saturation = {}, value = {}", channels[0], channels[1], channels[2]);
    return channels;
  }
  
  public double[] RGB2HSV(double[] pixel) {
    double[] channels = {0.0, 0.0, 0.0};
    int r = (int)pixel[2], g = (int)pixel[1], b = (int)pixel[0];
    final int hsv_shift = 12;
    int[] sdiv_table = new int[256];
    int[] hdiv_table180 = new int[256];
    int[] hdiv_table256 = new int[256];
    boolean initialized = false;

    int hr = 180;
    int[] hdiv_table = hr == 180 ? hdiv_table180 : hdiv_table256;

    if (!initialized) {
      sdiv_table[0] = hdiv_table180[0] = hdiv_table256[0] = 0;
      for (int i = 1; i < 256; i++) {
        sdiv_table[i] = (int)((255 << hsv_shift) / (1.0 * i));
        hdiv_table180[i] = (int)((180 << hsv_shift) / (6.0 * i));
        hdiv_table256[i] = (int)((256 << hsv_shift) / (6.0 * i));
      }
      initialized = true;
    }

    int h, s, v = b;
    int vmin = b, diff;
    int vr, vg;

    if (Double.compare(v, g) < 0)
      v = g;
    if (Double.compare(v, r) < 0)
      v = r;
    if (Double.compare(vmin, g) > 0)
      vmin = g;
    if (Double.compare(vmin, r) > 0)
      vmin = r;

    diff = v - vmin;
    vr = (Double.compare(v, r) == 0) ? -1 : 0;
    vg = (Double.compare(v, g) == 0) ? -1 : 0;

    s = (diff * sdiv_table[v] + (1 << (hsv_shift - 1))) >> hsv_shift;
    h = (vr & (g - b)) +
      (~vr & ((vg & (b - r + 2 * diff)) + ((~vg) & (r - g + 4 * diff))));
    h = (h * hdiv_table[diff] + (1 << (hsv_shift-1))) >> hsv_shift;
    h += h < 0 ? hr : 0;



    channels[0] = h; // Blue
    channels[1] = s;          // Green
    channels[2] = v;          // Red
    
    logger.info("hue = {}, saturation = {}, value = {}", channels[0], channels[1], channels[2]);
    return channels;
  }

}

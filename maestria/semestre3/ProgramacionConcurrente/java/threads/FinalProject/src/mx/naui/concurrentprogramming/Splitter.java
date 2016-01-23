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
  private Mat modifiedImage;
  private int nSlices;
  private Kernel selectedFilter;
  private double divisor = 1.0;
  private double offset = 0.0;
  private int kernelSize = 1;
  private boolean preservingChanges;
  // Threads
  private ExecutorService executor;
  // HSV Filter
  private final int hrange = 180;
  private int[] sdivTable = new int[256];
  private int[] hdivTable180 = new int[256];
  private int[] hdivTable256 = new int[256];
  private boolean initialized = false;
  public static final int HSV_SHIFT = 12;

  Splitter(String path) {
    this(path, 1);
  }

  Splitter(String path, int nSlices) {
    this(path, nSlices, Kernel.IDENTITY);
  }

  Splitter(String imagePath, int nSlices, Kernel selectedFilter) {
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

  public Kernel getSelectedFilter() {
    return selectedFilter;
  }

  public void setSelectedFilter(Kernel selectedFilter) {
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

  public int getKernelSize() {
    return kernelSize;
  }

  public void setKernelSize(int kernelSize) {
    this.kernelSize = kernelSize;
  }
  
  public void setPreservingChanges(boolean preservingChanges) {
    this.preservingChanges = preservingChanges;
  }
  
  public boolean isPreservingChanges() {
    return preservingChanges;
  }

  public int getHrange() {
    return hrange;
  }

  public int[] getSdivTable() {
    return sdivTable;
  }

  public int[] getHdivTable180() {
    return hdivTable180;
  }

  public int[] getHdivTable256() {
    return hdivTable256;
  }

  public void doSomething() {
    Mat auxImage = image;  // Always use original image as default
    if (isPreservingChanges()){ // if user wants to preserve changes use 
      modifiedImage = outImage.clone();
      auxImage = modifiedImage;
    }
    int columnSlices = (int) Math.ceil(Math.sqrt(nSlices));
    int rowSlices = nSlices / columnSlices;
    int orphanSlice = nSlices % columnSlices;
    int sliceWidth = auxImage.cols() / columnSlices;
    int sliceHeight = auxImage.rows() / (orphanSlice == 0 ? rowSlices : (rowSlices + 1));
    logger.debug("rowSlices = {}, columnSlices = {}, orphanSlice = {}, sliceWidth = {}, sliceHeight = {}",
            rowSlices, columnSlices, orphanSlice, sliceWidth, sliceHeight);

    if (nSlices == 1) {
      if (selectedFilter != Kernel.HSV) {
        filter(auxImage, selectedFilter.getKernel(), 1, divisor, offset)
                .copyTo(outImage);
      } else {
        if (!initialized) {
          initializeTables();
        }
        filterHSV(auxImage).copyTo(outImage);
      }
    } else {
      executor = Executors.newFixedThreadPool((nSlices > 1) ? nSlices / 2 : 1);

      if (selectedFilter != Kernel.HSV) {
        for (int y = 0; y < rowSlices; ++y) {
          for (int x = 0; x < columnSlices; ++x) {
            executor.submit(new Filter(this,
                    splitMat(auxImage,
                            ((x * sliceWidth) - ((x > 0) ? 1 : 0)), // decide if x starts before in order to avoid blank lines
                            ((y * sliceHeight) - ((y > 0) ? 1 : 0)), // decide if y starts before in order to avoid blank lines
                            (sliceWidth + (((x + 1) < columnSlices && (x > 0)) ? 2 : 1)), // decide width considering the starting x
                            (sliceHeight + ((orphanSlice > 0 || (y + 1) < rowSlices || y > 0) ? (((orphanSlice > 0 && y > 0) || ((y + 1) < rowSlices && y > 0)) ? 2 : 1) : 0))),
                    x, y,
                    (x > 0) ? 1 : 0, (y > 0) ? 1 : 0, sliceWidth, sliceHeight));
          }
        }

        if (orphanSlice > 0) {
          int orphanWidth = auxImage.cols() / orphanSlice;
          for (int x = 0; x < orphanSlice; ++x) {
            executor.submit(new Filter(this,
                    splitMat(auxImage,
                            ((x * orphanWidth) - ((x > 0) ? 1 : 0)),
                            ((rowSlices * sliceHeight) - 1),
                            (orphanWidth + (((x + 1) < orphanSlice && (x > 0)) ? 2 : (orphanSlice > 1 ? 1 : 0))),
                            (sliceHeight + 1)),
                    x, rowSlices,
                    (x > 0) ? 1 : 0, 1, orphanWidth, sliceHeight));
          }
        }
      } else {
        if (!initialized) {
          initializeTables();
        }
        for (int y = 0; y < rowSlices; ++y) {
          for (int x = 0; x < columnSlices; ++x) {
            executor.submit(new Filter(this,
                    splitMat(auxImage,
                            (x * sliceWidth),
                            (y * sliceHeight),
                            sliceWidth,
                            sliceHeight),
                    x, y,
                    0, 0, sliceWidth, sliceHeight));
          }
        }

        if (orphanSlice > 0) {
          int orphanWidth = auxImage.cols() / orphanSlice;
          for (int x = 0; x < orphanSlice; ++x) {
            executor.submit(new Filter(this,
                    splitMat(auxImage,
                            (x * orphanWidth),
                            (rowSlices * sliceHeight),
                            orphanWidth,
                            sliceHeight),
                    x, rowSlices,
                    0, 0, orphanWidth, sliceHeight));
          }
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
          om.put(iy, ix, BGR2HSV(inputMat.get(iy, ix)));
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

  // This method seems not work properly 
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
        hue = (Math.toRadians(60.0) * (g - b)) / delta;
      } else if (gComparison == 0) { // between cyan and yellow.
        hue = (Math.toRadians(120.0) + Math.toRadians(60.0) * (b - r)) / delta;
      } else if (bComparison == 0) {  // between magenta and cyan.
        hue = (Math.toRadians(240.0) + Math.toRadians(60.0) * (r - g)) / delta;
      }

      logger.info("hue = {}", hue);

      // Wrap outlier Hues around the circle.
      if (hue < 0.0) {
        hue += 360; //1.0;
      }      //if (hue >= 1.0)
      //    hue -= 1.0;
    } else {
      // color is pure Black.
      saturation = 0;
      hue = 0;	// undefined hue
    }

    // Convert from floats to 8-bit integers.
    int bHue = ((int) ((hue / 2))); // * 255.0); // hue
    int bSaturation = (int) (0.5 + saturation * 255.0); // saturation
    int bValue = (int) (0.5 + value * 255.0); // value

    // Clip the values to make sure it fits within the 8bits.
    if (bHue > 255) {
      bHue = 255;
    }
    if (bHue < 0) {
      bHue = 0;
    }
    if (bSaturation > 255) {
      bSaturation = 255;
    }
    if (bSaturation < 0) {
      bSaturation = 0;
    }
    if (bValue > 255) {
      bValue = 255;
    }
    if (bValue < 0) {
      bValue = 0;
    }

    channels[0] = (double) bHue;        // Blue
    channels[1] = (double) bSaturation; // Green
    channels[2] = (double) bValue;      // Red

    logger.info("hue = {}, saturation = {}, value = {}", channels[0], channels[1], channels[2]);
    return channels;
  }

  public double[] BGR2HSV(double[] pixel) {
    double[] channels = {0.0, 0.0, 0.0};
    int r = (int) pixel[2], g = (int) pixel[1], b = (int) pixel[0];
    int hr = hrange;
    int[] hdiv_table = hr == 180 ? hdivTable180 : hdivTable256;

    int h, s, v = b;
    int vmin = b, diff;
    int vr, vg;

    if (Double.compare(v, g) < 0) {
      v = g;
    }
    if (Double.compare(v, r) < 0) {
      v = r;
    }
    if (Double.compare(vmin, g) > 0) {
      vmin = g;
    }
    if (Double.compare(vmin, r) > 0) {
      vmin = r;
    }

    diff = v - vmin;
    vr = (Double.compare(v, r) == 0) ? -1 : 0;
    vg = (Double.compare(v, g) == 0) ? -1 : 0;

    s = (diff * sdivTable[v] + (1 << (HSV_SHIFT - 1))) >> HSV_SHIFT;
    h = (vr & (g - b))
            + (~vr & ((vg & (b - r + 2 * diff)) + ((~vg) & (r - g + 4 * diff))));
    h = (h * hdiv_table[diff] + (1 << (HSV_SHIFT - 1))) >> HSV_SHIFT;
    h += h < 0 ? hr : 0;

    channels[0] = h; // Blue
    channels[1] = s; // Green
    channels[2] = v; // Red

    return channels;
  }

  private void initializeTables() {
    sdivTable[0] = hdivTable180[0] = hdivTable256[0] = 0;
    for (int i = 1; i < 256; i++) {
      sdivTable[i] = (int) ((255 << HSV_SHIFT) / (1.0 * i));
      hdivTable180[i] = (int) ((180 << HSV_SHIFT) / (6.0 * i));
      hdivTable256[i] = (int) ((256 << HSV_SHIFT) / (6.0 * i));
    }
    initialized = true;
  }

}

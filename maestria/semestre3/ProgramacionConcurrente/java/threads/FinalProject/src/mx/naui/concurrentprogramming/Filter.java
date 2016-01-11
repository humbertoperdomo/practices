package mx.naui.concurrentprogramming;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;

/**
 *
 * @author humberto
 */
public class Filter {

  private static final Logger logger = LogManager.getLogger(Filter.class);
  private String imagePath;
  private Mat image;
  private Mat outImage;
  private int nSlices;
  private int selectedFilter;
  private double divisor = 1.0;
  private double offset = 0.0;

  private double[] identity_kernel = {
    0.0, 0.0, 0.0,
    0.0, 1.0, 0.0,
    0.0, 0.0, 0.0
  };
  private double[] sharpen_kernel = {
    0.0, -1.0, 0.0,
    -1.0, 5.0, -1.0,
    0.0, -1.0, 0.0
  };
  private double[] box_blur_kernel = {
    1.0, 1.0, 1.0,
    1.0, 1.0, 1.0,
    1.0, 1.0, 1.0
  }; // 1/9
  private double[] gaussian_blur_kernel = {
    1.0, 1.0, 1.0,
    1.0, 1.0, 1.0,
    1.0, 1.0, 1.0
  }; // 1/16
  private double[] edge_enhance_kernel = {
    0.0, 0.0, 0.0,
    -1.0, 1.0, 0.0,
    0.0, 0.0, 0.0
  };
  private double[] edge_detect_kernel = {
    0.0, 1.0, 0.0,
    1.0, -4.0, 1.0,
    0.0, 1.0, 0.0
  };
  private double[] edge_detect_kernel_2 = {
    1.0, 0.0, -1.0,
    0.0, 0.0, 0.0,
    -1.0, 0.0, 1.0
  };
  private double[] edge_detect_kernel_3 = {
    -1.0, -1.0, -1.0,
    -1.0, 8.0, -1.0,
    -1.0, -1.0, -1.0
  };
  private double[] edge_emboss_kernel = {
    -2.0, -1.0, 0.0,
    -1.0, 1.0, 1.0,
    0.0, 1.0, 2.0
  };
  private double[][] filters = {identity_kernel, sharpen_kernel, box_blur_kernel,
    gaussian_blur_kernel, edge_enhance_kernel, edge_detect_kernel,
    edge_detect_kernel_2, edge_detect_kernel_3, edge_emboss_kernel};

  Filter(String path) {
    this(path, 1);
  }

  Filter(String path, int nSlices) {
    this(path, nSlices, 0);
  }

  Filter(String imagePath, int nSlices, int selectedFilter) {
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

  public double[][] getFilters() {
    return filters;
  }

  public void doSomething() {
    int columnSlices = (int) Math.ceil(Math.sqrt(nSlices));
    int rowSlices = nSlices / columnSlices;
    int orphanSlice = nSlices % columnSlices;
    int sliceWidth = image.cols() / columnSlices;
    int sliceHeight = image.rows() / (orphanSlice == 0 ? rowSlices : (rowSlices + 1));

    if (nSlices == 1) {
      filter(image, filters[selectedFilter], 1, divisor, offset).copyTo(outImage);
    } else {
      logger.debug("rowSlices = {}, columnSlices = {}, orphanSlice = {}, sliceWidth = {}", rowSlices, columnSlices, orphanSlice, sliceWidth);
      for (int y = 0; y < rowSlices; ++y) {
        for (int x = 0; x < columnSlices; ++x) {
          new Mat(filter(
                  splitMat(image,
                          ((x * sliceWidth) - ((x > 0) ? 1 : 0)), // decide if x starts before in order to avoid blank lines
                          ((y * sliceHeight) - ((y > 0) ? 1 : 0)), // decide if y starts before in order to avoid blank lines
                          (sliceWidth + (((x + 1) < columnSlices && (x > 0)) ? 2 : 1)), // decide width considering the starting x
                          (sliceHeight + ((orphanSlice > 0 || (y + 1) < rowSlices || y > 0) ? (((orphanSlice > 0 && y > 0) || ((y + 1) < rowSlices && y > 0)) ? 2 : 1) : 0))),
                  filters[selectedFilter], 1, divisor, offset),
                  (new Rect((x > 0) ? 1 : 0, (y > 0) ? 1 : 0, sliceWidth, sliceHeight))
          )
                  .copyTo(outImage.submat(new Rect(x * sliceWidth, y * sliceHeight, sliceWidth, sliceHeight)));
        }
      }

      if (orphanSlice > 0) {
        int orphanWidth = image.cols() / orphanSlice;
        for (int x = 0; x < orphanSlice; ++x) {
          new Mat(filter(
                  splitMat(image,
                          ((x * orphanWidth) - ((x > 0) ? 1 : 0)),
                          ((rowSlices * sliceHeight) - 1),
                          (orphanWidth + (((x + 1) < orphanSlice && (x > 0)) ? 2 : (orphanSlice > 1 ? 1 : 0))),
                          (sliceHeight + 1)),
                  filters[selectedFilter], 1, divisor, offset), //new Random().nextInt(9) instead selectedFilter 
                  (new Rect((x > 0) ? 1 : 0, 1, orphanWidth, sliceHeight))
          )
                  .copyTo(outImage.submat(new Rect(x * orphanWidth, rowSlices * sliceHeight, orphanWidth, sliceHeight)));
        }

      }
    }
  }

  public Mat splitMat(Mat inputMat, int x, int y, int w, int h) {
    logger.debug("x = {}, y = {}, w = {}, h = {}", x, y, w, h);
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
}

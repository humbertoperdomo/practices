/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.naui.concurrentprogramming;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.opencv.core.Mat;
import org.opencv.core.Rect;

/**
 *
 * @author humberto
 */
public class Filter implements Runnable {
  private static final Logger logger = LogManager.getLogger(Filter.class);
  private final Mat finalMat;
  private Mat matToFilter;
  private double[] kernel;
  private int kernelSize = 1;
  private double divisor = 1.0;
  private double offset = 0.0;
  private int x, y, fittedX, fittedY;
  private int width, height;
  

  public Filter(Mat finalMat, Mat matToFilter, double[] kernel, int kernelSize,
          double divisor, double offset, int x, int y, int fittedX, int fittedY,
          int width, int height) {
    this.finalMat = finalMat;
    this.matToFilter = matToFilter;
    this.kernel = kernel;
    this.kernelSize = kernelSize;
    this.divisor = divisor;
    this.offset = offset;
    this.x = x;
    this.y = y;
    this.fittedX = fittedX;
    this.fittedY = fittedY;
    this.width = width;
    this.height = height;
  }

  @Override
  public void run() {
    logger.debug("Starting {} x = {}, y = {}, w = {}, h = {}", Thread.currentThread().getName(), x, y, width, height);
    filter();
    logger.debug("Ending {} x = {}, y = {}, w = {}, h = {}", Thread.currentThread().getName(), x, y, width, height);
  }

  public void filter() {
    Mat om = matToFilter.clone();
    double channels[] = new double[om.channels()];

    if (!om.empty()) {
      for (int ix = 0; ix < matToFilter.cols(); ix++) {
        for (int iy = 0; iy < matToFilter.rows(); iy++) {
          // initializes channels array values to 0.0
          for (int i = 0; i < channels.length; i++) {
            channels[i] = 0.0;
          }

          for (int kx = -kernelSize; kx <= kernelSize; kx++) {
            for (int ky = -kernelSize; ky <= kernelSize; ky++) {
              for (int l = 0; l < om.channels(); l++) {

                channels[l] += (kernel[(kx + kernelSize) + (ky + kernelSize) * (2 * kernelSize + 1)] / divisor)
                        * checkPixel(ix + kx, iy + ky, l) + offset;
              }
            }
          }

          for (int l = 0; l < om.channels(); l++) {
            channels[l] = (channels[l] > 255.0) ? 255.0 : ((channels[l] < 0.0) ? 0.0 : channels[l]);
          }

          om.put(iy, ix, channels);
        }
      }
      
      new Mat(om, (new Rect(fittedX, fittedY, width, height)))
              .copyTo(finalMat.submat(new Rect(x * width, y * height, width, height)));
    }
  }

  private double checkPixel(int x, int y, int l) {
    if ((x < 0) || (x >= matToFilter.cols()) || (y < 0) || (y >= matToFilter.rows())) {
      return 0.0;
    }

    return matToFilter.get(y, x)[l];
  }
}

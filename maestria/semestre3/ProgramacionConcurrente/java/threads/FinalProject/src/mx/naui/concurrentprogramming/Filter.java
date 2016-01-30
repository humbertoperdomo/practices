/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.naui.concurrentprogramming;

import static mx.naui.concurrentprogramming.Splitter.HSV_SHIFT;
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
  private Splitter splitter;
  private Mat matToFilter;
  private int x, y, fittedX, fittedY;
  private int width, height;

  public Filter(Splitter splitter, Mat matToFilter, int x, int y, int fittedX, int fittedY,
          int width, int height) {
    this.splitter = splitter;
    this.matToFilter = matToFilter;
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
    if (!om.empty()) {
      if (splitter.getSelectedFilter() != Kernel.HSV) {
        double channels[] = new double[om.channels()];
        for (int ix = 0; ix < matToFilter.cols(); ix++) {
          for (int iy = 0; iy < matToFilter.rows(); iy++) {
            // initializes channels array values to 0.0
            for (int i = 0; i < channels.length; i++) {
              channels[i] = 0.0;
            }

            for (int kx = -splitter.getKernelSize(); kx <= splitter.getKernelSize(); kx++) {
              for (int ky = -splitter.getKernelSize(); ky <= splitter.getKernelSize(); ky++) {
                for (int l = 0; l < om.channels(); l++) {

                  channels[l] += (splitter.getSelectedFilter().getKernel()[(kx + splitter.getKernelSize()) + (ky + splitter.getKernelSize()) * (2 * splitter.getKernelSize() + 1)] / splitter.getDivisor())
                          * checkPixel(ix + kx, iy + ky, l) + splitter.getOffset();
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
                .copyTo(splitter.getOutImage().submat(new Rect(x * width, y * height, width, height)));
      } else {
        for (int ix = 0; ix < matToFilter.cols(); ix++) {
          for (int iy = 0; iy < matToFilter.rows(); iy++) {
            om.put(iy, ix, BGR2HSV(matToFilter.get(iy, ix)));
          }
        }
        new Mat(om, (new Rect(fittedX, fittedY, width, height)))
                .copyTo(splitter.getOutImage().submat(new Rect(x * width, y * height, width, height)));
      }
    }
  }

  private double checkPixel(int x, int y, int l) {
    if ((x < 0) || (x >= matToFilter.cols()) || (y < 0) || (y >= matToFilter.rows())) {
      return 0.0;
    }

    return matToFilter.get(y, x)[l];
  }

  public double[] BGR2HSV(double[] pixel) {
    double[] channels = {0.0, 0.0, 0.0};
    int r = (int)pixel[2], g = (int)pixel[1], b = (int)pixel[0];
    int hr = splitter.getHrange();
    int[] hdiv_table = hr == 180 ? splitter.getHdivTable180() : splitter.getHdivTable256();

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

    s = (diff * splitter.getSdivTable()[v] + (1 << (HSV_SHIFT - 1))) >> HSV_SHIFT;
    h = (vr & (g - b))
            + (~vr & ((vg & (b - r + 2 * diff)) + ((~vg) & (r - g + 4 * diff))));
    h = (h * hdiv_table[diff] + (1 << (HSV_SHIFT - 1))) >> HSV_SHIFT;
    h += h < 0 ? hr : 0;

    channels[0] = h; // Blue
    channels[1] = s; // Green
    channels[2] = v; // Red

    return channels;
  }
  
  

}

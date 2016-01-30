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
public class HSVFilter implements Runnable {
  
  private static final Logger logger = LogManager.getLogger(HSVFilter.class);
  private Splitter splitter;
  private Mat matToFilter;
  private int x, y, width, height;
  private int hsv, summand;
  
  public HSVFilter(Splitter splitter, Mat matToFilter, int x, int y, int width, 
          int height, int hsv, int summand) {
    this.splitter = splitter;
    this.matToFilter = matToFilter;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.hsv = hsv;
    this.summand = summand;
  }
  
  @Override
  public void run() {
    logger.debug("Starting {} x = {}, y = {}, w = {}, h = {}, hsv = {}, summand = {}", Thread.currentThread().getName(), x, y, width, height, hsv, summand);
    filter();
    logger.debug("Ending {} x = {}, y = {}, w = {}, h = {}, hsv = {}, summand = {}", Thread.currentThread().getName(), x, y, width, height, hsv, summand);
  }
  
  public void filter() {
    Mat om = matToFilter.clone();
    if (!om.empty()) {
      for (int ix = 0; ix < matToFilter.cols(); ix++) {
        for (int iy = 0; iy < matToFilter.rows(); iy++) {
            om.put(iy, ix, changeHSV(matToFilter.get(iy, ix)));
        }
      }
      
      new Mat(om, (new Rect(0, 0, width, height)))
              .copyTo(splitter.getOutImage().submat(new Rect(x * width, y * height, width, height)));
    }
  }
  
  public double[] changeHSV(double[] pixel) {
    double[] channels = {pixel[0], pixel[1], pixel[2]};
    double newVal = channels[hsv] + summand;
    channels[hsv] = (Double.compare(newVal, 255.0) > 0) ? 255.0 : 
            ((Double.compare(newVal, 0.0) < 0) ? 0.0 : newVal);
    return channels;
  }
}

package mx.naui.concurrentprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author humberto
 */
public enum Kernel {
  IDENTITY("Identity", 0, 
    new double[]{0.0, 0.0, 0.0, 
                 0.0, 1.0, 0.0, 
                 0.0, 0.0, 0.0}),
  SHARPEN("Sharpen", 1, 
    new double[]{0.0, -1.0, 0.0, 
                 -1.0, 5.0, -1.0, 
                 0.0, -1.0, 0.0}),
  BOX_BLUR("Box Blur", 2, 
    new double[]{1.0, 1.0, 1.0, 
                 1.0, 1.0, 1.0, 
                 1.0, 1.0, 1.0}),
  GAUSSIAN_BLUR("Gaussian Blur", 3, 
    new double[]{1.0, 1.0, 1.0, 
                 1.0, 1.0, 1.0, 
                 1.0, 1.0, 1.0}),
  EDGE_ENHANCE("Edge Enhance", 4, 
    new double[]{0.0, 0.0, 0.0, 
                 -1.0, 1.0, 0.0, 
                 0.0, 0.0, 0.0}),
  EDGE_DETECT("Edge Detect", 5, 
    new double[]{0.0, 1.0, 0.0, 
                 1.0, -4.0, 1.0, 
                 0.0, 1.0, 0.0}),
  EDGE_DETECT_2("Edge Detect 2", 6, 
    new double[]{1.0, 0.0, -1.0, 
                 0.0, 0.0, 0.0, 
                 -1.0, 0.0, 1.0}),
  EDGE_DETECT_3("Edge Detect 3", 7, 
    new double[]{-1.0, -1.0, -1.0, 
                 -1.0, 8.0, -1.0, 
                 -1.0, -1.0, -1.0}),
  EDGE_EMBOSS("Edge Emboss", 8, 
    new double[]{-2.0, -1.0, 0.0, 
                 -1.0, 1.0, 1.0, 
                 0.0, 1.0, 2.0});

  private final static Map<Integer, Kernel> map
          = new HashMap<Integer, Kernel>(Kernel.values().length, 1.0f);

  static {
    for (Kernel c : Kernel.values()) {
      map.put(c.index, c);
    }
  }
  
  private String name;
  private int index;
  private double[] kernel;

  Kernel(String name, int index, double[] kernel) {
    this.name = name;
    this.index = index;
    this.kernel = kernel;
  }

  public String toString() {
    return name;
  }

  public int getIndex() {
    return index;
  }

  public double[] getKernel() {
    return kernel;
  }

  public static Kernel at(int index) {
    Kernel result = map.get(index);
    if (result == null) {
      throw new IllegalArgumentException("No Category Exists");
    }
    return result;
  }

}

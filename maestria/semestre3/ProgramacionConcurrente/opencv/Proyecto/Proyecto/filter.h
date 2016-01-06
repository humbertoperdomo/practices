#ifndef FILTER_H
#define FILTER_H
#include <opencv2/core/core.hpp>
#include <iostream>

using namespace cv;

class Filter
{
private:
    Mat image;
    Mat out_image;
    char* imageName;
    int nThreads;
    int columns;
    int fullRows;
    int orphanSlice;
    int sliceWidth;
    int sliceHeight;
    int selectedFilter = 0;
    double divisor = 1.0;
    double offset = 0.0;
    double sharpen_kernel[3*3] = {
        0.0, -1.0, 0.0,
        -1.0, 5.0, -1.0,
        0.0, -1.0, 0.0
    };
    double blur_kernel[3*3] = {
      1.0, 1.0, 1.0,
      1.0, 1.0, 1.0,
      1.0, 1.0, 1.0
    };
    double edge_enhance_kernel[3*3] = {
      0.0, 0.0, 0.0,
      -1.0, 1.0, 0.0,
      0.0, 0.0, 0.0
    };
    double edge_detect_kernel[3*3] = {
      0.0, 1.0, 0.0,
      1.0, -4.0, 1.0,
      0.0, 1.0, 0.0
    };
    double edge_emboss_kernel[3*3] = {
      -2.0, -1.0, 0.0,
      -1.0, 1.0, 1.0,
      0.0, 1.0, 2.0
    };
    double *filters[5] = {sharpen_kernel, blur_kernel, edge_enhance_kernel,
                          edge_detect_kernel, edge_emboss_kernel};

public:
    void doSomething();
    void setNThreads(int);
    int getSelectedFilter();
    void setSelectedFilter(int);
    double getDivisor();
    void setDivisor(double);
    double getOffset();
    void setOffset(double);
    Mat getImage();
    void printMat();
    Mat splitMat(int, int, int, int);
    Mat filter(Mat, double*, int, double, double);

    inline static float CHECK_PIXEL(Mat mat, int x, int y, int l)
    {
        if ( (x < 0) || (x >= mat.cols) || (y < 0) || (y >= mat.rows) ) return 0;
        return (float) (mat.at<Vec3b>(y, x)[l]);
    }

    Filter(char*);
    Filter(char*, int);
    Filter(char*, int, int);
    ~Filter();
};

#endif // FILTER_H

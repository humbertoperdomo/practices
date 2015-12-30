#ifndef FILTER_H
#define FILTER_H
#include <opencv2/core/core.hpp>

using namespace cv;

class Filter
{
private:
    Mat image;
    Mat out_image;
    int nThreads;
    int columns;
    int fullRows;
    int orphans;
    int width;
    int height;

public:
    void doSomething();
    void setNThreads(int);
    Mat getImage();
    void printMat();
    Mat splitMat(int, int, int, int);
    Filter(char*);
    Filter(char*, int);
    ~Filter();
};

#endif // FILTER_H

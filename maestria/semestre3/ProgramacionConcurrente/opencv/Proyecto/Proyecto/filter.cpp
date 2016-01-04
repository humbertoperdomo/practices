#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <iostream>

using namespace cv;
using namespace std;
#include "filter.h"

using namespace std;


Filter::Filter(char* imageName)
{
    Filter(imageName, 1);
    cout << "Filter constructor with imageName." << endl;
}

Filter::Filter(char* imageName, int nThreads)
{
    image = imread(imageName, CV_LOAD_IMAGE_COLOR);
    setNThreads(nThreads);
    cout << "Filter constructor with imageName and thread number." << endl;
}

Filter::~Filter() {
    cout << "Filter destroyed." << endl;
}

void Filter::doSomething()
{
    for (int y = 0; y < fullRows; ++y)
    {
        for (int x = 0; x < columns; ++x)
        {
            //Mat sub(filter1.getImage(), Rect(x * width, y * sliceHeight, width, sliceHeight));
            //namedWindow("Splitted image"+to_string(y)+to_string(x), CV_WINDOW_AUTOSIZE);
            //imshow("Splitted image"+to_string(y)+to_string(x), splitMat(x * sliceWidth, y * sliceHeight, sliceWidth, sliceHeight));
        }
    }

    if (orphanSlice > 0)
    {
        int orphanWidth = image.cols / orphanSlice;
        for (int x = 0; x < orphanSlice; ++x)
        {
            //Mat sub(filter1.getImage(), Rect(x * orphanWidth, fullRows * sliceHeight, orphanWidth, sliceHeight));
            namedWindow("Splitted imageO"+to_string(x), CV_WINDOW_AUTOSIZE);
            imshow("Splitted imageO"+to_string(x), splitMat(x * orphanWidth, fullRows * sliceHeight, orphanWidth, sliceHeight));
            namedWindow("Splitted image"+to_string(x), CV_WINDOW_AUTOSIZE);
            imshow("Splitted image"+to_string(x), filter(splitMat(x * orphanWidth, fullRows * sliceHeight, orphanWidth, sliceHeight),filters[3],1,1.0,0.0));
        }

    }
}

void Filter::setNThreads(int nThreads)
{
    this->nThreads = nThreads;
    columns = ceil(sqrt(nThreads));
    fullRows = nThreads / columns;
    orphanSlice = nThreads % columns;
    sliceWidth =  image.cols / columns;
    sliceHeight = image.rows / (orphanSlice == 0 ? fullRows : (fullRows + 1));
}

Mat Filter::getImage()
{
    return this->image;
}

void Filter::printMat()
{
    cout << "C =" << endl << " " << image.col(0) << endl;
    cout << "R =" << endl << " " << image.row(0) << endl;
    cout << "M = " << endl << " " << image << endl;
}

Mat Filter::splitMat(int x, int y, int w, int h)
{
    Mat subImage(image, Rect(x, y, w, h));
    return  subImage;
}

Mat Filter::filter(Mat inputMat, double *kernel, int kernelSize, double divisor, double offset)
{
    Mat om;
    double cp[3];
    om = inputMat(Range::all(), Range::all());

    if (om.data) {
        for (int ix = 0; ix < inputMat.cols; ix++)
        {
            for(int iy = 0; iy < inputMat.rows; iy++)
            {
                cp[0] = cp[1] = cp[2] = 0.0;

                for(int kx = -kernelSize; kx <= kernelSize; kx++)
                {
                    for(int ky = -kernelSize; ky <= kernelSize; ky++)
                    {
                        for(int l = 0; l < 3; l++)
                        {
                            cp[l] = (kernel[(kx + kernelSize) + (ky + kernelSize) * (2 * kernelSize + 1)] / divisor) *
                                    ((double)CHECK_PIXEL(inputMat, ix + kx, iy + ky, l)) + offset;
                        }
                    }
                }

                for(int l = 0; l < 3; l++)
                {
                    cp[l] = (cp[l] > 255.0) ? 255.0 : ((cp[l]<0.0) ? 0.0 : cp[l]);
                    cout << "cp[" << l << "] = " << cp[l] << endl;
                }

                om.at<Vec3b>(Point(ix, iy)).val[0] = cp[0];
                om.at<Vec3b>(Point(ix, iy)).val[1] = cp[1];
                om.at<Vec3b>(Point(ix, iy)).val[2] = cp[2];
            }

        }
    }
    return om;
}


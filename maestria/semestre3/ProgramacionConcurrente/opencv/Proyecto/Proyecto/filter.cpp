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
    Filter(imageName, nThreads, 0);
    cout << "Filter constructor with imageName and thread number." << endl;
}

Filter::Filter(char* imageName, int nThreads, int selectedFilter)
{
    image = imread(imageName, CV_LOAD_IMAGE_COLOR);
    this->imageName = imageName;
    this->selectedFilter = selectedFilter;
    out_image = image.clone();
    setNThreads(nThreads);
    cout << "Filter constructor with imageName, thread number and selected filter." << endl;
}

Filter::~Filter() {
    cout << "Filter destroyed." << endl;
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

void Filter::doSomething()
{
    namedWindow(imageName, CV_WINDOW_AUTOSIZE);
    imshow(imageName, image);
    if (nThreads == 1) {
        filter(out_image, filters[selectedFilter],1,divisor,offset).copyTo(out_image);
    } else {
        cout << "fullRows = " << fullRows << ", columns = " << columns << ", orphanSlice = " << orphanSlice << ", sliceWidth = " << sliceWidth << endl;
        for (int y = 0; y < fullRows; ++y)
        {
            for (int x = 0; x < columns; ++x)
            {
                filter(
                    splitMat((x * sliceWidth) - ((x > 0) ? 1 : 0), // decide if x starts before in order to avoid blank lines
                        (y * sliceHeight) - ((y > 0) ? 1 : 0), // decide if y starts before in order to avoid blank lines
                        sliceWidth + (((x + 1) < columns && (x > 0)) ? 2 : 1), // decide width considering the starting x
                        sliceHeight + ((orphanSlice > 0 || (y + 1) < fullRows || y > 0) ? (((orphanSlice > 0 && y > 0) || ((y + 1) < fullRows && y > 0)) ? 2 : 1) : 0)),
                    filters[selectedFilter], 1, divisor, offset)
                (Rect((x > 0) ? 1 : 0, (y > 0) ? 1 : 0, sliceWidth, sliceHeight))
                .copyTo(out_image(Rect(x * sliceWidth, y * sliceHeight, sliceWidth, sliceHeight)));
            }
        }

        if (orphanSlice > 0)
        {
            int orphanWidth = image.cols / orphanSlice;
            for (int x = 0; x < orphanSlice; ++x)
            {
                filter(
                    splitMat((x * orphanWidth) - ((x > 0) ? 1 : 0),
                        (fullRows * sliceHeight) - 1,
                        orphanWidth + (((x + 1) < orphanSlice && (x > 0)) ? 2 : (orphanSlice > 1 ? 1 : 0)),
                        sliceHeight + 1),
                    filters[selectedFilter], 1, divisor, offset)
                (Rect((x > 0) ? 1 : 0, 1, orphanWidth, sliceHeight))
                .copyTo(out_image(Rect(x * orphanWidth, fullRows * sliceHeight, orphanWidth, sliceHeight)));
            }

        }
    }
    namedWindow("Temp image", CV_WINDOW_AUTOSIZE);
    imshow("Temp image", out_image);
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
    // Debug
    //cout << "x = " << x << ", y = " << y << ", w = " << w << ", h = " << h << endl;
    Mat subImage(image, Rect(x, y, w, h));
    return  subImage;
}

Mat Filter::filter(Mat inputMat, double *kernel, int kernelSize, double divisor, double offset)
{
    Mat om = inputMat.clone();
    float cp[3];

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
                            /* Debug
                            //if ((ix + kx) <=1 && (iy + ky) <= 1) {
                            if (ix ==1 && iy == 1) {
                                cout << "ix = " << ix << ", iy = " << iy << ", kx = " << kx << ", ky = " << ky << ", l = " << l << endl;
                                cout << "kernel[" << (kx + kernelSize) + (ky + kernelSize) * (2 * kernelSize + 1) << "] = " << kernel[(kx + kernelSize) + (ky + kernelSize) * (2 * kernelSize + 1)] << endl;
                                cout << "cp[" << l << "] = " << cp[l] << endl;
                            }*/
                            cp[l] += (kernel[(kx + kernelSize) + (ky + kernelSize) * (2 * kernelSize + 1)] / divisor) *
                                    CHECK_PIXEL(inputMat, ix + kx, iy + ky, l) + offset;

                        }
                    }
                }

                for(int l = 0; l < 3; l++)
                {
                    cp[l] = (cp[l] > 255.0) ? 255.0 : ((cp[l] < 0.0) ? 0.0 : cp[l]);
                }

                om.at<Vec3b>(iy, ix)[0] = (uchar) cp[0];
                om.at<Vec3b>(iy, ix)[1] = (uchar) cp[1];
                om.at<Vec3b>(iy, ix)[2] = (uchar) cp[2];
            }

        }
    }
    return om;
}

void Filter::setSelectedFilter(int filter)
{
    this->selectedFilter = filter;
}

int Filter::getSelectedFilter()
{
    return this->selectedFilter;
}

void Filter::setDivisor(double divisor)
{
    this->divisor = (divisor > 0.0) ? divisor : 1.0;
}

double Filter::getDivisor()
{
    return this->divisor;
}

void Filter::setOffset(double offset)
{
    this->offset = offset;
}

double Filter::getOffset()
{
    return this->offset;
}

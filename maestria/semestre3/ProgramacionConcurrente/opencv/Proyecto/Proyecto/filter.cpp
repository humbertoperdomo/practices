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
            //Mat sub(filter1.getImage(), Rect(x * width, y * height, width, height));
            namedWindow("Splitted image"+to_string(y)+to_string(x), CV_WINDOW_AUTOSIZE);
            imshow("Splitted image"+to_string(y)+to_string(x), splitMat(x * width, y * height, width, height));
        }
    }

    if (orphans > 0)
    {
        int orphanWidth = image.cols / orphans;
        for (int x = 0; x < orphans; ++x)
        {
            //Mat sub(filter1.getImage(), Rect(x * orphanWidth, fullRows * height, orphanWidth, height));
            namedWindow("Splitted image"+to_string(x), CV_WINDOW_AUTOSIZE);
            imshow("Splitted image"+to_string(x), splitMat(x * orphanWidth, fullRows * height, orphanWidth, height));
        }

    }
}

void Filter::setNThreads(int nThreads)
{
    this->nThreads = nThreads;
    columns = ceil(sqrt(nThreads));
    fullRows = nThreads / columns;
    orphans = nThreads % columns;
    width =  image.cols / columns;
    height = image.rows / (orphans == 0 ? fullRows : (fullRows + 1));
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

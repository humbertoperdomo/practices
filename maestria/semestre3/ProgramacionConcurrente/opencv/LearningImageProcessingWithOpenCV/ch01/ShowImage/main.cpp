#include <iostream>
using namespace std;
#include <opencv2/opencv.hpp>
#include <opencv2/highgui/highgui.hpp>
using namespace cv;

int main()
{
    Mat jpgFromPixelWrench2 = imread("/home/humberto/Documents/Maestria/Semestre4/openCVTestRAWImages/TTC00009.JPG", CV_LOAD_IMAGE_UNCHANGED);
    namedWindow("JPGFromPixelWrench2", CV_WINDOW_NORMAL);
    imshow("JPGFromPixelWrench2",jpgFromPixelWrench2);

    Mat rawImage;
    FILE *filePointer = NULL;
    char *rawImageData = NULL;
    int IMAGE_WIDTH = 2048;
    int IMAGE_HEIGHT = 1536;
    int frameSize = IMAGE_WIDTH * IMAGE_HEIGHT;

    //Open raw Bayer image.
    filePointer = fopen("/home/humberto/Documents/Maestria/Semestre4/TetraCAM/TTC00009.RAW", "rb");

    //Memory allocation for bayer image data buffer.
    rawImageData = (char*) malloc (sizeof(char) * frameSize);

    //Read image data and store in buffer.
    fread(rawImageData, sizeof(char), frameSize, filePointer);

    //Image dimension.
    //imageSize.height = IMAGE_WIDTH;
    //imageSize.width = IMAGE_HEIGHT;

    //Create Opencv mat structure for image dimension. For 8 bit bayer, type should be CV_8UC1.
    rawImage.create(IMAGE_HEIGHT, IMAGE_WIDTH, CV_8UC1);

    memcpy(rawImage.data, rawImageData, frameSize);

    free(rawImageData);

    fclose(filePointer);

    cout << "RAW image:" << endl;
    cout << "cols:" << rawImage.cols << endl;
    cout << "rows:" << rawImage.rows << endl;
    cout << "channels:" << rawImage.channels() << endl;
    namedWindow("RAW", CV_WINDOW_NORMAL);
    imshow("RAW",rawImage);

    imwrite("/home/humberto/Documents/Maestria/Semestre4/openCVTestRAWImages/RAW.jpg", rawImage);

    Mat raw2bgr_image;

    //Perform demosaicing process
    //cvtColor(rawImage, raw2bgr_image, CV_BayerBG2RGB);
    //cvtColor(rawImage, raw2bgr_image, CV_BayerGB2BGR);
    //cvtColor(rawImage, raw2bgr_image, CV_BayerRG2BGR);
    //cvtColor(rawImage, raw2bgr_image, CV_BayerGR2BGR);
    cvtColor(rawImage, raw2bgr_image, CV_GRAY2BGR);

    imwrite("/home/humberto/Documents/Maestria/Semestre4/openCVTestRAWImages/RAW-RGB-5.jpg", raw2bgr_image);

    namedWindow("RAW2RGB", CV_WINDOW_NORMAL);
    imshow("RAW2RGB",raw2bgr_image);

    waitKey(0);

    return 0;
}


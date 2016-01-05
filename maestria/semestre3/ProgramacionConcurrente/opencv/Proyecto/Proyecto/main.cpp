#include "filter.h"
#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <iostream>
#include <sstream>
#include <thread>
//#include <chrono>
#include <fstream>
//#include "threadpool.h"

using namespace cv;
using namespace std;

bool fexists(const char *filename) {
  ifstream ifile(filename);
  return ifile;
}

int main(int argc, char** argv)
{
    int nThreads;
    int selectedFilter;

    if (argc < 2 || argc > 4) {
        cout << "Usage: main <IMAGE_PATH> [NUMBER_OF_THREADS] [FILTER]" << endl;
        return -1;
    }

    if (argc == 4) {
        istringstream sf(argv[3]);
        if (!(sf >> selectedFilter)) {
            selectedFilter = 0;
        }

        istringstream nt(argv[2]);
        if (!(nt >> nThreads)) {
            nThreads = 1;
        }
    }

    if (argc == 3) {
        istringstream nt(argv[2]);
        if (!(nt >> nThreads)) {
            nThreads = 1;
        }
    }

    if (!fexists(argv[1])){
        cout << "No image data" << endl;
        return -1;
    }

    char* imageName = argv[1];
    Filter filter1(imageName, nThreads, selectedFilter);

    if (!filter1.getImage().data) {
        cout << "No image data" << endl;
        return -1;
    }

    //Mat gray_image;
    //cvtColor(filter1.getImage(), gray_image, CV_BGR2GRAY);

    //imwrite("/home/humberto/Pictures/Gray_Image.jpg", gray_image);

    namedWindow(imageName, CV_WINDOW_AUTOSIZE);
    imshow(imageName, filter1.getImage());

    filter1.doSomething();

    // create a thread pool of 4 worker threads
    /*ThreadPool pool(4);

    // queue a bunch of "work items"
    for(int i = 0;i<8;++i)
    {
        pool.enqueue([i]
        {
            cout << "hello " << i << endl;
            this_thread::sleep_for(chrono::seconds(1));
            cout << "world " << i << endl;
        });
    }*/


    //using memfunc_type = void (Filter::*)(Mat); // Functions that receive a Mat parameter and return void
    //using memfunc_type = void (Filter::*)(); // Functions that no receive paramenters and return void
    //memfunc_type jeje = &Filter::printMat;

    //void (Filter::*jeje)(Mat) = &Filter::printMat;
    //thread je(jeje, filter1, filter1.splitMat(filter1.getImage(), 0, 0, 9, 9));
    //je.join();

    waitKey(0);

    return 0;
}


#ifndef WORKER_H
#define WORKER_H
#include "threadpool.h"

// our worker thread objects
class Worker {
public:
    Worker(ThreadPool &s) : pool(s) { }
    void operator()();
private:
    ThreadPool &pool;
};

#endif // WORKER_H

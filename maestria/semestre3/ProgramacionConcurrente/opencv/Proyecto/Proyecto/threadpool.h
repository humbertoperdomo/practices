#ifndef THREADPOOL_H
#define THREADPOOL_H
#include <thread>
#include <mutex>
#include <condition_variable>
#include <vector>
#include <deque>

using namespace std;

class ThreadPool;

// our worker thread objects
class Worker {
public:
    Worker(ThreadPool &s) : pool(s) { }
    void operator()();
private:
    ThreadPool &pool;
};

// the actual thread pool
class ThreadPool {
public:
    ThreadPool(size_t);
    template<class F>
    void enqueue(F f);
    ~ThreadPool();
private:
    friend class Worker;

    // need to keep track of threads so we can join them
    vector< thread > workers;

    // the task queue
    deque< function<void()> > tasks;

    // synchronization
    mutex queue_mutex;
    condition_variable condition;
    bool stop;
};

#endif // THREADPOOL_H

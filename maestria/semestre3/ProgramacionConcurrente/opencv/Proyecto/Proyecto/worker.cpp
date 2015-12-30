#include "worker.h"

Worker::Worker()
{

}

void Worker::operator()()
{
    std::function<void()> task;

    while(true)
    {
        {   // acquire lock
            unique_lock<std::mutex>
                lock(pool.queue_mutex);

            // look for a work item
            while(!pool.stop && pool.tasks.empty())
            { // if there are none wait for notification
                pool.condition.wait(lock);
            }

            if(pool.stop) // exit if the pool is stopped
                return;

            // get the task from the queue
            task = pool.tasks.front();
            pool.tasks.pop_front();

        }   // release lock

        // execute the task
        task();
    }
}

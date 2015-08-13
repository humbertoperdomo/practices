package com.nutiteq.core;

import com.mgmaps.utils.AsyncRunner;
import com.nutiteq.task.LocalTask;

//TODO jaanus : evaluate this implementation
public class MappingCore {

  private static MappingCore instance;

  private MappingCore() {

  }

  public static MappingCore getInstance() {
    if (instance == null) {
      instance = new MappingCore();
    }

    return instance;
  }

  public void runAsync(final LocalTask task) {
    final AsyncRunner runner = new AsyncRunner(task);
    runner.start();
  }

}

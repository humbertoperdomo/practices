package com.nutiteq.location.providers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface LocationDataConnection {
  /**
   * Disconnect from given source
   */
  void disconnect();

  /**
   * Open input stream to given location source
   * 
   * @return opened input stream
   * @throws IOException
   *           if something went wrong
   */
  InputStream openInputStream() throws IOException;
  
  /**
   * Open output stream to given location source
   * 
   * @return opened output stream
   * @throws IOException
   *           if something went wrong
   */
  OutputStream openOutputStream() throws IOException;
}

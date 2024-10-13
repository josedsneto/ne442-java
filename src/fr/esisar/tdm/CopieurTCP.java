package fr.esisar.tdm;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.InetSocketAddress;
import java.net.Socket;

public class CopieurTCP {
  public static void main(String[] args) throws Exception {
    long start = System.currentTimeMillis();
    CopieurTCP fr = new CopieurTCP();
    fr.execute();
    long stop = System.currentTimeMillis();
    System.out.println("Elapsed Time = " + (stop - start) + " ms");
  }

  /**
   * 
   */
  private void execute() throws IOException {
    System.out.println("Début lecture du fichier");

    InetSocketAddress addrdest = new InetSocketAddress("127.0.0.1", 5099);
    Socket soc = new Socket();
    soc.connect(addrdest);

    FileOutputStream fos = new FileOutputStream("/tmp/file_copiee.txt");

    byte[] buf = new byte[10];
    InputStream is = soc.getInputStream();

    int len = is.read(buf);
    while (len != -1) {
      // displayBufContent(buf, len);
      fos.write(buf, 0, len);
      len = is.read(buf);
    }

    fos.close();
    soc.close();
  }

  private void displayBufContent(byte[] buf, int len) {
    System.out.println("len=" + len);
    for (int i = 0; i < len; i++) {
      System.out.println("Caractère lu : " + buf[i]);
    }

  }
}

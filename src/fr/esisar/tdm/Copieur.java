package fr.esisar.tdm;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Copieur {
  public static void main(String[] args) throws Exception {
    long start = System.currentTimeMillis();
    Copieur fr = new Copieur();
    fr.execute();
    long stop = System.currentTimeMillis();
    System.out.println("Elapsed Time = " + (stop - start) + " ms");
  }

  /**
   * 
   */
  private void execute() throws IOException {

    //System.out.println("Début écriture du fichier file1");

    //FileOutputStream fos = new FileOutputStream("/tmp/file1");

    byte[] buf = new byte[6];

    //buf[0] = 69;
    //buf[1] = 83;
    //buf[2] = 73;
    //buf[3] = 83;
    //buf[4] = 65;
    //buf[5] = 82;

    //// Ecriture des 6 premiers octets du buffer
    //fos.write(buf, 0, 6);

    //// Fermeture du fichier
    //fos.close();

    //System.out.println("Fin écriture du fichier file1");

    System.out.println("Début lecture du fichier file1");

    FileInputStream fis = new FileInputStream("/tmp/file1");

    int len = fis.read(buf);
    while (len != -1) {
      displayBufContent(buf, len);
      len = fis.read(buf);
    }

    fis.close();

    System.out.println("Fin lecture du fichier file1");

    System.out.println("Début écriture du fichier file2");

    FileOutputStream fos2 = new FileOutputStream("/tmp/file2");

    // Ecriture des 6 premiers octets du buffer
    fos2.write(buf, 0, 6);

    // Fermeture du fichier
    fos2.close();

    System.out.println("Fin écriture du fichier file2");
  }

  private void displayBufContent(byte[] buf, int len) {
    System.out.println("len=" + len);
    for (int i = 0; i < len; i++) {
      System.out.println("Caractère lu : " + buf[i]);
    }

  }
}

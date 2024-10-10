package fr.esisar.tdm;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientTCP {
  public static void main(String[] args) throws Exception {
    long start = System.currentTimeMillis();
    ClientTCP fr = new ClientTCP();
    fr.execute();
    long stop = System.currentTimeMillis();
    System.out.println("Elapsed Time = " + (stop - start) + " ms");
  }

  /**
   * 
   */
  private void execute() throws IOException {
    // Creation de la socket
    Socket socket = new Socket();

    // Connexion au serveur
    InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 5099);
    socket.connect(adrDest);
    OutputStream os = socket.getOutputStream();

    System.out.println("Début lecture du fichier");

    FileInputStream fis = new FileInputStream("/home/userir/file2.txt");
    byte[] buf = new byte[18];

    int len = fis.read(buf);
    while (len != -1) {
      displayBufContent(buf, len);
      len = fis.read(buf);
    }
    fis.close();

    System.out.println("Fin lecture du fichier");

    System.out.println("Début écriture du fichier");

    FileOutputStream fos = new FileOutputStream("/home/userir/file2.txt");

    // Ecriture des 18 premiers octets du buffer
    os.write(buf, 0, 18);

    // Fermeture du fichier
    os.close();

    System.out.println("Fin écriture du fichier");
  }

  private void displayBufContent(byte[] buf, int len) {
    System.out.println("len=" + len);
    for (int i = 0; i < len; i++) {
      System.out.println("Caractère lu : " + buf[i]);
    }

  }
}

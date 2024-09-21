package fr.esisar.tdm;

import java.awt.Color;
import javax.swing.JFrame;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * Serveur basique UDP
 */

public class Pingred {

  public static void main(String[] args) throws Exception, IOException {
    Pingred pred = new Pingred();
    pred.execute();

  }

  public void pingpong(JFrame frame) throws Exception, IOException {
    frame.getContentPane().setBackground(Color.RED);
    frame.setVisible(true);
    Thread.sleep(1000);
    frame.getContentPane().setBackground(Color.GREEN);
    frame.setVisible(true);
  }

  private void execute() throws IOException, Exception {
    // Le serveur se declare aupres de la couche transport
    // sur le port 5099
    int destport = 4002;
    int port = 4001;
    String ip = "10.106.0.240";
    //
    JFrame frame = new JFrame("PingRouge");
    frame.setSize(300, 300);

    this.pingpong(frame);

    // Creation de la socket
    DatagramSocket socket = new DatagramSocket(null);
    socket.bind(new InetSocketAddress(port));

    for (int i = 0; i < 5; i++) {
      // Creation et envoi du message
      InetSocketAddress adrDest = new InetSocketAddress(ip, destport);
      byte[] bufE = new String("rouge").getBytes();
      DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
      socket.send(dpE);

      // Attente de la reponse
      byte[] bufR = new byte[2048];
      DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
      socket.receive(dpR);
      String reponse = new String(bufR, dpR.getOffset(), dpR.getLength());

      if (reponse.contains("rouge")) {
        this.pingpong(frame);
      } else {
        System.out.println("Error");
      }
    }

    // Fermeture de la socket
    socket.close();
    frame.dispose();
    System.out.println("Arret du match .");
  }

}

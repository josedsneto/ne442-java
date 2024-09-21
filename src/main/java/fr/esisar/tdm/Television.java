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

public class Television {

  public static void main(String[] args) throws Exception {
    Television tele = new Television();
    tele.execute();

  }

  private void execute() throws IOException, Exception {
    // Le serveur se declare aupres de la couche transport
    // sur le port 5099
    int port = 7050;
    DatagramSocket socket = new DatagramSocket(null);
    socket.bind(new InetSocketAddress(port));

    // Attente du premier message
    byte[] bufR = new byte[2048];
    DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
    socket.receive(dpR);
    String message = new String(bufR, dpR.getOffset(), dpR.getLength());
    // System.out.println("Message recu = "+message);

    JFrame frame = new JFrame("Chenillard");
    frame.setSize(300, 300);

    if (message.contains("red")) {
      frame.getContentPane().setBackground(Color.RED);
      frame.setVisible(true);
      Thread.sleep(2000);
      frame.dispose();
    } else if (message.contains("green")) {
      frame.getContentPane().setBackground(Color.GREEN);d
      frame.setVisible(true);
      Thread.sleep(2000);
      frame.dispose();
    } else {
      System.out.println("Error");
    }

    // Fermeture de la socket
    socket.close();
    System.out.println("Arret du serveur .");
  }

}

package fr.esisar.tdm;

import java.awt.Color;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JFrame;

/**
 * Serveur basique UDP
 */

public class ChenillardUDP {

  public static void main(String[] args) throws Exception, IOException {
    ChenillardUDP pred = new ChenillardUDP();
    pred.execute(args);

  }

  public void pingpong(JFrame frame) throws Exception, IOException {
    frame.getContentPane().setBackground(Color.RED);
    frame.setVisible(true);
    Thread.sleep(1000);
    frame.getContentPane().setBackground(Color.GREEN);
    frame.setVisible(true);
  }

  private void execute(String[] args) throws IOException, Exception {
    // Le serveur se declare aupres de la couche transport
    // sur le port 5099
    Vector<Integer> ports = new Vector<>(Arrays.asList(4001, 4002, 4003, 4004));
    String ip = "127.0.0.1";
    //
    JFrame frame = new JFrame("ChenillardUDP");
    frame.setSize(300, 300);
    frame.getContentPane().setBackground(Color.GREEN);
    frame.setVisible(true);

    // Creation de la socket
    DatagramSocket socket = new DatagramSocket(null);

    int port = Integer.valueOf(args[0]);
    socket.bind(new InetSocketAddress(port));
    
    int match = 0;

    while (match < 10) {
      switch (args[0]) {
        case "4001":
          // Creation et envoi du message
          InetSocketAddress adrDest1 = new InetSocketAddress(ip, ports.get(1));
          byte[] bufE1 = new String("rouge").getBytes();
          DatagramPacket dpE1 = new DatagramPacket(bufE1, bufE1.length, adrDest1);
          socket.send(dpE1);
          break;

        case "4002":
          // Creation et envoi du message
          InetSocketAddress adrDest2 = new InetSocketAddress(ip, ports.get(2));
          byte[] bufE2 = new String("rouge").getBytes();
          DatagramPacket dpE2 = new DatagramPacket(bufE2, bufE2.length, adrDest2);
          socket.send(dpE2);
          break;

        case "4003":
          // Creation et envoi du message
          InetSocketAddress adrDest3 = new InetSocketAddress(ip, ports.get(3));
          byte[] bufE3 = new String("rouge").getBytes();
          DatagramPacket dpE3 = new DatagramPacket(bufE3, bufE3.length, adrDest3);
          socket.send(dpE3);
          break;

        case "4004":
          // Creation et envoi du message
          InetSocketAddress adrDest4 = new InetSocketAddress(ip, ports.get(0));
          byte[] bufE4 = new String("rouge").getBytes();
          DatagramPacket dpE4 = new DatagramPacket(bufE4, bufE4.length, adrDest4);
          socket.send(dpE4);
          break;

        default:
          break;
      }

      // Attente de la reponse
      byte[] bufR = new byte[2048];
      DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
      socket.receive(dpR);
      String reponse = new String(bufR, dpR.getOffset(), dpR.getLength());

      if (reponse.contains("rouge")) {
        this.pingpong(frame);
      }

      match++;
    }

    // Fermeture de la socket
    frame.dispose();
    socket.close();
  }

}

package fr.esisar.tdm;

import java.awt.Color;
import javax.swing.JFrame;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Vector;

/**
 * Serveur basique UDP
 */

public class ChenillardServeur {

  public static void main(String[] args) throws Exception, IOException {
    ChenillardServeur pred = new ChenillardServeur();
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
    Vector<Integer> destports = new Vector<>();
    int port = 4001;
    String ip = "127.0.0.1";
    //
    JFrame frame = new JFrame("PingRouge");
    frame.setSize(300, 300);

    frame.getContentPane().setBackground(Color.GREEN);
    frame.setVisible(true);

    // Creation de la socket
    DatagramSocket socket = new DatagramSocket(null);
    socket.bind(new InetSocketAddress(port));

    byte[] bufR = new byte[2048];
    DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);

    System.out.println("Waiting for client 1");
    socket.receive(dpR);
    String rep1 = new String(bufR, dpR.getOffset(), dpR.getLength());
    System.out.println("Received: " + rep1);
    System.out.println("Client of port " + dpR.getPort() + " is registered");
    destports.add(dpR.getPort());
    System.out.println("Ports: " + destports);

    System.out.println("Waiting for client 2");
    socket.receive(dpR);
    String rep2 = new String(bufR, dpR.getOffset(), dpR.getLength());
    System.out.println("Received: " + rep2);
    System.out.println("Client of port " + dpR.getPort() + " is registered");
    destports.add(dpR.getPort());
    System.out.println("Ports: " + destports);

    System.out.println("Waiting for client 3");
    socket.receive(dpR);
    String rep3 = new String(bufR, dpR.getOffset(), dpR.getLength());
    System.out.println("Received: " + rep3);
    System.out.println("Client of port " + dpR.getPort() + " is registered");
    destports.add(dpR.getPort());
    System.out.println("Ports: " + destports);

    int iteractions = 0;

    while (iteractions < 10) {

      this.pingpong(frame);
      byte[] bufE = new String("rouge").getBytes();

      // Creation et envoi du message
      System.out.println("Changing client 1");
      InetSocketAddress client1 = new InetSocketAddress(ip, destports.get(0));
      DatagramPacket dpE1 = new DatagramPacket(bufE, bufE.length, client1);
      socket.send(dpE1);

      socket.receive(dpR);
      System.out.println("Changing client 2");
      InetSocketAddress client2 = new InetSocketAddress(ip, destports.get(1));
      DatagramPacket dpE2 = new DatagramPacket(bufE, bufE.length, client2);
      socket.send(dpE2);

      socket.receive(dpR);
      System.out.println("Changing client 3");
      InetSocketAddress client3 = new InetSocketAddress(ip, destports.get(2));
      DatagramPacket dpE3 = new DatagramPacket(bufE, bufE.length, client3);
      socket.send(dpE3);
      socket.receive(dpR);

      iteractions++;

      System.out.println(client1.toString());

    }

    // Fermeture de la socket
    socket.close();
    frame.dispose();
  }

}

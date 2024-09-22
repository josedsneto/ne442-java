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

public class ChenillardClients {

  public static void main(String[] args) throws Exception, IOException {
    ChenillardClients pgreen = new ChenillardClients();
    pgreen.execute(args);

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
    int destport = 4001;
    String ip = "127.0.0.1";
    //
    JFrame frame = new JFrame("ChenillardClient");
    frame.setSize(300, 300);

    frame.getContentPane().setBackground(Color.GREEN);
    frame.setVisible(true);

    // Creation de la socket
    DatagramSocket socket = new DatagramSocket();

    // Creation enregistrement
    InetSocketAddress adrDest = new InetSocketAddress(ip, destport);
    byte[] bufE = new String("enregistrement").getBytes();
    DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
    socket.send(dpE);

    byte[] bufR = new byte[2048];
    DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);

    int iteractions = 0;

    while (iteractions < 10) {
      // Attente de la reponse

      socket.receive(dpR);
      String command = new String(bufR, dpR.getOffset(), dpR.getLength());
      System.out.println("Command: " + command);

      this.pingpong(frame);

      byte[] confirmation = new String("changed").getBytes();
      DatagramPacket confirm = new DatagramPacket(confirmation, confirmation.length, adrDest);
      socket.send(confirm);

      iteractions++;
    }

    // Fermeture de la socket
    frame.dispose();
    socket.close();
  }

}

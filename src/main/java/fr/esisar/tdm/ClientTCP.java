package fr.esisar.tdm3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Vector;

/**
 * Client basique TCP
 * 
 */
public class ClientTCP {

  public static void main(String[] args) throws Exception {
    ClientTCP clientTCP = new ClientTCP();
    clientTCP.execute();
  }

  /**
   * Le client cree une socket, envoie un message au serveur
   * et attend la reponse
   * 
   */
  private void execute() throws IOException, Exception {
    //
    System.out.println("Demarrage du client ...");

    // Creation de la socket
    Socket socket = new Socket();

    // Connexion au serveur
    InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 7500);
    socket.connect(adrDest);

    // Attente de la reponse
    byte[] bufR = new byte[2048];
    InputStream is = socket.getInputStream();

    for (int match = 0; match < 10; match++) {
      Thread.sleep(50);
      int lenBufR = is.read(bufR);

      if (lenBufR != -1) {

        int buffer=0;
        String reponse = new String(bufR, 0, lenBufR);

        if (reponse.contains("Erreur")) {
          // Fermeture de la socket
          socket.close();
          System.out.println("Arret du client .");
        }

        Vector<String> expressions = new Vector<>();
        Vector<Integer> perebas = new Vector<>();
        System.out.println("Question recue = " + reponse);

        if (reponse.contains("=?")) {

          String[] answer = reponse.split("=\\?");
          System.out.println("Vector of operations (str) = " + Arrays.toString(answer));

          for (String part : answer) {
            if (!part.isEmpty() && part.contains("+")) {
              expressions.add(part);

              String[] numeros = part.split("\\+");
              System.out.println("Subvector (str) = " + Arrays.toString(numeros));

              int num1 = Integer.parseInt(numeros[0]);
              int num2 = Integer.parseInt(numeros[1]);
              int soma = num1 + num2;

              perebas.add(soma);
            } else if (!part.isEmpty() && !part.contains("+")) {
              buffer = Integer.valueOf(part);
            }
          }
        } else if (!reponse.contains("=?")) {
          if (reponse.contains("+")) {
            String[] answer = reponse.split("+");
            if (answer.length != 0 && answer.length < 2) {
              int num2 = Integer.valueOf(answer[0]);
              int soma = buffer + num2;
              perebas.add(soma);

            } else if (answer.length != 0 && answer.length < 3) {
              int num1 = Integer.parseInt(answer[0]);
              int num2 = Integer.parseInt(answer[1]);
              int soma = num1 + num2;

              perebas.add(soma);
            } else {
              String zero = new String("0");
              byte[] bufE = String.valueOf(zero).getBytes();
              OutputStream os = socket.getOutputStream();
              os.write(bufE);
              System.out.println("Sent " + zero);
            }
          } else {
            buffer = Integer.valueOf(reponse);
          }
        }

        StringBuilder resultString = new StringBuilder();
        for (int result : perebas) {
          resultString.append(result).append(";");
        }

        byte[] bufE = resultString.toString().getBytes();
        OutputStream os = socket.getOutputStream();
        os.write(bufE);
        System.out.println("Sent " + resultString);
      }
    }
    // Fermeture de la socket
    socket.close();
    System.out.println("Arret du client .");
  }
}

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;

/**
 * Client basique TCP
 * 
 */
public class AdditionTCP {

  public static void main(String[] args) throws Exception {
    AdditionTCP clientTCP = new AdditionTCP();
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

    // Buffer
    String strBuffer = "";

    // Attente de la reponse
    byte[] bufR = new byte[2048];
    InputStream is = socket.getInputStream();

    for (int matches = 0; matches < 100; matches++) {
      int lenBufR = is.read(bufR);
      Thread.sleep(10);

      if (lenBufR != -1) {
        String command = new String(bufR, 0, lenBufR);
        System.out.println("Question recue = " + command);

        String question = strBuffer + command;
        System.out.println("Question current avec strBuffer = " + question);

        if (question.contains("=?")) {
          String[] answer = question.split("=\\?", 2);
          System.out.println("Current operation (str) = " + Arrays.toString(answer));

          String[] nombres = answer[0].split("\\+");
          System.out.println("Vector (str) = " + Arrays.toString(nombres));

          int num1 = Integer.parseInt(nombres[0]);
          int num2 = Integer.parseInt(nombres[1]);
          int somme = num1 + num2;

          String resultat = String.valueOf(somme) + ";";

          byte[] bufE = resultat.toString().getBytes();
          OutputStream os = socket.getOutputStream();
          os.write(bufE);
          System.out.println("Sent " + resultat);

          strBuffer = answer[1];
        } else {
          strBuffer += command;
        }
      } 
    }

    // Fermeture de la socket
    socket.close();
    System.out.println("Arret du client .");
  }
}

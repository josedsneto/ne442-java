package fr.esisar.tdm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Client basique TCP
 * 
 */
public class NewJeuxTCP {

  public static void main(String[] args) throws Exception {
    String buff = "";
    NewJeuxTCP clientTCP = new NewJeuxTCP(buff);
    clientTCP.execute();
  }

  /**
   * Le client cree une socket, envoie un message au serveur
   * et attend la reponse
   * 
   */

  public String reponsebuffer = "";

  public NewJeuxTCP(String buff) {

    this.reponsebuffer = buff;

  }

  private void execute() throws IOException, Exception {
    //
    System.out.println("OK, o pai tá on ");

    // Creation de la socket
    Socket socket = new Socket();

    // Connexion au serveur
    InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 7500);
    socket.connect(adrDest);

    // Attente de la reponse
    byte[] bufR = new byte[2048];
    InputStream is = socket.getInputStream();
    OutputStream os = socket.getOutputStream();

    String respostacompleta = "";
    String question = "";

    int comeco = 0;
    int fim = 100;

    while (comeco < fim) {
      int lenBufR = is.read(bufR);

      if (lenBufR != -1) {
        String recebido = new String(bufR, 0, lenBufR);
        this.reponsebuffer += recebido;
        System.out.println("Agora calcule esta parada = " + recebido);
      }

      question = this.getQuestion();

      while (!question.contains("morreu o buffer")) {
        Integer idxPlus = question.indexOf("+");
        Integer idxEquals = question.indexOf("=?");

        String num1str = question.substring(0, idxPlus);
        String num2str = question.substring(idxPlus, idxEquals);

        Integer soma = Integer.parseInt(num1str) + Integer.parseInt(num2str);

        respostacompleta += String.valueOf(soma) + ";";

        question = this.getQuestion();
      }
      if (respostacompleta.length() > 0) {
        // Caro servidor, RECEBA!
        byte[] bufE = respostacompleta.getBytes();
        os.write(bufE);
        comeco++;
      }
    }
    String boraparar = new String("40028922;");
    byte[] okvouparar = boraparar.getBytes();
    os.write(okvouparar);
    System.out.println("Parei bixo");
    socket.close();
  }

  public String getQuestion() {
    Integer idx = this.reponsebuffer.indexOf("=?");

    // Check if buffer has a question
    if (idx != -1) {

      String questaozinhasaliente = this.reponsebuffer.substring(0, idx + 2);

      // This is costly on memory, but building a queue is a lot of work
      this.reponsebuffer = this.reponsebuffer.substring(idx + 2);

      return questaozinhasaliente;
    } else {
      return "morreu o buffer";
    }
  }
}

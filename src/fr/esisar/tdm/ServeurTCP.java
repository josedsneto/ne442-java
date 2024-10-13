package fr.esisar.tdm;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Serveur basique TCP
 */
public class ServeurTCP {

  public static void main(String[] args) throws Exception {
    ServeurTCP serveurTCP = new ServeurTCP();
    serveurTCP.execute();

  }

  private void execute() throws IOException {
    //
    System.out.println("Demarrage du serveur ...");

    // Le serveur se declare aupres de la couche transport
    // sur le port 5099
    ServerSocket socketEcoute = new ServerSocket();
    socketEcoute.bind(new InetSocketAddress(5099));

    // Attente de la connexion d'un client
    System.out.println("Attente de la connexion du client ...");
    Socket socketConnexion = socketEcoute.accept();

    // Affichage du port et de l'ip du client
    System.out.println("Un client est connecté");
    System.out.println("IP:" + socketConnexion.getInetAddress());
    System.out.println("Port:" + socketConnexion.getPort());

    // Un client s'est connecte, le serveur lit le message envoye par le client
    // (sans
    // garantie de lire tout le message)
    byte[] bufR = new byte[2048];
    InputStream is = socketConnexion.getInputStream();
    int lenBufR = is.read(bufR);

    FileOutputStream fos = new FileOutputStream("c:/tmp/ecriture_fichier.txt");
    byte[] buf = new byte[18];
    if (lenBufR != -1) {
      // Ecriture des 6 premiers octets du buffer
      fos.write(buf, 0, 6);
    }
    // Fermeture du fichier
    fos.close();
    System.out.println("Fin écriture du fichier");

    // Emission d'un message en retour
    byte[] bufE = new String("ok").getBytes();
    OutputStream os = socketConnexion.getOutputStream();
    os.write(bufE);
    System.out.println("Message envoye = ok");

    // Fermeture de la socket de connexion
    socketConnexion.close();

    // Arret du serveur
    socketEcoute.close();
    System.out.println("Arret du serveur .");
  }

}

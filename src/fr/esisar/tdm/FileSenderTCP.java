package fr.esisar.tdm;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Serveur basique TCP
 */
public class FileSenderTCP
{

    public static void main(String[] args) throws Exception
    {
        FileSenderTCP serveurTCP = new FileSenderTCP();
        serveurTCP.execute();

    }



    private void execute() throws IOException
    {
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
        System.out.println("IP:"+socketConnexion.getInetAddress());
        System.out.println("Port:"+socketConnexion.getPort());


        FileInputStream fis = new FileInputStream("/tmp/texte_a_lire.txt");
        OutputStream os = socketConnexion.getOutputStream();
        byte[] buf = new byte[10];

        int len = fis.read(buf);
        while(len!=-1)
        {
            os.write(buf);
            len = fis.read(buf);
        }
        fis.close();

        // Fermeture de la socket de connexion
        socketConnexion.close();


        // Arret du serveur 
        socketEcoute.close();
        System.out.println("Arret du serveur .");
    }

}

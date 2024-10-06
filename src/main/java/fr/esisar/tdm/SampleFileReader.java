package fr.esisar.tdm4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SampleFileReader 
{
    public static void main(String[] args) throws Exception
    {
    	long start = System.currentTimeMillis();
    	SampleFileReader fr = new SampleFileReader();
        fr.execute();
        long stop = System.currentTimeMillis();
        System.out.println("Elapsed Time = "+(stop-start)+" ms");
    }


    /**
     * 
     */
    private void execute() throws IOException
    {
    	System.out.println("Début lecture du fichier");

        FileInputStream fis = new FileInputStream("/home/userir/file1.txt");
        byte[] buf = new byte[18];

        int len = fis.read(buf);
        while(len!=-1)
        {
            displayBufContent(buf,len);
            len = fis.read(buf);
        }
        fis.close();

        System.out.println("Fin lecture du fichier");
        
        System.out.println("Début écriture du fichier");

        FileOutputStream fos = new FileOutputStream("/home/userir/file2.txt");

        // Ecriture des 18 premiers octets du buffer 
        fos.write(buf,0,18);

        // Fermeture du fichier
        fos.close();

        System.out.println("Fin écriture du fichier");
    }


    private void displayBufContent(byte[] buf, int len) 
    {
        System.out.println("len="+len);
        for (int i = 0; i < len; i++) 
        {
            System.out.println("Caractère lu : "+buf[i]);
        }

    }
}

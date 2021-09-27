package com.example.melone;

import java.io.*;
import java.net.*;


public class Client 
{
    String nomeserver="localhost";
    int portaserver=6789;
    Socket msocket;
    BufferedReader tastiera;
    String stringautente;
    String stringarispostaserver;
    DataOutputStream outversoserver;
    BufferedReader indalserver;
    
    public static void main( String[] args )
    {
        Client mclient=new Client();
        mclient.connetti();
        mclient.comunica();
    }
    
    public Socket connetti()
    {
        System.out.println("Client in esecuzione");
        try
        {
            tastiera=new BufferedReader(new InputStreamReader(System.in));
            msocket= new Socket(nomeserver, portaserver);
            outversoserver=new DataOutputStream(msocket.getOutputStream());
            indalserver=new BufferedReader(new InputStreamReader(msocket.getInputStream()));
        }
        catch(UnknownHostException e)
        {
            System.err.println("Host sconosciuto");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore connessione");
            System.exit(1);
        }
        return msocket;
    }

    public void comunica()
    {
        try
        {
            System.out.println("Inserisci la stringa"+'\n');
            stringautente=tastiera.readLine();
            
            System.out.println("invio stringa");
            outversoserver.writeBytes(stringautente+'\n');

            stringarispostaserver=indalserver.readLine();
            System.out.println("risposta dal server"+'\n'+stringarispostaserver);

            System.out.println("client termina istruzioni e chiude connessione");
            msocket.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore connessione");
            System.exit(1);
        }
    }

}

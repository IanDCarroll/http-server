package com.ian;

import java.io.*;
import java.net.*;

public class ClientSocketHelper {

    public static String request(String toServer) throws IOException {
        //System.out.println("request called");
        Socket clientSocket = new Socket("127.0.0.1", 5000);
        //System.out.println("ClientSocket initiated");
        PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        outToServer.println(toServer);
        //System.out.println("Client called");
        return inFromServer.readLine();
    }
}

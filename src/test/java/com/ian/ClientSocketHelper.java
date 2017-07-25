package com.ian;

import java.io.*;
import java.net.*;

public class ClientSocketHelper {

    public static String request(String toServer) {
        String response = "";
        boolean trying = true;
        while( trying ) {
            try {
                Socket clientSocket = new Socket("127.0.0.1", 5000);
                PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                trying = false;
                outToServer.println(toServer);
                response = inFromServer.readLine();
            } catch (IOException e) {}
        }
        return response;
    }
}

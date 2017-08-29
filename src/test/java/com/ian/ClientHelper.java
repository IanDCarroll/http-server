package com.ian;

import java.io.*;
import java.net.*;

public class ClientHelper {

    public static String request(String toServer) {
        String response;
        try {
            Socket clientSocket = new Socket("127.0.0.1", 5000);
            PrintWriter giveOrder =
                    new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader getWhatWasAksedFor =
                    new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            giveOrder.println(toServer);
            response = getWhatWasAksedFor.readLine();
            clientSocket.close();
        } catch (IOException e) { response = e.getMessage(); }
        return response;
    }
}

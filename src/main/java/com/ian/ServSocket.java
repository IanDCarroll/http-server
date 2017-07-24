package com.ian;

import java.net.*;
import java.io.*;

public class ServSocket {
    public static void main(String[] args) {
        int port = 5000;
        try { port = Integer.parseInt(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.format("No param for port number; using default port %d", port);
        } finally {
            serve(port);
        }
    }

    public static void serve(int port) {

        try {
            ServerSocket maitreD = new ServerSocket(port);

            while (true) {
                Socket garconDeCafe = maitreD.accept();

                PrintWriter out = new PrintWriter(garconDeCafe.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(garconDeCafe.getInputStream(), "UTF-8"));

                out.println(Parser.parse(in.readLine()));
                garconDeCafe.close();
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " + port + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}


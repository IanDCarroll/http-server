package com.ian;

import java.net.*;
import java.io.*;

public class Server {
    public static int port = 5000;

    public static void main(String[] args) {
        try { port = Integer.parseInt(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.format("No param for port number; using default port %d", port);
        } finally {
            Server cafeFrance = new Server();
            cafeFrance.serve();
        }
    }

    public void serve() {

        try {
            ServerSocket maitreD = new ServerSocket(port);
            synchronized (this) { notify(); }

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
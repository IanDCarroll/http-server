package com.ian;

import java.net.*;
import java.io.*;

public class ServSocket {
    public static void main(String[] args) {
        validateArgNumber(1);
        //int port = Integer.parseInt(args[0]);
        serve(5000);
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
            printIOException(port, e);
        }
    }

    private static void validateArgNumber(int argNumber) {
        if(argNumber != 1) {
            System.err.println ("Server needs exactly one arg: the Port Number.");
            System.exit(1);
        }
    }

    private static void printIOException(int port, IOException e) {
        System.out.println("Exception caught when trying to listen on port " + port + " or listening for a connection");
        System.out.println(e.getMessage());
    }
}


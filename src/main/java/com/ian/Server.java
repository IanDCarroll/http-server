package com.ian;

import java.net.*;
import java.io.*;

public class Server {
    public int port = 5000;
    public String directory = "/Users/ian/cob_spec/public";
    private ServerSocket maitreD;
    public boolean theDiningRoomIsOpen = true;

    public static void main(String[] args) {
        Server server;
        if (args.length == 0) {
            server = new Server();
        } else if (args.length == 2 && args[0].equals("-p")) {
            server = new Server(Integer.parseInt(args[1]));
        } else if (args.length == 2 && args[0].equals("-d")) {
            server = new Server(args[1]);
        } else if (args.length == 3 && args[0].equals("-pd")) {
            server = new Server(Integer.parseInt(args[1]), args[2]);
        } else if (args.length == 3 && args[0].equals("-dp")) {
            server = new Server(Integer.parseInt(args[2]), args[1]);
        } else if (args.length == 4 && args[0].equals("-p") && args[2].equals("-d")) {
            server = new Server(Integer.parseInt(args[1]), args[3]);
        } else if (args.length == 4 && args[0].equals("-d") && args[2].equals("-p")) {
            server = new Server(Integer.parseInt(args[3]), args[1]);
        } else if (args.length > 4) {
            System.out.println("Arguments make no sense. Switching to defaults.");
            server = new Server();
        } else {
            System.out.println("You can use -p to specify the port and -d to specify the directory.");
            server = new Server();
        }
        server.serve();
    }

    public Server() {
        report();
    }

    public Server(int port) {
        this.port = port;
        report();
    }

    public Server(String directory) {
        this.directory = directory;
        report();
    }

    public Server(int port, String directory) {
        this.port = port;
        this.directory = directory;
        report();
    }

    public String report() {
        return "Serving " + directory + " on port " + Integer.toString(port);
    };

    public void serve() {

        try {
            maitreD = new ServerSocket(port);
            synchronized (this) { notify(); }

            while (theDiningRoomIsOpen) {
                Socket garconDeCafe = maitreD.accept();

                PrintWriter out = new PrintWriter(garconDeCafe.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(garconDeCafe.getInputStream(), "UTF-8"));

                out.println(Parser.parse(in.readLine(), directory));
                garconDeCafe.close();
            }
        } catch (IOException e) { e.getMessage(); }
    }

    public void close() {
        theDiningRoomIsOpen = false;
        try{ maitreD.close();
        } catch (IOException e) { e.getMessage(); }
    }
}
package Java_2.HW_Java_2.lesson_6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created Александр Руденко on 16.09.2017.
 */


/**
 * Главная задумка сделать отделными потоки рид райт и на клиенте и на сервере
 */

public class ConsoleServer {

    final int PORT = 1024;
    final String SERVER_ON = "server on";
    final String SERVER_OFF = "server off"; // command for exit
    final String JOIN = " client join";
    ServerSocket server;
    Socket socket;
    int numberOfClient = 0;

    public static void main(String[] args) {
        new ConsoleServer();
    }

    ConsoleServer() {
        System.out.println(SERVER_ON);
        try {
            server = new ServerSocket(PORT);

            while (true) {
                socket = server.accept();
                numberOfClient++;
                System.out.println("#" + numberOfClient + JOIN);
                new Thread(new ClientThreadOnSerer(socket, "Client_" + numberOfClient)).join();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                server.close();
                System.out.println(SERVER_OFF);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}

class ClientThreadOnSerer extends Thread {

    final String DISCONNECT = " disconnect client";
    final String EXIT_KEY = "q";

    BufferedReader reader;
    PrintWriter writer;
    Socket socket;
    String name;

    ClientThreadOnSerer(Socket clientSocket, String name) {

        super(name);
        this.name = name;
        try {
            socket = clientSocket;
            reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        start();
    }

    @Override
    public void run() {
        String message;
        try {
            do {

                message = reader.readLine();
                System.out.println(name + ": " + message);
                writer.println("echo: " + message);
                writer.flush();

            } while (!message.equalsIgnoreCase(EXIT_KEY));
            System.out.println(name + DISCONNECT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}



package Java_2.HW_Java_2.lesson_6;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Александр Руденко on 16.09.2017.
 * консольная часть клиенцта
 */
class ConsoleClient {

    //    final String SERVER_ADDR = "127.0.0.1";
    final String SERVER_ADDR = "localhost";
    final int SERVER_PORT = 1024;
    final String EXIT_KEY = "q";
    final String CLIENT_PROMPT = "$ ";
    final String CONNECT_TO_SERVER = "Connection to server established.";
    final String CONNECT_CLOSED = "Connection closed.";

    Socket socket;
    Scanner scanner;
    PrintWriter writer;
    String message;

    public static void main(String[] args) {
        new ConsoleClient();
    }

    ConsoleClient() {
        scanner = new Scanner(System.in);
        try {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            writer = new PrintWriter(socket.getOutputStream());
            System.out.println(CONNECT_TO_SERVER);
            do {

                System.out.print(CLIENT_PROMPT);
                message = scanner.nextLine();
                writer.println(message);
                writer.flush();

            } while (!message.equalsIgnoreCase(EXIT_KEY));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                socket.close();
                System.out.println(CONNECT_CLOSED);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}



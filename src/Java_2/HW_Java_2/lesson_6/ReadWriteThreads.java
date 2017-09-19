package Java_2.HW_Java_2.lesson_6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Red Panda on 18.09.2017.
 */
public class ReadWriteThreads extends Thread {

    final String DISCONNECT = " disconnect client";
    final String EXIT_KEY = "exit";

    Scanner reader;
    PrintWriter writer;
    Scanner scanner;
    Socket socket;
    boolean flag;

    ReadWriteThreads(Socket clientSocket, boolean flag) {

        super();
        this.flag = flag;

        try {

            socket = clientSocket;
            reader = new Scanner(socket.getInputStream());
            scanner = new Scanner(System.in);
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
                if (flag) {
                    message = reader.nextLine();
                    if (!message.equals("")) {
                        System.out.println("->" + ": " + message);
                    }
                } else {
                    message = scanner.nextLine();
                    if (!message.equals("")) {
                        System.out.println("<-" + ":" + message);
                        writer.println(message);
                        writer.flush();
                    }
                }
            } while (!message.equalsIgnoreCase(EXIT_KEY));
            System.out.println(DISCONNECT);
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
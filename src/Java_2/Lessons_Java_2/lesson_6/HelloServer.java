package Java_2.Lessons_Java_2.lesson_6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by  Александр Руденко on 16.09.2017.
 * тестовый сервер с занятия
 */
public class HelloServer {
    public static void main(String[] args) {
        new HelloServer();
    }

    PrintWriter writer;
    HelloServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(1024);
            System.out.println("Server has been started");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected.");
                writer = new PrintWriter(socket.getOutputStream());
                writer.println("ConsoleServer");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            writer.close();
        }

    }
}


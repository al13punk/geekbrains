package Java_2.HW_Java_2.lesson_6;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created Александр Руденко on 16.09.2017.
 * консольная часть сервера
 */


public class ConsoleServer {

    final int PORT = 1024;
    final String SERVER_ON = "server on";
    final String SERVER_OFF = "server off"; // command for exit
    final String JOIN = " client join";
    ServerSocket server;
    Socket socket;


    public static void main(String[] args) {
        new ConsoleServer();
        System.out.println(111);
    }

    ConsoleServer() {
        System.out.println(SERVER_ON);
        try {
            server = new ServerSocket(PORT);
            socket = server.accept();
            System.out.println("#" + socket + JOIN);
            new Thread(new ReadWriteThreads(socket,true));
            new Thread(new ReadWriteThreads(socket,false));
            while (!socket.isClosed()) {
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                server.close();
                System.out.println(SERVER_OFF);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}





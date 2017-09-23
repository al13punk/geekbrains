package Java_2.HW_Java_2.lesson_6;

import java.net.Socket;

/**
 * Created by Александр Руденко on 23.09.2017.
 */
class ConsoleClient {

    //    final String SERVER_ADDR = "127.0.0.1";
    final String SERVER_ADDR = "localhost";
    final int SERVER_PORT = 1024;
    final String CLIENT_ON = "client on";
    final String CLIENT_OFF = "client off"; // command for exit
    final String JOIN = " join to server";

    Socket socket;

    public static void main(String[] args) {
        new ConsoleClient();
    }

    ConsoleClient() {
        System.out.println(CLIENT_ON);
        try {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);

            new Thread(new ReadWriteThreads(socket,  false));
            new Thread(new ReadWriteThreads(socket,  true));
            System.out.println(socket+":"+JOIN);
            while (!socket.isClosed()) {
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(CLIENT_OFF);

            /*try {
                socket.close();
                System.out.println(CONNECT_CLOSED);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }*/
        }
    }
}



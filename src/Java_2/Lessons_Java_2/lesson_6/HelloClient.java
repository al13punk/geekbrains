package Java_2.Lessons_Java_2.lesson_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Александр Руденко on 16.09.2017.
 * тестовый клиетн
 */
class HelloClient {
    public static void main(String[] args) {
        new HelloClient();
    }

    HelloClient() {
        try {
            int i = 0;
            while (i < 199) {
                Socket socket = new Socket("localhost", 1024);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String str = reader.readLine();
                System.out.println(str);
                i++;
                reader.close();
            }


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

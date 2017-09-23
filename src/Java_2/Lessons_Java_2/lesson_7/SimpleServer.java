package Java_2.Lessons_Java_2.lesson_7;
/**
 * Created by Александр Руденко on 23.09.2017.
 */


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

class SimpleServer implements IConstants {

    int client_count = 0; //колличество клиентов
    ServerSocket server; //сокет сервера
    Socket socket; //сокет клиента

    //костыль дз
    ArrayList<ClientHandler> allClientsTread = new ArrayList<>();

    public static void main(String[] args) {
        new SimpleServer();//запуск
    }

    SimpleServer() {
        System.out.println(SERVER_START);//сообщение сервер старт
        new Thread(new CommandHandler()).start(); //создаем и запускаем поток команд сервера(пока только выход)
        try {
            server = new ServerSocket(SERVER_PORT);//создаем сокет сервера
            while (true) {//цикл на прослушку порта
                socket = server.accept(); //сокет ждет подключения
                client_count++;//каунт клиентов
                System.out.println("#" + client_count + CLIENT_JOINED);//сообщение что клиент № присоеденился

                /*
                new Thread(new ClientHandler(socket)).start(); //создаем и запускаем поток клиента
                 */
//костыль дз начало
                ClientHandler b = new ClientHandler(socket);
                Thread a = new Thread(b); //создаем и запускаем поток клиента
                a.start();//запускаем
                allClientsTread.add(b);//запускаем добавляем в коллекцию
// костыль дз конец

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(SERVER_STOP); //сообщение что сервер офф
    }

    /**
     * checkAuthentication: check login and password
     */
    //проверка аунтификации клиента
    private boolean checkAuthentication(String login, String password) {
        Connection connect;
        boolean result = false;
        try {
            // connect db
            Class.forName(DRIVER_NAME);
            connect = DriverManager.getConnection(SQLITE_DB);
            // looking for login && password in db
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_SELECT.replace("?", login));
            while (rs.next())
                result = rs.getString(PASSWD_COL).equals(password);
            // close all
            rs.close();
            stmt.close();
            connect.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return result;
    }

    /**
     * CommandHandler: processing of commands from server console
     */
    //пооток команд серверу
    class CommandHandler implements Runnable {
        Scanner scanner = new Scanner(System.in);//сканер для консоли

        @Override
        public void run() {
            String command;
            do
                command = scanner.nextLine(); //ввод с консоли
            while (!command.equals(EXIT_COMMAND));//проверка на команду выхода
            try {
                server.close();//не красивое закрытие сервера
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * ClientHandler: service requests of clients
     */
    //поток обработки сообщений клиента
    class ClientHandler implements Runnable {

        BufferedReader reader; //читать из потока клиента
        PrintWriter writer;
        PrintWriter tempWriter;
        Socket socket;
        String name;

        ClientHandler(Socket clientSocket) {
            try {
                socket = clientSocket;
                reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream());
                name = "Client #" + client_count;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        @Override
        public void run() {
            String message;
            try {
                do {
                    message = reader.readLine();//читаем сообщение клиента
                    if (message != null) {
                        System.out.println(name + ": " + message); //выводим это сообщение
                        if (message.startsWith(AUTH_SIGN)) {//проверка является ли эта строка аутификационной
                            String[] wds = message.split(" ");//делим строку на 3 подстроки
                            if (checkAuthentication(wds[1], wds[2])) { //проверяем лог и пасс
                                name = wds[1];
                                writer.println("Hello, " + name);
                                writer.println("\0");
                            } else {
                                System.out.println(name + ": " + AUTH_FAIL);//если лог/пасс не верны пишем что провал
                                writer.println(AUTH_FAIL);
                                message = EXIT_COMMAND;
                            }
                        } else if (!message.equalsIgnoreCase(EXIT_COMMAND)) {
// дз начало
                            if (message.startsWith("/w")) {

                                String[] personalMessage = message.split(" ");//делим строку на 3 подстроки
                                message = "";
                                for (int i = 2; i < personalMessage.length; i++) {
                                    message += personalMessage[i] + " ";
                                }
                                boolean flag = false;
                                for (int i = 0; i < allClientsTread.size(); i++) {
                                    if (allClientsTread.get(i).name.equals(personalMessage[1])) {
                                        tempWriter = new PrintWriter(allClientsTread.get(i).socket.getOutputStream());
                                        flag = true;
                                        break;
                                    }
                                }
                                if (flag) {

                                    tempWriter.println(name + " -> " + message);
                                } else {
                                    tempWriter.println(personalMessage[1] + " имя не найдено");
                                }
                                tempWriter.println("\0");
                                tempWriter.flush();//отправка либо эхо либо команды на выход

                            } else {
// дз конец
                                writer.println("echo: " + message);//эхо сообщение клиенту
                                writer.println("\0");
                                writer.flush();//отправка либо эхо либо команды на выход

                            }
//                            writer.println("echo: " + message);//эхо сообщение клиенту
//                            writer.println("\0");
                        }
                        writer.flush();//отправка либо эхо либо команды на выход
                    }
                } while (!message.equalsIgnoreCase(EXIT_COMMAND));
                socket.close();
                System.out.println(name + CLIENT_DISCONNECTED);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
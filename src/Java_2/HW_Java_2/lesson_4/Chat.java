package Java_2.HW_Java_2.lesson_4;

/**
 * Created by Red Panda on 10.09.2017.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * 1. Создать окно для клиентской части чата:
 * большое текстовое поле для отображения переписки
 * в центре окна, использовать для этого JTextArea.
 * Однострочное текстовое поле для ввода сообщений и
 * кнопка для отсылки сообщений на нижней панели.
 * Сообщение должно отсылаться либо по нажатию кнопки на форме,
 * либо по нажатию кнопки Enter. При «отсылке» сообщение
 * перекидывается из нижнего поля в центральное.
 * 2* Задание повышенной сложности: добавить логгирование сообщений в файл.
 */

public class Chat extends JFrame implements ActionListener {

    //
    private final String TITLE = "Chat";
    private final int START_LOCATION_X = 800;
    private final int START_LOCATION_Y = 350;
    private final int WINDOW_WIDTH = 350;
    private final int WINDOW_HEIGHT = 450;
    private final String BUTTON_ENTER_TITLE = "Enter";
    private final String FILE_NAME = "E:\\MyJavaWay\\geekbrains\\out\\production\\geekbrains\\Java_2\\HW_Java_2\\lesson_4\\log.txt";
//    private final String FILE_NAME = "log.txt";

    JButton enter;
    JTextArea chatFrame;
    JTextField writeLine;
    JPanel bottomPanel;
    JScrollPane scrollPane;

    public static void main(String[] args) {
        Chat chat = new Chat();
    }

    public void read(JTextArea chatFrame) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            while ((line = reader.readLine()) != null) {
                chatFrame.append(line + "\n");
            }
            reader.close();
        } catch (IOException ex) {
            //ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    public void write(JTextArea chatFrame) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
            writer.append(chatFrame.getText());
            writer.close();
        } catch (IOException ex) {
            //ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    Chat() {
        //заголовок;
        setTitle(TITLE);
        //сообщает системе о необходимости завершить работу программы при закрытии формы
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //устанавливает координаты формы и ее размер в пикселях
        setBounds(START_LOCATION_X, START_LOCATION_Y, WINDOW_WIDTH, WINDOW_HEIGHT);

        //создаем панель (строка для набора текста и кнопка)и текстовое поле для чата
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        writeLine = new JTextField();
        enter = new JButton(BUTTON_ENTER_TITLE);
        chatFrame = new JTextArea();

        read(chatFrame);

        chatFrame.setEnabled(false);//делаем недострупным окно чата
        scrollPane = new JScrollPane(chatFrame);

        //цвет
        chatFrame.setBackground(new Color(100, 42, 78));
        enter.setBackground(new Color(0, 255, 10));
        writeLine.setBackground(new Color(255, 251, 44));

        //события
        enter.addActionListener(this);
        writeLine.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                write(chatFrame);
            }
        });

        //добавляем панель (строка для набора текста и кнопка)и текстовое поле для чата
        bottomPanel.add(BorderLayout.NORTH, writeLine);
        bottomPanel.add(BorderLayout.SOUTH, enter);
        add(BorderLayout.SOUTH, bottomPanel);
        add(BorderLayout.CENTER, scrollPane);


        //показывает полученную форму на экране
        setVisible(true);
    }

    // переопределение для кнопки и нажатия enter в строке
    @Override
    public void actionPerformed(ActionEvent e) {
        if (writeLine.getText().length() > 0) {
            chatFrame.append(writeLine.getText() + "\n");
            writeLine.setText("");
        }
    }
}

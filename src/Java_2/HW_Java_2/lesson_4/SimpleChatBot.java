package Java_2.HW_Java_2.lesson_4;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Red Panda on 09.09.2017.
 */

public class SimpleChatBot extends JFrame implements ActionListener{

    final String TITLE_OG_PROGRAM = "Chartter: simple chatbot";
    final int START_LOCATION = 200;
    final int WINDOW_WINDTH = 350;
    final int WINDOW_HEIGHT = 450;
    final String CHB_AI = "AI";
    final String BTN_ENTER = "Enter";


    JTextPane dialogue;
    JCheckBox ai;
    JTextField massage;
    //    SimpleBot sbot;
    SimpleAttributeSet botStyle;

    public static void main(String[] args) {
        try {
            new SimpleChatBot();

        } catch (Exception ex) {

        }
    }

    SimpleChatBot() {
        setTitle(TITLE_OG_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, WINDOW_WINDTH, WINDOW_HEIGHT);

        dialogue = new JTextPane();
        dialogue.setEnabled(false);
        dialogue.setContentType("text/html");
        JScrollPane scrollBar = new JScrollPane(dialogue);

        botStyle = new SimpleAttributeSet();
        StyleConstants.setItalic(botStyle, true);
        StyleConstants.setForeground(botStyle, Color.blue);

        JPanel bp = new JPanel();
        bp.setLayout(new BoxLayout(bp, BoxLayout.X_AXIS));
        ai = new JCheckBox(CHB_AI);
        ai.doClick();
        massage = new JTextField();
        massage.addActionListener(this);
        JButton enter = new JButton(BTN_ENTER);
        enter.addActionListener(this);

        add(BorderLayout.CENTER, scrollBar);
        bp.add(ai);
        bp.add(massage);
        bp.add(enter);
        bp.add(BorderLayout.SOUTH, bp);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (massage.getText().trim().length()>0) {
            try {
                StyledDocument doc = dialogue.getStyledDocument();
                doc.insertString(doc.getLength(), massage.getText() + "\n", new SimpleAttributeSet());
            } catch (Exception e) {
            }
        }
        massage.setText("");
        massage.requestFocusInWindow();
    }
}

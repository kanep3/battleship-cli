import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends JFrame implements ActionListener {

    Font myFont = new Font("Lucida Sans Typewriter", Font.BOLD, 40);

    JButton button;

    MyFrame() {


        button = new JButton();
        button.setBounds(0,0,50, 50);
        button.addActionListener(e -> System.out.println("pp"));

        /*
        JButton[] map = new JButton[100];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j] = new JButton();
            }
        }

         */

        JButton[][] buttonsField = new JButton[10][10];
        JLabel layoutForButtons = new JLabel();
        layoutForButtons.setLayout(new GridLayout(10, 10));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; i < 10; j++) {
                JButton Button = new JButton();
                layoutForButtons.add(Button);
            }
        }

        JLabel title = new JLabel();
        title.setForeground(new Color(0xFFFFFF));
        title.setText("Battleship");
        title.setFont(new Font("Lucida Sans Typewriter", Font.BOLD,75));
        title.setVerticalAlignment(JLabel.TOP);

        JPanel shipPanel = new JPanel();
        shipPanel.setBounds(50, 50, 500, 500);
        shipPanel.setBackground(Color.green);
        shipPanel.setLayout(new BorderLayout());
        shipPanel.add(title);

        JPanel coordinatePanel = new JPanel();
        coordinatePanel.setBounds(600, 50, 500, 500);
        coordinatePanel.setBackground(Color.blue);
        coordinatePanel.setLayout(new GridLayout(10, 10, 15, 15));


        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1175, 650);
        frame.setVisible(true);
        frame.add(shipPanel);
        frame.add(coordinatePanel);
        frame.getContentPane().setBackground(new Color(0x1A1A1A));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button) {
            System.out.println("pp");
        }
    }
}

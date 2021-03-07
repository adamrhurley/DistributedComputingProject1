import java.awt.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
        static JFrame frame;

        static JButton buttonLogin;

        static JLabel usernameLabel,passwordLabel;

        static JTextField usernameText,passwordText;

        public static void main(String[] args) {

            frame = new JFrame("Login");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            // create a label to display text
            usernameLabel = new JLabel("Username");
            passwordLabel = new JLabel("Password");
            // create a new buttons
            buttonLogin = new JButton("Login");

            usernameText = new JTextField();
            usernameText.setPreferredSize(new Dimension(200,30) );
            usernameText.setMaximumSize(new Dimension(200,30) );

            passwordText = new JTextField();
            passwordText.setPreferredSize(new Dimension(200,30) );
            passwordText.setMaximumSize(new Dimension(200,30) );

            // create a panel to add buttons
            JPanel panel = new JPanel();



            //add buttons and textfield to panel
            panel.add(usernameLabel);
            panel.add(usernameText);
            panel.add(passwordLabel);
            panel.add(passwordText);
            panel.add(buttonLogin,BorderLayout.SOUTH);

            buttonLogin.addActionListener(e -> {
                if (usernameText.getText().equals("admin") && passwordText.getText().equals("admin")) {
                    new Thread(() ->EchoClient2.main(args)).start();
                    new Thread(() ->EchoServer3.main(args)).start();
                    frame.dispose();

                } else {
                    JOptionPane.showMessageDialog(frame, "Incorrect Username or Password");
                    passwordText.setText(null);
                    usernameText.setText(null);
                }
            });

            // set the size of frame
            frame.setSize(1000, 600);
            frame.add(panel);
            frame.setVisible(true);
        }
    }

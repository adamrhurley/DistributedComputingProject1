import javax.swing.*;
import java.io.IOException;

public class Main extends JFrame {
        static JFrame frame;

        static JButton buttonLogin;



        public static void main(String[] args) {

            frame = new JFrame("Login");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            buttonLogin = new JButton("Login");

            JPanel panel = new JPanel();

            panel.add(buttonLogin);

            buttonLogin.addActionListener(e -> {

                    new Thread(() -> {
                        try {
                            EchoClient2.main(args);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }).start();
                    new Thread(() -> {
                        try {
                            EchoServer3.main(args);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }).start();
                    frame.dispose();
            });

            frame.setSize(400, 400);
            frame.add(panel);
            frame.setVisible(true);
        }
    }

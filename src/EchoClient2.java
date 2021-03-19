import javax.swing.*;
import java.awt.*;
import java.io.IOException;


/**
 * This module contains the presentation logic of an Echo Client.
 * @author M. L. Liu
 */
public class EchoClient2{
    static JButton buttonLogin;
    static final String endMessage = ".";
    static JFrame frame;
    static String message;
    static boolean done = false;
    static JButton buttonCreateMessage;
    static JButton buttonViewMessages;
    static JButton buttonLogout;
    static JLabel usernameLabel,passwordLabel;

    static JTextField usernameText,passwordText;

    public static void main(String[] args) {

        try {
            String hostName = JOptionPane.showInputDialog("Welcome to the Echo client.\n" +
                    "What is the name of the server host?");
            if (hostName.length() == 0) // if user did not enter a name
                hostName = "localhost";  //   use the default host name
            String portNum = JOptionPane.showInputDialog("What is the port number of the server host?");
            if (portNum.length() == 0)
                portNum = "500";          // default port number
            EchoClientHelper2 helper =
                    new EchoClientHelper2(hostName, portNum);

            frame = new JFrame("Login");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



            usernameLabel = new JLabel("Username");
            passwordLabel = new JLabel("Password");

            buttonLogin = new JButton("Login");

            usernameText = new JTextField();
            usernameText.setPreferredSize(new Dimension(200, 30));
            usernameText.setMaximumSize(new Dimension(200, 30));

            passwordText = new JTextField();
            passwordText.setPreferredSize(new Dimension(200, 30));
            passwordText.setMaximumSize(new Dimension(200, 30));

            JPanel panel = new JPanel();


            panel.add(usernameLabel);
            panel.add(usernameText);
            panel.add(passwordLabel);
            panel.add(passwordText);
            panel.add(buttonLogin);

            buttonLogin.addActionListener(e -> {
                String username = usernameText.getText();
                String password = passwordText.getText();
                try {
                    String loginStatus;
                    loginStatus = helper.protocolInterpreter("100",username + "," + password);

                   if(loginStatus.equals("101")){
                       JOptionPane.showMessageDialog(frame, "Login Successful");
                       frame.dispose();
                       mainMenu(helper);
                   }

                   if(loginStatus.equals("102")){
                       JOptionPane.showMessageDialog(frame, "Incorrect Username or Password");
                   }

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            });

            frame.setSize(1000, 600);
            frame.add(panel);
            frame.setVisible(true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mainMenu(EchoClientHelper2 helper){

        JFrame frameMenu = new JFrame("Main Menu");
        frameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonCreateMessage = new JButton("Create Message");
        buttonViewMessages = new JButton("View All Messages");
        buttonLogout = new JButton("Log-out");

        JPanel panel1 = new JPanel();

        panel1.add(buttonCreateMessage);
        buttonCreateMessage.addActionListener(e -> {
            try {
                messageFrame(helper);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });
        panel1.add(buttonViewMessages);
        buttonViewMessages.addActionListener(e -> {
            try {
                String viewStatus = helper.protocolInterpreter("300",null);

            if (viewStatus.equals("302")) {
                JOptionPane.showMessageDialog(frame, "No messages have been added", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            }catch (IOException ioException) {
                ioException.printStackTrace(); }
        });

        panel1.add(buttonLogout);
        buttonLogout.addActionListener(e -> {

                    try {
                        String logoutStatus = helper.protocolInterpreter("400", null);

                        if (logoutStatus.equals("401")) {
                            System.exit(0);
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                });


        frameMenu.setSize(1000, 600);
        frameMenu.add(panel1);
        frameMenu.setVisible(true);

    }

    public static void messageFrame(EchoClientHelper2 helper) throws IOException {
        JFrame frameCreateMessage = new JFrame("Main Menu");
        frameCreateMessage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        while (!done) {
            message = JOptionPane.showInputDialog("Enter a message");


            if ((message.trim()).equals(endMessage)) {
                done = true;
                helper.done();
            } else {
                String createStatus = helper.protocolInterpreter("200", message);
                if (createStatus.equals("201")) {
                    JOptionPane.showMessageDialog(frameCreateMessage, "Message added successfully");
                }

            }
        }
    }
}
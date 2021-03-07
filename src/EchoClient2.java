import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This module contains the presentaton logic of an Echo Client.
 * @author M. L. Liu
 */
public class EchoClient2 extends Main {
    static final String endMessage = ".";
    static JFrame frame;
    static String message, echo;
    static boolean done = false;
    public static EchoClientHelper2 helper;
    static JButton buttonCreateMessage;
    static JButton buttonViewMessages;
    static JButton buttonLogout;
    static List<String> AllMessages = new ArrayList<>();

    public static void main(String[] args) {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);

//System.out.println("Welcome to the Echo client.\n" +
        //   "What is the name of the server host?");
        //String hostName = br.readLine();
        try {
            String hostName = JOptionPane.showInputDialog("Welcome to the Echo client.\n" +
                    "What is the name of the server host?");
            if (hostName.length() == 0) // if user did not enter a name
                hostName = "localhost";  //   use the default host name
            //System.out.println("What is the port number of the server host?");
            String portNum = JOptionPane.showInputDialog("What is the port number of the server host?");
            if (portNum.length() == 0)
                portNum = "500";          // default port number
            EchoClientHelper2 helper =
                    new EchoClientHelper2(hostName, portNum);


            frame = new JFrame("Main Menu");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            buttonCreateMessage = new JButton("Create Message");
            buttonViewMessages = new JButton("View All Messages");
            buttonLogout = new JButton("Log-out");

            JPanel panel = new JPanel();

            panel.add(buttonCreateMessage);
            buttonCreateMessage.addActionListener(e -> {
                if (usernameText.getText().equals("admin") && passwordText.getText().equals("admin")) {
                    // EchoClient2.main(args);
                    //EchoServer3.main(args);
                    new Thread(() -> CreateMessage(helper)).start();


                } else {
                    JOptionPane.showMessageDialog(frame, "Incorrect Username or Password");
                }
            });
            panel.add(buttonViewMessages);
            buttonViewMessages.addActionListener(e -> {
                String output = helper.viewMessages();
                if (output.equals("")) {
                    JOptionPane.showMessageDialog(frame, "No messages have been added", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, output);
                }
            });
            panel.add(buttonLogout);
            buttonLogout.addActionListener(e -> System.exit(0));
            frame.setSize(1000, 600);
            frame.add(panel);
            frame.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        } //end catch
    }


    public static void CreateMessage(EchoClientHelper2 helper1) {

        try {

            while (!done) {
                //System.out.println("Enter a line to receive an echo "
                //      + "from the server, or a single period to quit.");
                //message = br.readLine( );
                message = JOptionPane.showInputDialog("Enter a message");
                if ((message.trim()).equals(endMessage)) {
                    done = true;
                    helper1.done();
                } else {
                    //AllMessages.add(message);
                    //JOptionPane.showMessageDialog(frame, "Message added successfully");

                    System.out.println("HERE");
                    System.out.println(message);
                    System.out.println(EchoClient2.helper);
                    helper1.getEcho(message);
                    //System.out.println(echo);

                }
            } // end while
        } // end try
        catch (Exception ex) {
            ex.printStackTrace();
        } //end catch
        /* return AllMessages; */
    } //end main
} // end class

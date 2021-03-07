import org.jetbrains.annotations.NotNull;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Login extends JFrame {
    public static void login(String username, String password) {

        if(username.equals("admin") && password.equals("admin")){
            System.out.println("Success");

           EchoServer3.main(null);
        }
        else{
            System.out.println("Incorrect");
        }
    }
}

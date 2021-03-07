import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

/**
 * This class is a module which provides the application logic
 * for an Echo client using stream-mode socket.
 * @author M. L. Liu
 */

public class EchoClientHelper2 {

   static final String endMessage = ".";
   private MyStreamSocket mySocket;
   private InetAddress serverHost;
   private int serverPort;
   //String AllMessages = "";

   EchoClientHelper2(String hostName,
                     String portNum) throws IOException {
                                     
  	   this.serverHost = InetAddress.getByName(hostName);
  		this.serverPort = Integer.parseInt(portNum);
      //Instantiates a stream-mode socket and wait for a connection.
   	this.mySocket = new MyStreamSocket(this.serverHost,
         this.serverPort); 
/**/  System.out.println("Connection request made");
   } // end constructor
	
   public void getEcho(String message) throws IOException {
      System.out.println("HERE2");
      //String echo = "";
      mySocket.sendMessage(message);
	   // now receive the echo
      mySocket.saveMessages();
      //echo = mySocket.receiveMessage();
     // return echo;
   } // end getEcho

   public String viewMessages(){
      ArrayList<String> AllMessages = mySocket.viewAllMessages();
      StringBuilder output = new StringBuilder();
      //String output = "";
      for (String allMessage : AllMessages) {
         output.append(allMessage).append("\n");
      }
      return output.toString();
   }

   public void done( ) throws IOException{
      mySocket.sendMessage(endMessage);
      mySocket.close( );
   } // end done 
} //end class

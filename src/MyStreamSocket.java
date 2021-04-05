import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class MyStreamSocket extends java.net.Socket {
   private final java.net.Socket socket;
   private BufferedReader input;
   private PrintWriter output;
   private final ArrayList<String> allMessages = new ArrayList<>();
   JFrame streamFrame = new JFrame();

   MyStreamSocket(InetAddress acceptorHost, int acceptorPort ) throws IOException{
      socket = new java.net.Socket(acceptorHost, acceptorPort );
      setStreams( );

   }

   MyStreamSocket(java.net.Socket socket)  throws IOException {
      this.socket = socket;
      setStreams( );
   }

   private void setStreams( ) throws IOException{
      // get an input stream for reading from the data socket
      InputStream inStream = socket.getInputStream();
      input = 
         new BufferedReader(new InputStreamReader(inStream));
      OutputStream outStream = socket.getOutputStream();
      // create a PrinterWriter object for character-mode output
      output = 
         new PrintWriter(new OutputStreamWriter(outStream));
   }

   public void sendMessage(String message) {
      output.print(message + "\n");   
      //The ensuing flush method call is necessary for the data to
      // be written to the socket data stream before the
      // socket is closed.
      output.flush();               
   } // end sendMessage

   public String receiveMessage( )
		throws IOException {	
      // read a line from the data stream
      return input.readLine( );
   } //end receiveMessage

   public String viewAllMessages(){
      if(allMessages.isEmpty()){
         return "302";
      }
      else{
      StringBuilder output = new StringBuilder();
      for (String allMessage : allMessages) {
         output.append(allMessage).append("\n");
      }

      return "301!"+ output.toString();
   }
   }

   public String logout(){
      JOptionPane.showMessageDialog(streamFrame,"You have successfully logged out");
      return "401";
   }

   public String saveMessages() throws IOException{
      String message = input.readLine( );

      if(message.isEmpty()){
         return "202";
      }
      else{
         allMessages.add(message);
         return "201";
      }

   }

   public void sendText(String message) {

      output.print(message + "\n");
      //The ensuing flush method call is necessary for the data to
      // be written to the socket data stream before the
      // socket is closed.
      output.flush();


   } // end sendMessage

   public String checkLogin() throws IOException {

      String message = input.readLine();
      String[] split= message.split(",");

      if (split[0].equals("name") && split[1].equals("pass")) {
         return "101";
      }
      else {
         return "102";
      }
   }

   public void close( )
		throws IOException {	
      socket.close( );
   }
} //end class

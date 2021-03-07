import java.net.*;
import java.io.*;
import java.util.ArrayList;

/**
 * A wrapper class of Socket which contains 
 * methods for sending and receiving messages
 * @author M. L. Liu
 */
public class MyStreamSocket extends Socket {
   private Socket  socket;
   private BufferedReader input;
   private PrintWriter output;
   private ArrayList<String> allMessages = new ArrayList<>();

   MyStreamSocket(InetAddress acceptorHost,int acceptorPort ) throws IOException{
      socket = new Socket(acceptorHost, acceptorPort );
      setStreams( );

   }

   MyStreamSocket(Socket socket)  throws IOException {
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
      System.out.println("HERE SEND");
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

   public ArrayList<String> viewAllMessages(){
      return allMessages;
   }
   public void saveMessages() throws IOException{
      System.out.println("HERE SAVE");
      String message = input.readLine( );
      allMessages.add(message);
   }

   public void close( )
		throws IOException {	
      socket.close( );
   }
} //end class

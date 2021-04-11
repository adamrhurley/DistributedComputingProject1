import java.net.*;
import java.io.*;

public class EchoClientHelper2 {

   static final String endMessage = ".";
   private final MyStreamSocket mySocket;

   String returnCode;

EchoClientHelper2(String hostName,
                    String portNum) throws IOException {
      InetAddress serverHost = InetAddress.getByName(hostName);
      int serverPort = Integer.parseInt(portNum);
      //Instantiates a stream-mode socket and wait for a connection.
   	this.mySocket = new MyStreamSocket(serverHost,
            serverPort);
/**/  System.out.println("Connection request made");
   }




   public String protocolInterpreter(String code, String message) throws IOException {

      if(code.equals("100")){
         mySocket.sendMessage(message);


         returnCode = mySocket.checkLogin();
      }

      if(code.equals("200")){
         mySocket.sendMessage((message));

         returnCode = mySocket.saveMessages();
      }

      if(code.equals("300")){
         String status;
         status = mySocket.viewAllMessages();
         returnCode = status;
          }

      if(code.equals("400")){
         String status;
         status = mySocket.logout();

         returnCode = status;
      }


      return returnCode;
   }

   public void done( ) throws IOException{
      mySocket.sendMessage(endMessage);
      mySocket.close( );
   } // end done 
} //end class

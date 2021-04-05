import java.io.IOException;
import java.net.*;

/**
 * This module contains the application logic of an echo server
 * which uses a stream-mode socket for interprocess communication.
 * Unlike EchoServer2, this server services clients concurrently.
 * A command-line argument is required to specify the server port.
 * @author M. L. Liu
 */

public class EchoServer3 {

   public static void main(String[] args) throws IOException {
     // System.setProperty("javax.net.ssl.trustStore","DC.store");  !--SSL CODE--!
     // System.setProperty("javax.net.ssl.keyStorePassword","adamhurley"); !--SSL CODE--!
      //ServerSocket serverSocket = ((SSLServerSocketFactory)SSLServerSocketFactory.getDefault()).createServerSocket(500);!--SSL CODE--!
      int serverPort = 500;    // default port

     if (args.length == 1 )
         serverPort = Integer.parseInt(args[0]);
      try {
         // instantiates a stream socket for accepting
         //   connections
   	   ServerSocket myConnectionSocket =
            new ServerSocket(serverPort);
/**/     System.out.println("Server ready.");
         while (true) {  // forever loop
            // wait to accept a connection
/**/        System.out.println("Waiting for a connection.");
            MyStreamSocket myDataSocket = new MyStreamSocket
                (myConnectionSocket.accept( ));
/**/        System.out.println("connection accepted");
            // Start a thread to handle this client's session
            Thread theThread =
               new Thread(new EchoServerThread(myDataSocket));
            theThread.start();
           // new EchoServerThread(serverSocket.accept()).start();  !--SSL CODE--!
            // and go on to the next client
            } //end while forever
       } // end try
	    catch (Exception ex) {
          ex.printStackTrace( );
	    } // end catch
   } }
   //end main
 // end class

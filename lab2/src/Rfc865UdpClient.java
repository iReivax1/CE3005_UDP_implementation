/**
 * @(#)RFC865Client.java
 *
 *
 * @author
 * @version 1.00 2019/3/15
 */
package udp;
import java.io.*;
import java.net.*;
import java.util.*;


public class Rfc865UdpClient {


  	public static void main(String[] argv) {
	//
	// 1. Open UDP socket
	//
		int port = 50001;
		int bufferSize = 512; // quote is no greater than 512 characters in RFC 865
		byte[] buffer;
		byte[] receivingBuffer = new byte[bufferSize];
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket(port);
			//Connects the socket to a remote address for this socket.
			//InetAddress serverAddress = InetAddress.getByName(""); //change the name to hw1-b00 later for final testing
			InetAddress serverAddress = InetAddress.getByName("hw1-b00");
	        socket.connect(serverAddress, port);
		}
		catch (SocketException e) {
			e.printStackTrace();
		}
		catch (UnknownHostException e) {
            e.printStackTrace();
        }

		try {
		//
		// 2. Send UDP request to server
		//
			String sendMessage = "Xavier Tan,TE1,172.21.144.33";
			buffer = sendMessage.getBytes();
			DatagramPacket request = new DatagramPacket(buffer, buffer.length);
			socket.send(request);
			System.out.println("Sending");
			//
			// 3. Receive UDP reply from server
			//
			DatagramPacket reply = new DatagramPacket(receivingBuffer, receivingBuffer.length);
		    socket.receive(reply);
		    String s = new String(receivingBuffer); //create string of size receivingBuffer
		    System.out.println("Received message from server: " + s);
		    socket.close();
		}
		catch (IOException e) {
			e.printStackTrace(); //         Closes this datagram socket.
		}
	}

}

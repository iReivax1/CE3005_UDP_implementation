/**
 * @(#)RFC865Server.java
 *
 *
 * @author
 * @version 1.00 2019/3/15
 */

//message is Science without religion is lame, religion without science is blind- albert einstein
package udp;
import java.io.*;
import java.net.*;
import java.util.*;

public class Rfc865UdpServer {
	public static void main(String[] argv) {
	//
	// 1. Open UDP socket at well-known port
	// port is 17, max bytes it can send is 512
		DatagramSocket socket = null;
		int port = 17;
		int bufferSize = 512; // quote is no greater than 512 characters in RFC 865
		byte[] buffer;

		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		while (true) {
			try {

				//
				// 2. Listen for UDP request from client
				//
				buffer = new byte[512];// in ascii 1 char = 1byte therefore size should be 512 bytes

				//second argument is the length of the receiving packet,
				//buffer incoming = size of incoming packet.
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				socket.receive(request);

				//convert from bytestream to String
				String data = new String(buffer,0,request.getLength()); // create string of the size buffer, 0 offset, length of the request;
				System.out.println("Client says: " + data);

				//get client address
				InetAddress clientAddress = request.getAddress();
				int clientPort = request.getPort();
				System.out.println("Client Address: " + clientAddress + ": " + clientPort); //client port == port => 17

				//
				// 3. Send UDP reply to client
				//
				buffer = "Reply from server".getBytes();
				// Constructs a datagram packet for sending packets of length buffer to the specified port number on the specified host.
				DatagramPacket reply = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort) ;
				socket.send(reply);
			}
			catch (IOException e) {
				System.out.println(e.getMessage());
				socket.close();
			}
		}
	}
}

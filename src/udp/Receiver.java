package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Receiver {

	public Receiver() throws Exception {
		DatagramSocket socket = new DatagramSocket(8080);
		System.out.println("::8080 - Receiver running");
		Scanner keyboard = new Scanner(System.in);

		byte[] buffer = new byte[1500]; // MTU
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

		socket.receive(packet);
		String message = new String(buffer).trim();
		System.out.println("/127.0.0.1::" + message);

		int senders_port = packet.getPort();
		InetAddress senders_address = packet.getAddress();

		System.out.print("::8080 -: ");
		message = keyboard.nextLine();
		buffer = message.getBytes();
		packet = new DatagramPacket(buffer, buffer.length, senders_address, senders_port);

		socket.send(packet);

		System.out.println("::8080 - Log:Sent:" + message);
	}

	public static void main(String[] args) {
		try {
			new Receiver();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

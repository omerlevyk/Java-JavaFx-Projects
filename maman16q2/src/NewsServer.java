import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;

public class NewsServer {
    private static final int PORT = 7777;
    private static ArrayList<InetAddress> clients = new ArrayList<InetAddress>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the server computer:");
        String serverName = scanner.nextLine();
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(PORT);
            socket.setSoTimeout(1000);
            while (true) {
                byte[] buffer = new byte[256];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                if (message.equals("REGISTER")) {
                    clients.add(packet.getAddress());
                    System.out.println("Client registered: " + packet.getAddress().getHostAddress());
                } else if (message.equals("UNREGISTER")) {
                    clients.remove(packet.getAddress());
                    System.out.println("Client unregistered: " + packet.getAddress().getHostAddress());
                } else {
                    System.out.println("Received news flash: " + message);
                    for (InetAddress client : clients) {
                        DatagramPacket sendPacket = new DatagramPacket(message.getBytes(), message.getBytes().length, client, PORT);
                        socket.send(sendPacket);
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}

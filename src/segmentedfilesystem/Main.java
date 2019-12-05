package segmentedfilesystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.net.*;

public class Main {

    public static void main(String[] args) throws IOException {
        int port = 6014;
        InetAddress address = InetAddress.getByName("csci-4409.morris.umn.edu");
        byte[] buf = new byte[0];
        DatagramSocket socket = new DatagramSocket();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
        socket.send(packet);

        while (true) {
            buf = new byte[1028];
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
        }
    }

}

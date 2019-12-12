package segmentedfilesystem;

import java.net.DatagramPacket;
import java.util.Arrays;

public class HeaderPacket extends Packet {
    private byte[] filename;

    public HeaderPacket(DatagramPacket packet) {
        setStatus(packet.getData()[0]);
        setFileID(packet.getData()[1]);

        int filenameLength = packet.getLength();
        filename = Arrays.copyOfRange(packet.getData(), 2, filenameLength);
    }

    public byte[] getFilename() {
        return filename;
    }
}

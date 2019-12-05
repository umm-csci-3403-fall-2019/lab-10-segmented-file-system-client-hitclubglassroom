package segmentedfilesystem;

import java.math.BigInteger;
import java.net.DatagramPacket;
import java.util.Arrays;

public class DataPacket extends Packet {
    private int packetNumber;
    private byte[] data;
    private boolean isLast;

    public DataPacket(DatagramPacket packet) {
        setStatus(packet.getData()[0]);
        setFileID(packet.getData()[1]);

        byte[] packetNumberBytes = Arrays.copyOfRange(packet.getData(),2,4);
        BigInteger i = new BigInteger(packetNumberBytes);
        packetNumber = i.intValue();

        this.isLast = getStatus()%4 == 3;

        int dataLength = packet.getLength() - 4;
        data = Arrays.copyOfRange(packet.getData(), 4, dataLength);
    }

    public boolean isLast() {
        return isLast;
    }

    public int getPacketNumber() {
        return packetNumber;
    }

    public byte[] getData() {
        return data;
    }
}

package segmentedfilesystem;

import org.junit.Test;

import java.net.DatagramPacket;

public class DataPacketTest {

    @Test
    public void TestIsLast() {
        DatagramPacket datagramPacket = new DatagramPacket(new byte[1028], 1028);
        byte b = Byte.parseByte("127");
        byte[] data = {3, 1, 0, 1, b};
        datagramPacket.setData(data);
        DataPacket dataPacket = new DataPacket(datagramPacket);
        assert(dataPacket.isLast());
    }

}

package segmentedfilesystem;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;

import static org.junit.Assert.assertEquals;

public class PacketTest {

    @Test
    public void TestGetFileID() throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(new byte[1028], 1028);
        byte b = Byte.parseByte("127");
        byte[] data = {3, 1, 0, 1, b};
        datagramPacket.setData(data);
        DataPacket dataPacket = new DataPacket(datagramPacket);
        assertEquals(1, dataPacket.getFileID());

        DatagramPacket datagramPacketc = new DatagramPacket(new byte[1028], 1028);
        byte[] filenameBytes = "myfile.txt".getBytes();
        byte[] datac = {0, 5};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(datac);
        baos.write(filenameBytes);
        datac = baos.toByteArray();
        datagramPacketc.setData(datac);
        HeaderPacket headerPacket = new HeaderPacket(datagramPacketc);
        assertEquals(5, headerPacket.getFileID());

    }
}

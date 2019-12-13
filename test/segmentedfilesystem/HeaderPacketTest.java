package segmentedfilesystem;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;

import static org.junit.Assert.assertArrayEquals;

public class HeaderPacketTest {

    @Test
    public void TestGetFilename() throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(new byte[1028], 1028);
        byte[] filenameBytes = "myfile.txt".getBytes();
        byte[] data = {0, 1};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(data);
        baos.write(filenameBytes);
        data = baos.toByteArray();
        datagramPacket.setData(data);
        HeaderPacket headerPacket = new HeaderPacket(datagramPacket);
        assertArrayEquals(filenameBytes, headerPacket.getFilename());
    }

}

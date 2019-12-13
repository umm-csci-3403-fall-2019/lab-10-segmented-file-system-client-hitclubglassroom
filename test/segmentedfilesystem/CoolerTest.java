package segmentedfilesystem;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;

import static org.junit.Assert.*;

public class CoolerTest {

    @Test
    public void TestAddPacket() {
        Cooler yeti = new Cooler(5);
        assertEquals(0, yeti.lunchboxCount());

        DatagramPacket datagramPacket = new DatagramPacket(new byte[1028], 1028);
        byte b = Byte.parseByte("127");
        byte[] data = {3, 1, 0, 1, b};
        datagramPacket.setData(data);
        DataPacket dataPacket = new DataPacket(datagramPacket);

        yeti.addPacket(dataPacket);
        assertEquals(1, yeti.lunchboxCount());

        DatagramPacket datagramPacketa = new DatagramPacket(new byte[1028], 1028);
        byte a = Byte.parseByte("64");
        byte[] dataa = {1, 5, 0, 0, a};
        datagramPacketa.setData(dataa);
        DataPacket dataPacketa = new DataPacket(datagramPacketa);

        yeti.addPacket(dataPacketa);
        assertEquals(2, yeti.lunchboxCount());
    }

    @Test
    public void TestReadyToSort() throws IOException {
        Cooler yeti = new Cooler(1);

        DatagramPacket datagramPacket = new DatagramPacket(new byte[1028], 1028);
        byte b = Byte.parseByte("127");
        byte[] data = {3, 1, 0, 1, b};
        datagramPacket.setData(data);
        DataPacket dataPacket = new DataPacket(datagramPacket);

        DatagramPacket datagramPacketa = new DatagramPacket(new byte[1028], 1028);
        byte a = Byte.parseByte("64");
        byte[] dataa = {1, 1, 0, 0, a};
        datagramPacketa.setData(dataa);
        DataPacket dataPacketa = new DataPacket(datagramPacketa);

        DatagramPacket datagramPacketc = new DatagramPacket(new byte[1028], 1028);
        byte[] filenameBytes = "myfile.txt".getBytes();
        byte[] datac = {0, 1};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(datac);
        baos.write(filenameBytes);
        datac = baos.toByteArray();
        datagramPacketc.setData(datac);
        HeaderPacket headerPacket = new HeaderPacket(datagramPacketc);

        yeti.addPacket(dataPacket);
        assertFalse(yeti.readyToSort());

        yeti.addPacket(dataPacketa);
        assertFalse(yeti.readyToSort());

        yeti.addPacket(headerPacket);
        assertTrue(yeti.readyToSort());

    }
}

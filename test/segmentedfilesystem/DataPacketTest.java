package segmentedfilesystem;

import org.junit.Test;

import java.net.DatagramPacket;

import static org.junit.Assert.*;

public class DataPacketTest {

    @Test
    public void TestIsLast() {
        DatagramPacket datagramPacket = new DatagramPacket(new byte[1028], 1028);
        byte b = Byte.parseByte("127");
        byte[] data = {3, 1, 0, 1, b};
        datagramPacket.setData(data);
        DataPacket dataPacket = new DataPacket(datagramPacket);
        assertTrue(dataPacket.isLast());
    }

    @Test
    public void TestIsNotLast() {
        DatagramPacket datagramPacket = new DatagramPacket(new byte[1028], 1028);
        byte b = Byte.parseByte("127");
        byte[] data = {1, 1, 0, 1, b};
        datagramPacket.setData(data);
        DataPacket dataPacket = new DataPacket(datagramPacket);
        assertFalse(dataPacket.isLast());
    }

    @Test
    public void TestGetPacketNumber() {
        DatagramPacket datagramPacket = new DatagramPacket(new byte[1028], 1028);
        byte b = Byte.parseByte("127");
        byte[] data = {3, 1, 0, 1, b};
        datagramPacket.setData(data);
        DataPacket dataPacket = new DataPacket(datagramPacket);
        assertEquals(1, dataPacket.getPacketNumber());
    }

    @Test
    public void TestGetData() {
        DatagramPacket datagramPacket = new DatagramPacket(new byte[1028], 1028);
        byte b = Byte.parseByte("127");
        byte[] data = {3, 1, 0, 1, b};
        datagramPacket.setData(data);
        DataPacket dataPacket = new DataPacket(datagramPacket);
        byte[] expectedData = {b};
        assertArrayEquals(expectedData, dataPacket.getData());
    }

    @Test
    public void TestCompareTo() {
        DatagramPacket datagramPacket = new DatagramPacket(new byte[1028], 1028);
        byte b = Byte.parseByte("127");
        byte[] data = {3, 1, 0, 1, b};
        datagramPacket.setData(data);
        DataPacket dataPacket = new DataPacket(datagramPacket);

        DatagramPacket datagramPacketc = new DatagramPacket(new byte[1028], 1028);
        byte c = Byte.parseByte("64");
        byte[] datac = {1, 1, 0, 11, c};
        datagramPacketc.setData(datac);
        DataPacket dataPacketc = new DataPacket(datagramPacketc);

        assertTrue(dataPacket.compareTo(dataPacketc) < 0);
        assertTrue(dataPacketc.compareTo(dataPacket) > 0);
        assertTrue(dataPacket.compareTo(dataPacket) == 0);
    }
}

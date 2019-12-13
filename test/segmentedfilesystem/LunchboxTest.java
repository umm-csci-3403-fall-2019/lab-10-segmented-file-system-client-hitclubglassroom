package segmentedfilesystem;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class LunchboxTest {

    @Test
    public void TestGetFileID() {
        Lunchbox lunchbox = new Lunchbox(420);
        assertEquals(420, lunchbox.getFileID());
    }

    @Test
    public void TestAddLunch() throws IOException {
        Lunchbox lunchbox = new Lunchbox(420);

        assertFalse(lunchbox.isPacked());

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
        byte[] datac = {0, 5};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(datac);
        baos.write(filenameBytes);
        datac = baos.toByteArray();
        datagramPacketc.setData(datac);
        HeaderPacket headerPacket = new HeaderPacket(datagramPacketc);

        lunchbox.addLunch(headerPacket);

        assertEquals("myfile.txt", lunchbox.getFilename());
        assertFalse(lunchbox.isPacked());

        lunchbox.addLunch(dataPacketa);

        assertFalse(lunchbox.isPacked());

        lunchbox.addLunch(dataPacket);

        assertTrue(lunchbox.isPacked());

        ArrayList<DataPacket> expectedList = new ArrayList<>();
        expectedList.add(dataPacketa);
        expectedList.add(dataPacket);

        assertEquals(expectedList, lunchbox.getData());
    }
}

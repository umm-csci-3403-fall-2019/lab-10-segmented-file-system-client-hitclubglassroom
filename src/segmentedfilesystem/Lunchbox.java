package segmentedfilesystem;

import java.util.ArrayList;

public class Lunchbox {
    private HeaderPacket header;
    private ArrayList<DataPacket> data;
    private boolean hasLast;
    private int fileSize;
    private int fileID;

    public Lunchbox(int fileID) {
        this.fileID = fileID;
    }

    public void addLunch(Packet packet) {
        if (packet.getStatus()%2 == 0) {
            addLunchHeader((HeaderPacket) packet);
        } else {
            addLunchData((DataPacket) packet);
        }
    }

    public void addLunchHeader(HeaderPacket headerPacket) {
        this.header = (HeaderPacket) headerPacket;
    }

    public void addLunchData(DataPacket dataPacket) {
        if (dataPacket.isLast()) {
            this.hasLast = true;
            this.fileSize = dataPacket.getPacketNumber();
        }
        data.add(dataPacket);
    }

    public int getFileID() {
        return fileID;
    }
}

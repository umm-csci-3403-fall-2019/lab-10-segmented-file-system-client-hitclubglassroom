package segmentedfilesystem;

import java.util.ArrayList;

public class Lunchbox {
    private HeaderPacket header;
    private ArrayList<DataPacket> data = new ArrayList<>();
    private boolean hasLast;
    private boolean hasHeader = false;
    private int fileSize;
    private int fileID;

    public Lunchbox(int fileID) {
        this.fileID = fileID;
    }

    public void addLunch(Packet packet) {
        if (packet.getStatus()%2 == 0) {
            addLunchHeader((HeaderPacket) packet);
            hasHeader = true;
        } else {
            addLunchData((DataPacket) packet);
        }
    }

    public void addLunchHeader(HeaderPacket headerPacket) {
        this.header = headerPacket;
    }

    public void addLunchData(DataPacket dataPacket) {
        if (dataPacket.isLast()) {
            this.hasLast = true;
            this.fileSize = dataPacket.getPacketNumber() + 1;
        }
        data.add(dataPacket);
    }

    public int getFileID() {
        return fileID;
    }

    public ArrayList<DataPacket> getData() {
        return data;
    }

    public boolean isPacked() {
        if (hasLast && hasHeader) {
            return fileSize == data.size();
        } else {
            return false;
        }
    }

    public String getFilename() {
        return new String(header.getFilename());
    }
}

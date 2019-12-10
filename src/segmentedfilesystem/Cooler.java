package segmentedfilesystem;

import java.util.ArrayList;

public class Cooler {
    private ArrayList<Lunchbox> lunchboxes;

    private void addPacket(Packet packet) {
        int fileID = packet.getFileID();
        if (lunchboxes.size() == 0) {
            lunchboxes.add(new Lunchbox(fileID));
        }
        for (int i = 0; i<lunchboxes.size(); i++) {
            Lunchbox box = lunchboxes.get(i);
            if (fileID == box.getFileID()) {
                box.addLunch(packet);
            }
        }
    }
}

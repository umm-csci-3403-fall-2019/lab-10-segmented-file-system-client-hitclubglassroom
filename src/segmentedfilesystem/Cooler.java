package segmentedfilesystem;

import java.util.ArrayList;
import java.util.Collections;

public class Cooler {
    private ArrayList<Lunchbox> lunchboxes;

    public void addPacket(Packet packet) {
        int fileID = packet.getFileID();
        if (lunchboxes.size() == 0) {
            Lunchbox firstBox = new Lunchbox(fileID);
            firstBox.addLunch(packet);
            lunchboxes.add(firstBox);
        } else {
            boolean addNewBox = false;
            for (int i = 0; i < lunchboxes.size(); i++) {
                Lunchbox box = lunchboxes.get(i);
                if (fileID == box.getFileID()) {
                    box.addLunch(packet);
                    addNewBox = false;
                    break;
                } else {
                    addNewBox = true;
                }
            }
            if (addNewBox) {
                Lunchbox newBox = new Lunchbox(fileID);
                newBox.addLunch(packet);
                lunchboxes.add(newBox);
            }
            if (readyToSort()) {
                sortLunches();
            }
        }
    }

    public boolean readyToSort() {
        boolean readyToSort = false;
        if (lunchboxes.size() == 3) {
            readyToSort = true;
            for (int i = 0; i < 3; i++) {
                readyToSort = readyToSort && lunchboxes.get(i).isPacked();
            }
        }
        return readyToSort;
    }

    private void sortLunches() {
        for (int i = 0; i < 3; i++) {
            Lunchbox boxToSort = lunchboxes.get(i);
            ArrayList<DataPacket> dataToSort = boxToSort.getData();
            Collections.sort(dataToSort);
        }
    }
}

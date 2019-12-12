package segmentedfilesystem;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Cooler {
    private ArrayList<Lunchbox> lunchboxes = new ArrayList<>();

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

    public void sortAndWrite() throws IOException {
        sortLunches();
        writeLunches();
    }

    private void sortLunches() {
        for (int i = 0; i < 3; i++) {
            Lunchbox boxToSort = lunchboxes.get(i);
            ArrayList<DataPacket> dataToSort = boxToSort.getData();
            Collections.sort(dataToSort);
//            boxToSort.sortDataPackets();
        }
    }

    private void writeLunches() throws IOException {
        for (int i = 0; i < 3; i++) {
            Lunchbox box = lunchboxes.get(i);
            String filename = box.getFilename();
            File file = new File(filename);
            FileOutputStream fos = new FileOutputStream(file);
            ArrayList<DataPacket> dataPackets = box.getData();
            BufferedOutputStream out = new BufferedOutputStream(fos);
            for(int j = 0; j < dataPackets.size(); j++) {
                DataPacket packet = dataPackets.get(j);
                byte[] data = packet.getData();
                out.write(data);
            }
            out.flush();
            out.close();
        }
    }
}

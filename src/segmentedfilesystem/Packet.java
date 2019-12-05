package segmentedfilesystem;

public abstract class Packet {
    private int status;
    private int fileID;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getfileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }
}

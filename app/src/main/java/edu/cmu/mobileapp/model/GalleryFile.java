package edu.cmu.mobileapp.model;

/**
 * Created by srikrishnan_suresh on 04-07-2015.
 */
public class GalleryFile {
    private String filePath;
    private String dateTaken;
    private long type;

    public GalleryFile(String filePath, String dateTaken, long type) {
        this.filePath = filePath;
        this.dateTaken = dateTaken;
        this.type = type;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }
}

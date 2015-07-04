package edu.cmu.mobileapp.model;

/**
 * Created by srikrishnan_suresh on 04-07-2015.
 */
public class MediaFile {

    public MediaFile(String filePath, String dateCreated) {
        this.filePath = filePath;
        this.dateCreated = dateCreated;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }



    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    private String filePath;
    private String dateCreated;
}

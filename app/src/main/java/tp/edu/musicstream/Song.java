package tp.edu.musicstream;

import android.graphics.drawable.Drawable;

public class Song {
    private String id;
    private String title;
    private String artiste;
    private String fileLink;
    private double songLength;
    private String imgCoverArt;

    public Song(String id, String title, String artiste, String fileLink, double songLength, String imgCoverArt) {
        this.id = id;
        this.title = title;
        this.artiste = artiste;
        this.fileLink = fileLink;
        this.songLength = songLength;
        this.imgCoverArt = imgCoverArt;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id;}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getArtiste() { return artiste; }
    public void setArtiste(String artiste) { this.artiste = artiste; }

    public String getFileLink() { return fileLink; }
    public void setFileLink(String fileLink) { this.fileLink = fileLink; }

    public double getSongLength() { return songLength; }
    public void setSongLength(double songLength) { this.songLength = songLength; }

    public String getImgCoverArt() { return imgCoverArt; }
    public void setImgCoverArt(String imgCoverArt) { this.imgCoverArt = imgCoverArt; }


}

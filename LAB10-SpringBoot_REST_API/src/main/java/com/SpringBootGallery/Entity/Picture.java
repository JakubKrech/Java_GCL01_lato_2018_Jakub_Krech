package com.SpringBootGallery.Entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Instant;

public class Picture {

    private int index;
    private String name;
    private String resolution;
    private long sizeInBytes;
    private long created;
    private File image;


    //@Value("${fileFolder.path}") // reading path to folder containing files from app properties
    //private String fileFolderPath;

    public Picture(int index, String name) throws IOException {
        this.index = index;
        this.name = name;
        this.created = Instant.now().getEpochSecond();
        this.image = new File("src\\main\\resources\\image\\" + this.name + ".jpg");
        this.sizeInBytes = image.length();

        BufferedImage bimg = ImageIO.read(this.image);
        int width = bimg.getWidth();
        int height = bimg.getHeight();
        this.resolution = width + "x" + height;
    }

    /*@PostConstruct
    public void init() throws IOException {
        System.out.println(fileFolderPath);
        this.image = new File(fileFolderPath + this.name + ".jpg");
        this.sizeInBytes = image.length();

        BufferedImage bimg = ImageIO.read(this.image);
        int width = bimg.getWidth();
        int height = bimg.getHeight();
        this.resolution = width + "x" + height;
    }*/

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResolution() {
        return resolution;
    }

    public long getCreated() {
        return created;
    }

    public long getSizeInBytes() {
        return sizeInBytes;
    }
}

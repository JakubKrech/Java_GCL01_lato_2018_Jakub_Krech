package com.SpringBootGallery.Dao;

import com.SpringBootGallery.Entity.Picture;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;



@Repository
public class PictureDao {

    public static Map<Integer, Picture> pictures;


    @Value("${fileFolder.path}") // reading path to folder containing files from app properties
    private String fileFolderPath;

    static {
        pictures = new HashMap<Integer, Picture>(){
            {
                try {  // reading width and height required exception handling
                    put(1, new Picture(1, "Cracow"));
                    put(2, new Picture(2, "Warsaw"));
                    put(3, new Picture(3, "Gdansk"));
                    put(4, new Picture(4, "Katowice"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public Collection<Picture> getAllPictures(){
        return this.pictures.values();
    }

    public ResponseEntity<byte[]> getPictureByIndex(int index) throws IOException {

        String name = pictures.get(index).getName();
        File imgPath = new File(fileFolderPath + name + ".jpg");

        byte[] image = Files.readAllBytes(imgPath.toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(image.length);
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }

    public void removePictureByIndex(int index) {
        this.pictures.remove(index);
    }

    public int nextIndex = pictures.size() + 1;

    public void uploadPicture(String fileNameWithOutExt) throws IOException {
        pictures.put(nextIndex, new Picture(nextIndex++, fileNameWithOutExt));
    }
}

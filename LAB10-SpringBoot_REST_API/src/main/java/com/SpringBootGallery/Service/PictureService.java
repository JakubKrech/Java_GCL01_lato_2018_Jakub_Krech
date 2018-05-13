package com.SpringBootGallery.Service;

import com.SpringBootGallery.Dao.PictureDao;
import com.SpringBootGallery.Entity.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;

@Service
public class PictureService {

    @Autowired
    public PictureDao pictureDao;

    public Collection<Picture> getAllPictures(){
        return this.pictureDao.getAllPictures();
    }

    public ResponseEntity<byte[]> getPictureByIndex(int index) throws IOException {
        return this.pictureDao.getPictureByIndex(index);
    }

    public void removePictureByIndex(int index) {
        this.pictureDao.removePictureByIndex(index);
    }

    public void uploadPicture(String fileNameWithOutExt) throws IOException {
        this.pictureDao.uploadPicture(fileNameWithOutExt);

    }
}

package com.SpringBootGallery.Controller;

import com.SpringBootGallery.Entity.Picture;
import com.SpringBootGallery.Service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/gallery/pictures")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Picture> getAllPictures(){
        return pictureService.getAllPictures();
    }

    @RequestMapping(value = "/{index}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> getPictureByIndex(@PathVariable("index") int index) throws IOException {

        return pictureService.getPictureByIndex(index);
    }

    @RequestMapping(value = "/{index}", method = RequestMethod.DELETE)
    public Map<String, String> deletePictureByIndex(@PathVariable("index") int index){

        HashMap<String, String> result = new HashMap<>();

        if(pictureService.pictureDao.pictures.containsKey(index)) {
            pictureService.removeStudentByIndex(index);
            result.put("result:", "true");
            return result;
        }
        else{
            result.put("result:", "false");
            return result;
        }
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, String> uploadPicture(@RequestParam("file")MultipartFile file) throws IOException{
        HashMap<String, String> result = new HashMap<>();


        String filename = file.getOriginalFilename();
        String fileNameWithOutExt = filename.replaceFirst("[.][^.]+$", "");
        File convertFile = new File("src\\main\\resources\\image\\" + file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();

        int sizeBefore = pictureService.pictureDao.pictures.size();
        this.pictureService.uploadPicture(fileNameWithOutExt);

        if(pictureService.pictureDao.pictures.containsKey(sizeBefore + 1)) {
            result.put("result:", "true");
            return result;
        }
        else{
            result.put("result:", "false");
            return result;
        }

    }

}

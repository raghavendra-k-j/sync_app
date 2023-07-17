package com.example.syncapp.rest;

import com.example.syncapp.entity.MyFile;
import com.example.syncapp.exceptions.DefaultException;
import com.example.syncapp.rest.defaults.Response;
import com.example.syncapp.services.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequestMapping("sync")
public class FilesController {

    @Autowired
    FileService fileService;

    /*
    * Method to upload the file using request body containing json like
    * {
    *     "fileName" : "some_unique_file_name.[your_file_extension]",
    *     "file" : "base64 encoded file content"
    * }
    * */
    @PostMapping("file")
    public ResponseEntity<Response> upload(@RequestBody MyFile myPicture) {
        Response response = new Response();
        try {
            String fileContent = myPicture.getFile();
            fileContent = fileContent.replaceAll("[^A-Za-z0-9+/=]", "");
            byte[] fileBytes = Base64.getDecoder().decode(fileContent);
            boolean isUploadSuccessfully = fileService.uploadFile(fileBytes, myPicture.getFileName());
            response.addProperty("success", isUploadSuccessfully);
            return ResponseEntity.ok().body(response);
        } catch (DefaultException defaultException) {
            return ResponseEntity.badRequest().body(response.setError(defaultException));
        }
    }

//    @PostMapping("file")
//    public ResponseEntity<Response> upload(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName) {
//        Response response = new Response();
//        try {
//            boolean isUploadSuccessfully = fileService.uploadFile(file.getBytes(), fileName);
//            response.addProperty("success", isUploadSuccessfully);
//            return ResponseEntity.ok().body(response);
//        } catch (DefaultException defaultException) {
//            return ResponseEntity.badRequest().body(response.setError(defaultException));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(response.setError(e.getMessage()));
//        }
//    }
//
//    @PostMapping("file")
//    public ResponseEntity<Response> upload(@RequestParam("file") MultipartFile file) {
//        Response response = new Response();
//        try {
//            boolean isUploadSuccessfully = fileService.uploadFile(file.getBytes(), file.getName());
//            response.addProperty("success", isUploadSuccessfully);
//            return ResponseEntity.ok().body(response);
//        } catch (DefaultException defaultException) {
//            return ResponseEntity.badRequest().body(response.setError(defaultException));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(response.setError(e.getMessage()));
//        }
//    }

}


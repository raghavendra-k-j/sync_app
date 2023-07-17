package com.example.syncapp.services.file;

import com.example.syncapp.exceptions.DefaultException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {


    @Value("${file.upload.directory}")
    public static final String UPLOAD_DIRECTORY = "D:/uploads/";

    @Override
    public boolean uploadFile(byte[] bytes, String fileName) throws DefaultException {
        createUploadsDirectoryIfNotExists();

        String filePath = UPLOAD_DIRECTORY + fileName;

        if (Files.exists(Paths.get(filePath))) {
            throw new DefaultException("File already exists.", false, true);
        }

        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(filePath);
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        }
        catch (IOException e) {
            throw new DefaultException(e.getMessage(), true, false);
        }
        return true;
    }


    private void createUploadsDirectoryIfNotExists() {
        File directory = new File(UPLOAD_DIRECTORY);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new DefaultException("Failed to create uploads directory.", true, true);
            }
        }
    }
}

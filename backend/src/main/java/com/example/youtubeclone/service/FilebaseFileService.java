package com.example.youtubeclone.service;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class FilebaseFileService implements FileService{
    private static final String DOWNLOAD_URL =
            "https://firebasestorage.googleapis.com/v0/b/clone-d5a12.appspot.com/o/%s?alt=media";
    private String TEMP_URL;

    @Override
    public String uploadToServer(MultipartFile multipartFile) {
        try {
            //generate uniqe file name to store the file with that name in firebase
            //by geting the extention of the file and conncatenate random value with it
            String extentionFileName = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
            String fileName = UUID.randomUUID().toString() +"."+extentionFileName;
            //convert multipartfile to file
            File file = this.convertToFile(multipartFile, fileName);
            //upload the file to firebase
            TEMP_URL = this.uploadFile(file, fileName);
            //delete the created file
            file.delete();
            return  TEMP_URL;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"can not upload file");
        }
    }

    private String uploadFile(File file, String fileName) throws IOException {
        //BolbId object contains the bucket name and the blob name
        BlobId blobId = BlobId.of("clone-d5a12.appspot.com", fileName);
        // ctreat blobInfo obj that contain metadata of the blob
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        // authenticate firebase useing config json file downloaded from firebase
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("./firebase-config.json"));
        //save the file to storage
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        //create the dowaloaded url for the file
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        //creat file with name (fileName)
        File tempFile = new File(fileName);
        // copy the content of the multipartfile by getBytes method and write it to fileoutputstream
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }
}

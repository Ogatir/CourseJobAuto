import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileManager {
    private Drive service;

    public FileManager(Drive service){
        this.service = service;
    }

    public File UploadFile(String fileName,String fileType) throws IOException {
        File fileMetadata = new File();
        fileMetadata.setName(fileName);
        java.io.File filePath = new java.io.File(fileName);
        FileContent mediaContent = new FileContent(fileType, filePath);
        File file = service.files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute();
        System.out.println("File ID: " + file.getId());
        return file;
        }

    public void CreateFile(String fileName)throws  IOException{

        OutputStream outputStream = new FileOutputStream(fileName+".doc");
        File fileMetadata = new File();
        fileMetadata.setName(fileName+".doc");
        fileMetadata.setMimeType("application/vnd.google-apps.document");
       // fileMetadata.setMimeType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        java.io.File filePath = new java.io.File(fileName+".doc");
        FileContent mediaContent = new FileContent("text/plain", filePath);
        File file = service.files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute();
        DBG.Log("Created Google Drive file %s",file.getId());
    }

    public int DownloadFile(String fileID, String fileName){
        try {
            OutputStream outputStream = new FileOutputStream(fileName);
            service.files().export(fileID,"application/vnd.openxmlformats-officedocument.wordprocessingml.document")
                    .executeMediaAndDownloadTo(outputStream);
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public void UpdateFile(String fileName, String fileId)throws  IOException{
        File fileMetadata = new File();
        fileMetadata.setName(fileName);
        fileMetadata.setMimeType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        java.io.File filePath = new java.io.File(fileName);
        /*FileContent mediaContent =
                new FileContent("application/vnd.openxmlformats-officedocument.wordprocessingml.document", filePath);*/
        FileContent mediaContent =
                new FileContent("application/vnd.google-apps.document", filePath);
        service.files().update(fileId,fileMetadata,mediaContent).execute();
    }
}
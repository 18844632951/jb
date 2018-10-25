package com.jb.io;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by 27654 on 2018/10/12.
 */
public class TestFastDFS {
    private TrackerClient tracker;
    private TrackerServer trackerServer;
    private StorageServer storageServer;
    private StorageClient1 client;
    private ClassPathResource resource;

    public TestFastDFS() {
        resource = new ClassPathResource("fastdfs.properties");
        try {
            ClientGlobal.init(resource.getFile().getPath());
            tracker = new TrackerClient();
            trackerServer = tracker.getConnection();
            storageServer = null;
            client = new StorageClient1(trackerServer, storageServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * fastDFS文件删除
     *
     * @param fileName
     * @return 删除成功返回0，删除失败或文件不存在返回2
     */
    public int delete_file(String fileName) throws IOException, MyException {
        return client.delete_file1(fileName);
    }

    /**
     * @param fileName 文件名
     * @param bytes  文件二进制流
     * @return 文件ID号
     */
    public String uploadFile(String fileName, byte[] bytes) throws IOException, MyException {
        NameValuePair[] meteList = new NameValuePair[3];
        meteList[0] = new NameValuePair("fileName", fileName);
        String suffix = "";
        if (fileName.contains(".")) {
            suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        meteList[1] = new NameValuePair("fileExtName", suffix);
        meteList[2] = new NameValuePair("fileLength", String.valueOf(bytes.length));
        //返回文件的路径信息
        String fileId = null;
        try {
            fileId = client.upload_file1(bytes, suffix, meteList);
            trackerServer.close();
        } catch (IOException e) {
            throw e;
        } catch (MyException e) {
            throw e;
        }
        return fileId;
    }


    public static void main(String[] args) throws IOException, MyException {
        FileInputStream in = new FileInputStream("F://4.png");
       ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int size = 0;
        while ((size = in.read(bytes)) != -1){
            out.write(bytes);
        }
        String ID = new TestFastDFS().uploadFile("index.png", out.toByteArray());
        System.err.println("文件ID："+ ID);
    }
}

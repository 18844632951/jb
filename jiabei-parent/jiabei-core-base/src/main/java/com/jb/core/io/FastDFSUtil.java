package com.jb.core.io;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.IOException;

/**
 * FastDFS文件服务客户端
 * Created by 27654 on 2018/10/12.
 */
public class FastDFSUtil {
    private TrackerClient tracker;
    private TrackerServer trackerServer;
    private StorageServer storageServer;
    private StorageClient1 client;

    static{

    }

    public FastDFSUtil() {
        try {
            ClientGlobal.init("fastdfs.properties");
            tracker = new TrackerClient();
            trackerServer = tracker.getConnection();
            client = new StorageClient1(trackerServer, storageServer);
        }catch(Exception e){
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
     * @param bytes    文件二进制流
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
        fileId = client.upload_file1(bytes, suffix, meteList);
        return fileId;
    }

    public  void close(){
        try {
            if(trackerServer != null){
                trackerServer.close();
            }
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    //TODO  待完善....
  /* public static void main(String[] args) throws IOException, MyException {
        FileInputStream in = new FileInputStream("F://4.png");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int size = 0;
        while ((size = in.read(bytes)) != -1) {
            out.write(bytes);
        }
        String ID = new FastDFSUtil("fastdfs.properties").uploadFile("index.png", out.toByteArray());

        System.err.println("文件ID：" + ID);
    }*/


}

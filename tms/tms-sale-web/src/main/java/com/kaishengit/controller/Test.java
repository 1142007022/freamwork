package com.kaishengit.controller;


import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Test {

    public static List<String> upload(InputStream inputStream, String name) throws IOException, MyException {
        //配置tracker
        Properties properties = new Properties();
        properties.setProperty(ClientGlobal.PROP_KEY_TRACKER_SERVERS,"192.168.238.132:22122");
        ClientGlobal.initByProperties(properties);
        //文件上传
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();

        StorageServer storageServer = null;
        StorageClient storageClient = new StorageClient(trackerServer,storageServer);

        List<String> resList = new ArrayList<>();
        String[] result = storageClient.upload_file(IOUtils.toByteArray(inputStream),name,null);
        for (String str : result){
            resList.add(str);
        }
        return resList;
    }


    public byte[] download(String group,String fileName) throws IOException, MyException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer = null;
        StorageClient storageClient = new StorageClient(trackerServer,storageServer);
        byte[] bytes = storageClient.download_file(group,fileName);
        return bytes;
    }


    public static void main(String[] args) throws IOException, MyException {
        InputStream inputStream = new FileInputStream("D:/upload/2.jpg");
        List<String> list = upload(inputStream,"jpg");
        for (String str:list){
            System.out.println(str);
        }
    }
}

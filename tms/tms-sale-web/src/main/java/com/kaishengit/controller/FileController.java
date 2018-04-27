package com.kaishengit.controller;

import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Controller
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public String upload(InputStream inputStream, String name) throws IOException, MyException {
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
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : result) {
            stringBuffer.append(str).append("/");
        }
        return stringBuffer.toString().substring(0,stringBuffer.toString().lastIndexOf("/"));
    }


    @PostMapping("/downlad")
    public byte[] download(String fileId) throws IOException, MyException {

        String groupName = fileId.substring(0,fileId.indexOf("/"));
        String filePath = fileId.substring(fileId.indexOf("/")+1);
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer = null;
        StorageClient storageClient = new StorageClient(trackerServer,storageServer);
        byte[] bytes = storageClient.download_file(groupName,filePath);
        return bytes;
    }

}

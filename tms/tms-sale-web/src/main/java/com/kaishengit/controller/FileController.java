package com.kaishengit.controller;

import com.kaishengit.dto.Result;
import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Controller
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public @ResponseBody Result upload(MultipartFile  file) throws IOException, MyException {

        InputStream inputStream = file.getInputStream();
        String name = file.getOriginalFilename();
        String exName = name.substring(name.lastIndexOf(".") + 1);
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
        String[] res = storageClient.upload_file(IOUtils.toByteArray(inputStream),exName,null);
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : res) {
            stringBuffer.append(str).append("/");
        }
        String fileId = stringBuffer.toString().substring(0,stringBuffer.toString().lastIndexOf("/"));
        Result result = Result.success(fileId);
        return result;
    }


    @GetMapping("/download")
    public void download(@RequestParam String fileName, HttpServletResponse response) throws IOException, MyException {

        String groupName = fileName.substring(0,fileName.indexOf("/"));
        String filePath = fileName.substring(fileName.indexOf("/")+1);
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer = null;
        StorageClient storageClient = new StorageClient(trackerServer,storageServer);
        byte[] bytes = storageClient.download_file(groupName,filePath);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }

}

package com.kaishengit.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileController {

    @GetMapping
    public String file(){
        return "customer/upload";
    }

    @PostMapping
    public String file(String name, MultipartFile photo) throws IOException {

        InputStream inputStream = photo.getInputStream();
        OutputStream outputStream = new FileOutputStream(new File("D:/upload/"+ UUID.randomUUID()+photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf("."))));
        IOUtils.copy(inputStream,outputStream);
        inputStream.close();
        outputStream.close();

        //photo.transferTo(new File("D:/upload/"+ UUID.randomUUID()+photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf("."))));
        return "redirect:/file";
    }

}

package com.vanphuc.webbanhang.file.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface FileService {
    Resource load(String filename);

    String upload(MultipartFile file);

    void delete(String filename);
}

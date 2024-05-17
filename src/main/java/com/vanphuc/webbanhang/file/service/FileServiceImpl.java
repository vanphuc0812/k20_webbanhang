package com.vanphuc.webbanhang.file.service;

import com.vanphuc.webbanhang.common.exception.WBHBussinessException;
import com.vanphuc.webbanhang.common.utils.FileUtil;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public Resource load(String filename) {
        try {
            Path file = FileUtil.ROOTPATH.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new WBHBussinessException("Resource Not Found");
            }
        } catch (Exception e) {
            throw new WBHBussinessException(e.getMessage());
        }

    }

    @Override
    public String upload(MultipartFile file) {
        return FileUtil.saveFile(file);
    }

    @Override
    public void delete(String filename) {
        try {
            Path file = FileUtil.ROOTPATH.resolve(filename);
            Files.delete(file);
        } catch (IOException e) {
            throw new WBHBussinessException("Can't delete file: " + e.getMessage());
        }
    }
}

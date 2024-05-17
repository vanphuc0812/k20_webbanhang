package com.vanphuc.webbanhang.common.utils;

import com.vanphuc.webbanhang.common.exception.WBHBussinessException;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@UtilityClass
public class FileUtil {
    public static final Path ROOTPATH = Paths.get("images");

    public static String saveFile(MultipartFile file) {
        if (init()) {
            try {
                Files.copy(file.getInputStream(), ROOTPATH.resolve(file.getOriginalFilename()));
            } catch (IOException e) {
                throw new WBHBussinessException("Can not save file");
            }
        }
        return ROOTPATH.resolve(file.getOriginalFilename()).toString();
    }

    private static boolean init() {
        try {
            if (!Files.exists(ROOTPATH)) {
                Files.createDirectory(ROOTPATH);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}

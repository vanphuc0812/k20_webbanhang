package com.vanphuc.webbanhang.file.controller;

import com.vanphuc.webbanhang.common.utils.ResponseUtil;
import com.vanphuc.webbanhang.file.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileRestResource {
    private final FileService fileService;

    public FileRestResource(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/{file-name}")
    public Object load(@PathVariable("file-name") String filename) {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(fileService.load(filename));
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object upload(@RequestPart("file") MultipartFile file) {
        return ResponseUtil.get(fileService.upload(file), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    public Object upload(@RequestParam String filename) {
        fileService.delete(filename);
        return ResponseUtil.get("Delete file successfully", HttpStatus.OK);
    }
}

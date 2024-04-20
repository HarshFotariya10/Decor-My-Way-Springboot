package com.register.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	String UploadImage(String path,MultipartFile file) throws IOException; 
	InputStream getimage(String path,String filename) throws FileNotFoundException;
}

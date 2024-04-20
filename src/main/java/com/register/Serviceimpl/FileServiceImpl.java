package com.register.Serviceimpl;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.register.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	 @Autowired
    private Environment env;

	
	@Override
	public String UploadImage(String path, MultipartFile file) throws IOException {
		//File Name
		
        String productFilePath = env.getProperty("product_file_path");

		
		String name =file.getOriginalFilename();
		
		//Random Name Generate	
		
		//Full path
		String filepath=path+File.separator+name;

		//Create Folder If not Created
		
		File f = new File(path);
		if(!f.exists())
		{
			f.mkdirs();
			
		}
		//File Copy
			
		Files.copy(file.getInputStream(), Paths.get(filepath));
		return  name ;
	}

	@Override
	public InputStream getimage(String path, String filename) throws FileNotFoundException {
		String fullpath = path+File.separator+filename;
		InputStream is = new FileInputStream(fullpath);
		return is;
	}

}
	
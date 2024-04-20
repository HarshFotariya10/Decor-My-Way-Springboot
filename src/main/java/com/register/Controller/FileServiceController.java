package com.register.Controller;

import java.io.IOException;
import java.io.InputStream;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.register.Exceptions.ImageResponse;
import com.register.service.FileService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4000"})
@RequestMapping("/file")
public class FileServiceController {
	
	@Autowired
	private FileService fileservice;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/upload")
	public ResponseEntity<ImageResponse> fileupload(
			@RequestParam("image") MultipartFile image
			)
	{
	String filename;
	try {
		filename = this.fileservice.UploadImage(path, image);
	} catch (IOException e) {
		
		e.printStackTrace();
		return new ResponseEntity<>(new ImageResponse(null,"Not Uploaded Due to error no Server"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	return new ResponseEntity<>(new ImageResponse(filename,"Succesfully Uploaded"),HttpStatus.OK);
	
	}
	
	//method to serve image
	@GetMapping("/serve/{imagename}")
	public void downloadfile(@PathVariable String imagename,
			HttpServletResponse response) throws IOException 
	{
		InputStream	is =  this.fileservice.getimage(path, imagename);
		response.setContentType(MediaType.IMAGE_PNG_VALUE);	
		org.springframework.util.StreamUtils.copy(is, response.getOutputStream());
	}

}

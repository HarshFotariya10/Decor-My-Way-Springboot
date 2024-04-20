package com.register.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import jakarta.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;


@RestController
@RequestMapping(value = "file")

public class FileController {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private Environment env;


    @GetMapping("/**")
    @ResponseBody
    public ResponseEntity<?> getFile(HttpServletRequest request) {

        Object uriObject = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        if (null != uriObject) {

            String relativePath = uriObject.toString().replaceFirst("^/file/", "");
            Resource file;
            try {

                file = getResource(ResourceUtils.FILE_URL_PREFIX, relativePath, false);
                return ResponseEntity.ok(file);
            } catch (IOException e) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body(new ValidationError("File [" + relativePath + "] not found )"));
                return null;
            }

        }

        return ResponseEntity.notFound().build();
    }

    public Resource getResource(String fileUrlPrefix, String filePath, boolean isAbsolutePath) throws IOException {

        String uploadDirPath = env.getProperty("upload_file_dir_path");

        if (!StringUtils.isEmpty(fileUrlPrefix) && !StringUtils.isEmpty(filePath)) {

            if (ResourceUtils.CLASSPATH_URL_PREFIX.equals(fileUrlPrefix)) {

                return resourceLoader.getResource(fileUrlPrefix + filePath);

            } else {

                File file = new File(isAbsolutePath ? filePath : Paths.get(uploadDirPath, filePath).toString());

                if (null != file && file.exists() && !file.isDirectory()) {
                    return resourceLoader.getResource(fileUrlPrefix + file.getAbsolutePath());
                }

            }
        }
        throw new FileNotFoundException();

    }

}
package com.example.product_catalog.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.nio.file.Files;

@Controller
public class ImageController {
    @GetMapping("/images/{imageName}")
    public void getImage(@PathVariable String imageName, HttpServletResponse response) throws IOException
    {
        File resource = new File("G:\\Library\\Fawry Intership\\Project-2\\productCatalog\\src\\main\\resources\\static\\images\\"+imageName);

      if (!resource.exists())
        {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        response.setContentType(MediaType.ALL_VALUE);
        InputStream in= Files.newInputStream(resource.toPath());
        IOUtils.copy(in,response.getOutputStream());
    }
}

package ru.tinyakov.picnet.service;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RequiredArgsConstructor
@Slf4j
@Service
public class StorageServiceImpl implements StorageService {
    @Value("${picnet.storedir}")
    private String path;

    @Value("${picnet.pic.width1}")
    private int w1;

    @Value("${picnet.pic.width2}")
    private int w2;

    @Value("${picnet.pic.width3}")
    private int w3;

    @Override
    public String[] store(MultipartFile file) {
        try {
            String[] paths = new String[3];
            String randomPath = String.valueOf(Math.round(Math.random() * 100000000))+"_" + System.currentTimeMillis();
            paths[0] = "/pic" + randomPath + "_L" + ".png";
            paths[1] = "/pic" + randomPath + "_S" + ".png";
            paths[2] = "/pic" + randomPath + "_XS" + ".png";
            BufferedImage image = ImageIO.read(file.getInputStream());
            log.info("BufferedImage image {}", image);
            if (    scaleAndSave(image, w1, getHeight(w1, image), paths[0]) &&
                    scaleAndSave(image, w2, getHeight(w2, image), paths[1]) &&
                    scaleAndSave(image, w3, getHeight(w3, image), paths[2]))
                return paths;
        } catch (IOException e) {
            log.error("Cant copy file {} ", file.getOriginalFilename());
        } catch (URISyntaxException e) {
            log.error(e.toString());
        }
        return null;
    }

    private int getHeight(int width1, BufferedImage image) {
        return (int) image.getHeight() * width1 / image.getWidth();
    }

    private boolean scaleAndSave(BufferedImage image, int width, int height, String addPath) throws IOException, URISyntaxException {
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        String fullPath = new URI(path).getPath() + addPath;
        File file2 = new File(fullPath);
        return ImageIO.write(ResizeImageService.convertToBufferedImage(scaledImage), "png", file2);
    }
}

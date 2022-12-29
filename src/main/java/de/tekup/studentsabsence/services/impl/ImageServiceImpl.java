package de.tekup.studentsabsence.services.impl;

import de.tekup.studentsabsence.entities.Image;
import de.tekup.studentsabsence.entities.Student;
import de.tekup.studentsabsence.repositories.ImageRepository;
import de.tekup.studentsabsence.repositories.StudentRepository;
import de.tekup.studentsabsence.services.ImageService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor

public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final StudentRepository studentRepository;


    // TODO Complete this method
    @Override
    public Image getImage(String id) {
        return null;
    }

    @Override
    public Image addImage(MultipartFile image) throws IOException {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        String fileType = image.getContentType();
        byte[] data = image.getBytes();
        Image img = new Image( fileName, fileType, data);
        System.out.println("bonjour"+img.getId());
        return imageRepository.save(img);
    }
}

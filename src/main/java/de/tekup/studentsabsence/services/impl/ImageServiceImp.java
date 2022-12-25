package de.tekup.studentsabsence.services.impl;

import de.tekup.studentsabsence.entities.Image;
import de.tekup.studentsabsence.entities.Student;
import de.tekup.studentsabsence.repositories.ImageRepository;
import de.tekup.studentsabsence.repositories.StudentRepository;
import de.tekup.studentsabsence.services.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ImageServiceImp implements ImageService {
    private final ImageRepository imageRepository;
    private final StudentRepository studentRepository;


    //TODO Complete this method
    @Override
    public Image getImage(String id) {
        try {
            return imageRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public Image addImage(MultipartFile image, Long studentId) throws IOException {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        String fileType = image.getContentType();
        byte[] data = image.getBytes();
        Student student = studentRepository.findById(studentId).orElse(null);
        Image img = new Image();
        img.setId(UUID.randomUUID().toString());
        img.setFileName(fileName);
        img.setFileType(fileType);
        img.setData(data);
        img.setStudent(student);
        return imageRepository.save(img);
    }
}

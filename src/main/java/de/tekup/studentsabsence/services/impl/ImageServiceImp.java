package de.tekup.studentsabsence.services.impl;

import de.tekup.studentsabsence.entities.Image;
import de.tekup.studentsabsence.repositories.ImageRepository;
import de.tekup.studentsabsence.services.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageServiceImp implements ImageService {
    private final ImageRepository imageRepository;

    //TODO Complete this method
    /*DONE*/
    @Override
    public Optional<Image> getImage(String id) {
         return Optional.ofNullable(imageRepository.findById(id)
                 .orElseThrow(() ->
                    new IllegalArgumentException("Image not found with specific id")
                ));
    }

    @Override
    public Image addImage(MultipartFile image) throws IOException {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        String fileType = image.getContentType();
        byte[] data = image.getBytes();
        Image img = new Image(null, fileName, fileType, data);
        return imageRepository.save(img);
    }
}

package ba.unsa.etf.nwt.ingredient_service.service;

import ba.unsa.etf.nwt.ingredient_service.domain.Picture;
import ba.unsa.etf.nwt.ingredient_service.model.PictureDTO;
import ba.unsa.etf.nwt.ingredient_service.repos.PictureRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class PictureService {

    @Autowired
    private final PictureRepository pictureRepository;

    public PictureService(final PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public List<PictureDTO> findAll() {
        return pictureRepository.findAll()
                .stream()
                .map(picture -> mapToDTO(picture, new PictureDTO()))
                .collect(Collectors.toList());
    }

    public PictureDTO get(final UUID id) {
        return pictureRepository.findById(id)
                .map(picture -> mapToDTO(picture, new PictureDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public UUID create(final PictureDTO pictureDTO) {
        final Picture picture = new Picture();
        mapToEntity(pictureDTO, picture);
        return pictureRepository.save(picture).getId();
    }

    public void update(final UUID id, final PictureDTO pictureDTO) {
        final Picture picture = pictureRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(pictureDTO, picture);
        pictureRepository.save(picture);
    }

    public void delete(final UUID id) {
        pictureRepository.deleteById(id);
    }

    public void deleteAll() {
        pictureRepository.deleteAll();
    }

    private PictureDTO mapToDTO(final Picture picture, final PictureDTO pictureDTO) {
        pictureDTO.setId(picture.getId());
        pictureDTO.setPicData(picture.getPicData());
        return pictureDTO;
    }

    private Picture mapToEntity(final PictureDTO pictureDTO, final Picture picture) {
        picture.setPicData(pictureDTO.getPicData());
        return picture;
    }

}

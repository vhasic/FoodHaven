package etf.unsa.ba.nwt.recipe_service.service;

import etf.unsa.ba.nwt.recipe_service.domain.Category;
import etf.unsa.ba.nwt.recipe_service.domain.Picture;
import etf.unsa.ba.nwt.recipe_service.model.CategoryDTO;
import etf.unsa.ba.nwt.recipe_service.repos.CategoryRepository;
import etf.unsa.ba.nwt.recipe_service.repos.PictureRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final PictureRepository pictureRepository;

    public CategoryService(final CategoryRepository categoryRepository,
            final PictureRepository pictureRepository) {
        this.categoryRepository = categoryRepository;
        this.pictureRepository = pictureRepository;
    }

    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> mapToDTO(category, new CategoryDTO()))
                .collect(Collectors.toList());
    }

    public CategoryDTO get(final Integer id) {
        return categoryRepository.findById(id)
                .map(category -> mapToDTO(category, new CategoryDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Integer create(final CategoryDTO categoryDTO) {
        final Category category = new Category();
        mapToEntity(categoryDTO, category);
        return categoryRepository.save(category).getId();
    }

    public void update(final Integer id, final CategoryDTO categoryDTO) {
        final Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(categoryDTO, category);
        categoryRepository.save(category);
    }

    public void delete(final Integer id) {
        categoryRepository.deleteById(id);
    }

    private CategoryDTO mapToDTO(final Category category, final CategoryDTO categoryDTO) {
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setCategoryPicture(category.getCategoryPicture() == null ? null : category.getCategoryPicture().getId());
        return categoryDTO;
    }

    private Category mapToEntity(final CategoryDTO categoryDTO, final Category category) {
        category.setName(categoryDTO.getName());
        if (categoryDTO.getCategoryPicture() != null && (category.getCategoryPicture() == null || !category.getCategoryPicture().getId().equals(categoryDTO.getCategoryPicture()))) {
            final Picture categoryPicture = pictureRepository.findById(categoryDTO.getCategoryPicture())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "categoryPicture not found"));
            category.setCategoryPicture(categoryPicture);
        }
        return category;
    }

}

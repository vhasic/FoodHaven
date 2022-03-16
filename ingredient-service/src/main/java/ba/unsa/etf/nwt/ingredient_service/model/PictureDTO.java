package ba.unsa.etf.nwt.ingredient_service.model;

import javax.validation.constraints.Size;


public class PictureDTO {

    private Integer id;

    @Size(max = 255)
    private String picData;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getPicData() {
        return picData;
    }

    public void setPicData(final String picData) {
        this.picData = picData;
    }

}

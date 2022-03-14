package etf.unsa.ba.nwt.recipe_service.domain;

import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Picture {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String picData;

    @OneToOne(
            mappedBy = "recipePicture",
            fetch = FetchType.LAZY,
            optional = false
    )
    private Recipe recipePicture;

    @OneToOne(
            mappedBy = "categoryPicture",
            fetch = FetchType.LAZY,
            optional = false
    )
    private Category categoryPicture;

    @OneToOne(
            mappedBy = "stepPicture",
            fetch = FetchType.LAZY,
            optional = false
    )
    private Step stepPicture;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Picture(String picData) {
        this.picData = picData;
    }

    public Picture() {

    }

    @PrePersist
    public void prePersist() {
        dateCreated = OffsetDateTime.now();
        lastUpdated = dateCreated;
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdated = OffsetDateTime.now();
    }

}

package ba.etf.unsa.nwt.user_service.user_service.domain;

import java.time.OffsetDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Token {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "char(36)")
    @Type(type = "uuid-char")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @Column(nullable = false)
    @NotNull(message = "Token can't be null")
    @Size(min = 8, message = "Token must have at least 8 characters")
    private String token;

    @Column(nullable = false)
    private String type; // možda ovo ne treba

    @Column(nullable = false)
    @Positive(message = "Duration must be a positive integer")
    private Integer duration;

    @Column(nullable = false)
    @NotNull(message = "Field valid can't be null")
    private Boolean valid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @NotNull(message = "Token must have a user")
    private User user;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}

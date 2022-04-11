package ba.unsa.etf.nwt.system_events_service.domain;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Action {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "char(36)")
    @Type(type = "uuid-char")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @Column(nullable = false)
    private Instant timestamp;

    @Column(nullable = false)
    private String service;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String actionType;

    @Column(nullable = false)
    private String resourceName;

    @Column(nullable = false)
    private String responseType;
}

package com.urbanroute.entity;

import com.urbanroute.enums.TransportMode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_stop_id")
    private Stop fromStop;

    @ManyToOne
    @JoinColumn(name = "to_stop_id")
    private Stop toStop;

    @Enumerated(EnumType.STRING)
    private TransportMode transportMode;

    @Column(nullable = false)
    private Double costInRupees;

    @Column(nullable = false)
    private Integer durationInMinutes;

    @ManyToOne
    @JoinColumn(name = "contributed_by")
    private User contributedBy;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
}

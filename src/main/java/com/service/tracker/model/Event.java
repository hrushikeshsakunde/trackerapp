package com.service.tracker.model;

import com.service.tracker.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


/**
 * Base class to store events
 * -many events per session
 *
 * @author Hrushikesh Sakunde
 * @since 12.10.20
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Column(nullable = false, updatable = false)
    private LocalDateTime eventAt;

    @Column
    private String payload;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="session_id", nullable=false)
    private Session session;

}

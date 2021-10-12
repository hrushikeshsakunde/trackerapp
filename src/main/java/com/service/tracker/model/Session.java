package com.service.tracker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;


/**
 * class represent session
 * -one session for many event
 * -one session per user (user id)
 *
 * @author Hrushikesh Sakunde
 * @since 12.10.20
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private UUID machineId;

    @Column(nullable = false)
    private Integer orgId;

    @Column
    private LocalDateTime startAt;

    @Column
    private LocalDateTime endAt;

    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Event> events = new LinkedHashSet<>();

}

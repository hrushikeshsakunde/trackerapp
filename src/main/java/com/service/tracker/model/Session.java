package com.service.tracker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

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

    @Column
    private Long orgId ;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime startAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime endAt;

    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Event> events = new LinkedHashSet<>();

}

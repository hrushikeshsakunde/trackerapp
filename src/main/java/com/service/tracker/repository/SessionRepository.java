package com.service.tracker.repository;

import com.service.tracker.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {

    Optional<Session> findSessionByUserIdAndMachineIdAndEndAtIsNull(UUID userId, UUID machineId);
}

package com.jolyvert.deliveryapp.repository;


import com.jolyvert.deliveryapp.model.CurrentLoginSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<CurrentLoginSession, Integer> {

    public CurrentLoginSession findByUuid(String uuid);
}

package com.takima.race.races.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.takima.race.races.entities.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

}

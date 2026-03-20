package com.takima.race.races.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.takima.race.races.entities.Race;
import com.takima.race.races.entities.Registration;
import com.takima.race.races.services.RaceService;
import com.takima.race.races.services.RegistrationService;
import com.takima.race.runner.entities.Runner;

import java.util.List;

@RestController
@RequestMapping("/races")
public class RaceController {
    private final RaceService raceService;
    private final RegistrationService registrationService;

    public RaceController(RaceService raceService, RegistrationService registrationService) {
        this.raceService = raceService;
        this.registrationService = registrationService;
    }

    @GetMapping
    public List<Race> getAll() {
        return raceService.getAll();
    }

    @GetMapping("/{id}")
    public Race getById(@PathVariable Long id) {
        return raceService.getById(id);
    }

    @PostMapping
    public Race create(
        @RequestBody Race race
    ) {
        return raceService.create(race);
    }

    @PostMapping("{raceId}/registrations")
    public Registration createRegistration(
        @RequestBody Registration registration,
        @PathVariable("raceId") Long raceId
    ) {
        return registrationService.createRegistration(registration,raceId);
    }

    @GetMapping("{raceId}/registrations")
    public List<Runner> findRunnerByRace(
        @PathVariable("raceId") Long raceId
    ) {
        return registrationService.findRunnerByRace(raceId);
    }

    @GetMapping("{raceId}/participants/count")
    public Integer countRunnerByRace(
        @PathVariable("raceId") Long raceId
    ) {
        return registrationService.countRunnerByRace(raceId);
    }
}
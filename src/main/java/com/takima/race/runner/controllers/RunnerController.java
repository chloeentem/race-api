package com.takima.race.runner.controllers;

import com.takima.race.races.entities.Race;
import com.takima.race.races.services.RegistrationService;
import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.services.RunnerService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/runners")
public class RunnerController {
    private final RunnerService runnerService;
    private final RegistrationService registrationService;

    public RunnerController(RunnerService runnerService, RegistrationService registrationService) {
        this.runnerService = runnerService;
        this.registrationService = registrationService;
    }

    @GetMapping
    public List<Runner> getAll() {
        return runnerService.getAll();
    }

    @GetMapping("/{id}")
    public Runner getById(@PathVariable Long id) {
        return runnerService.getById(id);
    }

    @PostMapping
    public Runner create(
        @Valid @RequestBody Runner runner
    ) {
        return runnerService.create(runner);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id ){
        runnerService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Runner update(@PathVariable Long id,
       @RequestBody Runner runner
    ){
        runnerService.getById(id);
        runner.setId(id);
        return runnerService.update(runner);
    }
    
    @GetMapping("{runnerId}/races")
    public List<Race> findRacesByRunner(
        @PathVariable("runnerId") Long runnerId
    ) {
        return registrationService.findRacesByRunner(runnerId);
    }
}
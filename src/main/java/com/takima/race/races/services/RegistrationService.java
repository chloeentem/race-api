package com.takima.race.races.services;

import com.takima.race.races.entities.Race;
import com.takima.race.races.entities.Registration;
import com.takima.race.races.repositories.RaceRepository;
import com.takima.race.races.repositories.RegistrationRepository;
import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.repositories.RunnerRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final RaceRepository raceRepository;
    private final RunnerRepository runnerRepository;

    public RegistrationService(RegistrationRepository registrationRepository,RaceRepository raceRepository, RunnerRepository runnerRepository) {
        this.registrationRepository = registrationRepository;
        this.raceRepository=raceRepository;
        this.runnerRepository=runnerRepository;
    }

    public Registration createRegistration(Registration registration, Long raceId) {

        Race race = raceRepository.findById(raceId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Race not found"));

        Long runnerId = registration.getRunnerId();
        Runner runner = runnerRepository.findById(runnerId).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Runner not found", raceId)));;

        registration.setRaceId(raceId);
        registration.setRegistrationDate(new Date(System.currentTimeMillis())); 

        List<Runner> participants = findRunnerByRace(raceId);
        for (int i=0; i<participants.size();i++){
            if (participants.get(i).getId().equals(registration.getRunnerId())){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Vous êtes déjà inscrit à cette course");
            }
        }

        Integer nbParticipants = countRunnerByRace(raceId);
        Integer nbMaxParticipants = race.getMaxParticipants();
        if (nbParticipants>=nbMaxParticipants){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Vous ne pouvez pas vous inscrire, la course est complète");
        }

        return registrationRepository.save(registration);
    }

    public List<Runner> findRunnerByRace(Long raceId) {

        Race race = raceRepository.findById(raceId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Race not found"));
        List<Registration> registrations = registrationRepository.findAll();
        List<Runner> runners= new ArrayList<>() ;
        for (int i=0; i<registrations.size();i++){
            if (registrations.get(i).getRaceId().equals(raceId)){
                Long runnerId = registrations.get(i).getRunnerId();
                Runner runnerRegistration = runnerRepository.findById(runnerId).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Runner not found", raceId)
                )
                );;
                runners.add(runnerRegistration);
            }
        }
        return runners;
    }

    public List<Race> findRacesByRunner(Long runnerId) {

        Runner runner = runnerRepository.findById(runnerId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Runner not found"));
        List<Registration> registrations = registrationRepository.findAll();
        List<Race> races= new ArrayList<>() ;
        for (int i=0; i<registrations.size();i++){
            if (registrations.get(i).getRunnerId().equals(runnerId)){
                Long raceId = registrations.get(i).getRaceId();
                Race raceRegistration = raceRepository.findById(raceId).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Race not found", runnerId)
                )
                );;
                races.add(raceRegistration);
            }
        }
        return races;
    }

    public Integer countRunnerByRace(Long raceId){
        List<Runner> participants = findRunnerByRace(raceId);
        return participants.size();
    }
}

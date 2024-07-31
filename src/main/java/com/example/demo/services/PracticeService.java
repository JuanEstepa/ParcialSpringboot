package com.example.demo.services;

import com.example.demo.models.Practice;
import com.example.demo.repositories.PracticeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PracticeService {

    @Autowired
    private PracticeRepo practiceRepo;

    public List<Practice> getAllPractices() {
        return practiceRepo.findAll();
    }

    public Practice getByIdPractice(long id) {
        Optional<Practice> practice = practiceRepo.findById(id);
        return practice.isPresent() ? practice.get() : null;
    }

    public Practice addPractice(Practice practice) {
        return practiceRepo.save(practice);
    }

    public void deletePractice(long id) {
        practiceRepo.deleteById(id);
    }
}

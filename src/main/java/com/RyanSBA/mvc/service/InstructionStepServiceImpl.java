package com.RyanSBA.mvc.service;

import com.RyanSBA.mvc.model.InstructionStep;
import com.RyanSBA.mvc.model.Recipe;
import com.RyanSBA.mvc.repository.InstructionStepRepository;
import org.aspectj.apache.bcel.generic.Instruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service

public class InstructionStepServiceImpl implements InstructionStepService {

    final InstructionStepRepository repo;
    @Autowired
    public InstructionStepServiceImpl(InstructionStepRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<InstructionStep> findAllInstructionSteps() {
        return repo.findAll();
    }

    @Override
    public void saveInstructionStep(InstructionStep instructionStep) {
        repo.save(instructionStep);
    }

    @Override
    public Optional<InstructionStep> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public Set<InstructionStep> findByRecipe(Recipe recipe) {
        return null;
    }

    @Override
    public void updateInstructionStep(InstructionStep instructionStep) {
        repo.save(instructionStep);
    }

    @Override
    public void deleteInstructionStep(InstructionStep instructionStep) {
        repo.delete(instructionStep);
    }
}

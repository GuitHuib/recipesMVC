package com.RyanSBA.mvc.service;

import com.RyanSBA.mvc.model.InstructionStep;
import com.RyanSBA.mvc.model.Recipe;
import org.aspectj.apache.bcel.generic.Instruction;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface InstructionStepService {

    List<InstructionStep> findAllInstructionSteps();
    void saveInstructionStep(InstructionStep instructionStep);
    Optional<InstructionStep> findById(int id);
    Set<InstructionStep> findByRecipe(Recipe recipe);
    void updateInstructionStep(InstructionStep instructionStep);
    void deleteInstructionStep(InstructionStep instructionStep);
}

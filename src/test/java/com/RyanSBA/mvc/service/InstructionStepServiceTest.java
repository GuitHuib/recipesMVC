package com.RyanSBA.mvc.service;

import com.RyanSBA.mvc.model.InstructionStep;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InstructionStepServiceTest {

    @Autowired
    InstructionStepServiceImpl instructionStepService;

    InstructionStep step;
    @Test
    public void saveStepTest() {
        int start = instructionStepService.findAllInstructionSteps().size();
        step = new InstructionStep();
        step.setText("test");
        instructionStepService.saveInstructionStep(step);
        int end = instructionStepService.findAllInstructionSteps().size();
        Assertions.assertEquals(start+1, end);
    }
    @Test
    public void deleteStepTest() {
        int start = instructionStepService.findAllInstructionSteps().size();
        instructionStepService.deleteInstructionStep(step);
        int end = instructionStepService.findAllInstructionSteps().size();
        Assertions.assertEquals(start-1, end);
    }
}

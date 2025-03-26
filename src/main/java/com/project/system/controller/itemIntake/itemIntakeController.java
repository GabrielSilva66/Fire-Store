package com.project.system.controller.itemIntake;

import com.project.system.models.Intake;
import com.project.system.repositories.IntakeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class itemIntakeController {

    @Autowired
    IntakeRepository intakeRepository;

    @GetMapping("/intake/register")
    public ModelAndView registerIntake(Intake intake){
        ModelAndView mv = new ModelAndView("/intake/register");
        return  mv.addObject("intake", intake);
    }

    @GetMapping("/intakes")
    public ModelAndView listIntakes(){
        List<Intake> intakes = intakeRepository.findAll();
        ModelAndView mv = new ModelAndView("/intake/list");

        mv.addObject("listIntakes", intakes);
        return  mv;
    }


    @GetMapping("/intake/edit/{id}")
    public ModelAndView editIntake(@PathVariable("id") Long id){
        Optional<Intake> intake = intakeRepository.findById(id);

        if (intake.isPresent()){
            ModelAndView mv = new ModelAndView("/intake/register");
            mv.addObject("intake", intake.get());
            return mv;
        }
        return listIntakes();
    }


    @GetMapping("/intake/delete/{id}")
    public ModelAndView deleteIntake(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        Optional<Intake> intake = intakeRepository.findById(id);

        if (intake.isPresent()){
            intakeRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Intake deleted");
        } else{
            redirectAttributes.addFlashAttribute("error", "Fail deleted intake");
        }
        return listIntakes();
    }


    @PostMapping("intake/save")
    public  ModelAndView saveIntake(@Valid Intake intake, BindingResult result){
        if(result.hasErrors()){
            return registerIntake(intake);
        }
        intakeRepository.save(intake);
        return new ModelAndView("redirect:/intakes");
    }


}

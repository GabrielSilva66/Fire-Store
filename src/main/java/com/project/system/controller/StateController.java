package com.project.system.controller;


import com.project.system.models.state.State;
import com.project.system.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @GetMapping("/registerState")
    public ModelAndView register (State state) {
        ModelAndView mv = new ModelAndView("management/state/register");
        return mv.addObject("state", state);
    }

    @PostMapping("/saveState")
    public ModelAndView save (State state, BindingResult result){
        if(result.hasErrors()){
            return register(state);
        }
        stateRepository.saveAndFlush(state);
        return register(new State());
    }


}




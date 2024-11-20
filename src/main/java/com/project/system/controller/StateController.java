package com.project.system.controller;


import com.project.system.models.state.State;
import com.project.system.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @GetMapping("/registerState")
    public ModelAndView register (State state) {
        ModelAndView mv = new ModelAndView("management/state/register");
        return mv.addObject("state", state);
    }

    @GetMapping("/stateList")
    public ModelAndView listActiveStates() {
        ModelAndView mv = new ModelAndView("/management/state/list");

        // Buscar apenas estados ativos
        List<State> activeStates = stateRepository.findActiveStates();
        mv.addObject("stateList", activeStates);
        return mv;
    }

    @GetMapping("/editState/{id}")
    public ModelAndView edit(@PathVariable("id") UUID id){
        Optional<State> state = stateRepository.findById(id);
        stateRepository.updateUpdateIn(id, LocalDateTime.now());
        return register(state.get());
    }


    @GetMapping("/deleteState/{id}")
    public ModelAndView deactivateState(@PathVariable("id") UUID id, RedirectAttributes redirectAttributes) {
        Optional<State> state = stateRepository.findById(id);

        if (state.isPresent()) {
            stateRepository.updateStateStatus(id, false);  // Desativa o estado (false)
            redirectAttributes.addFlashAttribute("message", "Estado marcado como inativo com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Estado não encontrado!");
        }

        return listActiveStates();
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




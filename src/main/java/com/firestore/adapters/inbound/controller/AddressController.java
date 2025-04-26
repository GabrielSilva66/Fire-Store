package com.firestore.adapters.inbound.controller;

import com.firestore.domain.Address.Address;
import com.firestore.application.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/address/register")
    public ModelAndView registerAddress(Address address,
                                        @RequestParam(required = false) String entityType,
                                        @RequestParam(required = false) Long entityId) {
        ModelAndView mv = new ModelAndView("address/register");
        mv.addObject("address", address);
        mv.addObject("entityType", entityType);
        mv.addObject("entityId", entityId);
        return mv;
    }

    @GetMapping("/address/edit/{entityType}/{entityId}")
    public ModelAndView editAddress(@PathVariable("entityType") String entityType,
                                    @PathVariable("entityId") Long entityId) {

        Address address = addressService.getAddressFromEntity(entityType, entityId);
        if (address == null) address = new Address();

        ModelAndView mv = new ModelAndView("address/register");
        mv.addObject("address", address);
        mv.addObject("entityType", entityType);
        mv.addObject("entityId", entityId);

        return mv;
    }

    @PostMapping("/address/save")
    public ModelAndView saveAddress(@Valid Address address,
                                    BindingResult result,
                                    @RequestParam("entityType") String entityType,
                                    @RequestParam("entityId") Long entityId) {
        if (result.hasErrors()) {
            return registerAddress(address, entityType, entityId);
        }

        addressService.saveAddressAndBindToEntity(address, entityType, entityId);

        return new ModelAndView("redirect:/system");
    }
}

package com.firestore.adapters.inbound.controller;

import com.firestore.domain.customer.Customer;
import com.firestore.application.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/customer/register")
    public ModelAndView registerCustomer(Customer customer){
        return new ModelAndView("/customer/register").addObject("customer", customer);
    }

    @GetMapping("/customers")
    public ModelAndView listActiveCustomer(){
        List<Customer> activeCustomers = customerService.findActiveCustomers();
        return new ModelAndView("/customer/list")
                .addObject("activePage", "customers")
                .addObject("customerList", activeCustomers);
    }

    @GetMapping("/customer/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id){
        Optional<Customer> customer = customerService.findById(id);
        return customer.map(value -> new ModelAndView("/customer/register").addObject("customer", value))
                .orElseGet(this::listActiveCustomer);
    }

    @GetMapping("/customer/delete/{id}")
    public ModelAndView deleteActivateState(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<Customer> customer = customerService.findById(id);

        if (customer.isPresent()) {
            customerService.deactivateCustomer(id);
            redirectAttributes.addFlashAttribute("message", "Cliente marcado como inativo com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Cliente não encontrado!");
        }

        return new ModelAndView("redirect:/customers");
    }

    @PostMapping("/customer/save")
    public ModelAndView save(@Valid Customer customer, BindingResult result){
        if(result.hasErrors()){
            return registerCustomer(customer);
        }
        customerService.save(customer);
        return new ModelAndView("redirect:/customers");
    }
}


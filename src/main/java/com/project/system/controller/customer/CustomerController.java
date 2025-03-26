package com.project.system.controller.customer;


import com.project.system.models.Customer;
import com.project.system.repositories.CustomerRepository;
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
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/customer/register")
    public ModelAndView registerCustomer(Customer customer){
        ModelAndView mv = new ModelAndView("/customer/register");
        return  mv.addObject("customer", customer);
    }


    @GetMapping("/customers")
    public ModelAndView listActiveCustomer(){
        ModelAndView mv = new ModelAndView("/customer/list");

        mv.addObject("activePage", "customers");
        List<Customer> activeCustomers = customerRepository.findActiveCustomer();

        mv.addObject("customerList", activeCustomers);
        return  mv;
    }

    @GetMapping("/customer/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id){
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            ModelAndView mv = new ModelAndView("/customer/register");
            mv.addObject("customer", customer.get());
            return mv;
        }
        return listActiveCustomer(); // Se o funcionário não for encontrado
    }

    @GetMapping("/customer/delete/{id}")
    public ModelAndView deleteActivateState(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isPresent()) {
            customerRepository.updateCustomerStatus(id, false);  // Desativa o estado (false)
            redirectAttributes.addFlashAttribute("message", "Cliente marcado como inativo com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Cliente não encontrado!");
        }

        return listActiveCustomer();
    }


    @PostMapping("/customer/save")
    public ModelAndView save (@Valid Customer customer, BindingResult result){
        if(result.hasErrors()){
            return registerCustomer(customer);
        }
        customerRepository.save(customer);
        return new ModelAndView("redirect:/customers");
    }
}

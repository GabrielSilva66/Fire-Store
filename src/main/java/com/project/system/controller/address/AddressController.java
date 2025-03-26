package com.project.system.controller.address;

import com.project.system.models.*;
import com.project.system.repositories.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SupplierRepository supplierRepository;

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
    public ModelAndView editAddress(
            @PathVariable("entityType") String entityType,
            @PathVariable("entityId") Long entityId) {

        Address address = getAddressFromEntity(entityType, entityId) != null
                ? getAddressFromEntity(entityType, entityId)
                : new Address();

        ModelAndView mv = new ModelAndView("/address/register");
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

        Address savedAddress = addressRepository.save(address);

        switch (entityType.toUpperCase()) {
            case "EMPLOYEE" -> {
                Employee employee = employeeRepository.findById(entityId)
                        .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
                employee.setAddress(savedAddress);
                employeeRepository.save(employee);
            }
            case "CUSTOMER" -> {
                Customer customer = customerRepository.findById(entityId)
                        .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
                customer.setAddress(savedAddress);
                customerRepository.save(customer);
            }
            case "SUPPLIER" -> {
                Supplier supplier = supplierRepository.findById(entityId)
                        .orElseThrow(() -> new IllegalArgumentException("Supplier not found"));
                supplier.setAddress(savedAddress);
                supplierRepository.save(supplier);
            }
            default -> throw new IllegalArgumentException("Invalid entity type");
        }

        // Obtém o URL da página anterior (referer)
//        String referer = request.getHeader("Referer");
//
//        // Verifica se o cabeçalho Referer está presente, caso contrário, redireciona para uma página padrão
//        if (referer != null) {
//            return new ModelAndView("redirect:/" + referer);
//        }
        return new ModelAndView("redirect:/system");
    }



    private Address getAddressFromEntity(String entityType, Long entityId) {
        return switch (entityType.toUpperCase()) {
            case "EMPLOYEE" -> employeeRepository.findById(entityId)
                    .orElseThrow(() -> new IllegalArgumentException("Employee not found"))
                    .getAddress();
            case "CUSTOMER" -> customerRepository.findById(entityId)
                    .orElseThrow(() -> new IllegalArgumentException("Customer not found"))
                    .getAddress();
            case "SUPPLIER" -> supplierRepository.findById(entityId)
                    .orElseThrow(() -> new IllegalArgumentException("Supplier not found"))
                    .getAddress();
            default -> throw new IllegalArgumentException("Invalid entity type");
        };
    }

}

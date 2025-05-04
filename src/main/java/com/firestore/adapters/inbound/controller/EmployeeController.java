package com.firestore.adapters.inbound.controller;

import com.firestore.domain.employee.Employee;
import com.firestore.application.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/employee/register")
    public ModelAndView registerEmployee(Employee employee) {
        return new ModelAndView("/employee/register")
                .addObject("employee", employee);
    }

    @GetMapping("/employees")
    public ModelAndView listActiveEmployee() {
        List<Employee> activeEmployees = employeeService.findActiveEmployees();
        return new ModelAndView("/employee/list")
                .addObject("activePage", "employees")
                .addObject("employeeList", activeEmployees);
    }

    @GetMapping("/employee/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        Employee employee = employeeService.findById(id);
        if (employee != null) {
            return new ModelAndView("/employee/register")
                    .addObject("employee", employee);
        }
        return listActiveEmployee();
    }

    @GetMapping("/employee/delete/{id}")
    public ModelAndView deleteActivateState(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Employee employee = employeeService.findById(id);

        if (employee != null) {
            employeeService.deactivateEmployee(id);
            redirectAttributes.addFlashAttribute("message", "Funcionário marcado como inativo com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Funcionário não encontrado!");
        }

        return new ModelAndView("redirect:/employees");
    }

    @PutMapping("/employee/edit/{id}")
    public ModelAndView update(@PathVariable("id") Long id) {
        Employee employee = employeeService.findById(id);

        if (employee != null) {
            return new ModelAndView("/employee/register")
                    .addObject("employee", employee);
        }
//        employeeService.update(employee);

        return listActiveEmployee();
    }

    @PostMapping("/employee/save")
    public ModelAndView save(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("/employee/register").addObject("employee", employee);

        }

        employeeService.save(employee);
        return new ModelAndView("redirect:/employees");
    }
}

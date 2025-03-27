package com.project.system.controller;


import com.project.system.models.Employee;
import com.project.system.repositories.EmployeeRepository;
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
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employee/register")
    public ModelAndView registerEmployee(Employee employee) {
        ModelAndView mv = new ModelAndView("/employee/register");
        return mv.addObject("employee", employee);
    }


    @GetMapping("/employees")
    public ModelAndView listActiveEmployee() {
        ModelAndView mv = new ModelAndView("/employee/list");

        mv.addObject("activePage", "employees");
        List<Employee> activeEmployees = employeeRepository.findActiveEmployee();

        mv.addObject("employeeList", activeEmployees);
        return mv;
    }

    @GetMapping("/employee/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            ModelAndView mv = new ModelAndView("/employee/register");
            mv.addObject("employee", employee.get());
            return mv;
        } else {
            return listActiveEmployee(); // Se o funcionário não for encontrado
        }
    }

    @GetMapping("/employee/delete/{id}")
    public ModelAndView deleteActivateState(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {
            employeeRepository.updateEmployeeStatus(id, false);
            redirectAttributes.addFlashAttribute("message", "Funcionario marcado como inativo com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Funcionario não encontrado!");
        }

        return listActiveEmployee();
    }


    @PostMapping("/employee/save")
    public ModelAndView save(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return registerEmployee(employee);
        }

        if (employee.getId() != null) {
            Optional<Employee> existingEmployee = employeeRepository.findById(employee.getId());
            if (existingEmployee.isPresent()) {
                // Atualiza o funcionário existente
                Employee employeeToUpdate = existingEmployee.get();
                employeeToUpdate.setName(employee.getName());
                employeeToUpdate.setEmail(employee.getEmail());
                employeeToUpdate.setRole(employee.getRole());

                employeeRepository.save(employeeToUpdate);
            } else {
                employeeRepository.save(employee);
            }
        } else {
            employeeRepository.save(employee);
        }

        return new ModelAndView("redirect:/employees");
    }
}
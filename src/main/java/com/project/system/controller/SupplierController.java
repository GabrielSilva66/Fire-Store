package com.project.system.controller;


import com.project.system.models.Supplier;
import com.project.system.repositories.SupplierRepository;
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
public class SupplierController {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierController(SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }

    @GetMapping("/supplier/register")
    public ModelAndView registerSupplier(Supplier supplier){
        ModelAndView mv = new ModelAndView("/supplier/register");
        return  mv.addObject("supplier", supplier);
    }


    @GetMapping("/suppliers")
    public ModelAndView listActiveSupplier(){
        ModelAndView mv = new ModelAndView("/supplier/list");

        mv.addObject("activePage", "suppliers");
        List<Supplier> activeSuppliers= supplierRepository.findActiveSupplier();

        mv.addObject("supplierList", activeSuppliers);
        return  mv;
    }

    @GetMapping("/supplier/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id){
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if (supplier.isPresent()) {
            ModelAndView mv = new ModelAndView("/supplier/register");
            mv.addObject("supplier", supplier.get());
            return mv;
        } else {
            return listActiveSupplier();
        }
    }

    @GetMapping("/supplier/delete/{id}")
    public ModelAndView deleteActivateState(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<Supplier> supplier = supplierRepository.findById(id);

        if (supplier.isPresent()) {
            supplierRepository.updateSupplierStatus(id, false);  // Desativa o estado (false)
            redirectAttributes.addFlashAttribute("message", "Fornecedor marcado como inativo com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Fornecedor não encontrado!");
        }

        return listActiveSupplier();
    }


    @PostMapping("/supplier/save")
    public ModelAndView save (@Valid Supplier supplier, BindingResult result){
        if(result.hasErrors()){
            return registerSupplier(supplier);
        }
        supplierRepository.save(supplier);
        return new ModelAndView("redirect:/suppliers");
    }
}

package com.firestore.adapters.inbound.controller;
import com.firestore.domain.supplier.Supplier;
import com.firestore.application.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/supplier/register")
    public ModelAndView registerSupplier(Supplier supplier) {
        ModelAndView mv = new ModelAndView("/supplier/register");
        return mv.addObject("supplier", supplier);
    }

    @GetMapping("/suppliers")
    public ModelAndView listActiveSupplier() {
        ModelAndView mv = new ModelAndView("/supplier/list");
        mv.addObject("activePage", "suppliers");
        mv.addObject("supplierList", supplierService.findAllActiveSuppliers());
        return mv;
    }

    @GetMapping("/supplier/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        Supplier supplier = supplierService.findById(id);

        if (supplier != null) {
            ModelAndView mv = new ModelAndView("/supplier/register");
            mv.addObject("supplier", supplier);
            return mv;
        }

        return listActiveSupplier();
    }


    @GetMapping("/supplier/delete/{id}")
    public ModelAndView deleteActivateState(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        if (supplierService.deactivateSupplier(id)) {
            redirectAttributes.addFlashAttribute("message", "Fornecedor marcado como inativo com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Fornecedor não encontrado!");
        }
        return listActiveSupplier();
    }

    @PostMapping("/supplier/save")
    public ModelAndView save(@Valid Supplier supplier, BindingResult result) {
        if (result.hasErrors()) {
            return registerSupplier(supplier);
        }
        supplierService.saveSupplier(supplier);
        return new ModelAndView("redirect:/suppliers");
    }
}

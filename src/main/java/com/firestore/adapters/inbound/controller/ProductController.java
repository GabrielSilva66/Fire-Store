package com.firestore.adapters.inbound.controller;

import com.firestore.domain.product.Product;
import com.firestore.application.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/product/register")
    public ModelAndView registerProduct(Product product){
        ModelAndView mv = new ModelAndView("/product/register");
        return mv.addObject("product", product);
    }

    @GetMapping("/products")
    public ModelAndView listProduct(){
        ModelAndView mv = new ModelAndView("/product/list");
        mv.addObject("activePage", "products");
        mv.addObject("productList", productService.findAll());
        return mv;
    }

    @GetMapping("/product/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id){
        Product product = productService.findById(id);
        if (product != null) {
            ModelAndView mv = new ModelAndView("/product/register");
            mv.addObject("product", product);
            return mv;
        } else {
            return listProduct();
        }
    }

    @GetMapping("/product/delete/{id}")
    public ModelAndView deleteActivateState(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Product product = productService.findById(id);
        if (product != null) {
            productService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Deleted product!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Product not found!");
        }
        return listProduct();
    }

    @PostMapping("/product/save")
    public ModelAndView save(@Valid Product product, BindingResult result){
        if(result.hasErrors()){
            return registerProduct(product);
        }
        productService.save(product);
        return new ModelAndView("redirect:/products");
    }
}

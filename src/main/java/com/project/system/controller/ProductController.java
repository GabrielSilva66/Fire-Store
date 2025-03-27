package com.project.system.controller;

import com.project.system.models.Product;
import com.project.system.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Controller
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }



    @GetMapping("/product/register")
    public ModelAndView registerProduct(Product product){
        ModelAndView mv = new ModelAndView("/product/register");
        return  mv.addObject("product", product);
    }



    @GetMapping("/products")
    public ModelAndView listProduct(){
        ModelAndView mv = new ModelAndView("/product/list");

        mv.addObject("activePage", "products");
        List<Product> products = productRepository.findAll();

        mv.addObject("productList", products);
        return  mv;
    }

    @GetMapping("/product/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id){
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            ModelAndView mv = new ModelAndView("/product/register");
            mv.addObject("product", product.get());
            return mv;
        } else {
            return listProduct();
        }
    }

    @GetMapping("/product/delete/{id}")
    public ModelAndView deleteActivateState(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            productRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Deleted product!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Product not found!");
        }

        return listProduct();
    }


    @PostMapping("/product/save")
    public ModelAndView save (@Valid Product product, BindingResult result){
        if(result.hasErrors()){
            return registerProduct(product);
        }
        product.setCostPrice(new BigDecimal(product.getCostPrice().toString().replace(",", ".")));

        productRepository.save(product);
        return new ModelAndView("redirect:/products");
    }
}

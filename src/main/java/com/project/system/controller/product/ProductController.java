package com.project.system.controller.product;

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

import java.util.List;
import java.util.Optional;


@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

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
            return listProduct(); // Se o funcionário não for encontrado
        }
    }

    @GetMapping("/product/delete/{id}")
    public ModelAndView deleteActivateState(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            productRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Produto deletado!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Produto não encontrado!");
        }

        return listProduct();
    }


    @PostMapping("/product/save")
    public ModelAndView save (@Valid Product product, BindingResult result){
        if(result.hasErrors()){
            return registerProduct(product);
        }
        productRepository.save(product);
        return new ModelAndView("redirect:/products");
    }
}

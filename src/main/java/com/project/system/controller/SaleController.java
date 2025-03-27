package com.project.system.controller;


import com.project.system.models.*;
import com.project.system.repositories.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SaleController {

    private final SaleRepository saleRepository;
    private final ItemSaleRepository itemSaleRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public  SaleController(SaleRepository saleRepository, ItemSaleRepository itemSaleRepository,
                           EmployeeRepository employeeRepository, CustomerRepository customerRepository,
                           ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.employeeRepository = employeeRepository;
        this.itemSaleRepository = itemSaleRepository;
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;

    }


    @GetMapping("/sale/register")
    public ModelAndView registerSale(Sale sale)  {
        ModelAndView mv = new ModelAndView("/sale/register");
        List<Employee> employees = employeeRepository.findAll();
        List<Customer> customers =customerRepository.findAll();
        mv.addObject("employees", employees);
        mv.addObject("customers", customers);
        mv.addObject("products", productRepository.findAll());
        return mv.addObject("sale", sale);

    }

    @GetMapping("/sales")
    public ModelAndView listSales(){
        ModelAndView mv = new ModelAndView("/sale/list");

        mv.addObject("activePage", "sales");
        List<Sale> sales = saleRepository.findAll();
        mv.addObject("saleList", sales);

        return mv;
    }

    @GetMapping("/sale/products/{id}")
    public ModelAndView listProductsBySale(@PathVariable("id") Long id){
        List<Product> products = itemSaleRepository.findBySaleId(id).stream().map(ItemSale::getProduct).toList();

        ModelAndView mv = new ModelAndView("/sale/products");
        mv.addObject("productList", products);

        return  mv;
    }


    @PostMapping("/venda/save")
    public ModelAndView save(@Valid Sale sale, BindingResult result){
        if (result.hasErrors()){
            return registerSale(sale);
        }

        saleRepository.save(sale);
        return new ModelAndView("redirect:/vendas");
    }

}

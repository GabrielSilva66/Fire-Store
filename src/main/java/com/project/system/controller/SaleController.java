package com.project.system.controller;


import com.project.system.dto.ItemDTO;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class SaleController {

    private final SaleRepository saleRepository;
    private final ItemSaleRepository itemSaleRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Autowired
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
        mv.addObject("sales", sales);

        return mv;
    }

    @GetMapping("/sale/products/{id}")
    public ModelAndView listProductsBySale(@PathVariable("id") Long id){

        List<ItemSale> itemSales = itemSaleRepository.findBySaleId(id);

        List<ItemDTO> saleItems = itemSales.stream().map
                (itemSale -> new ItemDTO(itemSale.getProduct(), itemSale.getQuantity(),
                        itemSale.getValue())).collect(Collectors.toList());


        ModelAndView mv = new ModelAndView("/sale/products");
        mv.addObject("saleItems", saleItems);

        return  mv;
    }



    @PostMapping("/sale/save")
    public ModelAndView save(@Valid Sale sale, BindingResult result,
                             @RequestParam List<Long> productIds,
                             @RequestParam List<Integer> quantities) {
        if (result.hasErrors()) {
            return registerSale(sale);
        }

        saleRepository.save(sale);

        for (int i = 0; i < productIds.size(); i++) {
            Long productId = productIds.get(i);
            Integer quantity = quantities.get(i);
            Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

            ItemSale itemSale = new ItemSale();
            itemSale.setSale(sale);
            itemSale.setProduct(product);
            itemSale.setQuantity(quantity);
            itemSale.setValue(product.getSalePrice().multiply(BigDecimal.valueOf(quantity)).doubleValue()); // Valor total

            // Salvar o item de venda
            itemSaleRepository.save(itemSale);
        }

        return new ModelAndView("redirect:/sales");
    }


}

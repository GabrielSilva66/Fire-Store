package com.firestore.adapters.inbound.controller;


import com.firestore.adapters.outbound.repositories.JpaCustomerRepository;
import com.firestore.adapters.outbound.repositories.JpaEmployeeRepository;
import com.firestore.adapters.outbound.repositories.JpaProductRepository;
import com.firestore.domain.sale.Sale;
import com.firestore.dto.ItemDTO;
import com.firestore.application.service.SaleService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;


@Controller
public class SaleController {

    private final SaleService saleService;
    private final JpaEmployeeRepository employeeRepository;
    private final JpaCustomerRepository customerRepository;
    private final JpaProductRepository productRepository;

    public SaleController(SaleService saleService, JpaEmployeeRepository employeeRepository,
                          JpaCustomerRepository customerRepository, JpaProductRepository productRepository) {
        this.saleService = saleService;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/sale/register")
    public ModelAndView registerSale(Sale sale) {
        ModelAndView mv = new ModelAndView("/sale/register");
        mv.addObject("employees", employeeRepository.findAll());
        mv.addObject("customers", customerRepository.findAll());
        mv.addObject("products", productRepository.findAll());
        return mv.addObject("sale", sale);
    }

    @GetMapping("/sales")
    public ModelAndView listSales() {
        ModelAndView mv = new ModelAndView("/sale/list");
        mv.addObject("activePage", "sales");
        mv.addObject("sales", saleService.findAllSales());
        return mv;
    }

    @GetMapping("/sale/products/{id}")
    public ModelAndView listProductsBySale(@PathVariable("id") Long id) {
        List<ItemDTO> saleItems = saleService.findItemsBySaleId(id).stream().map(
                itemSale -> new ItemDTO(itemSale.getProduct(), itemSale.getQuantity(), itemSale.getValue())
        ).collect(Collectors.toList());

        ModelAndView mv = new ModelAndView("/sale/products");
        mv.addObject("saleItems", saleItems);
        return mv;
    }

    @PostMapping("/sale/save")
    public ModelAndView save(@Valid Sale sale, BindingResult result,
                             @RequestParam List<Long> productIds,
                             @RequestParam List<Integer> quantities) {
        if (result.hasErrors()) {
            return registerSale(sale);
        }

        saleService.saveSale(sale, productIds, quantities);
        return new ModelAndView("redirect:/sales");
    }
}


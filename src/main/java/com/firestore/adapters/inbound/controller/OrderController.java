package com.firestore.adapters.inbound.controller;
import com.firestore.adapters.outbound.repositories.JpaEmployeeRepository;
import com.firestore.adapters.outbound.repositories.JpaProductRepository;
import com.firestore.adapters.outbound.repositories.JpaSupplierRepository;
import com.firestore.domain.itemOrder.ItemOrder;
import com.firestore.domain.order.Order;
import com.firestore.dto.ItemDTO;
import com.firestore.application.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OrderController {

    private final OrderService orderService;
    private final JpaEmployeeRepository employeeRepository;
    private final JpaSupplierRepository supplierRepository;
    private final JpaProductRepository productRepository;

    @Autowired
    public OrderController(OrderService orderService,
                           JpaEmployeeRepository employeeRepository,
                           JpaSupplierRepository supplierRepository,
                           JpaProductRepository productRepository) {
        this.orderService = orderService;
        this.employeeRepository = employeeRepository;
        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/order/register")
    public ModelAndView registerOrder(Order order) {
        ModelAndView mv = new ModelAndView("/order/register");
        mv.addObject("employees", employeeRepository.findAll());
        mv.addObject("suppliers", supplierRepository.findAll());
        mv.addObject("products", productRepository.findAll());
        mv.addObject("order", order);
        return mv;
    }

    @GetMapping("/orders")
    public ModelAndView listOrders() {
        ModelAndView mv = new ModelAndView("/order/list");
        mv.addObject("activePage", "orders");
        mv.addObject("orders", orderService.findAllOrders());
        return mv;
    }

    @GetMapping("/order/products/{id}")
    public ModelAndView listProductsByOrder(@PathVariable("id") Long id) {
        List<ItemOrder> itemOrders = orderService.getItemsByOrderId(id);
        List<ItemDTO> orderItems = itemOrders.stream().map(
                item -> new ItemDTO(item.getProduct(), item.getQuantity(), item.getValue())
        ).collect(Collectors.toList());

        ModelAndView mv = new ModelAndView("/order/products");
        mv.addObject("orderItems", orderItems);
        return mv;
    }

    @PostMapping("/order/save")
    public ModelAndView save(@Valid Order order, BindingResult result,
                             @RequestParam List<Long> productIds,
                             @RequestParam List<Integer> quantities) {
        if (result.hasErrors()) {
            return registerOrder(order);
        }

        orderService.saveOrderWithItems(order, productIds, quantities);
        return new ModelAndView("redirect:/orders");
    }
}

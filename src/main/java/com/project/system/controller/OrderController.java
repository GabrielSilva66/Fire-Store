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
public class OrderController {

    private final OrderRepository orderRepository;
    private final ItemOrderRepository itemOrderRepository;
    private final EmployeeRepository employeeRepository;
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;

    @Autowired
    public  OrderController(OrderRepository orderRepository, ItemOrderRepository itemOrderRepository,
                           EmployeeRepository employeeRepository, SupplierRepository supplierRepository,
                           ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.employeeRepository = employeeRepository;
        this.itemOrderRepository = itemOrderRepository;
        this.orderRepository = orderRepository;
        this.supplierRepository = supplierRepository;

    }


    @GetMapping("/order/register")
    public ModelAndView registerOrder(Order order)  {
        ModelAndView mv = new ModelAndView("/order/register");
        List<Employee> employees = employeeRepository.findAll();
        List<Supplier> suppliers =supplierRepository.findAll();
        mv.addObject("employees", employees);
        mv.addObject("suppliers", suppliers);
        mv.addObject("products", productRepository.findAll());
        
        return mv.addObject("order", order);

    }

    @GetMapping("/orders")
    public ModelAndView listOrders(){
        ModelAndView mv = new ModelAndView("/order/list");

        mv.addObject("activePage", "orders");
        List<Order> orders = orderRepository.findAll();
        mv.addObject("orders", orders);

        return mv;
    }

    @GetMapping("/order/products/{id}")
    public ModelAndView listProductsByOrder(@PathVariable("id") Long id){

        List<ItemOrder> itemOrders = itemOrderRepository.findByOrderId(id);

        List<ItemDTO> orderItems = itemOrders.stream().map
                (itemOrder -> new ItemDTO(itemOrder.getProduct(), itemOrder.getQuantity(),
                        itemOrder.getValue())).collect(Collectors.toList());


        ModelAndView mv = new ModelAndView("/order/products");
        mv.addObject("orderItems", orderItems);

        return  mv;
    }



    @PostMapping("/order/save")
    public ModelAndView save(@Valid Order order, BindingResult result,
                             @RequestParam List<Long> productIds,
                             @RequestParam List<Integer> quantities) {
        if (result.hasErrors()) {
            return registerOrder(order);
        }

        orderRepository.save(order);

        for (int i = 0; i < productIds.size(); i++) {
            Long productId = productIds.get(i);
            Integer quantity = quantities.get(i);
            Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

            ItemOrder itemOrder = new ItemOrder();
            itemOrder.setOrder(order);
            itemOrder.setProduct(product);
            itemOrder.setQuantity(quantity);
            itemOrder.setValue(product.getSalePrice().multiply(BigDecimal.valueOf(quantity)).doubleValue());

            // Salvar o item de venda
            itemOrderRepository.save(itemOrder);
        }

        return new ModelAndView("redirect:/orders");
    }


}

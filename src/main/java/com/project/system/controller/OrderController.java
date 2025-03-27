package com.project.system.controller;

import com.project.system.models.Order;
import com.project.system.models.ItemOrder;
import com.project.system.repositories.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {

    private final OrderRepository orderRepository;
    private final ItemOrderRepository itemOrderRepositoy;
    private final ProductRepository productRepository;
    private final EmployeeRepository employeeRepository;
    private final SupplierRepository supplierRepository;

    private List<ItemOrder> listOrder = new ArrayList<>();

    @Autowired
    public OrderController(OrderRepository orderRepository, ItemOrderRepository itemOrderRepository,
                           ProductRepository productRepository, EmployeeRepository employeeRepository,
                           SupplierRepository  supplierRepository){

        this.orderRepository = orderRepository;
        this.itemOrderRepositoy = itemOrderRepository;
        this.employeeRepository = employeeRepository;
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;

    }



    @GetMapping("/order/register")
    public ModelAndView registerOrder(Order order, ItemOrder itemOrder){
        ModelAndView mv = new ModelAndView("/order/register");
        mv.addObject("order", order);
        mv.addObject("itemOrder", itemOrder);
        mv.addObject("listEmployee", employeeRepository.findAll());
        mv.addObject("listSupplier", supplierRepository.findAll());
        mv.addObject("listProduct", productRepository.findAll());
        mv.addObject("listOrder", listOrder);
        return  mv;
    }

    @GetMapping("/orders")
    public ModelAndView listOrders(){
        List<Order> orders = orderRepository.findAll();
        ModelAndView mv = new ModelAndView("/order/list");

        mv.addObject("listOrders", orders);
        return  mv;
    }


    @GetMapping("/order/edit/{id}")
    public ModelAndView editOrder(@PathVariable("id") Long id){
        Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent()){
            ModelAndView mv = new ModelAndView("/order/register");
            mv.addObject("order", order.get());
            return mv;
        }
        return listOrders();
    }


    @GetMapping("/order/delete/{id}")
    public ModelAndView deleteOrder(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent()){
            orderRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Order deleted");
        } else{
            redirectAttributes.addFlashAttribute("error", "Fail deleted order");
        }
        return listOrders();
    }


    @PostMapping("order/save")
    public  ModelAndView saveOrder(@Valid Order order, ItemOrder itemOrder, BindingResult result){
        if(result.hasErrors()){
            return registerOrder(order, itemOrder);
        }
        orderRepository.save(order);
        return new ModelAndView("redirect:/orders");

    }




}

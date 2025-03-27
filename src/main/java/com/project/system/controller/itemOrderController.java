package com.project.system.controller;

import com.project.system.models.ItemOrder;
import com.project.system.models.Order;
import com.project.system.repositories.ItemOrderRepository;
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
public class itemOrderController {

    private final ItemOrderRepository itemOrderRepository;

    @Autowired
    public itemOrderController(ItemOrderRepository itemOrderRepository){
        this.itemOrderRepository = itemOrderRepository;
    }
    

    @GetMapping("/itemOrder/register")
    public ModelAndView registerItemOrder(ItemOrder itemOrder){
        ModelAndView mv = new ModelAndView("/itemOrder/register");
        return  mv.addObject("itemOrder", itemOrder);
    }

    @GetMapping("/itemOrders")
    public ModelAndView listItemOrders(){
        List<ItemOrder> itemOrders = itemOrderRepository.findAll();
        ModelAndView mv = new ModelAndView("/itemOrder/list");

        mv.addObject("listOrders", itemOrders);
        return  mv;
    }


    @GetMapping("/itemOrder/edit/{id}")
    public ModelAndView editItemOrder(@PathVariable("id") Long id){
        Optional<ItemOrder> itemOrder = itemOrderRepository.findById(id);

        if (itemOrder.isPresent()){
            ModelAndView mv = new ModelAndView("/itemOrder/register");
            mv.addObject("itemOrder", itemOrder.get());
            return mv;
        }
        return listItemOrders();
    }


    @GetMapping("/itemOrders/delete/{id}")
    public ModelAndView deleteOrder(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        Optional<ItemOrder> itemOrder = itemOrderRepository.findById(id);

        if (itemOrder.isPresent()){
            itemOrderRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Item Order deleted");
        } else{
            redirectAttributes.addFlashAttribute("error", "Fail deleted item Order");
        }
        return listItemOrders();
    }


    @PostMapping("itemOrder/save")
    public  ModelAndView saveOrder(@Valid ItemOrder itemOrder, BindingResult result){
        if(result.hasErrors()){
            return registerItemOrder(itemOrder);
        }
        itemOrderRepository.save(itemOrder);
        return new ModelAndView("redirect:/itemOrders");
    }


}

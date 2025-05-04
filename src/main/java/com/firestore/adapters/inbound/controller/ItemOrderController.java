package com.firestore.adapters.inbound.controller;

import com.firestore.domain.itemOrder.ItemOrder;
import com.firestore.application.service.ItemOrderService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class ItemOrderController {

    private final ItemOrderService itemOrderService;

    public ItemOrderController(ItemOrderService itemOrderService){
        this.itemOrderService = itemOrderService;
    }

    @GetMapping("/itemOrder/register")
    public ModelAndView registerItemOrder(ItemOrder itemOrder){
        return new ModelAndView("/itemOrder/register").addObject("itemOrder", itemOrder);
    }

    @GetMapping("/itemOrders")
    public ModelAndView listItemOrders(){
        List<ItemOrder> itemOrders = itemOrderService.findAll();
        return new ModelAndView("/itemOrder/list").addObject("listOrders", itemOrders);
    }

    @GetMapping("/itemOrder/edit/{id}")
    public ModelAndView editItemOrder(@PathVariable("id") Long id){
        ItemOrder itemOrder = itemOrderService.findById(id);

        if (itemOrder != null) {
            return new ModelAndView("/itemOrder/register")
                    .addObject("itemOrder", itemOrder);
        }

        return listItemOrders();
    }

    @GetMapping("/itemOrders/delete/{id}")
    public ModelAndView deleteOrder(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        ItemOrder itemOrder = itemOrderService.findById(id);

        if (itemOrder != null) {
            itemOrderService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Item Order deleted");
        } else {
            redirectAttributes.addFlashAttribute("error", "Fail deleting item Order");
        }

        return new ModelAndView("redirect:/itemOrders");
    }

    @PostMapping("/itemOrder/save")
    public ModelAndView saveOrder(@Valid ItemOrder itemOrder, BindingResult result){
        if(result.hasErrors()){
            return registerItemOrder(itemOrder);
        }
        itemOrderService.save(itemOrder);
        return new ModelAndView("redirect:/itemOrders");
    }
}

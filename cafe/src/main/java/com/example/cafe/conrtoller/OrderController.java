package com.example.cafe.conrtoller;

import com.example.cafe.entity.Coworker;
import com.example.cafe.entity.Menu;
import com.example.cafe.entity.Order;
import com.example.cafe.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/order")
    @PreAuthorize("hasAuthority('developers:write')")
    public String findAll(Model model) {
        List<Order> orderList = orderService.findAllOrders();
        model.addAttribute("order", orderList);
        return "order/order-list";
    }

    @GetMapping("/actual-order")
    @PreAuthorize("hasAuthority('developers:read')")
    public String findAllActualOrder(Model model) {
        List<Order> orderList = orderService.findAllActualOrder(LocalDate.now().toString());
        model.addAttribute("order", orderList);
        return "order/actual-order";
    }

    @GetMapping("/order-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String createForm(Model model) {
        List<Coworker> coworkers = orderService.findAllCoworkers();
        List<Menu> menus = orderService.findAllMenus();
        model.addAttribute("coworker", coworkers);
        model.addAttribute("menu", menus);
        return "order/order-create";
    }

    @PostMapping("/order-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String create(@RequestParam String name,
                         @RequestParam String arrayNameDish,
                         @RequestParam String date,
                         @RequestParam String time,
                         @RequestParam int numberTable,
                         @RequestParam int count) {
        String[] getNameDishByDate = arrayNameDish.split("\\|");
        orderService.save(name, getNameDishByDate[0], getNameDishByDate[1], date, time, numberTable, count);
        return "redirect:/order";
    }

    @GetMapping("/order-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updateForm(@PathVariable Long id, Model model) {
        List<Coworker> coworkers = orderService.findAllCoworkers();
        List<Menu> menus = orderService.findAllMenus();

        if (orderService.orderList(id).isEmpty()) {
            return "redirect:/order";
        }
        model.addAttribute("coworker", coworkers);
        model.addAttribute("menu", menus);
        model.addAttribute("order", orderService.orderList(id));
        return "/order/order-update";
    }

    @PostMapping("/order-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam String arrayNameDish,
                         @RequestParam String date,
                         @RequestParam String time,
                         @RequestParam int numberTable,
                         @RequestParam int count) {
        String[] getNameDishByDate = arrayNameDish.split("\\|");
        orderService.update(id, name, getNameDishByDate[0], getNameDishByDate[1], date, time, numberTable, count);
        return "redirect:/order";
    }

    @GetMapping("/order-delete/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String delete(@PathVariable Long id) {
        orderService.delete(id);
        return "redirect:/order";
    }
}

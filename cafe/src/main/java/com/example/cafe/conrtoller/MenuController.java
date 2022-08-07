package com.example.cafe.conrtoller;

import com.example.cafe.entity.Dish;
import com.example.cafe.entity.Menu;
import com.example.cafe.export.MenuExcelExporter;
import com.example.cafe.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;


    @GetMapping("/menu")
    @PreAuthorize("hasAuthority('developers:write')")
    public String findAll(Model model) {
        List<Menu> menuList = menuService.findAll();
        model.addAttribute("menu", menuList);
        return "menu/menu-list";
    }

    @GetMapping("/actual-menu")
    @PreAuthorize("hasAuthority('developers:read')")
    public String findAllActualMenu(Model model) {
        List<Menu> menuList = menuService.findActualMenu(LocalDate.now().toString());
        model.addAttribute("menu", menuList);
        return "menu/menu-actual";
    }

    @GetMapping("/menu-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String createForm(Model model) {
        List<Dish> dishes = menuService.findAllDishes();
        model.addAttribute("dish", dishes);
        return "menu/menu-create";
    }

    @PostMapping("/menu-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String create(@RequestParam String name,
                         @RequestParam Double price,
                         @RequestParam String date) {
        menuService.save(name, price, date);
        return "redirect:/menu";
    }

    @GetMapping("/menu-delete/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String delete(@PathVariable long id) {
        menuService.delete(id);
        return "redirect:/menu";
    }

    @GetMapping("/menu-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updateForm(@PathVariable long id, Model model) {

        List<Dish> dishes = menuService.findAllDishes();
        if (menuService.menuList(id).isEmpty()) {
            return "redirect:/menu";
        }
        model.addAttribute("menu", menuService.menuList(id));
        model.addAttribute("dish", dishes);
        return "menu/menu-update";
    }

    @PostMapping("/menu-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String update(@PathVariable long id,
                         @RequestParam String name,
                         @RequestParam Double price,
                         @RequestParam String date) {
        menuService.update(id, name, price, date);
        return "redirect:/menu";
    }

    @GetMapping("/menu/export")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = dateFormatter.format(new Date());
        String fileName = "menu_" + currentDateTime + ".xlsx";
        String headerValue = "attachment; filename=" + fileName;

        response.setHeader(headerKey, headerValue);

        List<Menu> menuList = menuService.findAll();

        MenuExcelExporter excelExporter = new MenuExcelExporter(menuList);
        excelExporter.export(response);
    }
}

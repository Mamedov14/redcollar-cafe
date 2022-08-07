package com.example.cafe.conrtoller;

import com.example.cafe.entity.Position;
import com.example.cafe.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;


    @GetMapping("/position")
    @PreAuthorize("hasAuthority('developers:write')")
    public String findAll(Model model) {
        List<Position> positions = positionService.findAll();
        model.addAttribute("position", positions);
        return "position/position-list";
    }

    @GetMapping("/position-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String createPositionForm() {
        return "position/position-create";
    }

    @PostMapping("/position-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String createPosition(@RequestParam String name) {
        positionService.savePosition(name);
        return "redirect:/position";
    }

    @GetMapping("/position-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updatePositionForm(@PathVariable long id, Model model) {
        if (positionService.positionList(id).isEmpty()) {
            return "redirect:/position";
        }
        model.addAttribute("position", positionService.positionList(id));
        return "position/position-update";
    }

    @PostMapping("/position-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updatePosition(@PathVariable long id, @RequestParam String name, Model model) {
        positionService.updatePosition(id, name);
        return "redirect:/position";
    }

    @GetMapping("/page-400")
    private String getPage400() {
        return "error/page-400";
    }

    @GetMapping("/page-403")
    private String getPage403() {
        return "error/page-403";
    }

    @GetMapping("/position-delete/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String positionDelete(@PathVariable long id) {
        try{
            positionService.delete(id);
            return "redirect:/position";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/page-403";
        }

    }

}

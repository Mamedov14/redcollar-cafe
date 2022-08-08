package com.example.cafe.conrtoller;

import com.example.cafe.entity.Coworker;
import com.example.cafe.entity.Position;
import com.example.cafe.service.CoworkerService;
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
public class CoworkerController {

    private final CoworkerService coworkerService;

    @GetMapping("/coworker")
    @PreAuthorize("hasAuthority('developers:write')")
    public String findAll(Model model) {
        List<Coworker> coworkers = coworkerService.findAll();
        model.addAttribute("coworker", coworkers);
        return "coworker/coworker-list";
    }

    @GetMapping("/coworker-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String createCoworkerForm(Model model) {
        List<Position> positions = coworkerService.getPositionList();
        model.addAttribute("pos", positions);
        return "coworker/coworker-create";
    }


    @PostMapping("/coworker-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String createCoworker(@RequestParam String fio,
                                 @RequestParam String title) {
        coworkerService.saveCoworker(fio, title);
        return "redirect:/coworker";
    }

    @GetMapping("/coworker-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updateCoworkerForm(@PathVariable Long id, Model model) {
        List<Position> positions = coworkerService.getPositionList();

        if (coworkerService.coworkerList(id).isEmpty()) {
            return "redirect:/coworker";
        }
        model.addAttribute("coworkers", coworkerService.coworkerList(id));
        model.addAttribute("pos", positions);
        return "coworker/coworker-update";
    }

    @PostMapping("/coworker-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updateCoworker(@PathVariable Long id,
                                 @RequestParam String fio,
                                 @RequestParam String namePosition) {
        coworkerService.updateCoworker(id, fio, namePosition);
        return "redirect:/coworker";
    }

    @GetMapping("/coworker-delete/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String coworkerDelete(@PathVariable Long id, Model model) {
        coworkerService.deleteCoworker(id);
        return "redirect:/coworker";
    }

}

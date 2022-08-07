package com.example.cafe.conrtoller;

import com.example.cafe.entity.Contractor;
import com.example.cafe.service.ContractorService;
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
public class ContractorController {

    private final ContractorService contractorService;


    @GetMapping("/contractor")
    @PreAuthorize("hasAuthority('developers:write')")
    public String findAll(Model model) {
        List<Contractor> contractorList = contractorService.findAll();
        model.addAttribute("contractor", contractorList);
        return "contractor/contractor-list";
    }

    @GetMapping("/contractor-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String createForm() {
        return "contractor/contractor-create";
    }

    @PostMapping("/contractor-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String create(@RequestParam String name,
                         @RequestParam String address,
                         @RequestParam String phoneNumber) {
        contractorService.save(name, address, phoneNumber);
        return "redirect:/contractor";
    }

    @GetMapping("/contractor-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updateForm(@PathVariable Long id, Model model) {

        if (contractorService.contractorList(id).isEmpty()) {
            return "redirect:/contractor";
        }
        model.addAttribute("contractor", contractorService.contractorList(id));
        return "/contractor/contractor-update";
    }

    @PostMapping("/contractor-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam String address,
                         @RequestParam String phoneNumber) {
        contractorService.update(id, name, address, phoneNumber);
        return "redirect:/contractor";
    }

    @GetMapping("/contractor-delete/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String delete(@PathVariable Long id) {
        contractorService.delete(id);
        return "redirect:/contractor";
    }
}

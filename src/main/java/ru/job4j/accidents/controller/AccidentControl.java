package ru.job4j.accidents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.service.AccidenTypeService;
import ru.job4j.accidents.service.AccidentRuleService;
import ru.job4j.accidents.service.AccidentService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Controller
public class AccidentControl {
    private final AccidentService accidents;
    private final AccidenTypeService accidenTypeService;
    private final AccidentRuleService accidentRuleService;

    public AccidentControl(AccidentService accidents, AccidenTypeService accidenTypeService, AccidentRuleService accidentRuleService) {
        this.accidents = accidents;
        this.accidenTypeService = accidenTypeService;
        this.accidentRuleService = accidentRuleService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidenTypeService.getTypes());
        model.addAttribute("rules", accidentRuleService.getRules());
        return "/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, Model model, HttpServletRequest req) {
        int newAccid = accidents.save(accident, accident.getType().getId());
        if (req.getParameterValues("rIds") != null) {
            accidentRuleService.save(newAccid, getRules(req));
        }
        return "redirect:/index";
    }

    @GetMapping ("/formEdit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("accident", accidents.findById(id));
        model.addAttribute("types", accidenTypeService.getTypes());
        model.addAttribute("rules", accidentRuleService.getRules());
        return "edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, HttpServletRequest request) {
        accidents.update(accident, accident.getType().getId());
        if (request.getParameterValues("rIds") != null) {
            accidentRuleService.update(accident.getId(), getRules(request));
        }
        return "redirect:/index";
    }

    Set<Rule> getRules(HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        Set<Rule> list = new HashSet<>();
            for (String id : ids) {
                int idr = Integer.parseInt(id);
                list.add(accidentRuleService.getById(idr));
            }
            return list;
    }

    @GetMapping ("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        accidents.delete(id);
        return "redirect:/index";
    }
}

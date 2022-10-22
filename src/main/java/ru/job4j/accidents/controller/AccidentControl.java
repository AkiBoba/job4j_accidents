package ru.job4j.accidents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.service.AccidenTypeService;
import ru.job4j.accidents.service.AccidentRuleService;
import ru.job4j.accidents.service.AccidentService;

import javax.servlet.http.HttpServletRequest;
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
    public String save(@ModelAttribute Accident accident, HttpServletRequest request) {
        accident.setType(accidenTypeService.getById(accident.getType().getId()));
        if (request.getParameterValues("rIds") != null) {
            accident.setRules(getRules(request));
        }
        accidents.create(accident, accident.getType().getId());
        return "redirect:/index";
    }

    @GetMapping ("/formEdit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("accident", accidents.getById(id));
        model.addAttribute("types", accidenTypeService.getTypes());
        model.addAttribute("rules", accidentRuleService.getRules());
        return "edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, HttpServletRequest request) {
        accident.setType(accidenTypeService.getById(accident.getType().getId()));
        if (request.getParameterValues("rIds") != null) {
            accident.setRules(getRules(request));
        }
        accidents.update(accident);
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
        accidents.delete(accidents.getById(id));
        return "redirect:/index";
    }
}

package ru.agafonov.spring.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.agafonov.spring.library.dao.PersonDAO;
import ru.agafonov.spring.library.models.Person;
import ru.agafonov.spring.library.services.PeopleService;
import ru.agafonov.spring.library.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/person")
public class PersonController {

    private final PeopleService peopleService;
    private final PersonValidator personValidator;

    public PersonController(PeopleService peopleService, PersonValidator personValidator) {
        this.peopleService = peopleService;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", peopleService.findAll());
        return "person/index";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "person/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult){
        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors())
            return "person/new";

        peopleService.save(person);

        return "redirect:/person";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", peopleService.findOne(id));
        return "person/edit";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id){
        model.addAttribute("person", peopleService.findOne(id));

        //model.addAttribute("personBooks", personDAO.getPersonBook(id));

        return "person/show";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,BindingResult bindingResult,
                         @PathVariable("id") int id){

        if(bindingResult.hasErrors())
            return "person/edit";

        peopleService.update(id, person);
        return "redirect:/person";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        peopleService.delete(id);

        return "redirect:/person";
    }

}

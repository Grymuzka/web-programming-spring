package com.example.webprogrammingspring.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "authors/list";
    }
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("author", new Author());
        return "authors/form";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Author author) {
        authorService.save(author);
        return "redirect:/authors";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        authorService.deleteById(id);
        return "redirect:/authors";
    }
}

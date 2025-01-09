package com.example.webprogrammingspring.book;

import com.example.webprogrammingspring.author.Author;
import com.example.webprogrammingspring.author.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    private final AuthorService authorService;

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("book", new Book());
        List<Author> authors = authorService.findAll(); // Pobieranie danych
        model.addAttribute("authors", authors);
        return "books/form";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/books";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }
}

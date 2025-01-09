package com.example.webprogrammingspring.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> findAll() {
        return authorRepository.findAll();
    }
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}

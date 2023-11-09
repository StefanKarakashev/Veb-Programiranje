package mk.finki.ukim.mk.lab.service.impl;


import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.repository.AuthorRepository;
import mk.finki.ukim.mk.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImplementation implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImplementation(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public List<Author> listAuthors() {
        return Optional.ofNullable(authorRepository.findAll())
                .filter(l -> !l.isEmpty())
                .orElseThrow(() -> new RuntimeException("Author list is empty!"));
    }

    @Override
    public Author findById(Long id) {
//        if (id == null)
//            throw new RuntimeException("Author ID is null!");
//
//        Optional<Author> authorOptional = authorRepository.findById(id);
//
//        if (authorOptional.isEmpty())
//            throw new RuntimeException(String.format("No author with ID %d was found!", id));
//
//        return authorOptional.get();

        return Optional.ofNullable(Optional.ofNullable(id)
                        .orElseThrow(() -> new RuntimeException("aa")))
                .flatMap(authorRepository::findById)
                .orElseThrow(() -> new RuntimeException("No such author was found!"));
    }
}

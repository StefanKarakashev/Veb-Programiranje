package mk.finki.ukim.mk.lab.repository;


import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository {

    private List<Author> authorList;

    @PostConstruct
    public void init() {
        authorList = new ArrayList<>();
        for (int i = 1; i <=5; i++)
            authorList.add(new Author((long)i, "authorName" + i, "authorSurname" + i, "authorBiography" + i));
    }

    public List<Author> findAll() {
        return authorList;
    }

    public Optional<Author> findById(Long id) {
        return authorList.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }
}

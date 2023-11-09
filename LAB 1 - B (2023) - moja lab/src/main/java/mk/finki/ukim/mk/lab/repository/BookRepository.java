package mk.finki.ukim.mk.lab.repository;


import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    private List<Book> bookList;

    @PostConstruct
    public void init() {
        bookList = new ArrayList<>();
        for (int i = 1; i <= 5; i++)
            bookList.add(new Book(String.valueOf(i), "book" + i, "bookGenre" + i, i, new ArrayList<>()));
    }

    public List<Book> findAll() {
        return bookList;
    }

    public Book findByIsbn(String isbn) {
        return bookList.stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    public Author addAuthorToBook(Author author, Book book) {
        bookList.stream().
                filter(b -> b.getIsbn().equals(book.getIsbn()))
                .findFirst()
                .get()
                .getAuthors()
                .add(author);

        return author;
    }
}

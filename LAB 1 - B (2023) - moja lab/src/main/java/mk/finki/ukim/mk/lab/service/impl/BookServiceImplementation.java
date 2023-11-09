package mk.finki.ukim.mk.lab.service.impl;


import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.repository.AuthorRepository;
import mk.finki.ukim.mk.lab.repository.BookRepository;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImplementation implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImplementation(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listBooks() {
        return Optional.ofNullable(bookRepository.findAll())
                .filter(l -> !l.isEmpty())
                .orElseThrow(() -> new RuntimeException("Book list is empty!"));
    }

    @Override
    public Author addAuthorToBook(Long authorId, String isbn) {
       Optional<Author> author = authorRepository.findById(authorId);

       if (author.isEmpty())
           throw new RuntimeException("No such author was found!");

       Book book = bookRepository.findByIsbn(isbn);

       if (book == null)
           throw new RuntimeException("No such book was found!");

       return bookRepository.addAuthorToBook(author.get(), book);

    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return Optional.ofNullable(Optional.ofNullable(isbn)
                        .orElseThrow(() -> new RuntimeException("aa")))
                .map(bookRepository::findByIsbn)
                .orElseThrow(() -> new RuntimeException("No such book was found!"));
    }
}

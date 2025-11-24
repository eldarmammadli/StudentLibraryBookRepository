package com.example.demo.service;

import com.example.demo.dto.BookDTO;
import com.example.demo.dto.LibraryDTO;
import com.example.demo.model.Book;
import com.example.demo.model.Library;
import com.example.demo.payload.BookPayload;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final LibraryRepository libraryRepository;

    @Autowired
    public BookService(BookRepository bookRepository, LibraryRepository libraryRepository) {
        this.bookRepository = bookRepository;
        this.libraryRepository = libraryRepository;
    }

    public void create(BookPayload book) {
        Book bookEntity = new Book();
        bookEntity.setName(book.getName());
        bookEntity.setPages(book.getPages());
        bookEntity.setLibrary(libraryRepository.findById(book.getLibraryId()).orElseThrow(() -> new RuntimeException("Library not found")));
        bookRepository.save(bookEntity);
    }

    public List<BookDTO> get() {
        return bookRepository.findAll().stream().map(book -> {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(book.getId());
            bookDTO.setName(book.getName());
            bookDTO.setPages(book.getPages());
            LibraryDTO libraryDTO = new LibraryDTO();
            libraryDTO.setId(book.getLibrary().getId());
            libraryDTO.setName(book.getLibrary().getName());
            bookDTO.setLibrary(libraryDTO);
            return bookDTO;
        }).collect(Collectors.toList());
    }
}

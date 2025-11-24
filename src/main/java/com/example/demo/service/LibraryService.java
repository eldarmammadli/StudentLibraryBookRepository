package com.example.demo.service;

import com.example.demo.dto.BookDTO;
import com.example.demo.dto.LibraryDTO;
import com.example.demo.model.Book;
import com.example.demo.model.Library;
import com.example.demo.payload.BookPayload;
import com.example.demo.payload.LibraryPayload;
import com.example.demo.repository.LibraryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public void create(LibraryPayload libraryPayload) {
        Library library = new Library();
        library.setName(libraryPayload.getName());
        for(BookPayload bookPayload : libraryPayload.getBooks()) {
            Book book = new Book();
            book.setName(bookPayload.getName());
            book.setPages(bookPayload.getPages());
            book.setLibrary(library);
            library.getBooks().add(book);
        }
        libraryRepository.save(library);
    }


    public List<LibraryDTO> get() {
        return libraryRepository.findAll().stream().map(library -> {
            LibraryDTO libraryDTO = new LibraryDTO();
            libraryDTO.setId(library.getId());
            libraryDTO.setName(library.getName());
            libraryDTO.setBooks(
                    library.getBooks().stream().map(book -> {
                        BookDTO bookDTO = new BookDTO();
                        bookDTO.setId(book.getId());
                        bookDTO.setName(book.getName());
                        bookDTO.setPages(book.getPages());
                        return bookDTO;
                    }).collect(Collectors.toList())
            );
            return libraryDTO;
        }).collect(Collectors.toList());
    }

    public void delete(int id) {
        libraryRepository.deleteById(id);
    }
}

package com.example.demo.controller;

import com.example.demo.dto.LibraryDTO;
import com.example.demo.model.Library;
import com.example.demo.payload.LibraryPayload;
import com.example.demo.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("libraries")
public class LibraryController {

    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody LibraryPayload library) {
        libraryService.create(library);
    }

    @GetMapping
    public List<LibraryDTO> get() {
        return libraryService.get();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        libraryService.delete(id);
    }
}

package com.codingexam.notesapp.controller;

import com.codingexam.notesapp.entity.Notes;
import com.codingexam.notesapp.service.NotesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping(path = "/")
public class NotesController {
    private final NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }


    @Operation(description = "Create a note")
    @PostMapping("/notes")
    public Notes createNote(@RequestParam String title, @RequestParam String body) throws Exception {
//        although in swagger ui the fields are requried (default),
//        we still check if the arguments are empty to prevent saving notes with no title or no body
        if (!StringUtils.isEmpty(title) && !StringUtils.isEmpty(body)) {
            Notes noteToSave = new Notes();
            noteToSave.setTitle(title);
            noteToSave.setBody(body);
            return notesService.save(noteToSave);
        } else {
            throw new IllegalArgumentException("A note must contain a title and body");
        }
    }

    @Operation(description ="Get all notes")
    @GetMapping("/notes")
    public ArrayList<Notes> getNotes() {
        return notesService.getAllNotes();
    }
    @Operation(description ="Get note using id reference")
    @GetMapping("/notes/{id}")
    public Notes getNoteById(@PathVariable Integer id) {
        return notesService.getNoteById(id);
    }

    @Operation(description ="Update a note using id as reference")
    @PutMapping("/notes/{id}")
    public Notes updateNoteById(@PathVariable Integer id, @RequestParam(required = false) String title, @RequestParam(required = false) String body) {
//        we will still check if the arguments are both empty
//        if both are empty, it is best to just delete it and call the deleteMapping instead of the PutMapping endpoint
        if (!StringUtils.isEmpty(title) || !StringUtils.isEmpty(body)) {
            return notesService.updateNoteById(id, title, body);
        } else {
            throw new IllegalArgumentException("A title or a body is required to update a note");
        }
    }

    @Operation(description = "Delete a note using id as reference")
    @DeleteMapping("/notes/{id}")
    public void deleteNoteById(@PathVariable Integer id) {
        notesService.deleteNoteById(id);
    }
}




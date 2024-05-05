package com.codingexam.notesapp.service;

import com.codingexam.notesapp.entity.Notes;

import java.util.ArrayList;

public interface NotesService {


    ArrayList<Notes> getAllNotes();

    Notes save(Notes note) throws Exception;

    Notes getNoteById(Integer id);

    Notes updateNoteById(Integer id, String title, String body);

    void deleteNoteById(Integer id);
}

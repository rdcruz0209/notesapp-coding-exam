package com.codingexam.notesapp.service.impl;

import com.codingexam.notesapp.entity.Notes;
import com.codingexam.notesapp.repository.NotesEntityHolder;
import com.codingexam.notesapp.service.NotesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import static com.codingexam.notesapp.repository.NotesEntityHolder.*;

import java.util.ArrayList;
import java.util.Objects;

@Service
@AllArgsConstructor
public class NotesServiceImpl implements NotesService {

    private final NotesEntityHolder notesEntityHolder;

    @Override
    public ArrayList<Notes> getAllNotes() {
        return notesEntityHolder.getNotesRecords();
    }

    @Override
    public Notes save(Notes note) throws IllegalArgumentException {
        if (Objects.nonNull(note)){
            notesIdCounter++;
            note.setId(notesIdCounter);
        }else {
            throw new IllegalArgumentException("Cannot save null object");
        }
        return notesEntityHolder.save(note);
    }

    @Override
    public Notes getNoteById(Integer id) {
        if (Objects.nonNull(id)){
            return notesEntityHolder.getNoteById(id);
        } else {
            throw new IllegalArgumentException("Cannot retrieve note if id is not specified");
        }
    }

    @Override
    public Notes updateNoteById(Integer id, String title, String body) {
        if (Objects.nonNull(id)){
            return notesEntityHolder.updateNoteById(id,title,body);
        }
        return null;
    }

    @Override
    public void deleteNoteById(Integer id) {
        if (Objects.nonNull(id)){
            notesEntityHolder.deleteNoteById(id);
        }
    }
}

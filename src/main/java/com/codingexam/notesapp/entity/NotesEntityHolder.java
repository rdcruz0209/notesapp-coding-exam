package com.codingexam.notesapp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

//This will serve as the record holder
@Component
@Getter
@Setter
@Slf4j
public class NotesEntityHolder {
    private ArrayList<Notes> notesRecords = new ArrayList<>();
    //    we initialize the notes ID if it's saved
    public static int notesIdCounter = 0;

    public void save(Notes note) {
        notesRecords.add(note);
        log.info("Note saved with details id:{},title:{},body:{}", note.getId(), note.getTitle(), note.getBody());
    }

    public Notes getNoteById(Integer id) {
        Optional<Notes> noteOpt = getNotesOpt(id);
        if (noteOpt.isPresent()) {
            return noteOpt.get();
        } else {
            throw new NullPointerException("No note with this id");
        }
    }

    public Notes updateNoteById(Integer id, String title, String body) {
        Optional<Notes> noteOpt = getNotesOpt(id);
        if (noteOpt.isPresent()) {
            Notes noteToUpdate = noteOpt.get();
            noteToUpdate.setTitle(title);
            noteToUpdate.setBody(body);
            log.info("Updating note with with details id:{},title:{},body:{}", id, title, body);
            return noteToUpdate;
        } else {
            throw new NullPointerException("No note with this id");
        }
    }

    public void deleteNoteById(Integer id) {
        Optional<Notes> noteOpt = getNotesOpt(id);
        noteOpt.ifPresent(notes -> notesRecords.remove(notes));
    }

    //    helper method to avoid code duplication when checking for note presence in the record
    private Optional<Notes> getNotesOpt(Integer id) {
        Optional<Notes> noteOpt = notesRecords.stream()
                                              .filter(note -> note.getId() == id)
                                              .findFirst();
        return noteOpt;
    }


}

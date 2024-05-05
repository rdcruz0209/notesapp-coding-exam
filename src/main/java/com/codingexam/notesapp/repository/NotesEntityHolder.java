package com.codingexam.notesapp.repository;

import com.codingexam.notesapp.entity.Notes;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

//This will serve as the record holder (Repository)
@Component
@Getter
@Setter
@Slf4j
public class NotesEntityHolder {
    private ArrayList<Notes> notesRecords = new ArrayList<>();
    //    we initialize the notes ID as 0 upon creation of this bean,
    //    if a note is saved, we increment the counter by 1 (this is done in the service layer)
    public static int notesIdCounter = 0;

    public Notes save(Notes note) {
        notesRecords.add(note);
        log.info("Note saved with details id:{},title:{},body:{}", note.getId(), note.getTitle(), note.getBody());
        return note;
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

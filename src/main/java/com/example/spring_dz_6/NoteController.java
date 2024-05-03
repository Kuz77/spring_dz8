package com.example.spring_dz_6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        Note addedNote = noteService.addOrUpdate(note);
        return new ResponseEntity<>(addedNote, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteService.getAllNotes();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Optional<Note> noteOptional = noteService.getNoteById(id);
        return noteOptional.map(note -> new ResponseEntity<>(note, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note noteDetails) {
        Optional<Note> noteOptional = noteService.getNoteById(id);
        if (noteOptional.isPresent()) {
            Note existingNote = noteOptional.get();
            existingNote.setTitle(noteDetails.getTitle());
            existingNote.setContent(noteDetails.getContent());
            Note updatedNote = noteService.addOrUpdate(existingNote);
            return new ResponseEntity<>(updatedNote, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable Long id) {
        noteService.deleteNoteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}




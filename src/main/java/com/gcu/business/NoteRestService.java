package com.gcu.business;

import com.gcu.model.NoteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/note-service")
public class NoteRestService {

    @Autowired
    NoteBusinessService noteBusinessService;

    /**
     * Gets all notes in JSON format.
     * @return JSON notes.
     */
    @GetMapping(path="/notes", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getNotesAsJson()
    {
        try
        {
            // Get notes
            List<NoteModel> notes = noteBusinessService.getNotes();

            // If note doesn't exist
            if(notes == null)
            {
                // Return not found
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            {
                // Return note and OK
                return new ResponseEntity<>(notes, HttpStatus.OK);
            }
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/note-{noteIdNumber}", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getNoteById(@PathVariable("noteIdNumber") int noteIdNumber)
    {
        try
        {
            // Get user
            NoteModel note = noteBusinessService.getNoteById(noteIdNumber);

            // If user doesn't exist
            if(note == null)
            {
                // Return not found
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            {
                // Return user and OK
                return new ResponseEntity<>(note, HttpStatus.OK);
            }
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(path="/notes/{userIdNumber}", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getNotesByUserId(@PathVariable("userIdNumber") int userIdNumber)
    {
        try
        {
            // Get user
            List<NoteModel> notes = noteBusinessService.getNotesByUserId(userIdNumber);

            // If user doesn't exist
            if(notes == null)
            {
                // Return not found
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            {
                // Return user and OK
                return new ResponseEntity<>(notes, HttpStatus.OK);
            }
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Adds a new note.
     * @param note Note being added.
     */
    @PostMapping("/notes")
    public void addNote(@RequestBody NoteModel note)
    {
        noteBusinessService.addNote(note);
    }

    /**
     * Updates an existing note.
     * @param noteIdNumber Note ID number that is being updated.
     */
    @PutMapping(path="/notes/{noteIdNumber}")
    public ResponseEntity<NoteModel> updateNote(@PathVariable(value = "noteIdNumber") int noteIdNumber, @RequestBody NoteModel noteDetails) {
        NoteModel note = noteBusinessService.getNoteById(noteIdNumber);
        note.setTitle(noteDetails.getTitle());
        note.setText(noteDetails.getText());
        noteBusinessService.update(note);
        return ResponseEntity.ok(note);
    }

    /**
     * Deletes a note.
     * @param noteIdNumber The Note ID number of the note being deleted.
     */
    @DeleteMapping(path="/notes/{noteIdNumber}")
    public Map<String, Boolean> deleteNote(@PathVariable(value = "noteIdNumber") int noteIdNumber) throws Exception {
        NoteModel note = noteBusinessService.getNoteById(noteIdNumber);
        noteBusinessService.delete(note);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
}
}

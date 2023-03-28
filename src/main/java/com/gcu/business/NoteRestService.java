package com.gcu.business;

import com.gcu.model.NoteModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "https://notes-app-react.herokuapp.com")
@RestController
@RequestMapping("/note-service")
public class NoteRestService {

    Logger logger = LoggerFactory.getLogger(NoteRestService.class);

    @Autowired
    NoteBusinessService noteBusinessService;

    /**
     * Gets all notes in JSON format.
     * @return JSON notes.
     */
    @GetMapping(path="/notes", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getNotesAsJson()
    {
        logger.info("==========> ENTER NoteRestService.getNotesAsJson() at " + "/notes");

        try
        {
            // Get notes
            List<NoteModel> notes = noteBusinessService.getNotes();

            // If note doesn't exist
            if(notes == null)
            {
                logger.info("==========> EXIT NoteRestService.getNotesAsJson() at " + "/notes");

                // Return not found
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            {
                logger.info("==========> EXIT NoteRestService.getNotesAsJson() at " + "/notes");

                // Return note and OK
                return new ResponseEntity<>(notes, HttpStatus.OK);
            }
        }
        catch(Exception e)
        {
            logger.error("==========> EXCEPTION NoteRestService.getNotesAsJson() at " + "/notes", e);
            logger.info("==========> EXIT NoteRestService.getNotesAsJson() at " + "/notes");

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/note-{noteIdNumber}", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getNoteById(@PathVariable("noteIdNumber") int noteIdNumber)
    {
        logger.info("==========> ENTER NoteRestService.getNoteById() at " + "/note-{}", noteIdNumber);

        try
        {
            // Get user
            NoteModel note = noteBusinessService.getNoteById(noteIdNumber);

            // If user doesn't exist
            if(note == null)
            {
                logger.info("==========> EXIT NoteRestService.getNoteById() at " + "/note-{}", noteIdNumber);

                // Return not found
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            {
                logger.info("==========> EXIT NoteRestService.getNoteById() at " + "/note-{}", noteIdNumber);

                // Return user and OK
                return new ResponseEntity<>(note, HttpStatus.OK);
            }
        }
        catch(Exception e)
        {
            logger.error("==========> EXCEPTION NoteRestService.getNoteById() at " + "/note", e);
            logger.info("==========> EXIT NoteRestService.getNoteById() at " + "/note-{}", noteIdNumber);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(path="/notes/{userIdNumber}", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getNotesByUserId(@PathVariable("userIdNumber") int userIdNumber)
    {
        logger.info("==========> ENTER NoteRestService.getNotesByUserId() at " + "/notes/{}", userIdNumber);

        try
        {
            // Get user
            List<NoteModel> notes = noteBusinessService.getNotesByUserId(userIdNumber);

            // If user doesn't exist
            if(notes == null)
            {
                logger.info("==========> EXIT NoteRestService.getNotesByUserId() at " + "/notes/{}", userIdNumber);

                // Return not found
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            {
                logger.info("==========> EXIT NoteRestService.getNotesByUserId() at " + "/notes/{}", userIdNumber);

                // Return user and OK
                return new ResponseEntity<>(notes, HttpStatus.OK);
            }
        }
        catch(Exception e)
        {
            logger.error("==========> EXCEPTION NoteRestService.getNotesByUserId() at " + "/notes/", e);
            logger.info("==========> EXIT NoteRestService.getNotesByUserId() at " + "/notes/{}", userIdNumber);

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
        logger.info("==========> ENTER NoteRestService.addNote() at " + "/notes");

        noteBusinessService.addNote(note);

        logger.info("==========> EXIT NoteRestService.addNote() at " + "/notes");
    }

    /**
     * Updates an existing note.
     * @param noteIdNumber Note ID number that is being updated.
     */
    @PutMapping(path="/notes/{noteIdNumber}")
    public ResponseEntity<NoteModel> updateNote(@PathVariable(value = "noteIdNumber") int noteIdNumber, @RequestBody NoteModel noteDetails) {
        logger.info("==========> ENTER NoteRestService.updateNote() at " + "/notes/{}", noteIdNumber);

        NoteModel note = noteBusinessService.getNoteById(noteIdNumber);
        note.setTitle(noteDetails.getTitle());
        note.setText(noteDetails.getText());
        noteBusinessService.update(note);

        logger.info("==========> EXIT NoteRestService.updateNote() at " + "/notes/{}", noteIdNumber);

        return ResponseEntity.ok(note);
    }

    /**
     * Deletes a note.
     * @param noteIdNumber The Note ID number of the note being deleted.
     */
    @DeleteMapping(path="/notes/{noteIdNumber}")
    public Map<String, Boolean> deleteNote(@PathVariable(value = "noteIdNumber") int noteIdNumber) throws Exception {
        logger.info("==========> ENTER NoteRestService.deleteNote() at " + "/notes/{}", noteIdNumber);

        NoteModel note = noteBusinessService.getNoteById(noteIdNumber);
        noteBusinessService.delete(note);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        
        logger.info("==========> EXIT NoteRestService.deleteNote() at " + "/notes/{}", noteIdNumber);

        return response;
}
}

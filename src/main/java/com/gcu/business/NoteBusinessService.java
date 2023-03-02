package com.gcu.business;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.NoteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteBusinessService {

    @Autowired
    DataAccessInterface<NoteModel> noteDataService;

    /**
     * Gets a list of all notes.
     * @return List of notes.
     */
    public List<NoteModel> getNotes()
    {
        return noteDataService.findAll();
    }

    /**
     * Gets a specific note by ID number.
     * @param id ID being searched for.
     * @return The note assocaited with that ID number.
     */
    public NoteModel getNoteById(int id)
    {
        return noteDataService.findById(id);
    }

       /**
     * Gets a specific note by User ID number.
     * @param userId User ID being searched for.
     * @return The notes assocaited with that User ID number.
     */
    public List<NoteModel> getNotesByUserId(int userId)
    {
        return noteDataService.findByUserId(userId);
    }

    /**
     * Adds a note to the database.
     * @param note Note being added.
     */
    public void addNote(NoteModel note)
    {
        noteDataService.create(note);
    }

    /**
     * Updates an existing note information.
     * @param note Note being updated.
     */
    public void update(NoteModel note)
    {
        noteDataService.update(note);
    }

    /**
     * Deletes a note.
     * @param note Note being deleted.
     */
    public void delete(NoteModel note)
    {
        noteDataService.delete(note);
    }

    /**
     * Finds all posts that contain the search text.
     * @param searchText Text being searched for.
     * @return A list of posts containg that text.
     */
    public List<NoteModel> findByText(String searchText){
        return noteDataService.findByText(searchText);
    }
}

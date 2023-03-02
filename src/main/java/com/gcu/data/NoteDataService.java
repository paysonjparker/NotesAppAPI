package com.gcu.data;

import com.gcu.model.NoteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoteDataService implements DataAccessInterface<NoteModel>{


    @Autowired
    @SuppressWarnings("unused")
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplateObject;

    /**
     * Constructor for this class. Creates a a new instance of this class.
     * @param dataSource Source of user data.
     */
    public NoteDataService(DataSource dataSource)
    {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    /**
     * Finds all entities.
     *
     * @return A list of all found entities.
     */
    @Override
    public List<NoteModel> findAll() {
        String sql = "SELECT * FROM notes_app.notes"; //SQL statement to retrieve all note data
        List<NoteModel> notes = new ArrayList<NoteModel>();
        try
        {
            SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
            while(srs.next())
            {
                notes.add(new NoteModel(srs.getInt("id"),
                        srs.getString("title"),
                        srs.getString("text"),
                        srs.getInt("user_id")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return notes;
    }

    /**
     * Finds a specific entity by ID.
     *
     * @param id ID number being searched for.
     * @return The entity associated with that ID number.
     */
    @Override
    public NoteModel findById(int id) {
        String sql = "SELECT * FROM notes_app.notes WHERE id LIKE " + id;
        NoteModel note = null;
        try
        {
            SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
            while(srs.next())
            {
                note = new NoteModel(srs.getInt("id"),
                        srs.getString("title"),
                        srs.getString("text"),
                        srs.getInt("user_id"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return note;
    }

    /**
     * Finds entities by User ID.
     *
     * @param userId User ID number being searched for.
     * @return The entities associated with that User ID number.
     */
    @Override
    public List<NoteModel> findByUserId(int userId) {
        String sql = "SELECT * FROM notes_app.notes WHERE user_id LIKE " + userId;
        List<NoteModel> notes = new ArrayList<NoteModel>();
        try
        {
            SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
            while(srs.next())
            {
                notes.add(new NoteModel(srs.getInt("id"),
                srs.getString("title"),
                srs.getString("text"),
                srs.getInt("user_id")) );
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return notes;
    }

    /**
     * Creates a new entity.
     *
     * @param noteModel Entity being created.
     * @return True if entity was successfully created, otherwise false.
     */
    @Override
    public boolean create(NoteModel noteModel) {
        String sql = "INSERT INTO notes_app.notes (title, text, user_id) VALUES (?, ?, ?)";
        try
        {
            int rows = jdbcTemplateObject.update(sql,
                    noteModel.getTitle(),
                    noteModel.getText(),
                    noteModel.getUser_id());

            // Return result of Insert
            return rows == 1 ? true : false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Updates an existing entity.
     *
     * @param noteModel Entity being edited.
     * @return True if entity was successfully edited, otherwise false.
     */
    @Override
    public boolean update(NoteModel noteModel) {
        String sql = String.format("UPDATE notes_app.notes SET title='%s', text='%s', user_id='%s' WHERE id=%d;",
                noteModel.getTitle(),
                noteModel.getText(),
                noteModel.getUser_id(),
                noteModel.getId());
        try
        {
            // Executes SQL query
            int rows = jdbcTemplateObject.update(sql);
            // Return result of Insert
            return rows == 1 ? true : false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Deletes an existing entity.
     *
     * @param noteModel Entity being deleted.
     * @return True if entity was successfully deleted, otherwise false.
     */
    @Override
    public boolean delete(NoteModel noteModel) {
        String sql = "DELETE FROM notes_app.notes WHERE id=" + noteModel.getId();
        try
        {
            jdbcTemplateObject.execute(sql);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Finds all entities that contain specific text.
     *
     * @param searchText Text being searched for.
     * @return A list of entities that contain the searched for text.
     */
    @Override
    public List<NoteModel> findByText(String searchText) {
        String sql = "SELECT * FROM notes_app.notes WHERE text LIKE " + searchText; //SQL statement to retrieve all user data
        List<NoteModel> notes = new ArrayList<NoteModel>();
        try
        {
            SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
            while(srs.next())
            {
                notes.add(new NoteModel(srs.getInt("id"),
                        srs.getString("title"),
                        srs.getString("text"),
                        srs.getInt("user_id")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return notes;
    }
}

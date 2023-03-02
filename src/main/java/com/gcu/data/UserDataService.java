package com.gcu.data;

import com.gcu.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDataService implements DataAccessInterface<UserModel> {

    @Autowired
    @SuppressWarnings("unused")
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplateObject;

    /**
     * Constructor for this class. Creates a new instance of this class.
     * @param dataSource Source of user data.
     */
    public UserDataService(DataSource dataSource)
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
    public List<UserModel> findAll() {
        String sql = "SELECT * FROM notes_app.users"; //SQL statement to retrieve all user data
        List<UserModel> users = new ArrayList<UserModel>();
        try
        {
            SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
            while(srs.next())
            {
                users.add(new UserModel(srs.getInt("id"),
                        srs.getString("full_name"),
                        srs.getString("email"),
                        srs.getString("password")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Finds a specific entity by ID.
     *
     * @param id ID number being searched for.
     * @return The entity associated with that ID number.
     */
    @Override
    public UserModel findById(int id) {
        String sql = "SELECT * FROM notes_app.users WHERE id LIKE " + id;
        UserModel user = null;
        try
        {
            SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
            while(srs.next())
            {
                user = new UserModel(srs.getInt("id"),
                        srs.getString("full_name"),
                        srs.getString("email"),
                        srs.getString("password"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Creates a new entity.
     *
     * @param userModel Entity being created.
     * @return True if entity was successfully created, otherwise false.
     */
    @Override
    public boolean create(UserModel userModel) {
        String sql = "INSERT INTO notes_app.users (full_name, email, password) VALUES (?, ?, ?)";
        try
        {
            int rows = jdbcTemplateObject.update(sql,
                    userModel.getFull_name(),
                    userModel.getEmail(),
                    userModel.getPassword());

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
     * @param userModel Entity being edited.
     * @return True if entity was successfully edited, otherwise false.
     */
    @Override
    public boolean update(UserModel userModel) {
        String sql = String.format("UPDATE notes_app.users SET full_name='%s', email='%s', password='%s' WHERE id=%d;",
                userModel.getFull_name(),
                userModel.getEmail(),
                userModel.getPassword(),
                userModel.getId());
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
     * @param userModel Entity being deleted.
     * @return True if entity was successfully deleted, otherwise false.
     */
    @Override
    public boolean delete(UserModel userModel) {
        String sql = "DELETE FROM notes_app.users WHERE id=" + userModel.getId();
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
    public List<UserModel> findByText(String searchText) {
        //***METHOD NOT NEEDED FOR USERS***
        return null;
    }

    @Override
    public List<UserModel> findByUserId(int userId) {
        //***METHOD NOT NEEDED FOR USERS***
        return null;
    }
}

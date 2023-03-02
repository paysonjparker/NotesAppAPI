package com.gcu.data;

import java.util.List;

/**
 * Data access interface.
 * @param <T> Anonymous data type.
 */
public interface DataAccessInterface <T> {

    /**
     * Finds all entities.
     * @return A list of all found entities.
     */
    public List<T> findAll();

    /**
     * Finds a specific entity by ID.
     * @param id ID number being searched for.
     * @return The entity associated with that ID number.
     */
    public T findById(int id);

    /**
     * Creates a new entity.
     * @param t Entity being created.
     * @return True if entity was successfully created, otherwise false.
     */
    public boolean create(T t);

    /**
     * Updates an existing entity.
     * @param t Entity being edited.
     * @return True if entity was successfully edited, otherwise false.
     */
    public boolean update(T t);

    /**
     * Deletes an existing entity.
     * @param t Entity being deleted.
     * @return True if entity was successfully deleted, otherwise false.
     */
    public boolean delete(T t);

    /**
     * Finds all entities that contain specific text.
     * @param searchText Text being searched for.
     * @return A list of entities that contain the searched for text.
     */
    public List<T> findByText(String searchText);

        /**
     * Finds all entities that have a specific User ID.
     * @param userId User ID being searched for.
     * @return A list of entities that have the User ID.
     */
    public List<T> findByUserId(int userId);
}

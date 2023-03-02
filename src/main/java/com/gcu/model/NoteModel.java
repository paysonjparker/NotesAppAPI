package com.gcu.model;

/**
 * Note Model class.
 */
public class NoteModel {

    /**
     * Note ID number.
     */
    private int id;
    /**
     * Note title.
     */
    private String title;
    /**
     * Note text.
     */
    private String text;
    /**
     * User ID of the user that created this note.
     */
    private int user_id;

    /**
     * Constructs a Note Model.
     * @param id Note ID number.
     * @param title Note title.
     * @param text Note text.
     * @param user_id User ID of the user that created this note.
     */
    public NoteModel(int id, String title, String text, int user_id) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.user_id = user_id;
    }

    /**
     * Default Note Model constructor.
     */
    public NoteModel() {

    }

    /**
     * Gets the Note ID number.
     * @return Note ID number.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the Note ID number.
     * @param id New ID number.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the Note title.
     * @return Note title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the Note title.
     * @param title New Note title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the Note text.
     * @return Note text.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the Note text.
     * @param text New Note text.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets the User ID of the user that created this Note.
     * @return User ID of Note creator.
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * Sets the User ID of the user that created this Note.
     * @param user_id New User ID of the Note creator.
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}

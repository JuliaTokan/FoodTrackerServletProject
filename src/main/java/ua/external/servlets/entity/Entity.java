package ua.external.servlets.entity;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Class for marking all database entities of this application
 */
public abstract class Entity implements Serializable, Cloneable {
    @Id
    private Long id;

    public Entity() {
    }

    public Entity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

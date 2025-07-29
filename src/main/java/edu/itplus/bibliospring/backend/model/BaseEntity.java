package edu.itplus.bibliospring.backend.model;

import jakarta.persistence.*;

@MappedSuperclass
public class BaseEntity extends AbstractModel {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

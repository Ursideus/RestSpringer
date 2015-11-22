package com.ursideus.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by dovw on 11/20/15.
 */
@Component
@Entity
@Table(name="offer")
public class Offer {

    @Id
    @GeneratedValue
    //@GeneratedValue(strategy=GenerationType.AUTO)
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    //@Column(name="id", unique=true, nullable=false)
    private Long id;
    //@Column(name="name", nullable=false)
    private String name;
    //@Column(name="email", nullable=false)
    private String email;
    //@Column(name="text")
    private String text;

    public Offer() {
    }

    public Offer(Long id, String name, String email, String text) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

package com.event.details.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Event")
public class EventEntity {

    @Id
    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "genre")
    private String genre;

    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private List<EventPublishEntity> publishes;


    // Getters and Setters

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<EventPublishEntity> getPublishes() {
        return publishes;
    }

    public void setPublishes(List<EventPublishEntity> publishes) {
        this.publishes = publishes;
    }


    @Override
    public String toString() {
        return "Event ID: " + eventId +
                ", Event name: " + eventName +
                ", Event genre: " + genre;
    }
}
package com.event.details.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Venue")
public class VenueEntity {

    @Id
    @Column(name = "venue_id")
    private Integer venueId;

    @Column(name = "venue_name")
    private String venueName;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @OneToMany(mappedBy = "venue")
    @JsonIgnore
    private List<EventPublishEntity> eventPublishes;

    public Integer getVenueId() {
        return venueId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }


    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public List<EventPublishEntity> getEventPublishes() {
        return eventPublishes;
    }

    public void setEventPublishes(List<EventPublishEntity> eventPublishes) {
        this.eventPublishes = eventPublishes;
    }


    @Override
    public String toString() {
        return "Venue ID: " + venueId +
                ", Venue Name: " + venueName +
                ", City: " + city +
                ", State: " + state;
    }
}
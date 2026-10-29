package com.event.details.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "Event_Publish")
public class EventPublishEntity {

    @Id
    @Column(name = "publish_id")
    private Integer publishId;

    @ManyToOne
    @JoinColumn(
            name = "event_id",
            referencedColumnName = "event_id"
    )
    private EventEntity event;

    @ManyToOne
    @JoinColumn(
            name = "venue_id",
            referencedColumnName = "venue_id"
    )
    private VenueEntity venue;

    @ManyToOne
    @JoinColumn(name = "instruction_id", referencedColumnName = "instruction_id")
    private EventInstructionEntity instruction;

    @OneToMany(mappedBy = "eventPublish")
    @JsonIgnore
    private List<EventImageEntity> images;

    @Column(name = "event_date")
    private LocalDate eventDate;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "duration")
    private LocalTime duration;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "age_restriction")
    private Integer ageRestriction;

    @Column(name = "visibility")
    private String visibility;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Integer getPublishId() {
        return publishId;
    }

    public void setPublishId(Integer publishId) {
        this.publishId = publishId;
    }


    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }


    public VenueEntity getVenue() {
        return venue;
    }

    public void setVenue(VenueEntity venue) {
        this.venue = venue;
    }


    public List<EventImageEntity> getImages() {
        return images;
    }

    public void setImages(List<EventImageEntity> images) {
        this.images = images;
    }

    public EventInstructionEntity getInstruction() {
        return instruction;
    }

    public void setInstruction(EventInstructionEntity instruction) {
        this.instruction = instruction;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }


    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }


    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }


    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }


    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }


    public Integer getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(Integer ageRestriction) {
        this.ageRestriction = ageRestriction;
    }


    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Override
    public String toString() {
        return "Publish ID: " + publishId +
                ", Event: " + event +
                ", Venue: " + venue +
                ", Event Date: " + eventDate +
                ", Start Time: " + startTime +
                ", End Time: " + endTime +
                ", Duration: " + duration +
                ", Capacity: " + capacity +
                ", Age Restriction: " + ageRestriction +
                ", Visibility: " + visibility;
    }
}
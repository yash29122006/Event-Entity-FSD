package com.event.details.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "Event_Images")
public class EventImageEntity {

    @Id
    @Column(name = "image_id")
    private Integer imageId;

    @ManyToOne
    @JoinColumn(
            name = "publish_id",
            referencedColumnName = "publish_id"
    )
    @JsonIgnore
    private EventPublishEntity eventPublish;


    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "image_type")
    private String imageType;

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public EventPublishEntity getEventPublish() {
        return eventPublish;
    }

    public void setEventPublish(EventPublishEntity eventPublish) {
        this.eventPublish = eventPublish;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    @Override
    public String toString() {
        return "Image ID: " + imageId +
                ", Image URL: " + imageUrl +
                ", Image Type: " + imageType;
    }
}
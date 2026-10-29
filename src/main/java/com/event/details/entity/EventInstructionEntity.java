package com.event.details.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Event_Instruction")
public class EventInstructionEntity {

    @Id
    @Column(name = "instruction_id")
    private Integer instructionId;

    @OneToMany(mappedBy = "instruction")
    @JsonIgnore
    private List<EventPublishEntity> eventPublishes;

    @Column(name = "instruction_text")
    private String instructionText;

    public Integer getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(Integer instructionId) {
        this.instructionId = instructionId;
    }

    public List<EventPublishEntity> getEventPublishes() {
        return eventPublishes;
    }

    public void setEventPublishes(List<EventPublishEntity> eventPublishes) {
        this.eventPublishes = eventPublishes;
    }

    public String getInstructionText() {
        return instructionText;
    }

    public void setInstructionText(String instructionText) {
        this.instructionText = instructionText;
    }

    @Override
    public String toString() {
        return "Instruction ID: " + instructionId +
                ", Instruction: " + instructionText;
    }
}
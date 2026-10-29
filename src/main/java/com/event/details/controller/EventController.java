package com.event.details.controller;

import com.event.details.entity.EventEntity;
import com.event.details.entity.EventInstructionEntity;
import com.event.details.entity.EventPublishEntity;
import com.event.details.entity.VenueEntity;
import com.event.details.repository.EventRepository;
import com.event.details.response.ResponseBean;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
public class EventController {
    EventRepository eventRepository;
    public EventController(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @GetMapping("/Events")
    public ResponseEntity<ResponseBean> filterEvent(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String visibility,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String date)
    {
        ResponseBean response = new ResponseBean();
        Specification<EventPublishEntity> filter = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (city != null && !city.isEmpty()) {
                predicates.add(
                        cb.equal(
                                cb.lower(root.get("venue").get("city")),city.toLowerCase()
                        )
                );
            }
            if (visibility != null && !visibility.isEmpty()) {
                predicates.add(
                        cb.equal(
                                cb.lower(root.get("visibility")),
                                visibility.toLowerCase()
                        )
                );
            }
            // 1. Music, 2. comedy, 3. food, 4. automobile, 5. sports, 6. Art
            if (genre != null && !genre.isEmpty()) {
                predicates.add(
                        cb.equal(
                                cb.lower(root.get("event").get("genre")),
                                genre.toLowerCase()
                        )
                );
            }
            if (date != null && !date.isBlank()) {
                LocalDate parsedDate = LocalDate.parse(date);
                predicates.add(
                        cb.greaterThanOrEqualTo(
                                root.get("eventDate"),
                                parsedDate
                        )
                );
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        List<EventPublishEntity> events = eventRepository.findAll(filter);
        response.setData(events);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/Events")
    public ResponseEntity<ResponseBean> createEvent(@RequestBody EventPublishEntity event) {
        ResponseBean response = new ResponseBean();
        EventPublishEntity savedEvent = eventRepository.save(event);
        response.setErrorCode("201");
        response.setErrorDesc("Event Created Successfully");
        response.setData(savedEvent);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/Events/{id}")
    public ResponseEntity<ResponseBean> getEventById(@PathVariable Integer id) {
        ResponseBean response = new ResponseBean();
        EventPublishEntity event = eventRepository.findById(id).orElse(null);
        if (event == null) {
            response.setData("Event not found with ID: " + id);
            return ResponseEntity.notFound().build();
        }
        response.setData(event);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/Events/{id}")
    public ResponseEntity<ResponseBean> updateEvent(@PathVariable Integer id, @RequestBody EventPublishEntity event) {
        ResponseBean response = new ResponseBean();
        Optional<EventPublishEntity> existingEvent = eventRepository.findById(id);
        if (existingEvent.isPresent()) {
            EventPublishEntity updatedEvent = existingEvent.get();
            if (event.getEvent() != null) {
                updatedEvent.getEvent().setEventName(event.getEvent().getEventName());
                updatedEvent.getEvent().setGenre(event.getEvent().getGenre());
            }
            if (event.getVenue() != null) {
                updatedEvent.getVenue().setVenueName(event.getVenue().getVenueName());
                updatedEvent.getVenue().setCity(event.getVenue().getCity());
                updatedEvent.getVenue().setState(event.getVenue().getState());
            }
            if (event.getInstruction() != null) {
                updatedEvent.getInstruction().setInstructionText(event.getInstruction().getInstructionText());
            }
            updatedEvent.setEventDate(event.getEventDate());
            updatedEvent.setStartTime(event.getStartTime());
            updatedEvent.setEndTime(event.getEndTime());
            updatedEvent.setDuration(event.getDuration());
            updatedEvent.setCapacity(event.getCapacity());
            updatedEvent.setAgeRestriction(event.getAgeRestriction());
            updatedEvent.setVisibility(event.getVisibility());
            updatedEvent.setUpdatedAt(LocalDateTime.now());
            eventRepository.save(updatedEvent);
            response.setErrorCode("200");
            response.setErrorDesc("Event Updated Successfully");
            response.setData(updatedEvent);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setErrorCode("404");
            response.setErrorDesc("Event Not Found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/Events/{id}")
    public ResponseEntity<ResponseBean> patchEvent(
            @PathVariable Integer id,
            @RequestBody Map<String, Object> fields) {

        ResponseBean response = new ResponseBean();

        Optional<EventPublishEntity> existingEvent =
                eventRepository.findById(id);

        if (existingEvent.isPresent()) {

            EventPublishEntity event = existingEvent.get();

            fields.forEach((key, value) -> {

                switch (key) {

                    case "event" -> {

                        Map<String, Object> eventMap =
                                (Map<String, Object>) value;

                        EventEntity eventEntity = event.getEvent();

                        if (eventEntity != null) {

                            if (eventMap.containsKey("eventName")) {
                                eventEntity.setEventName(
                                        (String) eventMap.get("eventName")
                                );
                            }

                            if (eventMap.containsKey("genre")) {
                                eventEntity.setGenre(
                                        (String) eventMap.get("genre")
                                );
                            }
                        }
                    }

                    case "venue" -> {

                        Map<String, Object> venueMap =
                                (Map<String, Object>) value;

                        VenueEntity venue = event.getVenue();

                        if (venue != null) {

                            if (venueMap.containsKey("venueName")) {
                                venue.setVenueName(
                                        (String) venueMap.get("venueName")
                                );
                            }

                            if (venueMap.containsKey("city")) {
                                venue.setCity(
                                        (String) venueMap.get("city")
                                );
                            }

                            if (venueMap.containsKey("state")) {
                                venue.setState(
                                        (String) venueMap.get("state")
                                );
                            }
                        }
                    }

                    case "instruction" -> {

                        Map<String, Object> instructionMap =
                                (Map<String, Object>) value;

                        EventInstructionEntity instruction =
                                event.getInstruction();

                        if (instruction != null) {

                            if (instructionMap.containsKey("instructionText")) {
                                instruction.setInstructionText(
                                        (String) instructionMap.get("instructionText")
                                );
                            }
                        }
                    }

                    case "eventDate" ->
                            event.setEventDate(
                                    LocalDate.parse((String) value)
                            );

                    case "startTime" ->
                            event.setStartTime(
                                    LocalTime.parse((String) value)
                            );

                    case "endTime" ->
                            event.setEndTime(
                                    LocalTime.parse((String) value)
                            );

                    case "duration" ->
                            event.setDuration(
                                    LocalTime.parse((String) value)
                            );

                    case "capacity" ->
                            event.setCapacity((Integer) value);

                    case "ageRestriction" ->
                            event.setAgeRestriction((Integer) value);

                    case "visibility" ->
                            event.setVisibility((String) value);
                }
            });

            event.setUpdatedAt(LocalDateTime.now());

            eventRepository.save(event);

            response.setErrorCode("200");
            response.setErrorDesc("Event Updated Successfully");
            response.setData(event);

            return new ResponseEntity<>(
                    response,
                    HttpStatus.OK
            );

        } else {

            response.setErrorCode("404");
            response.setErrorDesc("Event Not Found");

            return new ResponseEntity<>(
                    response,
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping("/Events/{id}")
    public ResponseEntity<ResponseBean> deleteEvent(@PathVariable Integer id) {
        ResponseBean response = new ResponseBean();
        Optional<EventPublishEntity> existingEvent = eventRepository.findById(id);
        if (existingEvent.isPresent()) {
            eventRepository.deleteById(id);
            response.setErrorCode("200");
            response.setErrorDesc("Event Deleted Successfully");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setErrorCode("404");
            response.setErrorDesc("Event Not Found");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
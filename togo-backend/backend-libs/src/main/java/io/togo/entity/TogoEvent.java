package io.togo.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public abstract class TogoEvent {

    private String eventId = UUID.randomUUID().toString();
}

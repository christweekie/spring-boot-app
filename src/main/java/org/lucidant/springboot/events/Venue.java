package org.lucidant.springboot.events;

public record Venue(
        int id,
        String name,
        String street,
        String city,
        String country) {
}

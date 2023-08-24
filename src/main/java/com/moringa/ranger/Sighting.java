package com.moringa.ranger;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class Sighting {
    private int id;
    private int animalId;
    private String location;
    private String rangerName;

    private int endangeredAnimalId;

    private Timestamp timestamp;

    public Sighting() {
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public int getEndangeredAnimalId() {
        return endangeredAnimalId;
    }

    public void setEndangeredAnimalId(int endangeredAnimalId) {
        this.endangeredAnimalId = endangeredAnimalId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRangerName() {
        return rangerName;
    }

    public void setRangerName(String rangerName) {
        this.rangerName = rangerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sighting sighting = (Sighting) o;
        return id == sighting.id && animalId == sighting.animalId && endangeredAnimalId == sighting.endangeredAnimalId && Objects.equals(location, sighting.location) && Objects.equals(rangerName, sighting.rangerName) && Objects.equals(timestamp, sighting.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, animalId, location, rangerName, endangeredAnimalId, timestamp);
    }
}

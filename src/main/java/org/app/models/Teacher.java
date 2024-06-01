package org.app.models;

import java.util.ArrayList;
import java.util.List;

public class Teacher {

    private String name;
    private List<CoachingCenter> centers;
    private List<String> subjects;
    private List<Slot> slots;

    public Teacher(String name, List<String> subjects) {
        this.name = name;
        this.subjects = subjects;
        centers = new ArrayList<>();
        slots = new ArrayList<>();
    }

    public void addCenter(CoachingCenter center) {
        this.centers.add(center);
    }

    public void addSlot(Slot slot) {
        slots.add(slot);
    }

    public List<Slot> getSlots() {
        return slots;
    }


    public String getName() {
        return name;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public List<CoachingCenter> getCenters() {
        return centers;
    }

    public Slot findSlot(int startTime, int endTime) {
        for (Slot slot : slots) {
            if (slot.getStartTime() == startTime && slot.getEndTime() == endTime) {
                return slot;
            }
        }
        return null;
    }
}

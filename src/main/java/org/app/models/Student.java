package org.app.models;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private String name;
    private CoachingCenter center;

    private List<Slot> bookedSlots;

    public Student(String name ,CoachingCenter center) {
        this.name = name;
        this.center = center;
        bookedSlots = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addSlot(Slot slot) {
        bookedSlots.add(slot);
    }

    public List<Slot> getBookedSlots() {
        return bookedSlots;
    }
}

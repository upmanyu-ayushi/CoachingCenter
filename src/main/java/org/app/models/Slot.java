package org.app.models;

import java.util.UUID;

public class Slot {

    private int id = 0;

    private int maxCapacity = 100;
    private int startTime;
    private int endTime;
    private String subject;

    private int currentCapacity;


    public Slot(int startTime, int endTime, String subject) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.subject = subject;
        this.currentCapacity = 0;
        this.id = id++;
    }

    public String getTime() {
        return String.valueOf(startTime) + " - " + String.valueOf(endTime);
    }

    public String getSubject() {
        return subject;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public int getEndTime() {
        return this.endTime;
    }

    public boolean book() {
        synchronized (this) {
            if (currentCapacity < maxCapacity) {
                currentCapacity ++;
            }
        }
        try {
            //Do any payment or registration if required for the slot
        } catch (Exception e) {
            synchronized (this) {
                currentCapacity --;
                return false;
            }
        }

        return true;
    }

    public boolean isFull() {
        return currentCapacity == maxCapacity;
    }

    public int getRemainingSeats() {
        return maxCapacity - currentCapacity;
    }

    public int getId() {
        return id;
    }
}

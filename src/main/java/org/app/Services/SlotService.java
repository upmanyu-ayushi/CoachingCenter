package org.app.Services;

import org.app.models.Slot;

public class SlotService {

    private Slot slot;

    public SlotService(Slot slot) {
        this.slot = slot;
    }

    public void book() {
        slot.book();
    }

    public String getTime() {
        return slot.getTime();
    }

    public String getSubject() {
        return slot.getSubject();
    }
}

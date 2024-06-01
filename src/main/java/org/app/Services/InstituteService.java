package org.app.Services;

import org.app.models.CoachingCenter;
import org.app.models.Institute;
import org.app.models.Slot;
import org.app.models.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InstituteService {

    private Institute institute;

    public InstituteService(Institute institute) {
        this.institute = institute;
    }

    public void addCoachingCenter(CoachingCenter center) {
        institute.addCoachingCenter(center);
    }

    public List<CoachingCenter> getCoachingCenters() {
        return institute.getCoachingCenters();
    }

    public CoachingCenter findCoachingCenterByName(String name) {
        return institute.getCoachingCenters().stream()
                .filter(coachingCenter -> coachingCenter.getName().equals(name))
                .collect(Collectors.toList()).get(0);
    }

    public List<Slot> searchSlotsBySubject(String subject) {
        List<Slot> slots = new ArrayList<>();
        for (CoachingCenter center : institute.getCoachingCenters()) {
            for (Teacher teacher : center.getTeachers()) {
                for (Slot slot : teacher.getSlots()) {
                    if (slot.getSubject().equalsIgnoreCase(subject)) {
                        slots.add(slot);
                    }
                }
            }
        }
        return slots;
    }
}

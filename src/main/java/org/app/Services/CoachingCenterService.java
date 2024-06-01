package org.app.Services;

import org.app.models.CoachingCenter;
import org.app.models.Slot;
import org.app.models.Student;
import org.app.models.Teacher;

import java.util.List;
import java.util.stream.Collectors;

public class CoachingCenterService {

    CoachingCenter coachingCenter;

    public CoachingCenterService(CoachingCenter coachingCenter) {
        this.coachingCenter = coachingCenter;
    }

    public void addTeacher(Teacher teacher) {
        coachingCenter.addTeacher(teacher);
    }

    public void addStudent(Student student) {
        coachingCenter.addStudent(student);
    }

    public List<Slot> getSlots() {
        return coachingCenter.getSlots();
    }

    public String getName() {
        return coachingCenter.getName();
    }



}

package org.app.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CoachingCenter {

    private String id;
    private String name;
    private String pincode;
    private List<Teacher> teachers;
    private List<Student> students;

    public CoachingCenter(String name, String pincode) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.pincode = pincode;
        this.teachers = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPincode() {
        return pincode;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Slot> getSlots() {
        List<Slot> slots = new ArrayList<>();
        for(Teacher teacher : teachers) {
            slots.addAll(teacher.getSlots());
        }
        return slots;
    }

    public Teacher findTeacherByName(String name) {
        return getTeachers().stream().filter(t -> t.getName().equals(name)).collect(Collectors.toList()).get(0);
    }

    public Student findStudentByName(String name) {
        return getStudents().stream().filter(t -> t.getName().equals(name)).collect(Collectors.toList()).get(0);
    }

    public void printCoachingCenter() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        String output = "--------------------------------------------------------------------------------------------------\n";
        output += "--------------------------------------------------------------------------------------------------\n";

        output += "COACHING CENTER: " + name;
        output += "--------------------------------------------------------------------------------------------------\n";
        output += "TEACHERS and SLOTS: \n";
        for(Teacher teacher : getTeachers()) {
            output += "Teacher: " + teacher.getName() + "\n";
            for(Slot slot : teacher.getSlots()) {
                output += "Id: " + slot.getId() + ", Subject: " + slot.getSubject() + ", time: " + slot.getStartTime() + "-" + slot.getEndTime() + " , Remaining Seats: " + slot.getRemainingSeats() + "\n";
            }
            output += "--------------------------------------------------------------------------------------------------\n";
        }
        output += "--------------------------------------------------------------------------------------------------\n";
        output+="STUDENTS and booked SLOTS: \n";
        for(Student student : getStudents()) {
            output+= "Student: " + student.getName() + "\n";
            for(Slot slot : student.getBookedSlots()) {
                output += "Id: " + slot.getId() + ", Subject: " + slot.getSubject() + ", time: " + slot.getStartTime() + "-" + slot.getEndTime() + " , Remaining Seats: " + slot.getRemainingSeats() + "\n";
            }
            output += "--------------------------------------------------------------------------------------------------\n";
        }
        output += "--------------------------------------------------------------------------------------------------\n";
        output += "--------------------------------------------------------------------------------------------------\n";
        return output;
    }
}

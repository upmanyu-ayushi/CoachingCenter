import org.app.Services.InstituteService;
import org.app.models.*;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Institute institute = new Institute("Top Institute");
        InstituteService instituteService = new InstituteService(institute);

        while (true) {
            System.out.println("0. Show Coaching Center");
            System.out.println("1. Add Coaching Center");
            System.out.println("2. Add Teacher");
            System.out.println("3. Add Student");
            System.out.println("4. Add Slot to Teacher");
            System.out.println("5. Book Slot");
            System.out.println("6. Search Slots by Subject");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 0:
                    System.out.print("Enter Coaching Center Name: ");
                    String centerName = scanner.nextLine();
                    CoachingCenter coachingCenter = instituteService.findCoachingCenterByName(centerName);
                    if (coachingCenter != null) {
                        coachingCenter.printCoachingCenter();
                    }
                    break;
                case 1:
                    System.out.print("Enter Coaching Center Name: ");
                    centerName = scanner.nextLine();
                    System.out.print("Enter Pincode: ");
                    String pincode = scanner.nextLine();
                    coachingCenter = new CoachingCenter(centerName, pincode);
                    instituteService.addCoachingCenter(coachingCenter);
                    System.out.println("Coaching Center added.");
                    break;
                case 2:
                    System.out.print("Enter Teacher Name: ");
                    String teacherName = scanner.nextLine();
                    System.out.print("Enter Subjects (comma-separated): ");
                    String[] subjects = scanner.nextLine().split(",");
                    List<String> subjectList = Arrays.asList(subjects);
                    Teacher teacher = new Teacher(teacherName, subjectList);
                    System.out.print("Enter Coaching Center Name: ");
                    String centerNameForTeacher = scanner.nextLine();
                    CoachingCenter foundCenterForTeacher = instituteService.findCoachingCenterByName(centerNameForTeacher);
                    if (foundCenterForTeacher != null) {
                        foundCenterForTeacher.addTeacher(teacher);
                        System.out.println("Teacher added.");
                    } else {
                        System.out.println("Coaching Center not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Student Name: ");
                    String studentName = scanner.nextLine();
                    System.out.print("Enter Coaching Center Name: ");
                    String centerNameForStudent = scanner.nextLine();
                    CoachingCenter foundCenterForStudent = instituteService.findCoachingCenterByName(centerNameForStudent);
                    if (foundCenterForStudent != null) {
                        Student student = new Student(studentName, foundCenterForStudent);
                        foundCenterForStudent.addStudent(student);
                        System.out.println("Student added.");
                    } else {
                        System.out.println("Coaching Center not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Teacher Name: ");
                    String teacherNameForSlot = scanner.nextLine();
                    System.out.print("Enter Coaching Center Name: ");
                    String centerNameForSlot = scanner.nextLine();
                    CoachingCenter foundCenterForSlot = instituteService.findCoachingCenterByName(centerNameForSlot);
                    if (foundCenterForSlot != null) {
                        Teacher foundTeacher = foundCenterForSlot.findTeacherByName(teacherNameForSlot);
                        if (foundTeacher != null) {
                            System.out.print("Enter Slot Start Time: ");
                            int startTime = scanner.nextInt();
                            System.out.print("Enter Slot End Time: ");
                            int endTime = scanner.nextInt();
                            scanner.nextLine(); // consume newline
                            System.out.print("Enter Subject: ");
                            String subject = scanner.nextLine();
                            Slot slot = new Slot(startTime, endTime, subject);
                            foundTeacher.addSlot(slot);
                            System.out.println("Slot added.");
                        } else {
                            System.out.println("Teacher not found.");
                        }
                    } else {
                        System.out.println("Coaching Center not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter Student Name: ");
                    String studentNameForBooking = scanner.nextLine();
                    System.out.print("Enter Coaching Center Name: ");
                    String centerNameForBooking = scanner.nextLine();
                    CoachingCenter foundCenterForBooking = instituteService.findCoachingCenterByName(centerNameForBooking);
                    if (foundCenterForBooking != null) {
                        Student foundStudent = foundCenterForBooking.findStudentByName(studentNameForBooking);
                        if (foundStudent != null) {
                            System.out.print("Enter Slot Start Time: ");
                            int slotStartTime = scanner.nextInt();
                            System.out.print("Enter Slot End Time: ");
                            int slotEndTime = scanner.nextInt();
                            scanner.nextLine(); // consume newline
                            System.out.print("Enter Teacher Name: ");
                            String teacherNameForBooking = scanner.nextLine();
                            Teacher foundTeacherForBooking = foundCenterForBooking.findTeacherByName(teacherNameForBooking);
                            if (foundTeacherForBooking != null) {
                                Slot foundSlot = foundTeacherForBooking.findSlot(slotStartTime, slotEndTime);
                                if (foundSlot != null) {
                                    if (foundSlot.book()) {
                                        System.out.println("Slot booked successfully.");
                                    } else {
                                        System.out.println("Failed to book slot.");
                                    }
                                } else {
                                    System.out.println("Slot not found.");
                                }
                            } else {
                                System.out.println("Teacher not found.");
                            }
                        } else {
                            System.out.println("Student not found.");
                        }
                    } else {
                        System.out.println("Coaching Center not found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter Subject: ");
                    String searchSubject = scanner.nextLine();
                    List<Slot> slotsBySubject = instituteService.searchSlotsBySubject(searchSubject);
                    if (!slotsBySubject.isEmpty()) {
                        System.out.println("Available slots:");
                        for (Slot slot : slotsBySubject) {
                            System.out.println("Time: " + slot.getTime() + ", Subject: " + slot.getSubject());
                        }
                    } else {
                        System.out.println("No slots found for the subject: " + searchSubject);
                    }
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}

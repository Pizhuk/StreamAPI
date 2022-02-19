package com.homeworkStream.service;

import com.homeworkStream.model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;

public class StudentMethods {

    private static final String COMMA_WITH_SPACE = ", ";
    private static final String ENTER = "\n";

    /* 3.1
        - список студентов заданного факультета
    */
    public List<Student> getListOfStudentsFromFaculty(List<Student> listOfStudents, String faculty) {
        if (listOfStudents == null) {
            return null;
        }
        if (faculty == null) {
            return null;
        }
        List<Student> resultingList = listOfStudents.stream()
                .filter(x -> x.getFaculty().equals(faculty))
                .collect(Collectors.toList());
        return resultingList;
    }

    /* 3.2
        - списки студентов для каждого факультета и курса
     */
    public List<Student> getListFromFacultyAndCourse(List<Student> listOfStudents, String faculty, String course) {
        if (listOfStudents == null) {
            return null;
        }
        if (faculty == null || course == null) {
            return null;
        }
        List<Student> filteredList = listOfStudents.stream()
                .filter(c -> c.getFaculty().equals(faculty))
                .filter(c -> c.getCourse().equals(course))
                .collect(toList());
        return filteredList;
    }

    /* 3.3
        - список студентов, родившихся после заданного года
    */
    public List<Student> getListOfStudentsAboveCertainYear(List<Student> listOfStudents, int year) {
        if (listOfStudents == null) {
            return null;
        }
        List<Student> resultingList = listOfStudents.stream()
                .filter(x -> x.getYearOfBirth() > year)
                .collect(Collectors.toList());
        return resultingList;
    }

    /* 3.4
        - студента, родившихся после заданного года
     */
    public Student getStudentAboveCertainBirthYear(List<Student> listOfStudents, int year) {
        if (listOfStudents == null) {
            return null;
        }
        Student student = listOfStudents.stream()
                .filter(student1 -> student1.getYearOfBirth() > year).
                        findFirst().get();
        return student;
    }

    /* 3.5
        - список учебной группы в формате Фамилия, Имя, Отчество (List<String>)
     */
    public List<String> getStudentsGroupList(List<Student> students, String group) {
        if (students == null) {
            return null;
        }
        List<String> studentsList = students.stream()
                .filter(student -> student.getGroup().equals(group))
                .map(student -> student.getLastName() + " " + student.getFirstName())
                .collect(Collectors.toList());
        return studentsList;
    }

    /* 4
        - подсчитать количество студентов на заданном факультете
    */
    public int getAmountOfAllStudentAtFaculty(List<Student> listOfStudents, String faculty) {
        if (listOfStudents == null) {
            return -1;
        }
        if (faculty == null) {
            return -1;
        }
        int res = (int) listOfStudents.stream()
                .filter(x -> x.getFaculty().equals(faculty))
                .count();

        return res;
    }

    /* 5.1
        - перевести студентов на другой факультет
     */
    public List<Student> transferStudentsToOtherFaculty(List<Student> listOfStudents, String facultyFrom, String facultyTo) {
        if (listOfStudents == null) {
            return null;
        }

        if (facultyFrom == null || facultyFrom.isBlank() || facultyTo == null || facultyTo.isBlank()) {
            return new ArrayList<Student>();
        }

        List<Student> resultingList = listOfStudents.stream()
                .filter(student -> student.getFaculty().equals(facultyFrom))
                .map(student -> new Student(student.getId(), student.getFirstName(), student.getLastName(),
                        student.getYearOfBirth(), student.getAddress(), student.getTelephone(), facultyTo,
                        student.getCourse(), student.getGroup())).collect(Collectors.toList());
        return resultingList;
        //посмотри ещё

    }

    /* 5.2
        - перевести студентов в другую группу
     */
    public List<Student> transferStudentsToOtherGroup(List<Student> listOfStudents, String groupFrom, String groupTo) {
        if (listOfStudents == null) {
            return null;
        }

        if (groupFrom == null || groupFrom.isBlank() || groupTo == null || groupTo.isBlank()) {
            return new ArrayList<Student>();
        }
        List<Student> resultingList = listOfStudents.stream()
                .filter(student -> student.getGroup().equals(groupFrom))
                .map(student -> new Student(student.getId(), student.getFirstName(), student.getLastName(),
                        student.getYearOfBirth(), student.getAddress(), student.getTelephone(), student.getFaculty(),
                        student.getCourse(), groupTo)).collect(Collectors.toList());
        return resultingList;
    }

    /* 6
        - подсчитать количество студентов на заданном факультете
     */
    public int getNumberOfStudentsAtFaculty(List<Student> listOfStudent, String faculty) {
        if (listOfStudent == null) {
            return -1;
        }
        if (faculty == null) {
            return -1;
        }
        return (int) listOfStudent.stream().filter(student -> student.getFaculty().equals(faculty)).count();
    }

    /* 7
        - вывести строку в формате Фамилия, Имя, Отчество - Факультет, Группа; разделитель перенос строки, и принимает ограничение по кол-ву студентов;
     */
    public String getFormat(List<Student> students, int limit) {

        if (students == null || limit <= 0) {
            return null;
        }

        String studentString = students.stream()
                .limit(limit)
                .reduce("", (partialFirstNameResult, student) -> partialFirstNameResult + toForm(student) + ENTER, String::concat);
        return studentString;
    }

    private String toForm(Student student) {
        String form = student.getFirstName() + COMMA_WITH_SPACE
                + student.getLastName() + " - "
                + student.getFaculty() + COMMA_WITH_SPACE
                + student.getGroup() + ";";
        return form;
    }

    /* 8
        Сгруппировать данные, представив в формате Map<String, List<Student >>
     */

    public Map<String, List<Student>> getMapFaculty(List<Student> students) {
        Map<String, List<Student>> studentsInFaculty = students.stream()
                .collect(Collectors.groupingBy(Student::getFaculty));
        return studentsInFaculty;
    }

    public Map<String, List<Student>>  getMapCourse(List<Student> students) {
        Map<String, List<Student>> studentsInCourse = students.stream()
                .collect(Collectors.groupingBy(Student::getCourse));
        return studentsInCourse;
    }

    public Map<String, List<Student>>  getMapGroup(List<Student> students) {
        Map<String, List<Student>> studentsInGroup = students.stream()
                .collect(Collectors.groupingBy(Student::getGroup));
        return studentsInGroup;
    }

    /* 9
        Написать функцию, которая вернет true/false в зависимости от следующих условий
     */

    public boolean checkAllStudyFaculty(List<Student> students, String faculty) {
        if (students == null || students.size() == 0) {
            return false;
        }
        boolean allStudyInFaculty = students.stream().allMatch(student -> student.getFaculty().equals(faculty));

        return allStudyInFaculty;
    }

    public boolean checkAnyStudyFaculty(List<Student> students, String faculty) {
        if (students == null || students.size() == 0) {
            return false;
        }
        boolean anyStudyInFaculty = students.stream().anyMatch(student -> student.getFaculty().equals(faculty));
        return anyStudyInFaculty;
    }

    public boolean checkAllStudyGroup(List<Student> students, String group) {
        if (students == null || students.size() == 0) {
            return false;
        }
        boolean allStudyInGroup = students.stream().allMatch(student -> student.getGroup().equals(group));

        return allStudyInGroup;
    }

    public boolean checkAnyStudyGroup(List<Student> students, String group) {
        if (students == null || students.size() == 0) {
            return false;
        }
        boolean anyStudyInGroup = students.stream().anyMatch(student -> student.getGroup().equals(group));

        return anyStudyInGroup;
    }




}

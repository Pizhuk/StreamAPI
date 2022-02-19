package com.homeworkStream.model;

import java.util.Objects;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private int yearOfBirth;
    private String address;
    private String telephone;
    private String faculty;
    private String course;
    private String group;

    public Student(int id, String firstName, String lastName, int yearOfBirth, String address, String telephone, String faculty, String course, String group) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
        this.address = address;
        this.telephone = telephone;
        this.faculty = faculty;
        this.course = course;
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", faculty='" + faculty + '\'' +
                ", course='" + course + '\'' +
                ", group='" + group + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && yearOfBirth == student.yearOfBirth && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(address, student.address) && Objects.equals(telephone, student.telephone) && Objects.equals(faculty, student.faculty) && Objects.equals(course, student.course) && Objects.equals(group, student.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, yearOfBirth, address, telephone, faculty, course, group);
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getCourse() {
        return course;
    }

    public String getGroup() {
        return group;
    }
}

package com.homeworkStream.dataStorage;

import com.homeworkStream.model.Student;
import java.util.List;

public class Cache {

    Student student1 = new Student(1, "n1", "ln1", 1989, "Kharkiv", "+38097", "biology", "first", "g1");
    Student student2 = new Student(2, "n2", "ln2", 1999, "Kharkiv", "+38097", "biology", "first", "g1");
    Student student3 = new Student(3, "n3", "ln3", 1992, "Dnepr", "+38097", "biology", "second", "g2");
    Student student4 = new Student(4, "n4", "ln4", 1994, "Kharkiv", "+38097", "law", "first", "g1");
    Student student5 = new Student(5, "n5", "ln5", 1992, "Kharkiv", "+38097", "biology", "second", "g2");
    Student student6 = new Student(6, "n6", "ln6", 1993, "Dnepr", "+38097", "medicine", "third", "g3");
    Student student7 = new Student(7, "n7", "ln7", 1993, "Kharkiv", "+38097", "law", "fourth", "g4");
    Student student8 = new Student(8, "n8", "ln8", 1991, "Kiyv", "+38097", "medicine", "third", "g3");
    Student student9 = new Student(9, "n9", "ln9", 1988, "Kiyv", "+38097", "medicine", "third", "g3");

    List<Student> listOfStudents = List.of(student1, student2, student3, student4, student5, student6, student7, student8, student9);

    public List<Student> getListOfStudents () {
        return this.listOfStudents;
    }

}

package com.homeworkStream.service;

import com.homeworkStream.model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


class StudentMethodsTest {
    private static List<Student> listOfAllStudents;
    private static List<Student> listOfBiologyAllStudents;
    private static List<Student> listOfLawStudents;
    private static List<Student> listOfStudentsAbove1990;
    private static List<Student> listOfStudentsAbove1995;
    private static List<Student> listOfLawTransitionedToMed;
    private static List<Student> listOfGroupTransitionedToHELL;
    private static List<Student> listOfBiologyFirstCourse;
    private static List<Student> listOfMedicineThirdCourse;
    private static String studentForm;
    private static Map<String, List<Student>> STUDENTS_IN_FACULTY;
    private static Map<String, List<Student>> STUDENTS_IN_COURSE;
    private static Map<String, List<Student>> STUDENTS_IN_GROUP;


    StudentMethods cut = new StudentMethods();

    static {
        Student stu1 = new Student(1, "n1", "ln1", 1989, "Kharkiv", "+38097", "biology", "1", "g1");
        Student stu2 = new Student(2, "n2", "ln2", 1999, "Kharkiv", "+38097", "biology", "1", "g1");
        Student stu3 = new Student(3, "n3", "ln3", 1992, "Dnepr", "+38097", "biology", "2", "g2");
        Student stu4 = new Student(4, "n4", "ln4", 1994, "Kharkiv", "+38097", "law", "1", "g1");
        Student stu5 = new Student(5, "n5", "ln5", 1992, "Kharkiv", "+38097", "biology", "2", "g2");
        Student stu6 = new Student(6, "n6", "ln6", 1993, "Dnepr", "+38097", "medicine", "3", "g3");
        Student stu7 = new Student(7, "n7", "ln7", 1993, "Kharkiv", "+38097", "law", "4", "g4");
        Student stu8 = new Student(8, "n8", "ln8", 1991, "Kyiv", "+38097", "medicine", "3", "g3");
        Student stu9 = new Student(9, "n9", "ln9", 1988, "Kyiv", "+38097", "medicine", "3", "g3");

        Student stu11 = new Student(4, "n4", "ln4", 1994, "Kharkiv", "+38097", "medicine", "1", "g1");
        Student stu13 = new Student(3, "n3", "ln3", 1992, "Dnepr", "+38097", "biology", "2", "BrandFreshGroup");
        Student stu12 = new Student(7, "n7", "ln7", 1993, "Kharkiv", "+38097", "medicine", "4", "g4");
        Student stu14 = new Student(5, "n5", "ln5", 1992, "Kharkiv", "+38097", "biology", "2", "BrandFreshGroup");

        listOfAllStudents = List.of(stu1, stu2, stu3, stu4, stu5, stu6, stu7, stu8, stu9);
        listOfBiologyAllStudents = List.of(stu1, stu2, stu3, stu5);
        listOfLawStudents = List.of(stu4, stu7);
        listOfStudentsAbove1990 = List.of(stu2, stu3, stu4, stu5, stu6, stu7, stu8);
        listOfStudentsAbove1995 = List.of(stu2);
        listOfLawTransitionedToMed = List.of(stu11, stu12);
        listOfGroupTransitionedToHELL = List.of(stu13, stu14);
        listOfBiologyFirstCourse = List.of(stu1, stu2);
        listOfMedicineThirdCourse = List.of(stu6, stu8, stu9);

        studentForm =
                "n1, ln1 - biology, g1;\n" +
                "n2, ln2 - biology, g1;\n" +
                "n3, ln3 - biology, g2;\n" +
                "n4, ln4 - law, g1;\n";

        STUDENTS_IN_FACULTY = Map.of(
                "law", List.of(stu4, stu7),
                "biology", List.of(stu1, stu2, stu3, stu5),
                "medicine", List.of(stu6, stu8, stu9));

        STUDENTS_IN_COURSE = Map.of(
                "4", List.of(stu7),
                "2", List.of(stu3, stu5),
                "3", List.of(stu6, stu8, stu9),
                "1", List.of(stu1, stu2, stu4));

        STUDENTS_IN_GROUP = Map.of(
                "g1", List.of(stu1, stu2, stu4),
                "g2", List.of(stu3, stu5),
                "g3", List.of(stu6, stu8, stu9),
                "g4", List.of(stu7));
    }

    static Arguments[] getListOfStudentsFromFacultyTestArgs() {
        return new Arguments[]{
                Arguments.arguments(listOfAllStudents, "biology", listOfBiologyAllStudents),
                Arguments.arguments(listOfAllStudents, "law", listOfLawStudents),
                Arguments.arguments(listOfAllStudents, null, null),
                Arguments.arguments(null, "medicine", null),
                Arguments.arguments(listOfAllStudents, "dance", new ArrayList<>())
        };
    }

    @ParameterizedTest
    @MethodSource("getListOfStudentsFromFacultyTestArgs")
    void getListOfStudentsFromFacultyTest(List<Student> source, String faculty, List<Student> expected) {
        List<Student> actual = cut.getListOfStudentsFromFaculty(source, faculty);
        Assertions.assertEquals(expected, actual);
    }

    static Arguments[] getListFromFacultyAndCourseTestArgs() {
        return new Arguments[]{
                Arguments.arguments(listOfAllStudents, "biology", "1", listOfBiologyFirstCourse),
                Arguments.arguments(listOfAllStudents, "medicine", "3", listOfMedicineThirdCourse),
                Arguments.arguments(null, "medicine", "third", null),
                Arguments.arguments(listOfMedicineThirdCourse, "dance", "3", new ArrayList<Student>()),
                Arguments.arguments(listOfMedicineThirdCourse, null, "3", null),
                Arguments.arguments(listOfMedicineThirdCourse, "math", null, null)
        };
    }
    @ParameterizedTest
    @MethodSource("getListFromFacultyAndCourseTestArgs")
    void getListFromFacultyAndCourseTest(List<Student> source, String faculty, String course, List<Student> expected) {
        List<Student> actual = cut.getListFromFacultyAndCourse(source, faculty, course);
        Assertions.assertEquals(expected, actual);
    }

    static Arguments[] getListOfStudentsAboveCertainYearTestArgs() {
        return new Arguments[]{
                Arguments.arguments(listOfAllStudents, 1990, listOfStudentsAbove1990),
                Arguments.arguments(listOfAllStudents, 1995, listOfStudentsAbove1995),
                Arguments.arguments(listOfAllStudents, 3000, new ArrayList<>()),
                Arguments.arguments(null, 1598, null)
        };
    }

    @ParameterizedTest
    @MethodSource("getListOfStudentsAboveCertainYearTestArgs")
    void getListOfStudentsAboveCertainYearTest(List<Student> source, int ageThreshold, List<Student> expected) {
        List<Student> actual = cut.getListOfStudentsAboveCertainYear(source, ageThreshold);
        Assertions.assertEquals(expected, actual);
    }

    static Arguments[] getStudentAboveCertainBirthYearTestArgs() {
        return new Arguments[]{
                Arguments.arguments(listOfAllStudents, 1990, new Student(2, "n2", "ln2",
                        1999, "Kharkiv", "+38097", "biology", "1", "g1")),
                Arguments.arguments(listOfAllStudents, 1993, new Student(2, "n2", "ln2",
                        1999, "Kharkiv", "+38097", "biology", "1", "g1")),
                Arguments.arguments(null, 1993, null)

        };
    }

    @ParameterizedTest
    @MethodSource("getStudentAboveCertainBirthYearTestArgs")
    void getStudentAboveCertainBirthYearTest(List<Student> source, int thresholdYear, Student expected) {
        Student actual = cut.getStudentAboveCertainBirthYear(source, thresholdYear);
        Assertions.assertEquals(expected, actual);

    }

    static Arguments[] getStudentsGroupLisTestArgs() {
        return new Arguments[]{
                Arguments.arguments(listOfAllStudents, "g2", new ArrayList<>(List.of("ln3 n3", "ln5 n5"))),
                Arguments.arguments(null, "g2", null),
                Arguments.arguments(listOfAllStudents, "g3", new ArrayList<>(List.of("ln6 n6", "ln8 n8", "ln9 n9")))
        };
    }

    @MethodSource("getStudentsGroupLisTestArgs")
    @ParameterizedTest
    void getStudentsGroupListTest(List<Student> students, String group, List<String> expected) {
        List<String> actual = cut.getStudentsGroupList(students, group);
        Assertions.assertEquals(expected, actual);
    }

    static Arguments[] getAmountOfAllStudentAtFacultyTestArgs() {
        return new Arguments[]{
                Arguments.arguments(listOfAllStudents, "medicine", 3),
                Arguments.arguments(listOfAllStudents, "law", 2),
                Arguments.arguments(null, "law", -1),
                Arguments.arguments(listOfAllStudents, null, -1),
                Arguments.arguments(listOfAllStudents, "astrophysics", 0)
        };
    }

    @ParameterizedTest
    @MethodSource("getAmountOfAllStudentAtFacultyTestArgs")
    void getAmountOfAllStudentAtFacultyTest(List<Student> source, String faculty, int expected) {
        int actual = cut.getAmountOfAllStudentAtFaculty(source, faculty);
        Assertions.assertEquals(expected, actual);

    }

    static Arguments[] transferStudentsToOtherFacultyTestArgs() {
        return new Arguments[]{
                Arguments.arguments(listOfAllStudents, "law", "medicine", listOfLawTransitionedToMed),
                Arguments.arguments(listOfAllStudents, "law", "medicine", listOfLawTransitionedToMed),
                Arguments.arguments(listOfAllStudents, "demonology", "medicine", new ArrayList<>()),
                Arguments.arguments(listOfAllStudents, "", "medicine", new ArrayList<>()),
                Arguments.arguments(listOfAllStudents, "law", "", new ArrayList<>()),
                Arguments.arguments(listOfAllStudents, "law", null, new ArrayList<>()),
                Arguments.arguments(listOfAllStudents, null, "medicine", new ArrayList<>()),
                Arguments.arguments(null, "fightClub", "medicine", null),
                Arguments.arguments(null, "law", "medicine", null)
        };
    }

    @ParameterizedTest
    @MethodSource("transferStudentsToOtherFacultyTestArgs")
    void transferStudentsToOtherFaculty(List<Student> source, String facultyFrom, String facultyTo, List<Student> expected) {
        List<Student> actual = cut.transferStudentsToOtherFaculty(source, facultyFrom, facultyTo);
        Assertions.assertEquals(expected, actual);
    }

    static Arguments[] transferStudentsToOtherGroupTestArgs() {
        return new Arguments[]{
                Arguments.arguments(listOfAllStudents, "g2", "BrandFreshGroup", listOfGroupTransitionedToHELL),
                Arguments.arguments(listOfAllStudents, null, "BrandFreshGroup", new ArrayList<>()),
                Arguments.arguments(listOfAllStudents, "g2", "BrandFreshGroup", listOfGroupTransitionedToHELL),
                Arguments.arguments(listOfAllStudents, "", "BrandFreshGroup", new ArrayList<>()),
                Arguments.arguments(listOfAllStudents, "", "wave", new ArrayList<>()),
                Arguments.arguments(null, "g2", "BrandFreshGroup", null),
                Arguments.arguments(null, "666", "BrandFreshGroup", null)
        };
    }

    @ParameterizedTest
    @MethodSource("transferStudentsToOtherGroupTestArgs")
    void transferStudentsToOtherGroupTest(List<Student> source, String groupFrom, String groupTo, List<Student> expected) {
        List<Student> actual = cut.transferStudentsToOtherGroup(source, groupFrom, groupTo);
        Assertions.assertEquals(expected, actual);
    }

    static Arguments[] getNumberOfStudentsAtFacultyTestArgs() {
        return new Arguments[]{
                Arguments.arguments(listOfAllStudents, "biology", 4),
                Arguments.arguments(listOfAllStudents, "law", 2),
                Arguments.arguments(listOfAllStudents, "Gryffindor", 0),
                Arguments.arguments(null, "ATB", -1),
                Arguments.arguments(listOfAllStudents, null, -1)
        };
    }

    @ParameterizedTest
    @MethodSource("getNumberOfStudentsAtFacultyTestArgs")
    void getNumberOfStudentsAtFacultyTest(List<Student> source, String faculty, int expected) {
        int actual = cut.getNumberOfStudentsAtFaculty(source, faculty);
        Assertions.assertEquals(expected, actual);
    }

    static Arguments[] getFormatTestArgs() {
        return new Arguments[]{

                Arguments.arguments(listOfAllStudents, 4, studentForm),
                Arguments.arguments(null, 4, null),
                Arguments.arguments(listOfAllStudents, -4, null)
        };
    }

    @MethodSource("getFormatTestArgs")
    @ParameterizedTest
    void getFormatTest(List<Student> students, int limit, String expected) {

        String actual = cut.getFormat(students, limit);
        Assertions.assertEquals(expected, actual);
    }

    static Arguments[] getMapFacultyTestArgs() {
        return new Arguments[]{
                Arguments.arguments(listOfAllStudents, STUDENTS_IN_FACULTY),
        };
    }

    @MethodSource("getMapFacultyTestArgs")
    @ParameterizedTest
    void getMapFacultyTest(List<Student> students, Map<String, List<Student>> expected) {
        Map<String, List<Student>> actual = cut.getMapFaculty(students);
        Assertions.assertEquals(expected, actual);
    }

    static Arguments[] getMapCourseTestArgs() {
        return new Arguments[]{
                Arguments.arguments(listOfAllStudents, STUDENTS_IN_COURSE),
        };
    }

    @MethodSource("getMapCourseTestArgs")
    @ParameterizedTest
    void getMapCourseTest(List<Student> students, Map<String, List<Student>> expected) {
        Map<String, List<Student>> actual = cut.getMapCourse(students);
        Assertions.assertEquals(expected, actual);
    }

    static Arguments[] getMapGroupTestArgs() {
        return new Arguments[]{
                Arguments.arguments(listOfAllStudents, STUDENTS_IN_GROUP),
        };
    }

    @MethodSource("getMapGroupTestArgs")
    @ParameterizedTest
    void getMapGroupTest(List<Student> students, Map<String, List<Student>> expected) {
        Map<String, List<Student>> actual = cut.getMapGroup(students);
        Assertions.assertEquals(expected, actual);
    }

    static Arguments[] checkAllStudyFacultyTestArgs() {
        return new Arguments[]{
                Arguments.arguments(listOfBiologyAllStudents, "biology", true),
                Arguments.arguments(listOfBiologyAllStudents, "law", false),
                Arguments.arguments(listOfAllStudents, "karate", false),
                Arguments.arguments(null, "law", false),
                Arguments.arguments(new ArrayList<>(), "law", false)
        };
    }

    @MethodSource("checkAllStudyFacultyTestArgs")
    @ParameterizedTest
    void checkAllStudyFacultyTest(List<Student> students, String faculty, boolean expected) {
        boolean actual = cut.checkAllStudyFaculty(students, faculty);
        Assertions.assertEquals(expected, actual);
    }

    static Arguments[] checkAnyStudyFacultyTestArgs() {
        return new Arguments[]{

                Arguments.arguments(listOfBiologyAllStudents, "biology", true),
                Arguments.arguments(listOfBiologyAllStudents, "law", false),
                Arguments.arguments(listOfMedicineThirdCourse, "biology", false),
                Arguments.arguments(null, "law", false),
                Arguments.arguments(new ArrayList<>(), "law", false)
        };
    }

    @MethodSource("checkAnyStudyFacultyTestArgs")
    @ParameterizedTest
    void checkAnyStudyFacultyTest(List<Student> students, String faculty, boolean expected) {
        boolean actual = cut.checkAnyStudyFaculty(students, faculty);
        Assertions.assertEquals(expected, actual);
    }

    static Arguments[] checkAllStudyGroupTestArgs() {
        return new Arguments[]{
                Arguments.arguments(listOfGroupTransitionedToHELL, "BrandFreshGroup", true),
                Arguments.arguments(listOfMedicineThirdCourse, "BrandFreshGroup", false),
                Arguments.arguments(listOfBiologyFirstCourse, "2A", false),
                Arguments.arguments(null, "2A", false),
                Arguments.arguments(new ArrayList<>(), "2A", false)
        };
    }


    @MethodSource("checkAllStudyGroupTestArgs")
    @ParameterizedTest
    void checkAllStudyGroupTest(List<Student> students, String faculty, boolean expected) {
        boolean actual = cut.checkAllStudyGroup(students, faculty);
        Assertions.assertEquals(expected, actual);
    }

    static Arguments[] checkAnyStudyGroupTestArgs() {
        return new Arguments[]{
                Arguments.arguments(listOfGroupTransitionedToHELL, "BrandFreshGroup", true),
                Arguments.arguments(listOfGroupTransitionedToHELL, "2A", false),
                Arguments.arguments(listOfBiologyFirstCourse, "2A", false),
                Arguments.arguments(null, "2A", false),
                Arguments.arguments(new ArrayList<>(), "2A", false) };
    }

    @MethodSource("checkAnyStudyGroupTestArgs")
    @ParameterizedTest
    void checkAnyStudyGroupTest(List<Student> students, String faculty, boolean expected) {
        boolean actual = cut.checkAnyStudyGroup(students, faculty);
        Assertions.assertEquals(expected, actual);
    }
}
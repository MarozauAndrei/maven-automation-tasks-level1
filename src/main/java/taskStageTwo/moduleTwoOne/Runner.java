package taskStageTwo.moduleTwoOne;

import java.util.Arrays;
import java.util.List;

public class Runner {
    private static List<Student> university = Arrays.asList(
            new Student("Andrei"),
            new Student("Boris"),
            new Student("Nikolai"),
            new Student("Olga"),
            new Student("Lena"),
            new Student("Kattie")
    );

    public static void main(String[] args) {
        Faculty economicFaculty = new Faculty("Economic");
        Faculty physicalFaculty = new Faculty("Physics");
        Faculty mathematicalFaculty = new Faculty("Mathematics");
        Faculty physicalMathematicalFaculty = new Faculty("PhysMath");
        Group economicGroupOne = new Group("EconOne");
        Group economicGroupTwo = new Group("EconTwo");
        Group physicalGroupOne = new Group("PhysicOne");
        Group physicalGroupTwo = new Group("PhysicTwo");
        Group mathematicalGroupOne = new Group("MathOne");
        Group mathematicalGroupTwo = new Group("MathTwo");
        Subject mathematics = new Subject("Mathematics");
        Subject informatics = new Subject("Information");
        Subject literature = new Subject("Literature");

        AllStudents allStudents = new AllStudents(university);

        university.get(0).setFaculty(economicFaculty);
        university.get(0).setGroup(economicGroupOne);
        university.get(0).setSubjectOne(mathematics);
        university.get(0).setSubjectTwo(informatics);
        university.get(0).setMarksOfSubjectOne(Arrays.asList(3, 2, 6, 9));
        university.get(0).setMarksOfSubjectTwo(Arrays.asList(5, 6, 7, 10));

        university.get(1).setFaculty(physicalFaculty);
        university.get(1).setGroup(physicalGroupTwo);
        university.get(1).setSubjectOne(informatics);
        university.get(1).setSubjectTwo(literature);
        university.get(1).setMarksOfSubjectOne(Arrays.asList(1, 2, 3, 2));
        university.get(1).setMarksOfSubjectTwo(Arrays.asList(2, 4, 1, 1));

        university.get(2).setFaculty(mathematicalFaculty);
        university.get(2).setGroup(mathematicalGroupOne);
        university.get(2).setSubjectOne(mathematics);
        university.get(2).setSubjectThree(literature);
        university.get(2).setMarksOfSubjectOne(Arrays.asList(8, 9, 15, 0));
        university.get(2).setMarksOfSubjectThree(Arrays.asList(2, 5, 9, 3));

        university.get(3).setFaculty(mathematicalFaculty);
        university.get(3).setGroup(mathematicalGroupOne);
        university.get(3).setSubjectOne(mathematics);
        university.get(3).setSubjectTwo(informatics);
        university.get(3).setSubjectThree(literature);
        university.get(3).setMarksOfSubjectOne(Arrays.asList(4, 6, 5, 9));
        university.get(3).setMarksOfSubjectTwo(Arrays.asList(-3, 4));
        university.get(3).setMarksOfSubjectThree(Arrays.asList(4, 8, 6, 3));

        university.get(4).setFaculty(economicFaculty);
        university.get(4).setGroup(economicGroupTwo);
        university.get(4).setSubjectOne(informatics);
        university.get(4).setSubjectTwo(mathematics);
        university.get(4).setMarksOfSubjectOne(Arrays.asList(21, 8, 3, 6));
        university.get(4).setMarksOfSubjectTwo(Arrays.asList(2, 8, 1));

        university.get(5).setGroup(physicalGroupOne);

        System.out.println(university);
        System.out.println();
        allStudents.printAverageMarkOfStudent(0);
        allStudents.printAverageMarkOfStudent(3); // with Exception: wrong marks
        allStudents.printAverageMarkOfStudent(5); // with Exception: no subjects
        allStudents.printAverageMarkOfGroup(informatics, physicalGroupTwo, physicalFaculty);
        allStudents.printAverageMarkOfGroup(mathematics, mathematicalGroupOne, mathematicalFaculty);  // with Exception: wrong marks
        allStudents.printAverageMarkOfGroup(mathematics, mathematicalGroupTwo, mathematicalFaculty);  // with Exception: no students in group
        allStudents.printAverageMarkOfGroup(mathematics, physicalGroupTwo, physicalMathematicalFaculty);  // with Exception: no group in faculty
        allStudents.printAverageMarkOfUniversity(literature);
        allStudents.printAverageMarkOfUniversity(informatics);  // with Exception: wrong marks
    }
}

package taskStageTwo.moduleTwoOne;

import java.util.List;
import java.util.Objects;

public class Student {
    private String nameOfStudent;
    private Group group;
    private Faculty faculty;
    private Subject subjectOne;
    private Subject subjectTwo;
    private Subject subjectThree;
    private List<Integer> marksOfSubjectOne;
    private List<Integer> marksOfSubjectTwo;
    private List<Integer> marksOfSubjectThree;

    Student(String nameOfStudent) {
        this.nameOfStudent = nameOfStudent;
    }

    String getNameOfStudent() {
        return nameOfStudent;
    }

    Group getGroup() {
        return group;
    }

    Faculty getFaculty() {
        return faculty;
    }

    Subject getSubjectOne() {
        return subjectOne;
    }

    Subject getSubjectTwo() {
        return subjectTwo;
    }

    Subject getSubjectThree() {
        return subjectThree;
    }

    List<Integer> getMarksOfSubjectOne() {
        return marksOfSubjectOne;
    }

    List<Integer> getMarksOfSubjectTwo() {
        return marksOfSubjectTwo;
    }

    List<Integer> getMarksOfSubjectThree() {
        return marksOfSubjectThree;
    }

    void setGroup(Group group) {
        this.group = group;
    }

    void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    void setSubjectOne(Subject subjectOne) {
        this.subjectOne = subjectOne;
    }

    void setSubjectTwo(Subject subjectTwo) {
        this.subjectTwo = subjectTwo;
    }

    void setSubjectThree(Subject subjectThree) {
        this.subjectThree = subjectThree;
    }

    void setMarksOfSubjectOne(List<Integer> marksOfSubjectOne) {
        this.marksOfSubjectOne = marksOfSubjectOne;
    }

    void setMarksOfSubjectTwo(List<Integer> marksOfSubjectTwo) {
        this.marksOfSubjectTwo = marksOfSubjectTwo;
    }

    void setMarksOfSubjectThree(List<Integer> marksOfSubjectThree) {
        this.marksOfSubjectThree = marksOfSubjectThree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(nameOfStudent, student.nameOfStudent) &&
                Objects.equals(group, student.group) &&
                Objects.equals(faculty, student.faculty) &&
                Objects.equals(subjectOne, student.subjectOne) &&
                Objects.equals(subjectTwo, student.subjectTwo) &&
                Objects.equals(subjectThree, student.subjectThree) &&
                Objects.equals(marksOfSubjectOne, student.marksOfSubjectOne) &&
                Objects.equals(marksOfSubjectTwo, student.marksOfSubjectTwo) &&
                Objects.equals(marksOfSubjectThree, student.marksOfSubjectThree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfStudent, group, faculty, subjectOne, subjectTwo, subjectThree, marksOfSubjectOne, marksOfSubjectTwo, marksOfSubjectThree);
    }

    @Override
    public String toString() {
        return "Student " +
                nameOfStudent +
                ", { group=" + group +
                ", faculty=" + faculty +
                ", " + subjectOne +
                " " + marksOfSubjectOne +
                ", " + subjectTwo +
                " " + marksOfSubjectTwo +
                ", " + subjectThree +
                " " + marksOfSubjectThree +
                '}';
    }

    double getAverageMarkOfStudent() {
        int countOfSubjects = 0;
        double summOfMarks = 0.0;
        int countOfMarks = 0;
        if (subjectOne != null) {
            countOfSubjects++;
            for (Integer integer : marksOfSubjectOne) {
                if (integer<1 || integer>10) {
                    System.err.println("Marks of student " + nameOfStudent + " is wrong ");
                    throw new IllegalArgumentException();
                }
                summOfMarks += integer;
                countOfMarks++;
            }
        }
        if (subjectTwo != null) {
            countOfSubjects++;
            for (Integer integer : marksOfSubjectTwo) {
                if (integer<1 || integer>10) {
                    System.err.println("Marks of student " + nameOfStudent + " is wrong ");
                    throw new IllegalArgumentException();
                }
                summOfMarks += integer;
                countOfMarks++;
            }
        }
        if (subjectThree != null) {
            countOfSubjects++;
            for (Integer integer : marksOfSubjectThree) {
                if (integer<1 || integer>10) {
                    System.err.println("Marks of student " + nameOfStudent + " is wrong ");
                    throw new IllegalArgumentException();
                }
                summOfMarks += integer;
                countOfMarks++;
            }
        }
        if (countOfSubjects == 0) {
            System.out.println();
            System.err.println("Student " + nameOfStudent + " don't have any subject ");
            throw new NullPointerException();
        }
        if (countOfMarks == 0) return 0;
        else return summOfMarks / countOfMarks;
    }
}

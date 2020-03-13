package taskstagetwo.moduletwoone;

import java.util.List;

    class AllStudents {
    private List<Student> university;

    AllStudents(List<Student> university) {
        this.university = university;
    }

    private double getAverageMarkOfSubject(Subject subject) {
        double amountOfMarks = 0.0;
        int countOfMarks = 0;
        for (Student student : university) {
            if (student.getSubjectOne() != null && student.getSubjectOne().equals(subject)) {
                for (int i = 0; i < student.getMarksOfSubjectOne().size(); i++) {
                    if (student.getMarksOfSubjectOne().get(i) < 1 || student.getMarksOfSubjectOne().get(i) > 10) {
                        System.err.println("Subject " + subject.getNameOfSubject() + " has wrong marks '");
                        throw new IllegalArgumentException();
                    }
                    amountOfMarks += student.getMarksOfSubjectOne().get(i);
                    countOfMarks++;
                }
            }
            if (student.getSubjectTwo() != null && student.getSubjectTwo().equals(subject)) {
                for (int i = 0; i < student.getMarksOfSubjectTwo().size(); i++) {
                    if (student.getMarksOfSubjectTwo().get(i) < 1 || student.getMarksOfSubjectTwo().get(i) > 10) {
                        System.err.println("Subject " + subject.getNameOfSubject() + " has wrong marks '");
                        throw new IllegalArgumentException();
                    }
                    amountOfMarks += student.getMarksOfSubjectTwo().get(i);
                    countOfMarks++;
                }
            }
            if (student.getSubjectThree() != null && student.getSubjectThree().equals(subject)) {
                for (int i = 0; i < student.getMarksOfSubjectThree().size(); i++) {
                    if (student.getMarksOfSubjectThree().get(i) < 1 || student.getMarksOfSubjectThree().get(i) > 10) {
                        System.err.println("Subject " + subject.getNameOfSubject() + " has wrong marks '");
                        throw new IllegalArgumentException();
                    }
                    amountOfMarks += student.getMarksOfSubjectThree().get(i);
                    countOfMarks++;
                }
            }
        }
        if (countOfMarks == 0) return 0;
        else return amountOfMarks / countOfMarks;
    }
    private double getAverageMarkOfSubjectInGroup(Subject subject, Group group, Faculty faculty) {
        int countOfStudentsInGroup = 0;
        int countOfGroupsInFaculty = 0;
        int countOfFacultyInUniversity = 0;
        for (Student student : university) {
            if (student.getGroup() != null && student.getGroup().equals(group)) countOfStudentsInGroup++;
            if (student.getGroup() != null && student.getGroup().equals(group) && student.getFaculty() != null && student.getFaculty().equals(faculty)) countOfGroupsInFaculty++;
            if (student.getFaculty() != null && student.getFaculty().equals(faculty)) countOfFacultyInUniversity++;
        }
        if (countOfStudentsInGroup == 0) {
            System.err.println("There are not students in " + group.getNameOfGroup() + " group ");
            throw new NullPointerException();
        }
        if (countOfGroupsInFaculty == 0) {
            System.err.println("There are not " + group.getNameOfGroup() + " group in " + faculty.getNameOfFaculty() + " faculty ");
            throw new NullPointerException();
        }
        if (countOfFacultyInUniversity == 0) {
            System.err.println("There are not " + faculty.getNameOfFaculty() + " faculty in university ");
            throw new NullPointerException();
        }

        double amountOfMarks = 0.0;
        int countOfMarks = 0;
        for (Student student : university) {
            if (student.getSubjectOne() != null && student.getSubjectOne().equals(subject) && student.getGroup().equals(group) && student.getFaculty().equals(faculty)) {
                for (int i = 0; i < student.getMarksOfSubjectOne().size(); i++) {
                    if (student.getMarksOfSubjectOne().get(i) < 1 || student.getMarksOfSubjectOne().get(i) > 10) {
                        System.err.println("Subject " + subject.getNameOfSubject() + " has wrong marks '");
                        throw new IllegalArgumentException();
                    }
                    amountOfMarks += student.getMarksOfSubjectOne().get(i);
                    countOfMarks++;
                }
            }
            if (student.getSubjectTwo() != null && student.getSubjectTwo().equals(subject) && student.getGroup().equals(group) && student.getFaculty().equals(faculty)) {
                for (int i = 0; i < student.getMarksOfSubjectTwo().size(); i++) {
                    if (student.getMarksOfSubjectTwo().get(i) < 1 || student.getMarksOfSubjectTwo().get(i) > 10) {
                        System.err.println("Subject " + subject.getNameOfSubject() + " has wrong marks '");
                        throw new IllegalArgumentException();
                    }
                    amountOfMarks += student.getMarksOfSubjectTwo().get(i);
                    countOfMarks++;
                }
            }
            if (student.getSubjectThree() != null && student.getSubjectThree().equals(subject) && student.getGroup().equals(group) && student.getFaculty().equals(faculty)) {
                for (int i = 0; i < student.getMarksOfSubjectThree().size(); i++) {
                    if (student.getMarksOfSubjectThree().get(i) < 1 || student.getMarksOfSubjectThree().get(i) > 10) {
                        System.err.println("Subject " + subject.getNameOfSubject() + " has wrong marks '");
                        throw new IllegalArgumentException();
                    }
                    amountOfMarks += student.getMarksOfSubjectThree().get(i);
                    countOfMarks++;
                }
            }
        }
        if (countOfMarks == 0) return 0;
        else return amountOfMarks / countOfMarks;
    }
    void printAverageMarkOfStudent(int indexInList) {
        System.out.println("Average mark of student " + university.get(indexInList).getNameOfStudent() + " = " + university.get(indexInList).getAverageMarkOfStudent());
    }

    void printAverageMarkOfGroup(Subject subject, Group group, Faculty faculty) {
        System.out.println("Average mark of " + subject.getNameOfSubject() + " in " + group.getNameOfGroup() + " group of " + faculty.getNameOfFaculty() + " faculty in university = " + getAverageMarkOfSubjectInGroup(subject, group, faculty));
    }

    void printAverageMarkOfUniversity(Subject subject) {
        System.out.println("Average mark of " + subject.getNameOfSubject() + " in university = " + getAverageMarkOfSubject(subject));
    }
}

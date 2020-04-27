package taskstagetwo.moduletwoone;

import java.util.Objects;

public class Faculty {
    private String nameOfFaculty;

    Faculty(String nameOfFaculty) {
        this.nameOfFaculty = nameOfFaculty;
    }

    String getNameOfFaculty() {
        return nameOfFaculty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(nameOfFaculty, faculty.nameOfFaculty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfFaculty);
    }

    @Override
    public String toString() {
        return nameOfFaculty;
    }
}

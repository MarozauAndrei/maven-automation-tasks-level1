package taskStageTwo.moduleTwoOne;

import java.util.Objects;

public class Group {
    private String nameOfGroup;

    Group(String nameOfGroup) {
        this.nameOfGroup = nameOfGroup;
    }

    String getNameOfGroup() {
        return nameOfGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Group group = (Group) o;
        return Objects.equals(nameOfGroup, group.nameOfGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nameOfGroup);
    }

    @Override
    public String toString() {
        return nameOfGroup;
    }
}

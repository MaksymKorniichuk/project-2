package model.composite;

/**
 * Created by Maksim on 11.12.2016.
 */
public abstract class Node implements Component {
    private Character value;

    public Character getValue() {
        return value;
    }

    public void setValue(Character value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;

        Node node = (Node) o;

        return value.equals(node.value);

    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

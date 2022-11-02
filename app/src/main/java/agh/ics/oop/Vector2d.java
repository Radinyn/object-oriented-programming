package agh.ics.oop;
import java.util.Objects;

public class Vector2d {
    
    public final int x, y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return String.format("(%d,%d)", this.x, this.y);
    }

    public boolean precedes(Vector2d other) {
        return (this.x <= other.x) && (this.y <= other.y);
    }

    public boolean follows(Vector2d other) {
        return (this.x >= other.x) && (this.y >= other.y);
    }

    public boolean in_bound(Vector2d low, Vector2d high) {
        return this.follows(low) && this.precedes(high);
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    public Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    public Vector2d upperRight(Vector2d other) {
        return new Vector2d(Math.max(this.x, other.x), Math.max(this.y, other.y));
    }

    public Vector2d lowerLeft(Vector2d other) {
        return new Vector2d(Math.min(this.x, other.x), Math.min(this.y, other.y));
    }

    public Vector2d opposite() {
        return new Vector2d(-this.x, -this.y);
    }

    public boolean equals(Object other) {
        if (this == other)
          return true;
        if (!(other instanceof Vector2d))
          return false;
        Vector2d that = (Vector2d) other;
        return (that.x == this.x) && (that.y == this.y);
    }

    public int hashCode() {
        return Objects.hash(this.x) ^ Objects.hash(this.y);
    }
}

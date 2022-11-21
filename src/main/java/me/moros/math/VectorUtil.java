/*
 * Copyright 2020-2022 Moros
 *
 * This file is part of Math.
 *
 * Math is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Math is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Math. If not, see <https://www.gnu.org/licenses/>.
 */

package me.moros.math;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility class with useful vector related methods.
 */
public final class VectorUtil {
  private VectorUtil() {
  }

  private static final Vector3d[] AXES = {
    Vector3d.PLUS_I, Vector3d.PLUS_J, Vector3d.PLUS_K, Vector3d.MINUS_I, Vector3d.MINUS_J, Vector3d.MINUS_K,
    Vector3d.PLUS_I.add(Vector3d.PLUS_K).normalize(), Vector3d.PLUS_K.add(Vector3d.MINUS_I).normalize(),
    Vector3d.MINUS_I.add(Vector3d.MINUS_K).normalize(), Vector3d.MINUS_K.add(Vector3d.PLUS_I).normalize()
  };

  /**
   * Create an arc by combining {@link #rotate(Vector3d, Rotation, int)} and {@link #rotateInverse(Vector3d, Rotation, int)}.
   * Amount of rays will be rounded up to the nearest odd number. Minimum value is 3.
   * @param start the starting point
   * @param axis the axis around which to rotate
   * @param angle the rotation angle in radians
   * @param rays the amount of vectors to return, must be an odd number, minimum 3
   * @return a list consisting of all the directions for this arc
   * @see #rotateInverse(Vector3d, Rotation, int)
   */
  public static Collection<Vector3d> createArc(Vector3d start, Vector3d axis, double angle, int rays) {
    Rotation rotation = Rotation.from(axis, angle);
    rays = Math.max(3, rays);
    if (rays % 2 == 0) {
      rays++;
    }
    int half = (rays - 1) / 2;
    Collection<Vector3d> arc = new ArrayList<>(rays);
    arc.add(start);
    arc.addAll(rotate(start, rotation, half));
    arc.addAll(rotateInverse(start, rotation, half));
    return arc;
  }

  /**
   * Samples points around the perimeter of a circle around the specified axis.
   * @param start the center point
   * @param axis the axis perpendicular to the circle's plane
   * @param times the sample size of points
   * @return the points around the circle's perimeter
   */
  public static Collection<Vector3d> circle(Vector3d start, Vector3d axis, int times) {
    double angle = 2 * Math.PI / times;
    return rotate(start, axis, angle, times);
  }

  /**
   * Repeat a rotation (clockwise) on a specific vector.
   * @param start the starting point
   * @param axis the axis around which to rotate
   * @param angle the rotation angle in radians
   * @param times the amount of times to repeat the rotation
   * @return a list consisting of all the directions for this arc
   * @see #rotateInverse(Vector3d, Vector3d, double, int)
   */
  public static Collection<Vector3d> rotate(Vector3d start, Vector3d axis, double angle, int times) {
    return rotate(start, Rotation.from(axis, angle), times);
  }

  /**
   * Repeat a rotation (clockwise) on a specific vector.
   * @param start the starting point
   * @param rotation the rotation delta
   * @param times the amount of times to repeat the rotation
   * @return a list consisting of all the directions for this arc
   * @see #rotateInverse(Vector3d, Rotation, int)
   */
  public static Collection<Vector3d> rotate(Vector3d start, Rotation rotation, int times) {
    Collection<Vector3d> arc = new ArrayList<>();
    double[] vector = start.toArray();
    for (int i = 0; i < times; i++) {
      rotation.applyTo(vector, vector);
      arc.add(Vector3d.from(vector));
    }
    return arc;
  }

  /**
   * Repeat a rotation (counter-clockwise) on a specific vector.
   * @param start the starting point
   * @param axis the axis around which to rotate
   * @param angle the rotation angle in radians
   * @param times the amount of times to repeat the rotation
   * @return a list consisting of all the directions for this arc
   * @see #rotate(Vector3d, Vector3d, double, int)
   */
  public static Collection<Vector3d> rotateInverse(Vector3d start, Vector3d axis, double angle, int times) {
    return rotateInverse(start, Rotation.from(axis, angle), times);
  }

  /**
   * Repeat a rotation (counter-clockwise) on a specific vector.
   * @param start the starting point
   * @param rotation the rotation delta
   * @param times the amount of times to repeat the rotation
   * @return a list consisting of all the directions for this arc
   * @see #rotate(Vector3d, Rotation, int)
   */
  public static Collection<Vector3d> rotateInverse(Vector3d start, Rotation rotation, int times) {
    Collection<Vector3d> arc = new ArrayList<>();
    double[] vector = start.toArray();
    for (int i = 0; i < times; i++) {
      rotation.applyInverseTo(vector, vector);
      arc.add(Vector3d.from(vector));
    }
    return arc;
  }

  /**
   * Get the orthogonal vector for the specified parameters.
   * @param axis the axis perpendicular to the plane
   * @param radians the angle in radians
   * @param length the length of the resulting vector
   * @return an orthogonal vector at the specified angle and length
   */
  public static Vector3d orthogonal(Vector3d axis, double radians, double length) {
    double[] arr = {axis.y(), -axis.x(), 0};
    Rotation rotation = Rotation.from(axis, radians);
    return rotation.applyTo(Vector3d.from(arr).normalize().multiply(length));
  }

  /**
   * Rotate a vector around the X axis.
   * @param v the vector to rotate
   * @param cos the rotation's cosine
   * @param sin the rotation's sine
   * @return the resulting vector
   * @see #rotateAroundAxisY(Vector3d, double, double)
   * @see #rotateAroundAxisZ(Vector3d, double, double)
   */
  public static Vector3d rotateAroundAxisX(Vector3d v, double cos, double sin) {
    return Vector3d.of(v.x(), v.y() * cos - v.z() * sin, v.y() * sin + v.z() * cos);
  }

  /**
   * Rotate a vector around the Y axis.
   * @param v the vector to rotate
   * @param cos the rotation's cosine
   * @param sin the rotation's sine
   * @return the resulting vector
   * @see #rotateAroundAxisX(Vector3d, double, double)
   * @see #rotateAroundAxisZ(Vector3d, double, double)
   */
  public static Vector3d rotateAroundAxisY(Vector3d v, double cos, double sin) {
    return Vector3d.of(v.x() * cos + v.z() * sin, v.y(), v.x() * -sin + v.z() * cos);
  }

  /**
   * Rotate a vector around the Z axis.
   * @param v the vector to rotate
   * @param cos the rotation's cosine
   * @param sin the rotation's sine
   * @return the resulting vector
   * @see #rotateAroundAxisX(Vector3d, double, double)
   * @see #rotateAroundAxisY(Vector3d, double, double)
   */
  public static Vector3d rotateAroundAxisZ(Vector3d v, double cos, double sin) {
    return Vector3d.of(v.x() * cos - v.y() * sin, v.x() * sin + v.y() * cos, v.z());
  }

  /**
   * Given a line segment AB and a point C in space, get the closest point to the projection of C on AB.
   * If C is projected outside the line segment, the closest point will be one of the end points defining the line.
   * @param start the start point for the line segment
   * @param end the end point for the line segment
   * @param target the point to check
   * @return the closest point to target's projection on the line segment
   * @see #distanceFromLine(Vector3d, Vector3d, Vector3d)
   */
  public static Vector3d closestPoint(Vector3d start, Vector3d end, Vector3d target) {
    Vector3d toEnd = end.subtract(start);
    double t = FastMath.clamp(target.subtract(start).dot(toEnd) / toEnd.dot(toEnd), 0, 1);
    return start.add(toEnd.multiply(t));
  }

  /**
   * Get the distance between a point and a line.
   * @param line the vector defining a line
   * @param pointOnLine a point on the line
   * @param point the point to check
   * @return the distance between the point and the line
   * @see #closestPoint(Vector3d, Vector3d, Vector3d)
   */
  public static double distanceFromLine(Vector3d line, Vector3d pointOnLine, Vector3d point) {
    Vector3d temp = point.subtract(pointOnLine);
    return temp.cross(line).length() / line.length();
  }

  /**
   * Decompose diagonal vectors into their cardinal components, so they can be checked individually.
   * This is helpful for resolving collisions when moving blocks diagonally and need to consider all block faces.
   * @param origin the point of origin
   * @param direction the direction to check
   * @return a collection of normalized vectors corresponding to cardinal block faces
   */
  public static Collection<Vector3i> decomposeDiagonals(Vector3d origin, Vector3d direction) {
    Vector3i temp = origin.add(direction).toVector3i().subtract(origin.toVector3i());
    Collection<Vector3i> possibleCollisions = new ArrayList<>(3);
    int delta = FastMath.clamp(temp.blockX(), -1, 1);
    if (delta != 0) {
      possibleCollisions.add(Vector3i.of(delta, 0, 0));
    }
    delta = FastMath.clamp(temp.blockY(), -1, 1);
    if (delta != 0) {
      possibleCollisions.add(Vector3i.of(0, delta, 0));
    }
    delta = FastMath.clamp(temp.blockZ(), -1, 1);
    if (delta != 0) {
      possibleCollisions.add(Vector3i.of(0, 0, delta));
    }
    if (possibleCollisions.isEmpty()) {
      return List.of(Vector3i.ZERO);
    }
    return possibleCollisions;
  }

  /**
   * Returns a vector with a Gaussian distributed offset.
   * @param target the base vector
   * @param offset the standard deviation for the Gaussian distribution
   * @return the resulting vector
   * @see #gaussianOffset(Vector3d, double, double, double)
   */
  public static Vector3d gaussianOffset(Vector3d target, double offset) {
    return gaussianOffset(target, offset, offset, offset);
  }

  /**
   * Returns a vector with a Gaussian distributed offset for each component.
   * @param target the base vector
   * @param offsetX the standard deviation for the Gaussian distribution in the x component
   * @param offsetY the standard deviation for the Gaussian distribution in the y component
   * @param offsetZ the standard deviation for the Gaussian distribution in the z component
   * @return the resulting vector
   */
  public static Vector3d gaussianOffset(Vector3d target, double offsetX, double offsetY, double offsetZ) {
    ThreadLocalRandom r = ThreadLocalRandom.current();
    return target.add(Vector3d.of(r.nextGaussian() * offsetX, r.nextGaussian() * offsetY, r.nextGaussian() * offsetZ));
  }

  /**
   * Get the closest matching unit vector axis.
   * @param dir the vector to check
   * @return the closest matching axis
   */
  public static Vector3d nearestFace(Vector3d dir) {
    Vector3d normal = dir.normalize();
    Vector3d result = AXES[0];
    double f = Double.MIN_VALUE;
    for (Vector3d face : AXES) {
      double g = normal.dot(face);
      if (g > f) {
        f = g;
        result = face;
      }
    }
    return result;
  }
}

/*
 * Copyright 2020-2024 Moros
 *
 * This file is part of Math.
 *
 * Math is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Math is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Math. If not, see <https://www.gnu.org/licenses/>.
 */

package me.moros.math;

/**
 * Defines vector related operations.
 */
public interface VectorOperations<T extends Position> extends Position {
  /**
   * Get the norm for this instance.
   * @return Euclidean norm for the vector
   */
  default double length() {
    return Math.sqrt(lengthSq());
  }

  /**
   * Get the square of the norm for this instance.
   * @return square of the Euclidean norm for the vector
   */
  default double lengthSq() {
    return x() * x() + y() * y() + z() * z();
  }

  /**
   * Compute a vector from this instance with x coordinate set to value.
   * @param value the value to set to x coordinate
   * @return a new vector with the given x coordinate
   */
  default T withX(double value) {
    return add(value - x(), 0, 0);
  }

  /**
   * Compute a vector from this instance with y coordinate set to value.
   * @param value the value to set to y coordinate
   * @return a new vector with the given y coordinate
   */
  default T withY(double value) {
    return add(0, value - y(), 0);
  }

  /**
   * Compute a vector from this instance with z coordinate set to value.
   * @param value the value to set to z coordinate
   * @return a new vector with the given z coordinate
   */
  default T withZ(double value) {
    return add(0, 0, value - z());
  }

  /**
   * Add a vector to the instance.
   * @param p vector to add
   * @return a new vector
   */
  default T add(Position p) {
    return add(p.x(), p.y(), p.z());
  }

  /**
   * Add values to the instance.
   * @param dx the amount to add for the x coordinate
   * @param dy the amount to add for the y coordinate
   * @param dz the amount to add for the z coordinate
   * @return a new vector
   */
  T add(double dx, double dy, double dz);

  /**
   * Subtract a vector from the instance.
   * @param p vector to subtract
   * @return a new vector
   */
  default T subtract(Position p) {
    return subtract(p.x(), p.y(), p.z());
  }

  /**
   * Subtract values from the instance.
   * @param dx the amount to subtract for the x coordinate
   * @param dy the amount to subtract for the y coordinate
   * @param dz the amount to subtract for the z coordinate
   * @return a new vector
   */
  default T subtract(double dx, double dy, double dz) {
    return add(-dx, -dy, -dz);
  }

  /**
   * Multiply this instance by the components of the given vector.
   * @param p the vector to multiply by
   * @return a new vector
   */
  default T multiply(Position p) {
    return multiply(p.x(), p.y(), p.z());
  }

  /**
   * Multiply the instance by a scalar.
   * @param a scalar
   * @return a new vector
   */
  default T multiply(double a) {
    return multiply(a, a, a);
  }

  /**
   * Multiply each component by a scalar value.
   * @param ax scalar to multiply x with
   * @param ay scalar to multiply y with
   * @param az scalar to multiply z with
   * @return a new vector
   */
  T multiply(double ax, double ay, double az);

  /**
   * Get the opposite of the instance.
   * @return a new vector which is opposite to the instance
   */
  T negate();

  /**
   * Compute the cross-product of this instance with the given vector.
   * @param v the other vector
   * @return the cross product this.v
   */
  T cross(Position v);

  /**
   * Compute the dot-product of this instance with the given vector.
   * @param p the other vector
   * @return the dot product
   */
  default double dot(Position p) {
    return x() * p.x() + y() * p.y() + z() * p.z();
  }

  /**
   * Compute the angular separation between this and another vector.
   * @param v the other vector
   * @return angular separation between this and v or zero if either vector has a null norm
   */
  default double angle(Vector3d v) {
    double normProduct = length() * v.length();
    if (normProduct == 0) {
      return 0;
    }
    double dot = Math.min(Math.max(dot(v) / normProduct, -1), 1);
    return Math.acos(dot);
  }

  /**
   * Compute a new vector using the minimum components of this instance and another vector.
   * @param v the other vector
   * @return a new vector
   */
  T min(Position v);

  /**
   * Compute a new vector using the maximum components of this instance and another vector.
   * @param v the other vector
   * @return a new vector
   */
  T max(Position v);

  /**
   * Compute a new vector using the absolute value for each component of the instance.
   * @return a new vector
   */
  T abs();

  /**
   * Compute a new vector using the floored value for each component of the instance.
   * @return a new vector
   */
  T floor();

  /**
   * Compute a new vector with clamped components to be used for velocity purposes.
   * @return the clamped velocity vector
   */
  T clampVelocity();
}


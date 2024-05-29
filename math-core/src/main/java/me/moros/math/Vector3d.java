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

import me.moros.math.adapter.Adapters;

/**
 * Immutable 3D Vector with double precision floating point coordinates.
 */
public interface Vector3d extends VectorOperations<Vector3d> {
  /**
   * [0, 0, 0]
   */
  Vector3d ZERO = of(0, 0, 0);
  /**
   * [1, 1, 1]
   */
  Vector3d ONE = of(1, 1, 1);
  /**
   * [1, 0, 0]
   */
  Vector3d PLUS_I = of(1, 0, 0);
  /**
   * [-1, 0, 0]
   */
  Vector3d MINUS_I = of(-1, 0, 0);
  /**
   * [0, 1, 0]
   */
  Vector3d PLUS_J = of(0, 1, 0);
  /**
   * [0, -1, 0]
   */
  Vector3d MINUS_J = of(0, -1, 0);
  /**
   * [0, 0, 1]
   */
  Vector3d PLUS_K = of(0, 0, 1);
  /**
   * [0, 0, -1]
   */
  Vector3d MINUS_K = of(0, 0, -1);

  @Override
  default int blockX() {
    return FastMath.floor(x());
  }

  @Override
  default int blockY() {
    return FastMath.floor(y());
  }

  @Override
  default int blockZ() {
    return FastMath.floor(z());
  }

  @Override
  default Vector3d toVector3d() {
    return this;
  }

  @Override
  default Vector3d add(double dx, double dy, double dz) {
    return of(x() + dx, y() + dy, z() + dz);
  }

  @Override
  default Vector3d multiply(double ax, double ay, double az) {
    return of(ax * x(), ay * y(), az * z());
  }

  @Override
  default Vector3d negate() {
    return of(-x(), -y(), -z());
  }

  @Override
  default Vector3d cross(Position v) {
    double newX = y() * v.z() - v.y() * z();
    double newY = z() * v.x() - v.z() * x();
    double newZ = x() * v.y() - v.x() * y();
    return of(newX, newY, newZ);
  }

  @Override
  default Vector3d min(Position v) {
    return of(Math.min(x(), v.x()), Math.min(y(), v.y()), Math.min(z(), v.z()));
  }

  @Override
  default Vector3d max(Position v) {
    return of(Math.max(x(), v.x()), Math.max(y(), v.y()), Math.max(z(), v.z()));
  }

  @Override
  default Vector3d abs() {
    return of(Math.abs(x()), Math.abs(y()), Math.abs(z()));
  }

  @Override
  default Vector3d floor() {
    return of(blockX(), blockY(), blockZ());
  }

  @Override
  default Vector3d clampVelocity() {
    double clampedX = Math.clamp(x(), -4, 4);
    double clampedY = Math.clamp(y(), -4, 4);
    double clampedZ = Math.clamp(z(), -4, 4);
    return of(clampedX, clampedY, clampedZ);
  }

  /**
   * Get a normalized vector aligned with the instance. If norm is zero it will default to {@link #PLUS_I}
   * @return a new normalized vector
   */
  default Vector3d normalize() {
    return normalize(PLUS_I);
  }

  /**
   * Get a normalized vector aligned with the instance.
   * @param def the default vector to return if norm is zero
   * @return a new normalized vector
   */
  default Vector3d normalize(Vector3d def) {
    double s = length();
    if (s == 0) {
      return def;
    }
    return multiply(1 / s);
  }

  @Override
  default Adapters<Vector3d> adapters() {
    return Adapters.vector3d();
  }

  /**
   * Create a vector from its coordinates.
   * @param x the x coordinate
   * @param y the y coordinate
   * @param z the z coordinate
   * @return a new vector with the given coordinates
   */
  static Vector3d of(double x, double y, double z) {
    return new DoublePoint(x, y, z);
  }

  /**
   * Create a vector from an array of coordinates.
   * @param v coordinates array
   * @return a new vector with the given coordinates
   * @throws IllegalArgumentException if array does not have 3 elements
   * @see #toArray()
   */
  static Vector3d from(double[] v) {
    if (v.length != 3) {
      throw new IllegalArgumentException("Expected array length " + 3 + " found " + v.length);
    }
    return of(v[0], v[1], v[2]);
  }

  /**
   * Create a vector from another object.
   * @param object the object to adapt from
   * @param <T> type of object
   * @return a new vector with the adapted object's components
   */
  static <T> Vector3d from(T object) {
    return Adapters.vector3d().adapt(object);
  }
}

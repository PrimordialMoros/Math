/*
 * Copyright 2020-2023 Moros
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
 * Immutable 3D Vector with integer coordinates.
 */
public interface Vector3i extends VectorOperations<Vector3i> {
  /**
   * [0, 0, 0]
   */
  Vector3i ZERO = of(0, 0, 0);
  /**
   * [1, 1, 1]
   */
  Vector3i ONE = of(1, 1, 1);
  /**
   * [1, 0, 0]
   */
  Vector3i PLUS_I = of(1, 0, 0);
  /**
   * [-1, 0, 0]
   */
  Vector3i MINUS_I = of(-1, 0, 0);
  /**
   * [0, 1, 0]
   */
  Vector3i PLUS_J = of(0, 1, 0);
  /**
   * [0, -1, 0]
   */
  Vector3i MINUS_J = of(0, -1, 0);
  /**
   * [0, 0, 1]
   */
  Vector3i PLUS_K = of(0, 0, 1);
  /**
   * [0, 0, -1]
   */
  Vector3i MINUS_K = of(0, 0, -1);

  @Override
  default double x() {
    return blockX();
  }

  @Override
  default double y() {
    return blockY();
  }

  @Override
  default double z() {
    return blockZ();
  }

  @Override
  default Vector3i toVector3i() {
    return this;
  }

  @Override
  default Vector3i add(double dx, double dy, double dz) {
    return of(x() + dx, y() + dy, z() + dz);
  }

  @Override
  default Vector3i multiply(double ax, double ay, double az) {
    return of(ax * x(), ay * y(), az * z());
  }

  @Override
  default Vector3i negate() {
    return of(-blockX(), -blockY(), -blockZ());
  }

  @Override
  default Vector3i cross(Position v) {
    int newX = blockY() * v.blockZ() - v.blockY() * blockZ();
    int newY = blockZ() * v.blockX() - v.blockZ() * blockX();
    int newZ = blockX() * v.blockY() - v.blockX() * blockY();
    return of(newX, newY, newZ);
  }

  @Override
  default Vector3i min(Position v) {
    return of(Math.min(blockX(), v.blockX()), Math.min(blockY(), v.blockY()), Math.min(blockZ(), v.blockZ()));
  }

  @Override
  default Vector3i max(Position v) {
    return of(Math.max(blockX(), v.blockX()), Math.max(blockY(), v.blockY()), Math.max(blockZ(), v.blockZ()));
  }

  @Override
  default Vector3i abs() {
    return of(Math.abs(blockX()), Math.abs(blockY()), Math.abs(blockZ()));
  }

  @Override
  default Vector3i floor() {
    return this;
  }

  @Override
  default Vector3i clampVelocity() {
    int clampedX = FastMath.clamp(blockX(), -4, 4);
    int clampedY = FastMath.clamp(blockY(), -4, 4);
    int clampedZ = FastMath.clamp(blockZ(), -4, 4);
    return of(clampedX, clampedY, clampedZ);
  }

  @Override
  default Adapters<Vector3i> adapters() {
    return Adapters.vector3i();
  }

  /**
   * Create a vector from its coordinates.
   * @param x the x coordinate
   * @param y the y coordinate
   * @param z the z coordinate
   * @return a new vector with the given coordinates
   */
  static Vector3i of(int x, int y, int z) {
    return new IntPoint(x, y, z);
  }

  /**
   * Create a vector from its coordinates.
   * @param x the x coordinate
   * @param y the y coordinate
   * @param z the z coordinate
   * @return a new vector with the given coordinates
   */
  static Vector3i of(double x, double y, double z) {
    return of(FastMath.floor(x), FastMath.floor(y), FastMath.floor(z));
  }

  /**
   * Create a vector from an array of coordinates.
   * @param v coordinates array
   * @return a new vector with the given coordinates
   * @throws IllegalArgumentException if array does not have 3 elements
   * @see #toIntArray()
   */
  static Vector3i from(int[] v) throws IllegalArgumentException {
    if (v.length != 3) {
      throw new IllegalArgumentException();
    }
    return of(v[0], v[1], v[2]);
  }

  /**
   * Create a vector from another object.
   * @param object the object to adapt from
   * @param <T> type of object
   * @return a new vector with the adapted object's components
   */
  static <T> Vector3i from(T object) {
    return Adapters.vector3i().adapt(object);
  }
}

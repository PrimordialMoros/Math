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
 * Represents a position in 3D space.
 */
public interface Position {
  /**
   * Get the x coordinate.
   * @return the x coordinate
   */
  double x();

  /**
   * Get the y coordinate.
   * @return the y coordinate
   */
  double y();

  /**
   * Get the z coordinate.
   * @return the z coordinate
   */
  double z();

  /**
   * Get the x coordinate.
   * @return the x coordinate
   */
  int blockX();

  /**
   * Get the y coordinate.
   * @return the y coordinate
   */
  int blockY();

  /**
   * Get the z coordinate.
   * @return the z coordinate
   */
  int blockZ();

  /**
   * Compute the distance between the instance and another position.
   * @param v second position
   * @return the distance between the instance and p
   */
  default double distance(Position v) {
    return Math.sqrt(distanceSq(v));
  }

  /**
   * Compute the square of the distance between the instance and another position.
   * @param v second position
   * @return the square of the distance between the instance and p
   */
  default double distanceSq(Position v) {
    double dx = v.x() - x();
    double dy = v.y() - y();
    double dz = v.z() - z();
    return dx * dx + dy * dy + dz * dz;
  }

  /**
   * Create an integer vector from this instance.
   * @return a new vector
   */
  default Vector3i toVector3i() {
    return Vector3i.of(blockX(), blockY(), blockZ());
  }

  /**
   * Create a double floating point precision vector from this instance.
   * @return a new vector
   */
  default Vector3d toVector3d() {
    return Vector3d.of(x(), y(), z());
  }

  /**
   * Compute a new vector pointing at its block center (each component is floored and offset by 0.5).
   * @return a new vector
   */
  default Vector3d center() {
    return Vector3d.of(blockX() + 0.5, blockY() + 0.5, blockZ() + 0.5);
  }

  /**
   * Create an adapted object from this instance.
   * @param nativeType the type to adapt to
   * @param <T> the class type
   * @return the adapted object
   */
  default <T> T to(Class<T> nativeType) {
    return adapters().converter(nativeType).apply(this);
  }

  /**
   * Get the position coordinates as a dimension 3 array.
   * @return position coordinates
   */
  default double[] toArray() {
    return new double[]{x(), y(), z()};
  }

  /**
   * Get the position integer coordinates as a dimension 3 array.
   * @return position integer coordinates
   */
  default int[] toIntArray() {
    return new int[]{blockX(), blockY(), blockZ()};
  }

  /**
   * Get the minimum component.
   * @return the minimum component
   */
  default double minComponent() {
    return Math.min(x(), Math.min(y(), z()));
  }

  /**
   * Get the maximum component.
   * @return the maximum component
   */
  default double maxComponent() {
    return Math.max(x(), Math.max(y(), z()));
  }

  Adapters<? extends Position> adapters();
}

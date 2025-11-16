/*
 * Copyright 2020-2025 Moros
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
 * Represents a quaternion.
 */
public interface Quaternion {
  /**
   * The scalar part of the quaternion.
   * @return the scalar part
   */
  double q0();

  /**
   * The first vector part of the quaternion.
   * @return the first vector part
   */
  double q1();

  /**
   * The second vector part of the quaternion.
   * @return the second vector part
   */
  double q2();

  /**
   * The third vector part of the quaternion.
   * @return the third vector part
   */
  double q3();

  /**
   * Array representation of this quaternion.
   * The first double represents the scalar coordinate of the quaternion while the last three are the vector parts.
   * @return an array representation of this quaternion
   */
  default double[] toArray() {
    return new double[]{q0(), q1(), q2(), q3()};
  }
}

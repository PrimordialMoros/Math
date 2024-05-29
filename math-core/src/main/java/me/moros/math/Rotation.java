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
 * Immutable representation of rotation in 3D space.
 */
public interface Rotation extends Quaternion {
  /**
   * Build a rotation from an axis and an angle.
   * @param axis the axis around which to rotate
   * @param angle the rotation angle
   * @return a rotation instance
   * @throws IllegalArgumentException if the axis length is zero
   */
  static Rotation from(Vector3d axis, double angle) throws IllegalArgumentException {
    double norm = axis.length();
    if (norm == 0) {
      throw new IllegalArgumentException();
    }
    double halfAngle = -0.5 * angle;
    double coeff = Math.sin(halfAngle) / norm;
    return new DoubleQuaternion(Math.cos(halfAngle), coeff * axis.x(), coeff * axis.y(), coeff * axis.z());
  }

  /**
   * Get the matrix for this rotation.
   * @return the 3x3 matrix corresponding to this instance
   */
  double[][] getMatrix();

  /**
   * Apply the rotation to a vector.
   * @param p vector to apply the rotation to
   * @return a new vector which is rotated
   */
  default Vector3d applyTo(Position p) {
    return applyTo(p.x(), p.y(), p.z());
  }

  /**
   * Apply the rotation to a vector stored in an array.
   * @param in an array with three items which stores vector to rotate
   * @param out an array with three items to put result to (it can be the same array as in)
   */
  default void applyTo(final double[] in, final double[] out) {
    Vector3d result = applyTo(in[0], in[1], in[2]);
    out[0] = result.x();
    out[1] = result.y();
    out[2] = result.z();
  }

  /**
   * Apply the rotation to a vector.
   * @param x the x coordinate to apply the rotation to
   * @param y the y coordinate to apply the rotation to
   * @param z the z coordinate to apply the rotation to
   * @return a new vector which is rotated
   */
  Vector3d applyTo(double x, double y, double z);

  /**
   * Apply the inverse of the rotation to a vector.
   * @param p position to apply the inverse of the rotation to
   * @return a new vector which is inversely rotated
   */
  default Vector3d applyInverseTo(Position p) {
    return applyInverseTo(p.x(), p.y(), p.z());
  }

  /**
   * Apply the inverse of the rotation to a vector stored in an array.
   * @param in an array with three items which stores vector to rotate
   * @param out an array with three items to put result to (it can be the same array as in)
   */
  default void applyInverseTo(final double[] in, final double[] out) {
    Vector3d result = applyInverseTo(in[0], in[1], in[2]);
    out[0] = result.x();
    out[1] = result.y();
    out[2] = result.z();
  }

  /**
   * Apply the rotation to a vector.
   * @param x the x coordinate to apply the rotation to
   * @param y the y coordinate to apply the rotation to
   * @param z the z coordinate to apply the rotation to
   * @return a new vector which is inversely rotated
   */
  Vector3d applyInverseTo(double x, double y, double z);

  /**
   * Apply the instance to another rotation.
   * @param r rotation to apply the rotation to
   * @return a new rotation which is the composition of r by the instance
   */
  Rotation applyTo(Rotation r);

  /**
   * Apply the inverse of the instance to another rotation.
   * @param r rotation to apply the rotation to
   * @return a new rotation which is the composition of r by the inverse of the instance
   */
  Rotation applyInverseTo(Rotation r);
}

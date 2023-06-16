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

record DoubleQuaternion(double q0, double q1, double q2, double q3) implements Rotation {
  @Override
  public double[][] getMatrix() {
    // products
    double q0q0 = q0 * q0;
    double q0q1 = q0 * q1;
    double q0q2 = q0 * q2;
    double q0q3 = q0 * q3;
    double q1q1 = q1 * q1;
    double q1q2 = q1 * q2;
    double q1q3 = q1 * q3;
    double q2q2 = q2 * q2;
    double q2q3 = q2 * q3;
    double q3q3 = q3 * q3;
    // create the matrix
    double[][] m = new double[3][];
    m[0] = new double[3];
    m[1] = new double[3];
    m[2] = new double[3];

    m[0][0] = 2.0 * (q0q0 + q1q1) - 1.0;
    m[1][0] = 2.0 * (q1q2 - q0q3);
    m[2][0] = 2.0 * (q1q3 + q0q2);

    m[0][1] = 2.0 * (q1q2 + q0q3);
    m[1][1] = 2.0 * (q0q0 + q2q2) - 1.0;
    m[2][1] = 2.0 * (q2q3 - q0q1);

    m[0][2] = 2.0 * (q1q3 - q0q2);
    m[1][2] = 2.0 * (q2q3 + q0q1);
    m[2][2] = 2.0 * (q0q0 + q3q3) - 1.0;
    return m;
  }

  @Override
  public Vector3d applyTo(double x, double y, double z) {
    return apply(x, y, z, q0);
  }

  @Override
  public Vector3d applyInverseTo(double x, double y, double z) {
    return apply(x, y, z, -q0);
  }

  @Override
  public Rotation applyTo(Rotation r) {
    return apply(r, r.q0(), r.q1(), r.q2(), r.q3());
  }

  @Override
  public Rotation applyInverseTo(Rotation r) {
    return apply(r, -r.q0(), -r.q1(), -r.q2(), -r.q3());
  }

  private Vector3d apply(double x, double y, double z, double m0) {
    double s = q1 * x + q2 * y + q3 * z;
    return Vector3d.of(2 * (m0 * (x * m0 - (q2 * z - q3 * y)) + s * q1) - x,
      2 * (m0 * (y * m0 - (q3 * x - q1 * z)) + s * q2) - y,
      2 * (m0 * (z * m0 - (q1 * y - q2 * x)) + s * q3) - z);
  }

  private Rotation apply(Rotation r, double m0, double m1, double m2, double m3) {
    return new DoubleQuaternion(m0 * q0 - (r.q1() * q1 + r.q2() * q2 + r.q3() * q3),
      m1 * q0 + r.q0() * q1 + (r.q2() * q3 - r.q3() * q2),
      m2 * q0 + r.q0() * q2 + (r.q3() * q1 - r.q1() * q3),
      m3 * q0 + r.q0() * q3 + (r.q1() * q2 - r.q2() * q1));
  }
}

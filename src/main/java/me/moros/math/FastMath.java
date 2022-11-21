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

/**
 * Math utility for rounding numbers according to Minecraft's coordinate system.
 */
public final class FastMath {
  private FastMath() {
  }

  /**
   * Floor the given value.
   * @param value the value to floor
   * @return the rounded down value
   */
  public static int floor(double value) {
    int y = (int) value;
    return value < y ? y - 1 : y;
  }

  /**
   * Ceil the given value.
   * @param value the value to ceil
   * @return the rounded up value
   */
  public static int ceil(double value) {
    int y = (int) value;
    return value > y ? y + 1 : y;
  }

  /**
   * Round up the given value.
   * @param value the value to round
   * @return the rounded up value
   */
  public static int round(double value) {
    return floor(value + 0.5);
  }

  /**
   * Clamp value between bounds.
   * @param value the value to clamp
   * @param min the minimum value
   * @param max the maximum value
   * @return the result
   */
  public static int clamp(int value, int min, int max) {
    return Math.min(max, Math.max(min, value));
  }

  /**
   * Clamp value between bounds.
   * @param value the value to clamp
   * @param min the minimum value
   * @param max the maximum value
   * @return the result
   */
  public static long clamp(long value, long min, long max) {
    return Math.min(max, Math.max(min, value));
  }

  /**
   * Clamp value between bounds.
   * @param value the value to clamp
   * @param min the minimum value
   * @param max the maximum value
   * @return the result
   */
  public static float clamp(float value, float min, float max) {
    return Math.min(max, Math.max(min, value));
  }

  /**
   * Clamp value between bounds.
   * @param value the value to clamp
   * @param min the minimum value
   * @param max the maximum value
   * @return the result
   */
  public static double clamp(double value, double min, double max) {
    return Math.min(max, Math.max(min, value));
  }
}

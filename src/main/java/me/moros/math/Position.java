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

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

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
  default int blockX() {
    return FastMath.floor(x());
  }

  /**
   * Get the y coordinate.
   * @return the y coordinate
   */
  default int blockY() {
    return FastMath.floor(y());
  }

  /**
   * Get the z coordinate.
   * @return the z coordinate
   */
  default int blockZ() {
    return FastMath.floor(z());
  }

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
   * Create a bukkit vector with clamped components to be used for velocity purposes.
   * @return the clamped velocity vector
   */
  default Vector clampVelocity() {
    double clampedX = FastMath.clamp(x(), -4, 4);
    double clampedY = FastMath.clamp(y(), -4, 4);
    double clampedZ = FastMath.clamp(z(), -4, 4);
    return new Vector(clampedX, clampedY, clampedZ);
  }

  /**
   * Create a bukkit vector from this instance.
   * @return the bukkit vector
   */
  default Vector toBukkitVector() {
    return new Vector(x(), y(), z());
  }

  /**
   * Create a location from this instance
   * @param world the world for the location
   * @return the bukkit location
   */
  default Location toLocation(World world) {
    return new Location(world, x(), y(), z());
  }

  /**
   * Get the block at this position for the given world
   * @param world the world to check
   * @return the block
   */
  default Block toBlock(World world) {
    return world.getBlockAt(blockX(), blockY(), blockZ());
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
}

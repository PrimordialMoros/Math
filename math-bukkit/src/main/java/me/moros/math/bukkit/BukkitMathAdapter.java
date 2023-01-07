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

package me.moros.math.bukkit;

import me.moros.math.Position;
import me.moros.math.Vector3d;
import me.moros.math.Vector3i;
import me.moros.math.adapter.Adapters;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

/**
 * Math adapter for Bukkit {@link Vector vectors}, {@link Location locations} and {@link Block blocks}.
 */
public enum BukkitMathAdapter {
  INSTANCE;

  public Vector vec(Position p) {
    return new Vector(p.x(), p.y(), p.z());
  }

  public Location loc(World world, Position p) {
    return new Location(world, p.x(), p.y(), p.z());
  }

  public Block block(World world, Position p) {
    return world.getBlockAt(p.blockX(), p.blockY(), p.blockZ());
  }

  public Vector3d fromVec(Vector vec) {
    return Vector3d.of(vec.getX(), vec.getY(), vec.getZ());
  }

  public Vector3d fromLoc(Location loc) {
    return Vector3d.of(loc.getX(), loc.getY(), loc.getZ());
  }

  public Vector3d fromBlock(Block block) {
    return Vector3d.of(block.getX(), block.getY(), block.getZ());
  }

  public Vector3i intFromVec(Vector vec) {
    return Vector3i.of(vec.getBlockX(), vec.getBlockY(), vec.getBlockZ());
  }

  public Vector3i intFromLoc(Location loc) {
    return Vector3i.of(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
  }

  public Vector3i intFromBlock(Block block) {
    return Vector3i.of(block.getX(), block.getY(), block.getZ());
  }

  public static void register() {
    Adapters.vector3d().register(Vector.class, INSTANCE::fromVec, INSTANCE::vec);
    Adapters.vector3d().register(Location.class, INSTANCE::fromLoc, INSTANCE::loc);
    Adapters.vector3d().register(Block.class, INSTANCE::fromBlock, INSTANCE::block);

    Adapters.vector3i().register(Vector.class, INSTANCE::intFromVec, INSTANCE::vec);
    Adapters.vector3i().register(Location.class, INSTANCE::intFromLoc, INSTANCE::loc);
    Adapters.vector3i().register(Block.class, INSTANCE::intFromBlock, INSTANCE::block);
  }
}

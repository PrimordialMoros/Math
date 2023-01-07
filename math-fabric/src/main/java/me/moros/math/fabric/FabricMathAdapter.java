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

package me.moros.math.fabric;

import me.moros.math.Position;
import me.moros.math.Vector3d;
import me.moros.math.Vector3i;
import me.moros.math.adapter.Adapters;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.phys.Vec3;

/**
 * Math adapter for Fabric {@link Vec3 real vectors}, {@link Vec3i int vectors} and {@link BlockPos block positions}.
 */
public enum FabricMathAdapter {
  INSTANCE;

  public Vec3 vec(Position p) {
    return new Vec3(p.x(), p.y(), p.z());
  }

  public Vec3i vec3i(Position p) {
    return new Vec3i(p.blockX(), p.blockY(), p.blockZ());
  }

  public BlockPos blockPos(Position p) {
    return new BlockPos(p.blockX(), p.blockY(), p.blockZ());
  }

  public Vector3d fromVec(Vec3 vec) {
    return Vector3d.of(vec.x(), vec.y(), vec.z());
  }

  public Vector3d fromVec3i(Vec3i vec) {
    return Vector3d.of(vec.getX(), vec.getY(), vec.getZ());
  }

  public Vector3d fromBlockPos(BlockPos blockPos) {
    return Vector3d.of(blockPos.getX(), blockPos.getY(), blockPos.getZ());
  }

  public Vector3i intFromVec(Vec3 vec) {
    return Vector3i.of(vec.x(), vec.y(), vec.z());
  }

  public Vector3i intFromVec3i(Vec3i vec) {
    return Vector3i.of(vec.getX(), vec.getY(), vec.getZ());
  }

  public Vector3i intFromBlockPos(BlockPos blockPos) {
    return Vector3i.of(blockPos.getX(), blockPos.getY(), blockPos.getZ());
  }

  public static void register() {
    Adapters.vector3d().register(Vec3.class, INSTANCE::fromVec, INSTANCE::vec);
    Adapters.vector3d().register(Vec3i.class, INSTANCE::fromVec3i, INSTANCE::vec3i);
    Adapters.vector3d().register(BlockPos.class, INSTANCE::fromBlockPos, INSTANCE::blockPos);

    Adapters.vector3i().register(Vec3.class, INSTANCE::intFromVec, INSTANCE::vec);
    Adapters.vector3i().register(Vec3i.class, INSTANCE::intFromVec3i, INSTANCE::vec3i);
    Adapters.vector3i().register(BlockPos.class, INSTANCE::intFromBlockPos, INSTANCE::blockPos);
  }
}

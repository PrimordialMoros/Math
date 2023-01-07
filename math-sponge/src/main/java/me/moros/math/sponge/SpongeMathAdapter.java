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

package me.moros.math.sponge;

import me.moros.math.Position;
import me.moros.math.Vector3d;
import me.moros.math.Vector3i;
import me.moros.math.adapter.Adapters;
import org.spongepowered.api.world.LocatableBlock;
import org.spongepowered.api.world.server.ServerLocation;
import org.spongepowered.api.world.server.ServerWorld;

/**
 * Math adapter for Sponge {@link org.spongepowered.math.vector.Vector3d vectors}, {@link ServerLocation locations} and {@link LocatableBlock blocks}.
 */
public enum SpongeMathAdapter {
  INSTANCE;

  public org.spongepowered.math.vector.Vector3d vec(Position p) {
    return org.spongepowered.math.vector.Vector3d.from(p.x(), p.y(), p.z());
  }

  public ServerLocation loc(ServerWorld world, Position p) {
    return ServerLocation.of(world, p.x(), p.y(), p.z());
  }

  public LocatableBlock block(ServerWorld world, Position p) {
    return world.locatableBlock(p.blockX(), p.blockY(), p.blockZ());
  }

  public Vector3d fromVec(org.spongepowered.math.vector.Vector3d vec) {
    return Vector3d.of(vec.x(), vec.y(), vec.z());
  }

  public Vector3d fromLoc(ServerLocation loc) {
    return Vector3d.of(loc.x(), loc.y(), loc.z());
  }

  public Vector3d fromBlock(LocatableBlock block) {
    return Vector3d.of(block.blockPosition().x(), block.blockPosition().y(), block.blockPosition().z());
  }

  public Vector3i intFromVec(org.spongepowered.math.vector.Vector3d vec) {
    return Vector3i.of(vec.x(), vec.y(), vec.z());
  }

  public Vector3i intFromLoc(ServerLocation loc) {
    return Vector3i.of(loc.blockX(), loc.blockY(), loc.blockZ());
  }

  public Vector3i intFromBlock(LocatableBlock block) {
    return Vector3i.of(block.blockPosition().x(), block.blockPosition().y(), block.blockPosition().z());
  }

  public static void register() {
    Adapters.vector3d().register(org.spongepowered.math.vector.Vector3d.class, INSTANCE::fromVec, INSTANCE::vec);
    Adapters.vector3d().register(ServerLocation.class, INSTANCE::fromLoc, INSTANCE::loc);
    Adapters.vector3d().register(LocatableBlock.class, INSTANCE::fromBlock, INSTANCE::block);

    Adapters.vector3i().register(org.spongepowered.math.vector.Vector3d.class, INSTANCE::intFromVec, INSTANCE::vec);
    Adapters.vector3i().register(ServerLocation.class, INSTANCE::intFromLoc, INSTANCE::loc);
    Adapters.vector3i().register(LocatableBlock.class, INSTANCE::intFromBlock, INSTANCE::block);
  }
}

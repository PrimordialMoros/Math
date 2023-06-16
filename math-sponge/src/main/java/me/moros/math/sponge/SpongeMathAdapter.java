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
import org.spongepowered.api.world.Locatable;

/**
 * Math adapter for Sponge.
 */
public enum SpongeMathAdapter {
  INSTANCE;

  public org.spongepowered.math.vector.Vector3d vec(Position p) {
    return org.spongepowered.math.vector.Vector3d.from(p.x(), p.y(), p.z());
  }

  public Vector3d fromVec(org.spongepowered.math.vector.Vector3d vec) {
    return Vector3d.of(vec.x(), vec.y(), vec.z());
  }

  public Vector3d fromLoc(Locatable locatable) {
    var loc = locatable.location();
    return Vector3d.of(loc.x(), loc.y(), loc.z());
  }

  public org.spongepowered.math.vector.Vector3i intVec(Position p) {
    return org.spongepowered.math.vector.Vector3i.from(p.blockX(), p.blockY(), p.blockZ());
  }

  public Vector3i intFromVec(org.spongepowered.math.vector.Vector3i vec) {
    return Vector3i.of(vec.x(), vec.y(), vec.z());
  }

  public Vector3i intFromLoc(Locatable locatable) {
    var loc = locatable.blockPosition();
    return Vector3i.of(loc.x(), loc.y(), loc.z());
  }

  public static void register() {
    Adapters.vector3d().register(org.spongepowered.math.vector.Vector3d.class, INSTANCE::fromVec, INSTANCE::vec);
    Adapters.vector3d().registerAdapter(Locatable.class, INSTANCE::fromLoc);

    Adapters.vector3i().register(org.spongepowered.math.vector.Vector3i.class, INSTANCE::intFromVec, INSTANCE::intVec);
    Adapters.vector3i().registerAdapter(Locatable.class, INSTANCE::intFromLoc);
  }
}

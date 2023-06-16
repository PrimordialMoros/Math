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

package me.moros.math.minestom;

import me.moros.math.Position;
import me.moros.math.Vector3d;
import me.moros.math.Vector3i;
import me.moros.math.adapter.Adapters;
import net.minestom.server.coordinate.Vec;

/**
 * Math adapter for Minestom.
 */
public enum MinestomMathAdapter {
  INSTANCE;

  public Vec vec(Position p) {
    return new Vec(p.x(), p.y(), p.z());
  }

  public Vector3d fromVec(Vec vec) {
    return Vector3d.of(vec.x(), vec.y(), vec.z());
  }

  public Vector3i intFromVec(Vec vec) {
    return Vector3i.of(vec.blockX(), vec.blockY(), vec.blockZ());
  }

  public static void register() {
    Adapters.vector3d().register(Vec.class, INSTANCE::fromVec, INSTANCE::vec);

    Adapters.vector3i().register(Vec.class, INSTANCE::intFromVec, INSTANCE::vec);
  }
}

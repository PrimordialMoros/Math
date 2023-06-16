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

package me.moros.math.adapter;

import java.util.HashMap;
import java.util.Map;

import me.moros.math.Position;
import me.moros.math.Vector3d;
import me.moros.math.Vector3i;
import org.checkerframework.checker.nullness.qual.Nullable;

@SuppressWarnings("unchecked")
public final class Adapters<E extends Position> {
  private static final Adapters<Vector3i> INT = new Adapters<>();
  private static final Adapters<Vector3d> DOUBLE = new Adapters<>();

  private final Map<Class<?>, Adapter<?, E>> ADAPTERS = new HashMap<>();
  private final Map<Class<?>, Converter<?>> CONVERTERS = new HashMap<>();

  private Adapters() {
  }

  public <T> void registerAdapter(Class<T> nativeType, Adapter<T, E> adapter) {
    ADAPTERS.put(nativeType, adapter);
  }

  public <T> void registerConverter(Class<T> nativeType, Converter<T> converter) {
    CONVERTERS.put(nativeType, converter);
  }

  public <T> void register(Class<T> nativeType, Adapter<T, E> adapter, Converter<T> converter) {
    registerAdapter(nativeType, adapter);
    registerConverter(nativeType, converter);
  }

  public <T> E adapt(T object) {
    final Class<?> type = object.getClass();
    Adapter<T, E> adapter = (Adapter<T, E>) ADAPTERS.computeIfAbsent(type, this::findClosestAdapter);
    if (adapter == null) {
      throw new IllegalArgumentException("Could not find a registered adapter for " + type.getName());
    }
    return adapter.apply(object);
  }

  private <T> @Nullable Adapter<T, E> findClosestAdapter(Class<T> type) {
    for (var entry : ADAPTERS.entrySet()) {
      if (entry.getKey().isAssignableFrom(type)) {
        return (Adapter<T, E>) entry.getValue();
      }
    }
    return null;
  }

  public <T> Converter<T> converter(Class<T> nativeType) {
    return (Converter<T>) getConverter(CONVERTERS, nativeType);
  }

  private Object getConverter(Map<Class<?>, ?> map, Class<?> nativeType) {
    Object value = map.get(nativeType);
    if (value == null) {
      throw new IllegalArgumentException("Could not find a registered adapter for " + nativeType.getName());
    }
    return value;
  }

  public static Adapters<Vector3i> vector3i() {
    return INT;
  }

  public static Adapters<Vector3d> vector3d() {
    return DOUBLE;
  }
}

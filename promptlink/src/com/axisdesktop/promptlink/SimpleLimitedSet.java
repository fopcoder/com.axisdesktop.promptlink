package com.axisdesktop.promptlink;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/*
 * 
 * Размер множества не может превышать 10 элементов.
 * 
 * Доступны операции добавления, удаления и проверка наличия элемента.
 * 
 * Если при добавлении очередного элемента размер множества превышает 10 - то удаляется элемент, к
 * которому было наименьшее количество обращений ( вызовов метода contains() ) Если таких элементов
 * несколько - удаляется любой один из них.
 */

public class SimpleLimitedSet<T> implements LimitedSet<T> {
  private final int maxCapacity;
  private final Map<T, Integer> dataSet = new HashMap<>();
  private T rarelyUsedElement; // element cache

  public SimpleLimitedSet() {
    this.maxCapacity = 10;
  }

  public SimpleLimitedSet(int maxCapacity) {
    this.maxCapacity = maxCapacity;
  }

  @Override
  public void add(T t) {
    if (t == null)
      return;

    if (!dataSet.containsKey(t)) {
      if (dataSet.size() == maxCapacity) {
        remove(getRarelyUsedElement());
      }

      dataSet.put(t, 0);
      rarelyUsedElement = t;
    }
  }

  @Override
  public boolean remove(T t) {
    if (t == null)
      return false;

    if (dataSet.remove(t) == null) {
      return false;
    }

    if (t.equals(rarelyUsedElement)) {
      rarelyUsedElement = null;
    }

    return true;
  }

  @Override
  public boolean contains(T t) {
    if (t == null)
      return false;

    if (dataSet.containsKey(t)) {
      dataSet.compute(t, (k, v) -> v + 1);

      if (t.equals(rarelyUsedElement)) {
        rarelyUsedElement = null; // no more rarely used
      } else {
        if (rarelyUsedElement != null && dataSet.get(t) < dataSet.get(rarelyUsedElement)) {
          rarelyUsedElement = t;
        }
      }

      return true;
    }

    return false;
  }

  private T getRarelyUsedElement() {
    if (rarelyUsedElement == null && dataSet.size() > 0) {
      Entry<T, Integer> entry =
          Collections.min(dataSet.entrySet(), Comparator.comparing(Entry::getValue));
      rarelyUsedElement = (entry == null) ? null : entry.getKey();
    }

    return rarelyUsedElement;
  }

  public int size() {
    return dataSet.size();
  }

  public Integer count(T t) {
    return dataSet.get(t);
  }
}

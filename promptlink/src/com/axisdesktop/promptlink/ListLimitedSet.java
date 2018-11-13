package com.axisdesktop.promptlink;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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

public class ListLimitedSet<T> implements LimitedSet<T> {
  private final int maxCapacity;
  private final List<LimitedSetItem<T>> dataSet = new LinkedList<>();

  public ListLimitedSet() {
    this.maxCapacity = 10;
  }

  public ListLimitedSet(int maxCapacity) {
    this.maxCapacity = maxCapacity;
  }

  @Override
  public void add(T t) {
    if (t == null)
      return;

    LimitedSetItem<T> item = new LimitedSetItem<>(t);

    if (!dataSet.contains(item)) {
      if (dataSet.size() == maxCapacity) {
        dataSet.remove(0);
      }

      dataSet.add(0, new LimitedSetItem<T>(t));
    }
  }

  @Override
  public boolean remove(T t) {
    if (t == null)
      return false;

    if (dataSet.remove(new LimitedSetItem<T>(t))) {
      return true;
    }

    return false;
  }

  @Override
  public boolean contains(T t) {
    if (t == null)
      return false;

    int idx = indexOf(t);

    if (idx >= 0) {
      LimitedSetItem<T> item = dataSet.get(idx);
      item.incCount();

      Collections.sort(dataSet);

      return true;
    }

    return false;
  }

  public int size() {
    return dataSet.size();
  }

  public Integer count(T t) {
    int idx = indexOf(t);

    if (idx >= 0) {
      LimitedSetItem<T> item = dataSet.get(idx);

      return item.getCount();// dataSet.get(t);
    }

    return null;
  }

  public void showData() {
    int i = 0;
    for (LimitedSetItem<T> item : dataSet) {
      System.out.println(i++ + " " + item);
    }
  }

  private int indexOf(T t) {
    return dataSet.indexOf(new LimitedSetItem<>(t));
  }

}

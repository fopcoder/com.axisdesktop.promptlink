package com.axisdesktop.promptlink;

public class LimitedSetItem<T> implements Comparable<LimitedSetItem<T>> {
  private final T obj;
  private int count;

  public LimitedSetItem(T obj) {
    this.obj = obj;
  }

  public LimitedSetItem(T obj, int count) {
    this.obj = obj;
    this.count = count;
  }

  public T getObj() {
    return obj;
  }

  public int getCount() {
    return count;
  }

  public void incCount() {
    count++;
  }

  @Override
  public boolean equals(Object o) {
    return obj.equals(((LimitedSetItem<T>) o).getObj());
  }

  @Override
  public String toString() {
    return "LimitedSetItem [obj=" + obj + ", count=" + count + "]";
  }

  @Override
  public int compareTo(LimitedSetItem<T> o) {
    return count - o.getCount();
  }
}

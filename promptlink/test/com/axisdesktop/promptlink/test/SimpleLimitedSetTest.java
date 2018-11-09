package com.axisdesktop.promptlink.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import com.axisdesktop.promptlink.SimpleLimitedSet;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class SimpleLimitedSetTest {
  private SimpleLimitedSet<String> dataSet;

  @BeforeEach
  public void kok() {
    dataSet = new SimpleLimitedSet<>();
  }

  @Test
  void test_001_addItems() {
    String str0 = "mi";
    String str1 = "samsung";
    String str2 = "lg";
    String str3 = "apple";

    dataSet.add(str0);

    dataSet.add(str1);
    dataSet.contains(str1);

    dataSet.add(str2);
    dataSet.contains(str2);
    dataSet.contains(str2);

    dataSet.contains(str3);

    assertTrue("wrong length", dataSet.size() == 3);
    assertFalse("wrong object", dataSet.contains(str3));

    assertTrue("wrong str0 counter", dataSet.count(str0) == 0);
    assertTrue("wrong str1 counter", dataSet.count(str1) == 1);
    assertTrue("wrong str2 counter", dataSet.count(str2) == 2);
    assertNull("wrong str3 counter", dataSet.count(str3));

  }

  @Test
  public void test_002_remove() {
    String str = "test";

    dataSet.add(str);
    assertTrue("string not found", dataSet.contains(str));

    boolean t = dataSet.remove(str);
    assertTrue("not removed", t);
    assertFalse("string found", dataSet.contains(str));
  }

  @Test
  public void test_003_removeOverflow() {
    for (int i = 0; i < 11; i++) {
      dataSet.add("" + i);

      if (i != 0) {
        dataSet.contains("" + i);
      }
    }

    assertTrue("wrong length", dataSet.size() == 10);
    assertFalse("contains 0", dataSet.contains("0"));
    assertTrue("contains 1", dataSet.contains("1"));
  }

  @Test
  public void test_004_nullCheck() {
    dataSet.add(null);

    assertTrue("wrong size", dataSet.size() == 0);
    assertFalse("wrong data", dataSet.contains(null));
    assertFalse("wrong data", dataSet.remove(null));
  }

}

package com.aston.app;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;

public class MyListTest {

    private MyList<String> list;

    @Before
    public void setUp() {
        list = new MyList<String>() {{
            add("Hello");
            add("Bye");
            add("Good");
        }};
    }

    @Test
    public void getElementByIndexTest() {
        Assert.assertEquals("Hello", list.get(0));
        Assert.assertNotEquals("some", list.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getElementByWrongIndexTest() {
        Assert.assertNotEquals("Hello", list.get(21));
    }

    @Test
    public void getSizeTest() {
        Assert.assertEquals(3, list.size());
        Assert.assertNotEquals(2, list.size());
    }

    @Test
    public void addNewElementTest() {
        String newString = "new";
        list.add(newString);
        Assert.assertEquals(newString, list.get(3));
        Assert.assertNotEquals("some", list.get(3));
        Assert.assertEquals(4, list.size());
        Assert.assertNotEquals(5, list.size());
    }

    @Test
    public void addNew1000000ElementTest() {
        for (int i = 0; i < 1000000; i++) {
            list.add("new " + i);
        }
        Assert.assertEquals(1000003, list.size());
    }

    @Test
    public void addNewElementByIndex() {
        String newString = "new";
        String movedString = list.get(1);
        list.add(1, newString);
        Assert.assertEquals(newString, list.get(1));
        Assert.assertNotEquals("some", list.get(1));
        Assert.assertEquals(movedString, list.get(2));
        Assert.assertEquals(4, list.size());
        Assert.assertNotEquals(5, list.size());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void addNewElementByWrongIndex() {
        String newString = "new";
        list.add(21, newString);
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void getElementTest() {
        Assert.assertEquals("Hello", list.get(0));
        Assert.assertEquals("Bye", list.get(1));
        Assert.assertEquals("Good", list.get(2));
        Assert.assertNotEquals("some", list.get(0));
        Assert.assertNotEquals("some", list.get(1));
        Assert.assertNotEquals("some", list.get(2));
        Assert.assertEquals(3, list.size());
        Assert.assertNotEquals(4, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getElementByWrongIndex() {
        String wrong = list.get(23);
    }


    @Test
    public void removeElementByIndexTest() {
        String removed = list.remove(0);
        Assert.assertEquals("Hello", removed);
        Assert.assertNotEquals("some", removed);
        Assert.assertEquals(2, list.size());
        Assert.assertNotEquals(3, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeElementByWrongIndexTest() {
        String removed = list.remove(10);
        Assert.assertNull(removed);
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void removeElementByElementTest() {
        boolean isDeleted = list.remove("Hello");
        Assert.assertTrue(isDeleted);
        Assert.assertEquals(2, list.size());
        Assert.assertNotEquals(3, list.size());
        Assert.assertEquals("Bye", list.get(0));
        Assert.assertNotEquals("Hello", list.get(0));
    }

    @Test
    public void removeElementByWrongElementTest() {
        boolean isDeleted = list.remove("wrong");
        Assert.assertFalse(isDeleted);
        Assert.assertEquals(3, list.size());
        Assert.assertNotEquals(2, list.size());
    }

    @Test
    public void setElementByIndexTest() {
        String elementBeforeReplace = list.get(1);
        String newElement = list.set(1, "new");
        Assert.assertEquals(3, list.size());
        Assert.assertNotEquals(4, list.size());
        Assert.assertEquals("new", list.get(1));
        Assert.assertNotEquals("some", list.get(1));
        Assert.assertEquals(elementBeforeReplace, newElement);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setElementByWrongIndexTest() {
        String newElement = list.set(23, "new");
        Assert.assertEquals(3, list.size());
        Assert.assertNotEquals(4, list.size());
    }

    @Test
    public void indexOfElementTest() {
        int index = list.indexOf("Hello");
        Assert.assertEquals(0, index);
        Assert.assertNotEquals(1, index);
    }

    @Test
    public void indexOfWrongElementTest() {
        int index = list.indexOf("wrong");
        Assert.assertEquals(-1, index);
        Assert.assertNotEquals(1, index);
    }

    @Test
    public void trimToSizeTest() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Method method = MyList.class.getDeclaredMethod("getElementDataLength");
        method.setAccessible(true);
        int elementDataBeforeTrim = (int) method.invoke(list, null);
        list.trimToSize();
        int elementDataAfterTrim = (int) method.invoke(list, null);
        Assert.assertEquals(10, elementDataBeforeTrim);
        Assert.assertEquals(3, elementDataAfterTrim);
        Assert.assertEquals(3, list.size());
    }


    @Test
    public void clearTest() {
        list.clear();
        Assert.assertEquals(0, list.size());
        Assert.assertNotEquals(3, list.size());

    }

    @Test
    public void isEmptyTest() {
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void containsTest() {
        Assert.assertTrue(list.contains("Hello"));
        Assert.assertFalse(list.contains("some"));
        Assert.assertNotEquals(true, list.contains("some"));
    }

    @Test
    public void sizeTest() {
        Assert.assertEquals(3, list.size());
        Assert.assertNotEquals(4, list.size());
    }

    @Test
    public void toArrayTest() {
        Object[] objects = list.toArray();
        Assert.assertEquals("Hello", objects[0]);
        Assert.assertEquals("Bye", objects[1]);
        Assert.assertEquals("Good", objects[2]);
        Assert.assertEquals(3, objects.length);
    }

    @Test
    public void sortTest() {
        list.sort(Comparator.naturalOrder());
        Assert.assertEquals("Bye", list.get(0));
        Assert.assertEquals("Good", list.get(1));
        Assert.assertEquals("Hello", list.get(2));

    }


}
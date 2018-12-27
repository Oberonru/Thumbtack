package net.thumbtack.school.windows.v3;

import net.thumbtack.school.windows.v3.base.RoundWindow;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestRoundWindow {

    @Test
    public void testRoundWindow() {
        Point center = new Point(10, 20);
        RoundWindow roundWindow = new RoundButton(center, 10, false, "OK");
        assertEquals(10, roundWindow.getCenter().getX());
        assertEquals(20, roundWindow.getCenter().getY());
        assertEquals(10, roundWindow.getRadius());
        assertFalse(roundWindow.isActive());
    }

    @Test
    public void testSetRoundWindow() {
        RoundWindow roundWindow = new RoundButton(10, 20, 10, "OK");
        roundWindow.setCenter(30, 40);
        roundWindow.setRadius(20);
        roundWindow.setActive(false);
        assertEquals(30, roundWindow.getCenter().getX());
        assertEquals(40, roundWindow.getCenter().getY());
        assertEquals(20, roundWindow.getRadius());
        assertFalse(roundWindow.isActive());
    }

    @Test
    public void testMoveToRoundWindow1() {
        RoundWindow roundWindow = new RoundButton(10, 20, 30, "OK");
        roundWindow.moveTo(100, 50);
        assertEquals(100, roundWindow.getCenter().getX());
        assertEquals(50, roundWindow.getCenter().getY());
        assertEquals(30, roundWindow.getRadius());
        assertTrue(roundWindow.isActive());
    }

    @Test
    public void testMoveToRoundWindow2() {
        RoundWindow roundWindow = new RoundButton(10, 20, 30, "OK");
        roundWindow.moveTo(new Point(100, 50));
        assertEquals(100, roundWindow.getCenter().getX());
        assertEquals(50, roundWindow.getCenter().getY());
        assertEquals(30, roundWindow.getRadius());
        assertTrue(roundWindow.isActive());
    }

    @Test
    public void testMoveRelRoundWindow() {
        RoundWindow roundWindow = new RoundButton(10, 20, 10, "OK");
        roundWindow.moveRel(100, 50);
        assertEquals(110, roundWindow.getCenter().getX());
        assertEquals(70, roundWindow.getCenter().getY());
        assertEquals(10, roundWindow.getRadius());
    }

    @Test
    public void testIsPointInsideRoundWindow1() {
        RoundWindow roundWindow = new RoundButton(10, 10, 10, "OK");
        assertTrue(roundWindow.isInside(10, 10));
        assertTrue(roundWindow.isInside(20, 10));
        assertTrue(roundWindow.isInside(10, 20));
        assertTrue(roundWindow.isInside(15, 15));
        assertFalse(roundWindow.isInside(18, 18));
    }

    @Test
    public void testIsPointInsideRoundWindow2() {
        RoundWindow roundWindow = new RoundButton(new Point(10, 10), 10, "OK");
        assertTrue(roundWindow.isInside(new Point(10, 10)));
        assertTrue(roundWindow.isInside(new Point(20, 10)));
        assertTrue(roundWindow.isInside(new Point(10, 20)));
        assertTrue(roundWindow.isInside(new Point(15, 15)));
        assertFalse(roundWindow.isInside(new Point(18, 18)));
    }

    @Test
    public void testResizeRoundWindow() {
        RoundWindow roundWindow1 = new RoundButton(10, 20, 10, "OK");
        roundWindow1.resize(2);
        assertEquals(10, roundWindow1.getCenter().getX());
        assertEquals(20, roundWindow1.getCenter().getY());
        assertEquals(20, roundWindow1.getRadius());
        RoundWindow roundWindow2 = new RoundButton(10, 20, 10, "OK");
        roundWindow2.resize(0.5);
        assertEquals(10, roundWindow2.getCenter().getX());
        assertEquals(20, roundWindow2.getCenter().getY());
        assertEquals(5, roundWindow2.getRadius());
        RoundWindow roundWindow3 = new RoundButton(10, 20, 10, "OK");
        roundWindow3.resize(0.01);
        assertEquals(10, roundWindow3.getCenter().getX());
        assertEquals(20, roundWindow3.getCenter().getY());
        assertEquals(1, roundWindow3.getRadius());
    }
}

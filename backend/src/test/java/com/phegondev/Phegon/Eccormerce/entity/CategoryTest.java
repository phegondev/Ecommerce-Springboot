package com.phegondev.Phegon.Eccormerce.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    private Category category1;
    private Category category2;

    @BeforeEach
    void setUp() {
        category1 = new Category();
        category1.setId(1L);
        category1.setName("Electronics");

        category2 = new Category();
        category2.setId(1L);
        category2.setName("Electronics");
    }

    @Test
    void testEquals_SameProperties_ReturnTrue() {
        assertEquals(category1, category2);
    }

    @Test
    void testEquals_Self_ReturnTrue() {
        assertEquals(category1, category1);
    }

    @Test
    void testEquals_Null_ReturnFalse() {
        assertNotEquals(category1, null);
    }

    @Test
    void testEquals_DifferentClass_ReturnFalse() {
        assertNotEquals(category1, new Object());
    }

    @Test
    void testEquals_DifferentId_ReturnFalse() {
        category2.setId(2L);
        assertNotEquals(category1, category2);
    }

    @Test
    void testEquals_DifferentName_ReturnFalse() {
        category2.setName("Home Appliances");
        assertNotEquals(category1, category2);
    }

    @Test
    void testHashCode_SameProperties_ReturnSameHashCode() {
        assertEquals(category1.hashCode(), category2.hashCode());
    }

    @Test
    void testHashCode_DifferentId_ReturnDifferentHashCode() {
        category2.setId(2L);
        assertNotEquals(category1.hashCode(), category2.hashCode());
    }

    @Test
    void testHashCode_DifferentName_ReturnDifferentHashCode() {
        category2.setName("Home Appliances");
        assertNotEquals(category1.hashCode(), category2.hashCode());
    }

    @Test
    void testCanEqual_SameClass_ReturnTrue() {
        assertTrue(category1.canEqual(category2));
    }

    @Test
    void testCanEqual_DifferentClass_ReturnFalse() {
        assertFalse(category1.canEqual(new Object()));
    }

    @Test
    void testEquals_Subclass_ReturnTrue() {
        class ExtendedCategory extends Category {}
        ExtendedCategory extendedCategory = new ExtendedCategory();
        extendedCategory.setId(category1.getId());
        extendedCategory.setName(category1.getName());
        assertTrue(category1.equals(extendedCategory));
        assertTrue(extendedCategory.canEqual(category1));
    }
}

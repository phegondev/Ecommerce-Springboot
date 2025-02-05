package com.phegondev.Phegon.Eccormerce.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {

    private Address address1;
    private Address address2;

    @BeforeEach
    public void setup() {
        address1 = new Address();
        address1.setId(1L);
        address1.setStreet("123 Main St");
        address1.setCity("Anytown");
        address1.setState("CA");
        address1.setZipCode("90210");
        address1.setCountry("USA");

        address2 = new Address();
        address2.setId(1L);
        address2.setStreet("123 Main St");
        address2.setCity("Anytown");
        address2.setState("CA");
        address2.setZipCode("90210");
        address2.setCountry("USA");
    }

    @Test
    public void testEquals_SameProperties_ReturnTrue() {
        assertEquals(address1, address2);
    }

    @Test
    public void testEquals_DifferentId_ReturnFalse() {
        address2.setId(2L);
        assertNotEquals(address1, address2);
    }



    @Test
    public void testHashCode_DifferentId_ReturnDifferentHashCode() {
        address2.setId(2L);
        assertNotEquals(address1.hashCode(), address2.hashCode());
    }

    @Test
    public void testCanEqual_SameClass_ReturnTrue() {
        assertTrue(address1.canEqual(address2));
    }

    @Test
    public void testCanEqual_DifferentClass_ReturnFalse() {
        assertFalse(address1.canEqual(new Object()));
    }

    @Test
    public void testEquals_NullObject_ReturnFalse() {
        assertFalse(address1.equals(null));
    }

    @Test
    public void testEquals_DifferentClass_ReturnFalse() {
        assertFalse(address1.equals(new Object()));
    }

    @Test
    public void testEquals_DifferentStreet_ReturnFalse() {
        address2.setStreet("124 Main St");
        assertNotEquals(address1, address2);
    }

    @Test
    public void testEquals_DifferentCity_ReturnFalse() {
        address2.setCity("Anothertown");
        assertNotEquals(address1, address2);
    }

    @Test
    public void testEquals_DifferentState_ReturnFalse() {
        address2.setState("NY");
        assertNotEquals(address1, address2);
    }

    @Test
    public void testEquals_DifferentZipCode_ReturnFalse() {
        address2.setZipCode("90211");
        assertNotEquals(address1, address2);
    }

    @Test
    public void testEquals_DifferentCountry_ReturnFalse() {
        address2.setCountry("Canada");
        assertNotEquals(address1, address2);
    }

    @Test
    public void testEquals_SameObject_ReturnTrue() {
        assertTrue(address1.equals(address1));
    }

    @Test
    public void testEquals_Subclass_ReturnTrue() {
        class ExtendedAddress extends Address {}
        Address extendedAddress = new ExtendedAddress();
        extendedAddress.setId(address1.getId());
        extendedAddress.setStreet(address1.getStreet());
        extendedAddress.setCity(address1.getCity());
        extendedAddress.setState(address1.getState());
        extendedAddress.setZipCode(address1.getZipCode());
        extendedAddress.setCountry(address1.getCountry());

        assertTrue(address1.equals(extendedAddress) && extendedAddress.canEqual(address1));
    }

}

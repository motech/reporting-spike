package org.motechproject.ananya.bbc.users.util;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class UtilsTest {
    
    @Test
    public void shouldGenerateCorrectPasswordHash() throws Exception {
        String passwordHash = Utils.getPasswordHash("fubar");
        assertEquals("5185e8b8fd8a71fc80545e144f91faf2", passwordHash);
    }
}

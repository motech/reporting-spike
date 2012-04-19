package org.motechproject.ananya.bbc.service;

import org.junit.Test;
import org.motechproject.ananya.bbc.mapper.LocationMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class LocationMapperIT extends SpringIntegrationTest {

    @Autowired
    private LocationMapper locationMapper;

    @Test
    public void shouldLoadLocations() {
        locationMapper.getAll();
    }
}

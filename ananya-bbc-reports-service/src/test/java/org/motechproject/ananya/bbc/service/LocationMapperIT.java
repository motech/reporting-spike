package org.motechproject.ananya.bbc.service;

import org.junit.Test;
import org.motechproject.ananya.bbc.domain.Location;
import org.motechproject.ananya.bbc.mapper.LocationMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LocationMapperIT extends SpringIntegrationTest {

    @Autowired
    private LocationMapper locationMapper;

    @Test
    public void shouldLoadLocations() {
        List<Location> locationList = locationMapper.getAll();
        System.out.println(locationList);
    }
}

package org.motechproject.ananya.bbc.service;

import org.motechproject.ananya.bbc.domain.CertificateCourseUsage;
import org.motechproject.ananya.bbc.domain.Location;
import org.motechproject.ananya.bbc.mapper.CertificateCourseUsageMapper;
import org.motechproject.ananya.bbc.mapper.LocationMapper;
import org.motechproject.ananya.bbc.request.UsageReportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateCourseReportService {

    @Autowired
    private CertificateCourseUsageMapper certificateCourseUsageMapper;

    @Autowired
    private LocationMapper locationMapper;

    public List<CertificateCourseUsage> getUsageReport(UsageReportRequest request) {
        return certificateCourseUsageMapper.getAll(request);
    }

    public Integer getCount(UsageReportRequest request) {
        return certificateCourseUsageMapper.getCount(request);
    }

    public List<Location> getLocations(){
        return locationMapper.getAll();
    }
}

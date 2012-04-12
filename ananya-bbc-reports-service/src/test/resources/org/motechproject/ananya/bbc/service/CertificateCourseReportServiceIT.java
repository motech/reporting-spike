package org.motechproject.ananya.bbc.service;


import org.junit.Test;
import org.motechproject.ananya.bbc.domain.CertificateCourseUsage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class CertificateCourseReportServiceIT extends SpringIntegrationTest {

    @Autowired
    private CertificateCourseReportService courseReportService;

    @Test
    public void shouldFetchFromReportingDBViaMyBatis(){
        List<CertificateCourseUsage> usageReports = courseReportService.getUsageReport(0, 2);
        assertEquals(2, usageReports.size());
    }

    @Test
    public void shouldFetchCountFromDB(){
        assertEquals((Integer)2, courseReportService.getCount());
    }
}

package org.motechproject.ananya.bbc.service;


import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.ananya.bbc.domain.CertificateCourseUsage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class CertificateCourseReportServiceIT extends SpringIntegrationTest {

    @Autowired
    private CertificateCourseReportService courseReportService;

    @Test
    public void shouldFetchFromReportingDBViaMyBatis(){
        List<CertificateCourseUsage> usageReports = courseReportService.getUsageReport(
                DateTime.parse("2012-1-1"), DateTime.parse("2012-4-1"), 0, 2);
        assertNotNull(usageReports);
    }

    @Test
    public void shouldFetchCountFromDB(){
        assertEquals((Integer)2, courseReportService.getCount());
    }
}

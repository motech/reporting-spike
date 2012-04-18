package org.motechproject.ananya.bbc.service;


import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.ananya.bbc.domain.CertificateCourseUsage;
import org.motechproject.ananya.bbc.request.UsageReportRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class CertificateCourseReportServiceIT extends SpringIntegrationTest {

    @Autowired
    private CertificateCourseReportService courseReportService;

    @Test
    public void shouldFetchFromReportingDBViaMyBatis(){
        DateTime startDate = DateTime.parse("2012-1-1");
        DateTime endDate = DateTime.parse("2012-4-1");
        List<CertificateCourseUsage> usageReports = courseReportService.getUsageReport(
                new UsageReportRequest(startDate, endDate, 0, 2, "", "", "", "district", "asc", null, null));
        assertNotNull(usageReports);
    }

    @Test
    public void shouldFetchCountFromDB(){
        DateTime startDate = DateTime.parse("2012-1-1");
        DateTime endDate = DateTime.parse("2012-4-1");
        assertTrue(courseReportService.getCount(startDate, endDate) > 0);
    }
}

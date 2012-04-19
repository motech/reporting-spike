package org.motechproject.ananya.bbc.service;


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
        DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy");
        DateTime startDate = DateTime.parse("01/01/2012", formatter);
        DateTime endDate = DateTime.parse("04/30/2012", formatter);
        assertTrue(courseReportService.getCount(
                new UsageReportRequest(startDate, endDate, 0, 2, "all", "all", "all", "district", "asc", null, null)) > 0);
    }
}

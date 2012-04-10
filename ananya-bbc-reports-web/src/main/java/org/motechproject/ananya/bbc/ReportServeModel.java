package org.motechproject.ananya.bbc;

import org.motechproject.ananya.bbc.domain.CertificateCourseUsage;
import org.motechproject.ananya.bbc.service.CertificateCourseReportService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportServeModel {
    private CertificateCourseReportService certificateCourseReportService;
    public List<CertificateCourseUsage> content;
    public Integer count;
    public Map<String, String> header;

    @Autowired
    public ReportServeModel(CertificateCourseReportService certificateCourseReportService) {
        this.certificateCourseReportService = certificateCourseReportService;

        content = certificateCourseReportService.getUsageReport();
        header = new HashMap<String, String>();
        header.put("msisdn", "MSISDN #");
        header.put("name", "Name");
        header.put("district", "District");
        header.put("block", "Block");
        header.put("panchayat", "Panchayat");
        header.put("courseStartDate", "Course Start Date");
        header.put("courseEndDate", "Course End Date");
        header.put("courseCompleted", "Course Completed");
        header.put("smsSent", "SMS Sent");
        header.put("smsReferenceNumber", "SMS Reference Number");
        header.put("numChaptersCompleted", "Number Of Chapters Completed");
        header.put("numLessonsCompleted", "Number Of Lessons Completed");
        count = content.size();
    }
}

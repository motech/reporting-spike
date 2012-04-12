package org.motechproject.ananya.bbc.domain;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;

public class ReportServeModel {
    public List<CertificateCourseUsage> content;
    public Integer count;
    public LinkedHashMap<String, String> header;

    @Autowired
    public ReportServeModel(List<CertificateCourseUsage> usageList) {

        content = usageList;
        header = new LinkedHashMap<String, String>();
        
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
        header.put("numQuizzesCompleted", "Number Of Quizzes Completed");
        header.put("totalCertificateCourseDuration", "Total no. of minutes used");
        
        count = content.size();
    }
}

package org.motechproject.ananya.bbc.domain;

import java.util.LinkedHashMap;
import java.util.List;

public class ReportServeModel {
    private List<CertificateCourseUsage> content;
    private Integer count;
    private LinkedHashMap<String, String> header;

    public ReportServeModel(Integer count, List<CertificateCourseUsage> usageReport) {
        this.count = count;
        setHeader();
        this.content = usageReport;
    }

    public ReportServeModel(List<CertificateCourseUsage> usageReport) {
        this.content = usageReport;
    }

    public List<CertificateCourseUsage> getContent() {
        return content;
    }

    public Integer getCount() {
        return count;
    }

    public LinkedHashMap<String, String> getHeader() {
        return header;
    }

    public void setHeader(){
        header = new LinkedHashMap<String, String>();
        header.put("msisdn", "MSISDN #");
        header.put("name", "Name");
        header.put("district", "District");
        header.put("block", "Block");
        header.put("panchayat", "Village");
        header.put("courseStartDate", "Course Start Date");
        header.put("courseEndDate", "Course End Date");
        header.put("numChaptersCompleted", "Number Of Chapters Completed");
        header.put("numLessonsCompleted", "Number Of Lessons Completed");
        header.put("numQuizzesCompleted", "Number Of Quizzes Completed");
        header.put("scoresByChapter", "Individual score for each chapter quiz");
        header.put("totalScore", "Overall Score");
        header.put("courseCompleted", "Certification Course Completed");
        header.put("smsSent", "SMS Sent");
        header.put("smsReferenceNumber", "SMS Reference Number");
        header.put("totalCertificateCourseDuration", "Total no. of minutes used");
    }
}

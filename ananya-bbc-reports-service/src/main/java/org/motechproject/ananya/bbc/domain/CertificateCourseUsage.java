package org.motechproject.ananya.bbc.domain;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.text.DecimalFormat;

public class CertificateCourseUsage implements Serializable{

    private String msisdn;
    private String name;

    private String district;
    private String block;
    private String panchayat;

    private Integer startDay;
    private Integer startYear;
    private Integer endDay;
    private Integer endYear;
    private String courseStartDate = StringUtils.EMPTY;
    private String courseEndDate = StringUtils.EMPTY;

    private Integer numChaptersCompleted;
    private Integer numLessonsCompleted;
    private Integer numQuizzesCompleted;
    
    private String courseCompleted;

    private String smsSent;
    private String smsReferenceNumber;
    private Double totalCertificateCourseDuration;

    private String scoresByChapter;
    private Integer totalScore;

    public String getScoresByChapter() {
        return scoresByChapter;
    }

    public void setScoresByChapter(String scoresByChapter) {
        this.scoresByChapter = scoresByChapter;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public String getSmsSent() {
        return smsSent;
    }

    public void setSmsSent(String smsSent) {
        this.smsSent = smsSent;
    }

    public String getSmsReferenceNumber() {
        return smsReferenceNumber;
    }

    public void setSmsReferenceNumber(String smsReferenceNumber) {
        this.smsReferenceNumber = smsReferenceNumber;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getPanchayat() {
        return panchayat;
    }

    public void setPanchayat(String panchayat) {
        this.panchayat = panchayat;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(String courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public String getCourseEndDate() {
        return courseEndDate;
    }

    public void setCourseEndDate(String courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    public Integer getNumChaptersCompleted() {
        return numChaptersCompleted;
    }

    public void setNumChaptersCompleted(Integer numChaptersCompleted) {
        this.numChaptersCompleted = numChaptersCompleted;
    }

    public Integer getNumLessonsCompleted() {
        return numLessonsCompleted;
    }

    public void setNumLessonsCompleted(Integer numLessonsCompleted) {
        this.numLessonsCompleted = numLessonsCompleted;
    }

    public String getCourseCompleted() {
        return courseCompleted;
    }

    public void setCourseCompleted(String courseCompleted) {
        this.courseCompleted = courseCompleted;
    }

    public Double getTotalCertificateCourseDuration() {
        return totalCertificateCourseDuration;
    }

    public void setTotalCertificateCourseDuration(Integer totalCertificateCourseDuration) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        this.totalCertificateCourseDuration = Double.valueOf(decimalFormat.format(totalCertificateCourseDuration / (double) 60));
    }

    public Integer getStartDay() {
        return startDay;
    }

    public void setStartDay(Integer startDay) {
        this.startDay = startDay;
        this.courseStartDate = setDateEquivalent(this.startYear, startDay, this.courseStartDate);
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
        this.courseStartDate = setDateEquivalent(startYear, this.startDay, this.courseStartDate);
    }

    public Integer getEndDay() {
        return endDay;
    }

    public void setEndDay(Integer endDay) {
        this.endDay = endDay;
        this.courseEndDate = setDateEquivalent(endYear, endDay, this.courseEndDate);
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
        this.courseEndDate = setDateEquivalent(endYear, this.endDay, this.courseEndDate);
    }

    public Integer getNumQuizzesCompleted() {
        return numQuizzesCompleted;
    }

    public void setNumQuizzesCompleted(Integer numQuizzesCompleted) {
        this.numQuizzesCompleted = numQuizzesCompleted;
    }

    private String setDateEquivalent(Integer year, Integer day, String eqDate) {
        if(StringUtils.isEmpty(eqDate) && day != null && year != null) {
            DateTime dateTime = new DateTime().withYear(year).withDayOfYear(day);
            return dateTime.toString("dd/MM/yyyy");
        }

        return eqDate;
    }
}

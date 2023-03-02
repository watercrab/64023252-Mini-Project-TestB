package com.example.testminiproject.service;

import com.example.testminiproject.models.ReportModel;
import com.example.testminiproject.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public List<ReportModel> findAllReport(){
        return this.reportRepository.findAll();
    }

    public Optional<ReportModel> findReportByID(Long id){
        return this.reportRepository.findById(id);
    }

    public List<ReportModel> findReportByName(String name){
        List<ReportModel> reportList = reportRepository.findByName(name);
        return reportList;
    }

    public ReportModel saveReport(ReportModel report){
        return this.reportRepository.save(report);
    }

    public Optional<ReportModel> updateReport(Long id , ReportModel newReport){
        return reportRepository.findById(id).map(report -> {
            report.setName(newReport.getName());
            report.setDormName(newReport.getDormName());
            report.setRoom(newReport.getRoom());
            report.setDetails(newReport.getDetails());
            return this.reportRepository.save(report);
        });
    }

    public void deleteByID(Long id){
        this.reportRepository.deleteById(id);
    }
}

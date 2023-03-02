package com.example.testminiproject.controllers;

import com.example.testminiproject.models.ReportModel;
import com.example.testminiproject.service.ImageService;
import com.example.testminiproject.service.ReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/report")
@CrossOrigin("*")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ObjectMapper objectMapper;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("")
    public @ResponseBody ResponseEntity<List<ReportModel>> getAllReport(){
        return new ResponseEntity<>(this.reportService.findAllReport(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Optional<ReportModel>> getReportByID(@PathVariable Long id){
        return new ResponseEntity<>(this.reportService.findReportByID(id),HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public @ResponseBody ResponseEntity<List<ReportModel>> getReportByName(@PathVariable String name){
        return new ResponseEntity<>(this.reportService.findReportByName(name),HttpStatus.OK);
    }

    @PostMapping
    public @ResponseBody ResponseEntity<ReportModel> postReport(@RequestParam("image") MultipartFile file, @RequestPart("report") String reportJson) throws IOException {
        try {
            ReportModel report = objectMapper.readValue(reportJson, ReportModel.class);
            String image = this.imageService.uploadMassageImage(file);
            report.setImage(image);
            return new ResponseEntity<>(this.reportService.saveReport(report),HttpStatus.CREATED);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity<Optional<ReportModel>> putOnsenAppoint(@PathVariable Long id ,@RequestBody ReportModel report){
        return new ResponseEntity<>(this.reportService.updateReport(id,report),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deleteReport(@PathVariable Long id){
        this.reportService.deleteByID(id);
        return new ResponseEntity<>(String.format("%d has deleted.",id),HttpStatus.OK);
    }
}

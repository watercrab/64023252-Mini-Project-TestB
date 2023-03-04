package com.example.testminiproject.controllers;

import com.example.testminiproject.models.ReportModel;
import com.example.testminiproject.repositories.ReportRepository;
import com.example.testminiproject.service.ReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ReportControllerTest {

    public MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private ReportService reportService;

    @InjectMocks
    private ReportController reportController;

//    ReportModel obj1 = new ReportModel(1L,"kim","01",1,"test","1234");
//    ReportModel obj2 = new ReportModel(2L,"kim","02",23,"test","4321");

    @Before
    private void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(reportController).build();
    }

    @Test
    public void createReport_success()throws Exception{
        ReportModel model = ReportModel.builder()
                .id(3L)
                .name("kimmy")
                .dormName("01")
                .room(1)
                .details("test")
                .image("12321321315454")
                .build();

        Mockito.when(reportRepository.save(model)).thenReturn(model);

        String content = objectWriter.writeValueAsString(model);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/report")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.name",is("kimmy")));
    }
}

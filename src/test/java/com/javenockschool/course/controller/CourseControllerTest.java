package com.javenockschool.course.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javenockschool.course.model.Course;
import com.javenockschool.course.request.CourseRequest;
import com.javenockschool.course.service.CourseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CourseControllerTest {

    @MockBean
    private CourseService courseService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("save course test")
    void shouldSaveCourse() throws Exception {
        Course newCos = new Course();
        CourseRequest courseRequest = new CourseRequest();
        courseRequest.builder()
                .coursename("Engineering")
                .coursecode("ENG 103")
                .build();
        when(courseService.saveNewCourse(any(CourseRequest.class))).thenReturn(newCos);

        this.mockMvc.perform(post("/course").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(courseRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.coursename").value(equalTo(courseRequest.getCoursename())));
    }
}

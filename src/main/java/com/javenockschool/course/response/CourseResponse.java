package com.javenockschool.course.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseResponse {
    private Long id;
    private String coursename;
    private String coursecode;
}

package com.javenockschool.course.controller;

import com.javenockschool.course.model.Course;
import com.javenockschool.course.request.CourseRequest;
import com.javenockschool.course.response.CourseResponse;
import com.javenockschool.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Course saveNewCourse(@RequestBody CourseRequest courseRequest){
        return courseService.saveNewCourse(courseRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CourseResponse getCourseByid(@PathVariable Long id){
        return courseService.findCourseById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CourseResponse> fetchAllCourses(){
        return courseService.fetchAllCourses();
    }

}

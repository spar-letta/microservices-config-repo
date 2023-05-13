package com.javenockschool.course.service;

import com.javenockschool.course.model.Course;
import com.javenockschool.course.repository.CourseRepository;
import com.javenockschool.course.request.CourseRequest;
import com.javenockschool.course.response.CourseResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public Course saveNewCourse(CourseRequest courseRequest){

        Course newCourse = Course.builder()
                .coursename(courseRequest.getCoursename())
                .coursecode(courseRequest.getCoursecode())
                .build();
        return courseRepository.save(newCourse);
    }


    public CourseResponse findCourseById(Long id) {
        Course fetchedCourse = courseRepository.findById(id).get();
        CourseResponse courseResponse = CourseResponse.builder()
                .id(fetchedCourse.getId())
                .coursename(fetchedCourse.getCoursename())
                .coursecode(fetchedCourse.getCoursecode())
                .build();
        return courseResponse;
    }


    public List<CourseResponse> fetchAllCourses() {
        List<Course> fetchCourse = courseRepository.findAll();
        List<CourseResponse> myCourse = fetchCourse.stream().map(this::mapCoursesToResponse).toList();
        return myCourse;
    }

    private CourseResponse mapCoursesToResponse(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .coursename(course.getCoursename())
                .coursecode(course.getCoursecode())
                .build();
    }
}

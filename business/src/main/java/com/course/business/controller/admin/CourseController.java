package com.course.business.controller.admin;

import com.course.server.dto.*;
import com.course.server.service.CourseCategoryService;
import com.course.server.service.CourseService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/course")
public class CourseController {

    private static final Logger LOG = LoggerFactory.getLogger(CourseController.class);
    public static final String BUSINESS_NAME = "课程";

    @Resource
    private CourseService courseService;

    @Resource
    private CourseCategoryService courseCategoryService;

    /**
     *
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto){
        ResponseDto responseDto = new ResponseDto();
        courseService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值是编辑修改，id无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody CourseDto courseDto){
        // 保存校验
        ValidatorUtil.require(courseDto.getName(), "名称");
        ValidatorUtil.length(courseDto.getName(), "名称", 1, 50);
        ValidatorUtil.length(courseDto.getSummary(), "概述", 1, 2000);
        ValidatorUtil.length(courseDto.getImage(), "封面", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        courseService.save(courseDto);
        responseDto.setContent(courseDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        ResponseDto responseDto = new ResponseDto();
        courseService.delete(id);
        return responseDto;
    }

    /**
     * 查找课程下所有分类
     * @param courseId
     */
    @PostMapping("/list-category/{courseId}")
    public ResponseDto listCategory(@PathVariable(value = "courseId") String courseId) {
        ResponseDto responseDto = new ResponseDto();
        List<CourseCategoryDto> dtoList = courseCategoryService.listByCourse(courseId);
        responseDto.setContent(dtoList);
        return responseDto;
    }

    /**
     * 查找课程内容
     */
    @GetMapping("/find-content/{id}")
    public ResponseDto findContent(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        CourseContentDto contentDto = courseService.findContent(id);
        responseDto.setContent(contentDto);
        return responseDto;
    }

    /**
     * 保存课程内容，包含新增和修改
     */
    @PostMapping("/save-content")
    public ResponseDto saveContent(@RequestBody CourseContentDto contentDto) {
        ResponseDto responseDto = new ResponseDto();
        courseService.saveContent(contentDto);
        return responseDto;
    }
}
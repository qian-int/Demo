package com.example.easyexceldemo.controller;


import com.example.easyexceldemo.dto.PeopleDto;
import com.example.easyexceldemo.entity.People;
import com.example.easyexceldemo.service.IPeopleService;
import com.example.easyexceldemo.service.impl.PeopleServiceImpl;
import com.example.easyexceldemo.utils.Result;
import com.example.easyexceldemo.utils.UuidUtils;
import com.example.easyexceldemo.vo.PeopleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qqq
 * @since 2022-11-17
 */
@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private IPeopleService peopleService;

    /**
     * 新增人员
     * @param peopleDto
     * @return
     */
    @PostMapping("/insert")
    public Result<String> insert(@RequestBody PeopleDto peopleDto){
        return peopleService.insert(peopleDto);
    }


    /**
     * 查看人员详情
     * @param peopleId 人员id
     * @return PeopleVo
     */
    @GetMapping("/getPeopleDetail")
    public Result<PeopleVo> getPeopleList(@RequestParam(value = "peopleId")String peopleId){
        return peopleService.getPeopleList(peopleId);
    }

    /**
     * 批量新增
     * @param peopleDto 新增对象
     * @return boolean
     */
    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody PeopleDto peopleDto){
        People people = PeopleServiceImpl.getPeople(peopleDto);
        return Result.success(peopleService.save(people));
    }

    /**
     * 查询人员列表
     * @return
     */
    @GetMapping("/list")
    public Result<List<People>> getPeopleList(){
        return Result.success(peopleService.selectPeopleList());
    }


    /**
     * 文件导出
     * @param httpServletResponse
     * @throws IOException
     */
    @GetMapping("/export")
    public void export(HttpServletResponse httpServletResponse) throws IOException {
        peopleService.export(httpServletResponse);
    }

    /**
     * 文件导入
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/import")
    public Result<String> importData(@RequestBody MultipartFile file) throws IOException {
        return peopleService.importData(file);
    }










}

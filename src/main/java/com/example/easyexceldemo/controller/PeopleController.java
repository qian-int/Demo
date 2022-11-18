package com.example.easyexceldemo.controller;


import com.example.easyexceldemo.dto.PeopleDto;
import com.example.easyexceldemo.entity.People;
import com.example.easyexceldemo.service.IPeopleService;
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

    @PostMapping("/insert")
    public Result<String> insert(@RequestBody PeopleDto peopleDto){
        return peopleService.insert(peopleDto);
    }


    @GetMapping("/getPeopleDetail")
    public Result<PeopleVo> getPeopleList(@RequestParam(value = "peopleId")String peopleId){
        return peopleService.getPeopleList(peopleId);
    }

    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody PeopleDto peopleDto){
        People people = new People();
        BeanUtils.copyProperties(peopleDto,people);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(peopleDto.getBirthday(), dateTimeFormatter);
        people.setBirthday(localDateTime);
        people.setId(UuidUtils.sampleUUID());
        people.setCreateBy("qqq");
        people.setCreateTime(LocalDateTime.now());
        people.setDelFlag("0");
        return Result.success(peopleService.save(people));
    }

    @GetMapping("/export")
    public void export(HttpServletResponse httpServletResponse) throws IOException {
        peopleService.export(httpServletResponse);
    }

    @PostMapping("/import")
    public Result<String> importData(@RequestBody MultipartFile file) throws IOException {
        return peopleService.importData(file);
    }









}

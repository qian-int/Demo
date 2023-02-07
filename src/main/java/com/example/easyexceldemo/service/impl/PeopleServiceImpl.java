package com.example.easyexceldemo.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.easyexceldemo.dto.PeopleDto;
import com.example.easyexceldemo.entity.People;
import com.example.easyexceldemo.enums.ResultCodeEnum;
import com.example.easyexceldemo.listener.ReadDataListener;
import com.example.easyexceldemo.mapper.PeopleMapper;
import com.example.easyexceldemo.service.IPeopleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.easyexceldemo.utils.Result;
import com.example.easyexceldemo.utils.UuidUtils;
import com.example.easyexceldemo.vo.PeopleVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qqq
 * @since 2022-11-17
 */
@Slf4j
@Service
public class PeopleServiceImpl extends ServiceImpl<PeopleMapper, People> implements IPeopleService {

    @Autowired
    private PeopleMapper peopleMapper;

    @Autowired
    private IPeopleService peopleService;

    /**
     * 根据人员id查询人员详情
     * @param peopleId 人员id
     * @return string
     */
    @Override
    public Result<PeopleVo> getPeopleList(String peopleId) {
        PeopleVo peopleVo = new PeopleVo();
        People people = peopleMapper.selectById(peopleId);
        LocalDateTime birthday = people.getBirthday();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        BeanUtils.copyProperties(people,peopleVo);
        peopleVo.setBirthday(birthday.format(fmt));
        for (int i = 0;i < 10000; i++){
            log.info("测试");
        }
        return Result.success(peopleVo);
    }

    /**
     * 新增人员
     * @param peopleDto 入参对象
     * @return string
     */
    @Override
    public Result<String> insert(PeopleDto peopleDto) {
        People people = getPeople(peopleDto);
        peopleMapper.insert(people);
        return Result.success(people.getId());
    }

    public static People getPeople(PeopleDto peopleDto) {
        People people = new People();
        BeanUtils.copyProperties(peopleDto,people);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(peopleDto.getBirthday(), dateTimeFormatter);
        people.setBirthday(localDateTime);
        people.setId(UuidUtils.sampleUUID());
        people.setCreateBy("qqq");
        people.setCreateTime(LocalDateTime.now());
        people.setDelFlag("0");
        return people;
    }

    @Override
    public void saveEasyExcel(List<People> cacheDataList) {
        cacheDataList.forEach(v->{
            v.setId(UuidUtils.sampleUUID());
            v.setDelFlag("0");
            v.setCreateBy("qqq");
            v.setCreateTime(LocalDateTime.now());
        });
        this.saveBatch(cacheDataList);
    }

    /**
     * 导出文件
     * @param httpServletResponse
     * @return
     */
    @Override
    public void export(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        httpServletResponse.setCharacterEncoding("utf-8");
        //设置文件名,ps:把字符串中所有的‘+’替换成‘%20’代表空格
        String fileName = URLEncoder.encode("人员表","UTF-8").replaceAll("\\+","%20");
        //设置响应头
        httpServletResponse.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        //获取全部的数据
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag","0");
        List<People> people = peopleMapper.selectList(queryWrapper);
        //获取写出流
        ExcelWriter writer = EasyExcel.write(httpServletResponse.getOutputStream(), People.class).build();
        WriteSheet sheet = EasyExcel.writerSheet("人员").build();
        writer.write(people,sheet);//读出
        writer.finish();//关闭流
    }

    /**
     * 导入文件
     * @param file
     * @return
     */
    @Override
    public Result<String> importData(MultipartFile file) throws IOException {
        try {
            EasyExcel.read(file.getInputStream(),People.class,new ReadDataListener(peopleService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(ResultCodeEnum.SERVER_ERROR);
        }
        return Result.success("导入成功!");
    }

}

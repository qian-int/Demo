package com.example.easyexceldemo.service;

import com.example.easyexceldemo.dto.PeopleDto;
import com.example.easyexceldemo.entity.People;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.easyexceldemo.utils.Result;
import com.example.easyexceldemo.vo.PeopleVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qqq
 * @since 2022-11-17
 */
public interface IPeopleService extends IService<People> {

    /**
     * 根据人员id查询人员详情
     * @param peopleId 人员id
     * @return string
     */
    Result<PeopleVo> getPeopleList(String peopleId);

    /**
     * 新增人员
     * @param peopleDto 入参对象
     * @return string
     */
    Result<String> insert(PeopleDto peopleDto);

    void saveEasyExcel(List<People> cacheDataList);

    /**
     * 导出文件
     * @param httpServletResponse
     * @return
     */
    void export(HttpServletResponse httpServletResponse) throws IOException;

    /**
     * 导入文件
     * @param file
     * @return
     */
    Result<String> importData(MultipartFile file) throws IOException;

    /**
     * 查询人员列表
     * @return list
     */
    List<People> selectPeopleList();


}

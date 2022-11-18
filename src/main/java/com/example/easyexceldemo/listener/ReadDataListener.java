package com.example.easyexceldemo.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.fastjson.JSON;
import com.example.easyexceldemo.entity.People;
import com.example.easyexceldemo.service.IPeopleService;
import com.example.easyexceldemo.service.impl.PeopleServiceImpl;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ReadDataListener 读监听器
 * @Author qqq
 * @create 2022/11/17 18:53
 */
@Slf4j
public class ReadDataListener implements ReadListener<People> {

    private static final int BATCH_COUNT = 3;

    private List<People> cacheDataList = Lists.newArrayListWithExpectedSize(BATCH_COUNT);

    private IPeopleService peopleService;

    public ReadDataListener(){
        peopleService = new PeopleServiceImpl();
    }


    public ReadDataListener(IPeopleService peopleService){
        this.peopleService = peopleService;
    }


    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {}

    @Override
    public void invokeHead(Map<Integer, CellData> headMap, AnalysisContext context) {}

    /**
     * 每一条数据解析都会来调用
     * @param data
     * @param context
     */
    @Override
    public void invoke(People data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        cacheDataList.add(data);
        //达到了BATCH_COUNT了,需要存一次数据库,防止数据几万条数据在内存,容易OOM
        if (cacheDataList.size() >= BATCH_COUNT){
            saveData();
            //存储完清理list
            cacheDataList = Lists.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //这里保存下数据库为了确保遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成!");
    }

    @Override
    public boolean hasNext(AnalysisContext context) {
        return true;
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cacheDataList.size());
        peopleService.saveEasyExcel(cacheDataList);
        log.info("存储数据库成功！");
    }
}

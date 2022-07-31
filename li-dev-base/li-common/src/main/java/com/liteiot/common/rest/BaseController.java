package com.liteiot.common.rest;

import com.liteiot.common.biz.BaseBiz;
import com.liteiot.common.context.BaseContextHandler;
import com.liteiot.common.msg.ObjectRestResponse;
import com.liteiot.common.msg.TableResultResponse;
import com.liteiot.common.util.Query;
import com.liteiot.common.validate.InsertVerify;
import com.liteiot.common.validate.UpdateVerify;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 基类REST接口
 */
@Slf4j
public class BaseController<Biz extends BaseBiz, Entity> {

    @Autowired
    protected HttpServletRequest request;


    @Autowired
    protected Biz baseBiz;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ObjectRestResponse<Entity> add(@Validated(value = {InsertVerify.class}) @RequestBody Entity entity) {
        baseBiz.insertSelective(entity);
        return new ObjectRestResponse<>();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<Entity> get(@PathVariable int id) {
        Object o = baseBiz.selectById(id);
        return ObjectRestResponse.successData((Entity) o);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse<Entity> update(@Validated(value = {UpdateVerify.class}) @RequestBody Entity entity) {
        baseBiz.updateSelectiveById(entity);
        return new ObjectRestResponse<>();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ObjectRestResponse<Entity> remove(@PathVariable int id) {
        baseBiz.deleteById(id);
        return new ObjectRestResponse<>();
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ObjectRestResponse<List<Entity>> all() {
        return ObjectRestResponse.successData(baseBiz.selectListAll());
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public TableResultResponse<Entity> list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        return baseBiz.selectByQuery(query);
    }

    /**
     * 获取当前用户名称
     *
     * @return
     */
    protected String getCurrentUserName() {
        return BaseContextHandler.getUsername();
    }

    /**
     * 获取当前用户Id
     *
     * @return
     */
    protected String getCurrentUserId() {
        return BaseContextHandler.getUserID();
    }
}

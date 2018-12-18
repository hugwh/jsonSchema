package com.example.json.schema.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.json.schema.entity.Result;
import com.example.json.schema.jsonSchema.JsonSchemaUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * demo controller
 *
 * @author: huangwh
 * @mail huangwh@txtws.com
 * @date: 2018-12-18 14:03
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Value("/jsonSchema/demo/test_schema.json")
    private String jsonSchema;

    /**
     * jsonschema自定义校验
     * @param rParams
     * @return
     * @throws Exception
     */
    @PostMapping("/test/1")
    private String test1 (@RequestBody JSONObject rParams) throws Exception {
        JsonSchemaUtil.validateJson(rParams.toJSONString(), jsonSchema);
        Result result = new Result().successResult(rParams);
        return Result.toString(result);
    }

    /**
     * jsonschema 过滤器统一校验
     * @param rParams
     * @return
     * @throws Exception
     */
    @PostMapping("/test/2")
    private String test2 (@RequestBody JSONObject rParams) throws Exception {
        Result result = new Result().successResult(rParams);
        return Result.toString(result);
    }
}

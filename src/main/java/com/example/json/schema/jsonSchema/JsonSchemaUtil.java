package com.example.json.schema.jsonSchema;

import com.example.json.schema.entity.Result;
import com.example.json.schema.enums.EnumResult;
import com.example.json.schema.exception.JsonSchemaFileNotFoundException;
import com.example.json.schema.exception.ParamException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jackson.JsonNodeReader;
import com.github.fge.jsonschema.core.report.LogLevel;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

/**
 * jsonSchema 校验工具类
 *
 * @author: huangwh
 * @mail huangwh@txtws.com
 * @date: 2018-11-22 9:46
 */
public class JsonSchemaUtil {
    private static Logger logger = LoggerFactory.getLogger(JsonSchemaUtil.class);

    public static void validateJson(String rParams, String schemaJson) throws Exception {
        Result result = new Result();

        JsonNode jsonNode = JsonSchemaUtil.getJsonNodeFromString(rParams);
        if (jsonNode == null) {
            logger.error("=====>error rParams:" + rParams);
            throw new ParamException("json报文格式错误");
        }
        JsonNode schemaNode = null;
        try {
            schemaNode = JsonSchemaUtil.getJsonNodeFromFile(schemaJson);
        }catch (IOException ioe) {
            logger.debug("=====>error jsonschema文件名："+schemaJson);
            throw new JsonSchemaFileNotFoundException("json Schema文件不存在，无需校验！");
        }

        JsonSchemaUtil.validateJsonByFgeByJsonNode(jsonNode, schemaNode);

    }

    private static Result validateJsonByFgeByJsonNode(JsonNode jsonNode, JsonNode schemaNode) throws ParamException {
        Result result = new Result();
        ProcessingReport report = null;
        report = JsonSchemaFactory.byDefault().getValidator().validateUnchecked(schemaNode, jsonNode);
        if (report.isSuccess()) {
            // 校验成功
            result.setStatus(EnumResult.SUCESS.getStatus());
            result.setMessage(EnumResult.SUCESS.getMessage());
            return result;
        } else {
            logger.error("校验失败！");
            Iterator<ProcessingMessage> it = report.iterator();
            String ms = "";
            while (it.hasNext()) {
                ProcessingMessage pm = it.next();
                if (!LogLevel.WARNING.equals(pm.getLogLevel())) {
                    ms += pm;
                }

            }

            logger.info("========>ms: " + ms);
            ms = ms.substring(0, ms.indexOf("\n"));
            ms = ms.replaceAll("\\\""," ");

            throw new ParamException(ms);
        }
    }

    private static JsonNode getJsonNodeFromString(String jsonStr) {
        JsonNode jsonNode = null;
        try {
            jsonNode = JsonLoader.fromString(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonNode;
    }

    private static JsonNode getJsonNodeFromFile(String filePath) throws IOException {
        JsonNode jsonNode = null;

//        Resource resource = new ClassPathResource(filePath);
//        File file = resource.getFile();
//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

//        BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filePath)));
        ClassPathResource resource = new ClassPathResource(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        jsonNode = new JsonNodeReader().fromReader(br);

        return jsonNode;
    }
}

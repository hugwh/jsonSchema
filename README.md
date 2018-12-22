# jsonSchema
json schema json参数校验神器， springboot 使用demo



接口校验走过滤器：
    request uri:/demo/test/2
    schema path:jsonSchema/demo/test_2_schema.json
    校验json文件规范：第一级：目录，后面按“_”拼接命名。

接口自定义校验：
    JsonSchemaUtil.validateJson( 参数，json文件路径 )。

------------------------------------------------------------

json schema文件 自动生成工具：https://www.jsonschema.net/

json schema关键字详解：https://www.jianshu.com/p/9967edb199f5

------------------------------------------------------------

测试参数：
{
    "a": 1,
    "b": "str",
    "c": {
        "cc": 123
    },
    "d": "01",
    "e": [
        {
            "ee": "eee"
        }
    ]
}

package com.xxbb.springbootapi.utils.code;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.xxbb.springbootapi.entity.dto.EntityField;
import com.xxbb.springbootapi.interceptor.LegalException;
import lombok.extern.slf4j.Slf4j;

import java.beans.Introspector;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class BuildVue {
    public static boolean build(List<EntityField> fields, String entityName, Boolean isCover) {
        //读取模板
        Map<String, String> listPair = new HashMap<>();
        //读取模板

        String apiTemp = CodeGen.class.getResource("/").getPath() + "/templates/view/ViewApi.template";//api
        String typesTemp = CodeGen.class.getResource("/").getPath() + "/templates/view/ViewTypes.template";//types
        String vueTemp = CodeGen.class.getResource("/").getPath() + "/templates/view/ViewVue.template";//vue

        //生成文件名
        String api = String.format("\\web\\src\\api\\%s\\index.ts", entityName.toLowerCase());//api
        String types = String.format("\\web\\src\\api\\%s\\types.ts", entityName.toLowerCase());//types
        String vue = String.format("\\web\\src\\views\\%s\\%s\\index.vue", entityName, entityName);//vue

        //添加到生成列表
        listPair.put(apiTemp, api);
        listPair.put(typesTemp, types);
        listPair.put(vueTemp, vue);
        //生成实体类型
        StringBuilder entityFieldsContent = new StringBuilder();
        //生成表格字段
        StringBuilder tableFieldsContent = new StringBuilder();
        //字段列表
        StringBuilder fieldValueContent = new StringBuilder();
        //字段描述
        StringBuilder fieldEleContent = new StringBuilder();
        for (EntityField field : fields) {
            String tableField = String.format("\n  {\n" + "    field: '%s',\n" + "    label: '%s',\n" + "    search: true\n" + "  },", field.getFiledName(), field.getDescription());
            tableFieldsContent.append(tableField);
            //处理最后一个字段不加\n
            if (fields.indexOf(field) == fields.size() - 1) {
                String entityField = String.format("  %s?: %s | null", field.getFiledName(), field.getFieldType().getType());
                entityFieldsContent.append(entityField);
                //添加字段
                String fieldValueStr = String.format("  %s: null", field.getFiledName());
                fieldValueContent.append(fieldValueStr);
                //添加字段描述
                String fieldEle = String.format("      <el-form-item label=\"%s\" prop=\"%s\">\n" + "        <el-input v-model=\"orderPayload.%s\" autocomplete=\"off\" />\n" + "      </el-form-item>", field.getDescription(), field.getFiledName(), field.getFiledName());
                fieldEleContent.append(fieldEle);
            } else {
                String entityField = String.format("  %s?: %s | null\n", field.getFiledName(), field.getFieldType().getType());
                entityFieldsContent.append(entityField);
                //添加字段
                String fieldValueStr = String.format("  %s: null,\n", field.getFiledName());
                fieldValueContent.append(fieldValueStr);
                //添加字段描述
                String fieldEle = String.format("      <el-form-item label=\"%s\" prop=\"%s\">\n" + "        <el-input v-model=\"orderPayload.%s\" autocomplete=\"off\" />\n" + "      </el-form-item>\n", field.getDescription(), field.getFiledName(), field.getFiledName());
                fieldEleContent.append(fieldEle);
            }


        }

        listPair.forEach((k, v) -> {
            File file = new File(k);//读取模板
            String content = FileUtil.readString(file, Charset.defaultCharset());
            Map<String, String> map = new HashMap<>();
            map.put("entityName", entityName);
            map.put("entityNameLowCase", Introspector.decapitalize(entityName));//首字母转小写
            map.put("tableFields", tableFieldsContent.toString());
            map.put("entityFields", entityFieldsContent.toString());
            map.put("fieldValue", fieldValueContent.toString());
            map.put("fieldEle", fieldEleContent.toString());
            String contents = StrUtil.format(content, map);//替换字符串
            try {
                write(contents, v, isCover);//输出模板
            } catch (IOException e) {
                throw new LegalException("代码生成异常！异常位置：" + v + "：异常信息：" + e.getMessage());
            }
        });
        return true;
    }

    /**
     * 输出文件
     *
     * @param content
     * @param fileName
     * @param isCover
     * @return
     */
    public static Boolean write(String content, String fileName, Boolean isCover) throws IOException {
        File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath();
        File file = new File(courseFile + fileName);
        if (file.exists()) {
            if (isCover) {
                System.out.println("生成文件：" + fileName + "文件已经存在！执行覆盖");
                FileUtil.writeString(content, file, Charset.defaultCharset());
            } else {
                System.out.println("生成文件：" + fileName + "文件已经存在！");
            }
        } else {
            FileUtil.writeString(content, file, Charset.defaultCharset());
        }
        return true;
    }

    /**
     * 删除代码
     */
    public static synchronized Boolean delete(String entityName) {
        try {
            log.info("***删除代码***");
            if (entityName.isEmpty()) {
                return false;
            }
            if (deleteStart(entityName)) {
                log.info("***删除完毕***");
                return true;
            }
        } catch (Exception e) {
            throw new LegalException("删除代码异常！异常位置：" + entityName + "：异常信息：" + e.getMessage());
        }
        return false;
    }

    /**
     * 开始删除
     *
     * @param entityName
     * @return
     */
    private static Boolean deleteStart(String entityName) throws IOException {
        //删除的文件
        String api = String.format("\\web\\src\\api\\%s\\index.ts", entityName);//api
        String types = String.format("\\web\\src\\api\\%s\\types.ts", entityName);//types
        String vue = String.format("\\web\\src\\views\\%s\\index.vue", entityName);//vue
        String apiPath = String.format("\\web\\src\\api\\%s", entityName);//api
        String vuePath = String.format("\\web\\src\\views\\%s", entityName);//vue
        //删除文件
        File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath();
        File file = new File(courseFile + api);
        if (file.exists()) {
            file.delete();
        }
        file = new File(courseFile + types);
        if (file.exists()) {
            file.delete();
        }
        file = new File(courseFile + vue);
        if (file.exists()) {
            file.delete();
        }
        //删除文件夹
        file = new File(courseFile + apiPath);
        if (file.exists()) {
            file.delete();
        }
        file = new File(courseFile + vuePath);
        if (file.exists()) {
            file.delete();
        }
        return true;
    }
}

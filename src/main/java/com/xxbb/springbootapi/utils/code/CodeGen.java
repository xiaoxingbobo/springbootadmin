package com.xxbb.springbootapi.utils.code;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.xxbb.springbootapi.entity.dto.CodeInput;
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

/**
 * 代码生成器
 */
@Slf4j
public class CodeGen {
    /**
     * 生成代码
     *
     * @param entityName
     * @param isCover
     * @return
     */
    public static synchronized Boolean build(CodeInput codeInput) {
        log.info("***代码生成***");
        if (codeInput.getEntityName().isEmpty()) {
            return false;
        }
        if (codeInput.getEntityFields() != null) {
            buildEntity(codeInput.getEntityFields(), codeInput.getEntityName(), codeInput.getIsCover());
        }
        if (start(codeInput.getEntityName(), codeInput.getIsCover())) {
            log.info("***生成完毕***");
            return true;
        }
        return false;
    }

    /**
     * 生成实体类
     *
     * @param entityName
     * @param isCover
     * @return
     */
    public static synchronized Boolean buildEntity(List<EntityField> fields, String entityName, Boolean isCover) {
        if (fields.isEmpty()) {
            return false;
        }
        String entity = String.format("\\src\\main\\java\\com\\xxbb\\springbootapi\\entity\\%s.java", entityName);
        String entityTemp = CodeGen.class.getResource("/").getPath() + "/templates/TemplateEntity.template";


        StringBuilder entityFieldsContent = new StringBuilder();
        for (EntityField field : fields) {
            String entityField = String.format("\n    @ApiModelProperty(value = \"%s\")\n    private %s %s;", field.getDescription(), field.getFieldType().getCode(), field.getFiledName());
            entityFieldsContent.append(entityField);
        }
        //生成实体类
        String entityContent = FileUtil.readString(entityTemp, Charset.defaultCharset());
        Map<String, String> entityMap = new HashMap<>();
        entityMap.put("entityName", entityName);
        entityMap.put("entityNameLowCase", Introspector.decapitalize(entityName));//首字母转小写
        entityMap.put("entityFields", entityFieldsContent.toString());
        String entityContents = StrUtil.format(entityContent, entityMap);//替换字符串
        try {
            write(entityContents, entity, isCover);//输出模板
        } catch (IOException e) {
            throw new LegalException("代码生成异常！异常位置：" + entityContent + "：异常信息：" + e.getMessage());
        }
        return true;
    }

    /**
     * 开始生成
     *
     * @param entityName
     * @param isCover
     * @return
     */
    public static Boolean start(String entityName, Boolean isCover) {

//        List<String> entityList=new ArrayList<>();
//        entityList.add("User");
        Map<String, String> listPair = new HashMap<>();
        //读取模板

        String idaoTemp = CodeGen.class.getResource("/").getPath() + "/templates/ITemplateDao.template";//dao接口
        String daoTemp = CodeGen.class.getResource("/").getPath() + "/templates/TemplateDao.template";//dao实现
        String iserviceTemp = CodeGen.class.getResource("/").getPath() + "/templates/ITemplateService.template";//service接口
        String serviceTemp = CodeGen.class.getResource("/").getPath() + "/templates/TemplateService.template";//service实现
        String controllerTemp = CodeGen.class.getResource("/").getPath() + "/templates/TemplateController.template";//控制器

        //生成文件名
        String idao = String.format("\\src\\main\\java\\com\\xxbb\\springbootapi\\dao\\I%sDao.java", entityName);//dao 接口
        String dao = String.format("\\src\\main\\java\\com\\xxbb\\springbootapi\\dao\\impl\\%sDao.java", entityName);//dao 实现
        String iservice = String.format("\\src\\main\\java\\com\\xxbb\\springbootapi\\service\\I%sService.java", entityName);//service 接口
        String service = String.format("\\src\\main\\java\\com\\xxbb\\springbootapi\\service\\impl\\%sService.java", entityName);//控制器
        String controller = String.format("\\src\\main\\java\\com\\xxbb\\springbootapi\\controller\\%sController.java", entityName);//控制器

        //添加到生成列表
        listPair.put(idaoTemp, idao);
        listPair.put(daoTemp, dao);
        listPair.put(iserviceTemp, iservice);
        listPair.put(serviceTemp, service);
        listPair.put(controllerTemp, controller);

        listPair.forEach((k, v) -> {
            File file = new File(k);//读取模板
            String content = FileUtil.readString(file, Charset.defaultCharset());
            Map<String, String> map = new HashMap<>();
            map.put("entityName", entityName);
            map.put("entityNameLowCase", Introspector.decapitalize(entityName));//首字母转小写
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
        //生成文件名
        String entity = String.format("\\src\\main\\java\\com\\xxbb\\springbootapi\\entity\\%s.java", entityName);
        String idao = String.format("\\src\\main\\java\\com\\xxbb\\springbootapi\\dao\\I%sDao.java", entityName);//dao 接口
        String dao = String.format("\\src\\main\\java\\com\\xxbb\\springbootapi\\dao\\impl\\%sDao.java", entityName);//dao 实现
        String iservice = String.format("\\src\\main\\java\\com\\xxbb\\springbootapi\\service\\I%sService.java", entityName);//service 接口
        String service = String.format("\\src\\main\\java\\com\\xxbb\\springbootapi\\service\\impl\\%sService.java", entityName);//控制器
        String controller = String.format("\\src\\main\\java\\com\\xxbb\\springbootapi\\controller\\%sController.java", entityName);//控制器
        File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath();
        File file = new File(courseFile + idao);
        if (file.exists()) {
            file.delete();
        }
        file = new File(courseFile + entity);
        if (file.exists()) {
            file.delete();
        }
        file = new File(courseFile + dao);
        if (file.exists()) {
            file.delete();
        }
        file = new File(courseFile + iservice);
        if (file.exists()) {
            file.delete();
        }
        file = new File(courseFile + service);
        if (file.exists()) {
            file.delete();
        }
        file = new File(courseFile + controller);
        if (file.exists()) {
            file.delete();
        }
        return true;
    }
}

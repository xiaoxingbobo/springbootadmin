package com.xxbb.springbootapi.util.code;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.xxbb.springbootapi.interceptor.LegalException;
import lombok.extern.slf4j.Slf4j;


import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

@Slf4j
public class Generation {
    /**
     * 生成代码
     *
     * @param entityName
     * @param isCover
     * @return
     */
    public static synchronized Boolean build(String entityName, Boolean isCover) {
        System.out.println("***代码生成***");
        if (start(entityName, isCover)) {
            System.out.println("***生成完毕***");
            return true;
        }
        return false;
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
        String idaoTemp = Generation.class.getResource("/").getPath() + "/templates/ITemplateDao.template";//dao接口
        String daoTemp = Generation.class.getResource("/").getPath() + "/templates/TemplateDao.template";//dao实现
        String iserviceTemp = Generation.class.getResource("/").getPath() + "/templates/ITemplateService.template";//service接口
        String serviceTemp = Generation.class.getResource("/").getPath() + "/templates/TemplateService.template";//service实现
        String controllerTemp = Generation.class.getResource("/").getPath() + "/templates/TemplateController.template";//控制器

        //生成文件名
        String idao = String.format("\\src\\main\\java\\com\\xxbb\\springbootapi\\dao\\I%sDao.java", entityName);//dao 接口
        String dao = String.format("\\src\\main\\java\\com\\xxbb\\springbootapi\\dao\\impl\\%sDao.java", entityName);//dao 实现
        String iservice = String.format("\\src\\main\\java\\com\\xxbb\\springbootapi\\service\\I%sService.java", entityName);//service 接口
        String service = String.format("\\src\\main\\java\\com\\xxbb\\springbootapi\\service\\impl\\%sService.java", entityName);//控制器
        String controller = String.format("\\src\\main\\java\\com\\xxbb\\springbootapi\\controller\\%sController.java", entityName);//控制器


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
            map.put("entityNameLowCase", entityName.toLowerCase());
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
}

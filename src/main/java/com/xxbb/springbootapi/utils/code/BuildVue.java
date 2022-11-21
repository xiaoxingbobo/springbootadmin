package com.xxbb.springbootapi.utils.code;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.xxbb.springbootapi.interceptor.LegalException;

import java.beans.Introspector;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class BuildVue {
    public static boolean build(String entityName, boolean isCover) {
        //读取模板
        Map<String, String> listPair = new HashMap<>();
        //读取模板

        String apiTemp = CodeGen.class.getResource("/").getPath() + "/templates/view/ViewApi.template";//api
        String typesTemp = CodeGen.class.getResource("/").getPath() + "/templates/view/ViewTypes.template";//types
        String vueTemp = CodeGen.class.getResource("/").getPath() + "/templates/view/ViewVue.template";//vue

        //生成文件名
        String api = String.format("\\web\\src\\api\\%s\\index.vue", entityName);//dao 接口
        String types = String.format("\\web\\src\\api\\%s\\index.ts", entityName);//dao 实现
        String vue = String.format("\\web\\src\\views\\%s\\types.ts", entityName);//service 接口

        //添加到生成列表
        listPair.put(apiTemp, api);
        listPair.put(typesTemp, types);
        listPair.put(vueTemp, vue);

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
}

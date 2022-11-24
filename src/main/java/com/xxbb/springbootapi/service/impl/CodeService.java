package com.xxbb.springbootapi.service.impl;

import com.xxbb.springbootapi.entity.Authority;
import com.xxbb.springbootapi.entity.GenRecord;
import com.xxbb.springbootapi.entity.dto.CodeInput;
import com.xxbb.springbootapi.entity.dto.enums.AuthorityType;
import com.xxbb.springbootapi.interceptor.LegalException;
import com.xxbb.springbootapi.service.ICodeGenerate;
import com.xxbb.springbootapi.utils.code.BuildVue;
import com.xxbb.springbootapi.utils.code.CodeGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 代码生成
 */
@Service
public class CodeService implements ICodeGenerate {
    private static final String BASE_CLASS = "User,Authority,Role,Base,GenRecord,SecurityUserDetail,Api,AuthApi,Code,File,Common,WebSocket";
    @Autowired
    private GenRecordService genRecordService;
    @Autowired
    private AuthorityService authorityService;

    @Override
    @Transactional
    public Boolean generate(CodeInput codeInput) {
        if (BASE_CLASS.contains(codeInput.getEntityName())) {
            throw new LegalException(codeInput.getEntityName()+"为内置基础实体类，不允许该操作");
        }
        genRecordService.add(new GenRecord().setEntityName(codeInput.getEntityName()));
        BuildVue.build(codeInput.getEntityFields(),codeInput.getEntityName(),codeInput.getIsCover());
        CodeGen.build(codeInput);//代码生成，参数：要生成代码的实体类名
        //添加路由
        /**
         * insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
         *                        authority_type)
         * values (NOW(), 0, 'code-view', NOW(), 'sys:code:view', 'vaadin:code',
         *         'Development/Code/index', 'code', '代码生成', 1,
         *         'ROUTER');
         * --代码生成-增加3
         * insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
         *                        authority_type)
         * values (NOW(), 0, 'code-build', NOW(), 'sys:code:add', null,
         *         null, null, '代码生成-添加', 2,
         *         'API');
         */
        Authority authorityRouter= new Authority().setName(codeInput.getEntityName()).setValue("sys:"+codeInput.getEntityName()).setIcon("vaadin:code").setComponent("Layout").setPath("/"+codeInput.getEntityName().toLowerCase()).setTitle(codeInput.getEntityName()).setParentId(0).setAuthorityType(AuthorityType.ROUTER);
        authorityService.add(authorityRouter);
        Authority authorityTowRouter= new Authority().setName(codeInput.getEntityName().toLowerCase()+"-view").setValue("sys:"+codeInput.getEntityName().toLowerCase()+":view").setComponent(codeInput.getEntityName()+"/"+codeInput.getEntityName()+"/index").setPath(codeInput.getEntityName().toLowerCase()).setTitle(codeInput.getEntityName()).setParentId(authorityRouter.getId()).setAuthorityType(AuthorityType.ROUTER);
        authorityService.add(authorityTowRouter);
        authorityService.add(new Authority().setName(codeInput.getEntityName().toLowerCase()+"-add").setValue("sys:"+codeInput.getEntityName().toLowerCase()+":add").setIcon(null).setComponent(null).setPath(null).setTitle(codeInput.getEntityName()+"-添加").setParentId(authorityTowRouter.getId()).setAuthorityType(AuthorityType.API));
        authorityService.add(new Authority().setName(codeInput.getEntityName().toLowerCase()+"-edit").setValue("sys:"+codeInput.getEntityName().toLowerCase()+":update").setIcon(null).setComponent(null).setPath(null).setTitle(codeInput.getEntityName()+"-编辑").setParentId(authorityTowRouter.getId()).setAuthorityType(AuthorityType.API));
        authorityService.add(new Authority().setName(codeInput.getEntityName().toLowerCase()+"-delete").setValue("sys:"+codeInput.getEntityName().toLowerCase()+":delete").setIcon(null).setComponent(null).setPath(null).setTitle(codeInput.getEntityName()+"-删除").setParentId(authorityTowRouter.getId()).setAuthorityType(AuthorityType.API));
        return authorityService.add(new Authority().setName(codeInput.getEntityName().toLowerCase()+"-list").setValue("sys:"+codeInput.getEntityName().toLowerCase()+":select").setIcon(null).setComponent(null).setPath(null).setTitle(codeInput.getEntityName()+"-查询").setParentId(authorityTowRouter.getId()).setAuthorityType(AuthorityType.API));



    }

    @Override
    @Transactional
    public Boolean delete(Integer id) {
        GenRecord genRecord = genRecordService.find(id);
        if (BASE_CLASS.contains(genRecord.getEntityName())) {
            throw new LegalException("内置基础实体类，不允许该操作");
        }
        genRecordService.delete(genRecord.getId());
        BuildVue.delete(genRecord.getEntityName());
        //删除路由
        Authority authorityRouter= authorityService.find(genRecord.getId());
        authorityService.mapper().delete(authorityService.mapper().query().where().id().eq(authorityRouter.getId()).or.parentId().eq(authorityRouter.getId()).end());
        return CodeGen.delete(genRecord.getEntityName());//代码删除，参数：要删除代码的实体类名
    }

    @Override
    public List<GenRecord> list() {
        return genRecordService.list();
    }
}

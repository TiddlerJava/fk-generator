package com.fastfk.fkgenerator;

import com.fastfk.fkgenerator.enums.TemplateEnum;

/**
 * @info: 代码生成启动类
 * @author: yuzhiqiang
 * @mail: yuzhiqiang@tieserv.com
 * @date: 2019/11/27 9:40
 * @vesion: 0.0.1
 */
public class FkGeneratorApplication {

    public static void main(String[] args) {

        //查询所有表名SQL：【select GROUP_CONCAT(TABLE_NAME) from information_schema.tables where table_schema='你的数据库名称'】

        //查询sys_前缀开始的所有表名SQL：【select GROUP_CONCAT(TABLE_NAME) from information_schema.tables where table_schema='你的数据库名称' AND TABLE_NAME LIKE 'sys_%'】

        /**
         * 默认使用beetl模板引擎
         * 数据库连接配置 {@link com.fastfk.fkgenerator.config.MySqlConnectionConfig} GeneratorConfig.OUTPUT_PROJECT 默认生成文件输出到项目下
         * 多张表的代码生成，表名使用英文逗号隔开
         */
        CodeGenerator.generator("fk-generator", "com.fastfk.fkgenerator", "user,size");

        /**
         * 如果有多级子module，按如下格式填写moduleName
         */
        //CodeGenerator.generator("module-name/child-module-name/child-child-module-name", "com.fastfk.fkservicetest", "user,size");


        /**
         * 使用不同模板引擎
         */
        //CodeGenerator.generator("fk-generator", "com.fastfk.fkgenerator", "user,size", TemplateEnum.BEETL_TEMPLATE_ENGINE);
        /**
         * ============================
         * =
         * =如果需要修改代码模板，可以修改 resources/templates/** 下的对应模板文件，使用不同引擎模板文件自动对应(如：beetl引擎对应.btl后缀模板文件)
         * =
         * ============================
         */
    }

}

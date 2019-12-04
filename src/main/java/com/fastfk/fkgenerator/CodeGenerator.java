package com.fastfk.fkgenerator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.fastfk.fkgenerator.config.GeneratorConfig;
import com.fastfk.fkgenerator.config.MySqlConnectionConfig;
import com.fastfk.fkgenerator.enums.TemplateEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @info: mybatisPlus代码生成器，复制官网代码略作修改后版本
 * @author: yuzhiqiang
 * @mail: yuzhiqiang@tieserv.com
 * @date: 2019/11/27 9:40
 * @vesion: 0.0.1
 */

public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * 启动代码生成器
     * @param moduleName 项目模块名称
     * @param rootPackage 模块下的项目根目录包名
     * @param tableName 表名称，多表使用英文逗号隔开
     * @param templateEnum 使用的代码生成器模板引擎
     */
    public static void generator(String moduleName, String rootPackage, String tableName, TemplateEnum templateEnum) {

        String areYouSure = scanner("确定生成代码文件？此操作可能会覆盖已有文件，确定操作输入 yes|no ");
        if("no".equals(areYouSure)){
            System.out.println("放弃操作！");
            return;
        }
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = GeneratorConfig.OUTPUT_PROJECT ? System.getProperty("user.dir") : "D:/Generator";

        gc.setOutputDir(projectPath +"/" + moduleName +"/src/main/java");
        gc.setAuthor("yuzhiqiang");
        gc.setOpen(false);
        gc.setActiveRecord(true);//实体类继承Model<T>类，也就是开启mubatis plus的ActiveRecord（AR）模式
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(MySqlConnectionConfig.CON_URL);
        // dsc.setSchemaName("public");
        dsc.setDriverName(MySqlConnectionConfig.DRIVER);
        dsc.setUsername(MySqlConnectionConfig.USER_NAME);
        dsc.setPassword(MySqlConnectionConfig.PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("");
        pc.setParent(rootPackage);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.btl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templateEnum.getTemplateMapperXmlFile()) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/" +moduleName+ "/src/main/resources/mapper/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
        strategy.setSuperControllerClass("com.baomidou.mybatisplus.extension.api.ApiController");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
        strategy.setInclude(tableName.split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(templateEnum.getTemplateEngine());
        mpg.execute();
    }

    /**
     * 启动代码生成器【默认使用beetl模板引擎】
     * @param moduleName 项目模块名称
     * @param rootPackage 模块下的项目根目录包名
     * @param tableName 表名称，多表使用英文逗号隔开
     */
    public static void generator(String moduleName, String rootPackage, String tableName){
        generator(moduleName, rootPackage, tableName, TemplateEnum.BEETL_TEMPLATE_ENGINE);
    }

}

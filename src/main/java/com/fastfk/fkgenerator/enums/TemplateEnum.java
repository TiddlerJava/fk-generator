package com.fastfk.fkgenerator.enums;

import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

/**
 * @info: 使用模板引擎配置
 * @author: yuzhiqiang
 * @mail: yuzhiqiang@tieserv.com
 * @date: 2019/11/28 16:39
 * @vesion: 0.0.1
 */

public enum TemplateEnum {
    BEETL_TEMPLATE_ENGINE("/templates/mapper.xml.btl", new BeetlTemplateEngine()),
    BEETL_FREEMARKER_ENGINE("/templates/mapper.xml.ftl", new FreemarkerTemplateEngine()),
    BEETL_VELOCITY_ENGINE("/templates/mapper.xml.vm", new VelocityTemplateEngine());
    private String templateMapperXmlFile;
    private AbstractTemplateEngine templateEngine;

    TemplateEnum(String templateMapperXmlFile, AbstractTemplateEngine templateEngine) {
        this.templateMapperXmlFile = templateMapperXmlFile;
        this.templateEngine = templateEngine;
    }

    public String getTemplateMapperXmlFile() {
        return templateMapperXmlFile;
    }

    public AbstractTemplateEngine getTemplateEngine() {
        return templateEngine;
    }
}

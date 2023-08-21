package top.sumhzehn.mybatisutil;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author sumhzehn
 * @Date 2023/8/21
 */
public class CodeGenerator {
    public static void main(String[] args) throws Exception {
        generator("D:/TempMapper");
        System.out.println("Mapper files generated successfully.");
    }

    /**
     * 创建mapper等文件
     * @param filepath 创建文件目录
     */
    private static void generator(String filepath) throws Exception {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(
                CodeGenerator.class.getResourceAsStream("/generatorConfig.xml"));
        // 设置生成文件路径
        Context context = config.getContexts().get(0);
        // 设置 JavaModelGenerator 的 targetProject 属性
        context.getJavaModelGeneratorConfiguration().setTargetProject(filepath);
        // 设置 SqlMapGenerator 的 targetProject 属性
        context.getSqlMapGeneratorConfiguration().setTargetProject(filepath);
        // 设置 JavaClientGenerator 的 targetProject 属性
        context.getJavaClientGeneratorConfiguration().setTargetProject(filepath);

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator generator = new MyBatisGenerator(config, callback, warnings);
        generator.generate(null);
    }
}

package com.example.annotation_compiler;

import com.example.annotations.BindPath;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

/**
 * 注解处理器
 */
@AutoService(Processor.class) // 注册注解处理器
public class AnnotationCompiler extends AbstractProcessor {
    // 生成文件的对象
    Filer mFiler;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnvironment.getFiler();
    }

    /**
     * 声明这个注解处理器要处理的注解是哪些
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new HashSet<>();
        types.add(BindPath.class.getCanonicalName());
        return types;
    }

    /**
     * 声明注解处理器支持的java版本
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return processingEnv.getSourceVersion();
    }

    /**
     * 写文件
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        // 通过这个api获取这个模块中所有用了BindPath的节点
        Set<? extends Element> elementsAnnotatedWith = roundEnvironment.getElementsAnnotatedWith(BindPath.class);
        // 初始化数据
        Map<String, String> map = new HashMap<>();
        for (Element element : elementsAnnotatedWith) {
            TypeElement typeElement = (TypeElement) element;
            // 获取到map中的key
            String key = typeElement.getAnnotation(BindPath.class).value();
            String value = typeElement.getQualifiedName().toString();
            map.put(key, value);
        }
        // 开始写文件
        if (map.size() > 0) {
            Writer writer = null;
            // 创建类名
            String utilName = "ActivityUtil" + System.currentTimeMillis();
            try {
                JavaFileObject sourceFile = mFiler.createSourceFile("com.example.util." + utilName);
                writer = sourceFile.openWriter();
                writer.write("package com.example.util;\n" + "\n" +
                        "import com.example.arouter2.ARouter;\n" +
                        "import com.example.arouter2.IRouter;\n" + "\n" +
                        "public class " + utilName + " implements IRouter { \n" +
                        "       @Override\n" +
                        "       public void putActivity() { \n"
                );

                Iterator<String> iterator = map.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    String value = map.get(key);
                    writer.write("              ARouter.getInstance().putActivity(\"" + key + "\"," + value + ".class);\n");
                }
                writer.write("     }\n}");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }
}

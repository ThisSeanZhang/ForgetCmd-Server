package io.whileaway.forgetcmd.util.auto;

import io.whileaway.forgetcmd.util.ListUtils;
import io.whileaway.forgetcmd.util.NumberUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Supplier;

public class AutoFill {

    private static Map<String, Function<String, Object>> converts;
    private static final Map<String, List<Field>> FIELD_CACHE = new ConcurrentHashMap<>();
    private final Map<String, Object> dataSource;

    static {
        converts = new HashMap<>();
        converts.put(String.class.getName(), String::toString);
        converts.put(Long.class.getName(), NumberUtils::str2Long);
        converts.put(Double.class.getName(), NumberUtils::str2Double);
        converts.put(Integer.class.getName(), NumberUtils::str2Integer);
    }

    /**
     * 将已经获取定义的类存储起来，再次获取时从缓存中读取.
     * @param clazz 类
     * @return 相关成员变量Field
     */
    private static List<Field> getFieldCache(final Class clazz) {
        if (null == clazz) {
            return null;
        }
        List<Field> fields = FIELD_CACHE.get(clazz.getName());
        if (ListUtils.notEmptyList(fields)) {
            return fields;
        }

        synchronized (clazz) {
            List<Field> fields2 = FIELD_CACHE.get(clazz.getName());
            if (ListUtils.notEmptyList(fields2)) {
                return fields2;
            }
            FIELD_CACHE.put(clazz.getName(),  Arrays.asList(clazz.getDeclaredFields()));
        }
        return FIELD_CACHE.get(clazz.getName());
    }

    /**
     * 私有构造方法.
     * @param inDataSource 传入的数据源
     */
    private AutoFill(Map<String, Object> inDataSource) {
        this.dataSource = inDataSource;
    }

    /**
     * 以后便后续在创建时做其他操作.
     * @param inDataSource 传入的数据源
     * @return autoSetter对象
     */
    public static AutoFill create(Map<String, Object> inDataSource ) {
        return new AutoFill(inDataSource);
    }

    /**
     * 自动填充主方法.
     * @param supplierObject 被填充对象
     * @param <T> 被填充对象类型
     * @return 填充后的对象
     */
    public <T> T autoFeed(Supplier<T> supplierObject) {
        T t = supplierObject.get();
        // 如果传入的对象为空，就直接退出
        if (Objects.isNull(t)) {
            return null;
        }
        List<Field> fields = getFieldCache(t.getClass());
        for (Field field : fields) {
            TargetKey annotation = Optional
                    .ofNullable(field.getAnnotation(TargetKey.class))
                    .orElse(createColumn(field::getName));
            String resourceKey = annotation.key();

            // 将source取出的类型转成需要的类型
            Function<String, Object> typeFunction = converts.get(field.getType().getName());
            Optional<String> value = Optional.ofNullable(dataSource.get(resourceKey)).map(Object::toString);
            if (value.isEmpty()) {
                continue;
            }
            field.setAccessible(true);
            try {
                field.set(t, typeFunction.apply(value.get()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return t;
    }

    /**
     * 创建一个空的注释，以便以下逻辑使用
     * @param name 成员变量名称
     * @return 返回创建的注释对象
     */
    private static TargetKey createColumn(final Supplier<String> name) {
        return new TargetKey() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return TargetKey.class;
            }

            @Override
            public String key() {
                return name.get();
            }

            @Override
            public String defaultValue() {
                return "";
            }
        };
    }
}

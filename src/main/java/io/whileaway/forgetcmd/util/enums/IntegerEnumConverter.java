//package io.whileaway.forgetcmd.util.enums;
//
//import javax.persistence.AttributeConverter;
//
//public abstract  class IntegerEnumConverter<ET extends EntityType<Integer>> implements AttributeConverter<ET, Integer> {
//
//    private final Class<Enum> clazz;
//
//    public IntegerEnumConverter(Class<Enum> clazz){
//        this.clazz = clazz;
//    }
//    @Override
//    public Integer convertToDatabaseColumn(ET attribute) {
//        return attribute != null ? attribute.dateBaseValue() : null;
//    }
//
//    @Override
//    public ET convertToEntityAttribute(Integer dbData) {
//        if (dbData == null) return null;
//
//
//        ET[] enums = clazz.getEnumConstants();
//
//        for (ET e : enums) {
//            if (e.getDateBaseValue().equals(dbData)) {
//                return e;
//            }
//        }
//
//        throw new UnsupportedOperationException("枚举转化异常。枚举【" + clazz.getSimpleName() + "】,数据库库中的值为：【" + dbData + "】");
//    }
//}

//package io.whileaway.forgetcmd.util.enums;
//
//import javax.persistence.AttributeConverter;
//
//public abstract  class AbstractEnumConverter<ET extends EntityType<DT>, DT> implements AttributeConverter<ET, DT> {
//    @Override
//    public DT convertToDatabaseColumn(ET attribute) {
//        return attribute != null ? attribute.dateBaseValue() : null;
//    }
//
//    @Override
//    public ET convertToEntityAttribute(DT dbData) {
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

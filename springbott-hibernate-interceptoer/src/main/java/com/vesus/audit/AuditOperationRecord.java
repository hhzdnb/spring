package com.vesus.audit;

import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class AuditOperationRecord {

    private AuditOperationRecordAware auditOperationRecordAware;

    @Autowired(required = false)
    public void setAuditOperationRecordAware(AuditOperationRecordAware auditOperationRecordAware) {
        this.auditOperationRecordAware = auditOperationRecordAware;
    }

    public boolean onSaveRecord(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        System.out.println("=========添加============》");
        boolean result = processor(entity, id, state, null, propertyNames, types);
        return result;
    }


    public boolean onUpdateRecord(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        System.out.println("=========更新============》");
        boolean result = processor(entity, id, currentState, previousState, propertyNames, types);
        return result;
    }


    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        System.out.println("=========删除============》");
        processor(entity, id, null, state, propertyNames, types);
    }

    /**
     * 处理相同变更实体信息的公共类
     *
     * @param entity
     *         操作实体对象
     * @param id
     *         操作实体主键编号
     * @param currentState
     *         当前更改字段对象数组
     * @param previousState
     *         变更前字段信息对象数组
     * @param propertyNames
     *         英文属性名数组
     * @param types
     *         属性类型数组hibernate封装
     * @return
     */
    private boolean processor(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        Class<?> entityClass = entity.getClass();
        if (auditOperationRecordAware == null) {
            return true;
        }
        if (!entityClass.isAnnotationPresent(AuditEntityAlias.class)) {
            return true;
        }
        AuditEntityAlias entityAlias = entityClass.getAnnotation(AuditEntityAlias.class);
        String tableName = entityAlias.value();

        //设置别名和审计字段长度
        boolean[] isAudits = new boolean[propertyNames.length];
        String[] alias = new String[propertyNames.length];
        try {
            for (int i = 0; i < propertyNames.length; i++) {
                //Field field = entityClass.getField(propertyNames[i]);
                Field field = entityClass.getDeclaredField(propertyNames[i]);
                field.setAccessible(true);
                if (field.isAnnotationPresent(AuditFieldAlias.class)) {
                    AuditFieldAlias auditFieldAlias = field.getAnnotation(AuditFieldAlias.class);
                    isAudits[i] = true;
                    alias[i] = auditFieldAlias.value();
                } else {
                    isAudits[i] = false;
                    alias[i] = "";
                }
            }
        } catch (NoSuchFieldException e) {
            System.err.println(e.getMessage());
        }

        //生成改变字段信息列表
        List<AuditModifyFieldObject> auditModifyFieldObject = getAuditModifyFieldObject(currentState, previousState, propertyNames, types, alias, isAudits);
        long timestamp = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()).getTime();
        AuditOperationType operationType = AuditOperationType.UPDATE;
        //根据变更对象是否为空判断操作类型
        if (previousState != null && currentState != null) {
            operationType = AuditOperationType.UPDATE;
        }
        if (previousState == null && currentState != null) {
            operationType = AuditOperationType.INSERT;
        }
        if (previousState != null && currentState == null) {
            operationType = AuditOperationType.DELETE;
        }
        if (auditOperationRecordAware != null) {
            auditOperationRecordAware.saveAuditOperationLog(tableName, id, auditModifyFieldObject, timestamp, operationType);
        }
        return true;

    }

    /**
     * 获取自定义封装的字段修改对象类，用来保存更改前后的字段变化信息
     *
     * @param currentState
     *         操作后对象，也是当前对象
     * @param previousState
     *         变更前对象
     * @param propertyNames
     *         属性英文变量名称
     * @param types
     *         属性类型，这里是hibernate帮忙生成的一个类型
     * @param alias
     *         中文别名，这个属性需要结合 AuditFieldAlias 注解使用才能获取别名
     * @param isAudits
     *         是否审计，如果为true表示此字段需要保存更改变更，false相反
     * @return
     */
    private List<AuditModifyFieldObject> getAuditModifyFieldObject(Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types, String[] alias, boolean[] isAudits) {
        List<AuditModifyFieldObject> list = new ArrayList<>(10);
        int fieldCount = 0;
        //获取字段总个数
        if (currentState != null && currentState.length > 0 && fieldCount == 0) {
            fieldCount = currentState.length;
        }
        if (previousState != null && previousState.length > 0 && fieldCount == 0) {
            fieldCount = previousState.length;
        }
        for (int i = 0; i < fieldCount; i++) {
            AuditModifyFieldObject auditModifyObject = new AuditModifyFieldObject();
            //设置更改前的值
            if (previousState != null && previousState[i] != null) {
                auditModifyObject.setOldValue(previousState[i]);
            }
            // 设置更改后的当前值
            if (currentState != null && currentState[i] != null) {
                auditModifyObject.setNewValue(currentState[i]);
            }
            //设置属性名
            if (propertyNames != null && propertyNames[i] != null) {
                auditModifyObject.setProperty(propertyNames[i]);
            }
            //设置类型
            if (types != null && types[i] != null) {
                auditModifyObject.setType(types[i]);
            }
            // 设置别名
            if (alias != null && alias[i] != null) {
                auditModifyObject.setAlias(alias[i]);
            }
            // 是否审计
            if (isAudits != null && isAudits[i]) {
                auditModifyObject.setAudit(true);
            } else {
                auditModifyObject.setAudit(false);
            }
            list.add(auditModifyObject);
        }
        return list;
    }

}

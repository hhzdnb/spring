package com.vesus.audit;

import com.vesus.domain.AuditRecordLog;
import com.vesus.service.AuditRecordLogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AuditOperationRecordAwareImpl implements AuditOperationRecordAware {

    @Autowired
    private AuditRecordLogService auditRecordLogService;

    @Override
    public void saveAuditOperationLog(String tableName, Serializable id, List<AuditModifyFieldObject> auditModifyFieldObject, long timestamp, AuditOperationType operation) {


        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date(timestamp));
        System.out.println("更改的表名=========》" + tableName);
        System.out.println("主键=========》" + id);
        System.out.println("更改时间=========》" + date);


        // todo 保存到数据库
        int i = 0;
        for (AuditModifyFieldObject modifyFieldObject : auditModifyFieldObject) {
            Object oldValue = modifyFieldObject.getOldValue();
            Object newValue = modifyFieldObject.getNewValue();
            if (modifyFieldObject.isAudit() && !(oldValue == null && newValue == null) && !("".equals(oldValue) && "".equals(newValue))) {
                AuditRecordLog auditRecordLog = new AuditRecordLog();
                // 判断值不全是空或者前后值是否相等
                if (oldValue == null || newValue == null) {
                    auditRecordLog.setOldValue(oldValue + "");
                    auditRecordLog.setNewValue(newValue + "");
                } else {
                    if (!oldValue.equals(newValue)) {
                        auditRecordLog.setOldValue(oldValue + "");
                        auditRecordLog.setNewValue(newValue + "");
                    } else {
                        continue;
                    }
                }
                auditRecordLog.setPropertyName(modifyFieldObject.getProperty() + "");
                auditRecordLog.setAlias(modifyFieldObject.getAlias());
                auditRecordLog.setEntityName(tableName);
                auditRecordLog.setModifiedDate(date);
                String username = "特斯特" + i++; //todo 获取用户名等信息
                auditRecordLog.setUserName(username);
                String description = "";
                //如果是添加
                if (AuditOperationType.INSERT.equals(operation)) {
                    description = getOperationDescription(username, date, operation, newValue + "");

                }
                //如果是更新
                if (AuditOperationType.UPDATE.equals(operation)) {
                    description = getOperationDescription(username, date, operation, oldValue + "为" + newValue + "");
                }
                //如果是删除
                if (AuditOperationType.DELETE.equals(operation)) {
                    description = getOperationDescription(username, date, operation, oldValue + "");

                }
                auditRecordLog.setDescription(description);
                System.out.println("============描述========》" + description);
                auditRecordLogService.save(auditRecordLog);
            }
        }

    }

    private String getOperationDescription(String userName, String datetime, AuditOperationType operation, String obj) {
        return new StringBuffer().append(userName).append("在").append(datetime).append(operation.getValue()).append("了").append(obj).toString();

    }


}

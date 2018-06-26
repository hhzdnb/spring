package com.vesus.springboothistory.utils;

import com.vesus.springboothistory.annotation.BusinessAlias;
import com.vesus.springboothistory.annotation.BusinessNotRecord;
import com.vesus.springboothistory.annotation.BussinessID;
import com.vesus.springboothistory.annotation.TableName;
import com.vesus.springboothistory.model.BusinessChangeLog;
import com.vesus.springboothistory.model.User;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 记录操作日志的工具类
 * @Author: vesus
 * @CreateDate: 2018/6/25 下午2:30
 * @Version: 1.0
 */
public class BusinessChangeUtil<T> {

    public void record(Class<T> clazz,T oldObj ,T newObj,int userid,String username){

        //判断传入的两个对象是否为空
        if (oldObj==null&&newObj==null){
            return ;
        }

        //如果两个对象是否相等
        if (oldObj==newObj){
            return  ;
        }

        //判断是否为新增情况
        if(oldObj==null&&newObj!=null){
            recordAdd(clazz,null ,newObj,userid,username);
        //判断是否为修改情况
        }else if(oldObj!=null&&newObj!=null){
            recordChange(clazz,oldObj ,newObj,userid,username);
        //判断是否为删除信息情况
        }else{
            recordDelete(clazz,oldObj ,null,userid,username);
        }

    }


    /***
     * 将修改记录保存入库
     * @param clazz 实体类
     * @param oldObj 旧的实体对象
     * @param newObj 新的实体对象
     * @param userid 用户id
     * @param username 用户名称
     */
    private void recordChange(Class<T> clazz,T oldObj ,T newObj,int userid,String username){
        Field[] fields = clazz.getDeclaredFields();

        try {
            //遍历所有的字段
            for (Field field :fields){
                //设置字段的私有属性可见
                field.setAccessible(true);
                //去掉id和不需要记录的字段
                if (!field.isAnnotationPresent(BusinessNotRecord.class)
                        &&!field.isAnnotationPresent(BussinessID.class)){
                    //记录更改历史
                    BusinessChangeLog businessChangeLog = new BusinessChangeLog();
                    businessChangeLog.setEntity(clazz.getAnnotation(TableName.class).name());
                    businessChangeLog.setProperty(field.getName());
                    if (field.isAnnotationPresent(BusinessAlias.class)) {
                        businessChangeLog.setAlias(field
                                .getAnnotation(BusinessAlias.class).alias());
                    }
                    businessChangeLog.setUserid(userid);
                    businessChangeLog.setUsername(username);
                    //判断全部为空和全部不为空的情况
                    if (!(field.get(oldObj) != null ^ field.get(newObj) != null)){
                        //判断取到的两个属性值是否相等
                        if (field.get(oldObj) != null&&!(field.get(oldObj).equals(field.get(newObj)))){
                            businessChangeLog.setOldvalue(String.valueOf(field.get(oldObj)));
                            businessChangeLog.setNewvalue(String.valueOf(field.get(newObj)));
                            System.out.println(businessChangeLog);
                        }
                    }else{
                        if (field.get(oldObj)!=null){
                            businessChangeLog.setOldvalue(String.valueOf(field.get(oldObj)));
                        }else{
                            businessChangeLog.setOldvalue("");
                        }
                        if (field.get(newObj)!=null){
                            businessChangeLog.setNewvalue(String.valueOf(field.get(newObj)));
                        }else{
                            businessChangeLog.setNewvalue("");
                        }
                        System.out.println(businessChangeLog);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /***
     * 将修改记录保存入库
     * @param clazz 实体类
     * @param oldObj 旧的实体对象
     * @param newObj 新的实体对象
     * @param userid 用户id
     * @param username 用户名称
     */
    private void recordAdd(Class<T> clazz,T oldObj ,T newObj,int userid,String username){
        Field[] fields = clazz.getDeclaredFields();

        try {
            //遍历所有的字段
            for (Field field :fields){
                //设置字段的私有属性可见
                field.setAccessible(true);
                //去掉id和不需要记录的字段
                if (!field.isAnnotationPresent(BusinessNotRecord.class)
                        &&!field.isAnnotationPresent(BussinessID.class)){
                        //如果旧的字段的值为空，新的字段值不为空
                        BusinessChangeLog businessChangeLog = new BusinessChangeLog();
                        businessChangeLog.setEntity(clazz.getAnnotation(TableName.class).name());
                        businessChangeLog.setProperty(field.getName());
                        if (field.isAnnotationPresent(BusinessAlias.class)) {
                            businessChangeLog.setAlias(field
                                    .getAnnotation(BusinessAlias.class).alias());
                        }
                        businessChangeLog.setOldvalue("");
                        businessChangeLog.setNewvalue(String.valueOf(field.get(newObj)));
                        businessChangeLog.setUserid(userid);
                        businessChangeLog.setUsername(username);

                        System.out.println(businessChangeLog);
                    }
                }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /***
     * 将修改记录保存入库
     * @param clazz 实体类
     * @param oldObj 旧的实体对象
     * @param newObj 新的实体对象
     * @param userid 用户id
     * @param username 用户名称
     */
    private void recordDelete(Class<T> clazz,T oldObj ,T newObj,int userid,String username){
        Field[] fields = clazz.getDeclaredFields();

        try {
            //遍历所有的字段
            for (Field field :fields){
                //设置字段的私有属性可见
                field.setAccessible(true);
                //去掉id和不需要记录的字段
                if (!field.isAnnotationPresent(BusinessNotRecord.class)
                        &&!field.isAnnotationPresent(BussinessID.class)){
                    //如果旧的字段的值为空，新的字段值不为空
                    BusinessChangeLog businessChangeLog = new BusinessChangeLog();
                    businessChangeLog.setEntity(clazz.getName());
                    businessChangeLog.setProperty(field.getName());
                    if (field.isAnnotationPresent(BusinessAlias.class)) {
                        businessChangeLog.setAlias(field
                                .getAnnotation(BusinessAlias.class).alias());
                    }
                    businessChangeLog.setOldvalue(String.valueOf(field.get(oldObj)));
                    businessChangeLog.setNewvalue("");
                    businessChangeLog.setUserid(userid);
                    businessChangeLog.setUsername(username);

                    System.out.println(businessChangeLog);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

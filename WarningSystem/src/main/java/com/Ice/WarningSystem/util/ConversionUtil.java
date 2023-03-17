package com.Ice.WarningSystem.util;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ConversionUtil {



    /**
     * 相同参数实体类类型转换
     * @param form
     * @param targetClass
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T  typeConversion(Object form,Class<T> targetClass) {

        T targetItem= null;
        try {
            targetItem = targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        if(null==form){
            return targetItem;
        }
        //Method[] sms = form.getClass().getMethods();
        Method[] tms = targetClass.getMethods();
        List<String> methods=new ArrayList<>();
        for (int i = 0; i < tms.length; i++) {
            //System.out.println(tms[i].getName());
            String methodName=tms[i].getName();
            if (methodName.startsWith("set")){
                methods.add(methodName.replace("set",""));
                String getMethodName="get"+methodName.replace("set","");
                Object res= null;
                try {
                    res = form.getClass().getMethod(getMethodName).invoke(form);
                } catch (NoSuchMethodException |IllegalAccessException | InvocationTargetException e) {
                    continue;
                }
                //log.info("getMethodName:"+getMethodName);

                try {
                    tms[i].invoke(targetItem,res);
                } catch (IllegalAccessException | InvocationTargetException|IllegalArgumentException e) {
                    continue;
                }
            }
        }
        return targetItem;
    }


    public static <T> List<T>  arrayTypeConversion(List<?> list , Class<T> targetClass){
        List<T> resList=new ArrayList<>();
        for (Object obj :list){
            T targetItem=null;
            try {
                targetItem = targetClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            Method[] sms = obj.getClass().getMethods();
            Method[] tms = targetClass.getMethods();
            List<String> methods=new ArrayList<>();
            for (int i = 0; i < tms.length; i++) {
                //System.out.println(tms[i].getName());
                String methodName=tms[i].getName();
                if (methodName.startsWith("set")){
                    methods.add(methodName.replace("set",""));
                    String getMethodName="get"+methodName.replace("set","");
                    Object res=null;
                    try {
                        res = obj.getClass().getMethod(getMethodName).invoke(obj);
                    } catch (NoSuchMethodException |IllegalAccessException | InvocationTargetException e) {
                        continue;
                    }
                    try {
                        tms[i].invoke(targetItem,res);
                    } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
                        continue;
                    }
                }
            }
            resList.add(targetItem);
        }
        return resList;
    }


    public static List<?>  jsonListTypeConversion(List<JSONObject> list , Class targetClass){
        List<Object> res =new ArrayList<>();
        for (JSONObject jsonObject : list) {
            res.add(jsonObject.toJavaObject(targetClass));
        }
        return  res;
    }

    public static List<JSONObject>  toJSONObjectList(List<?> list){
        List<JSONObject> res =new ArrayList<>();
        for (Object o : list) {
            res.add((JSONObject) JSONObject.toJSON(o));
        }
        return  res;
    }


    /**
     * @Description: Ipage类型转换
     * @Author: HeZhipeng
     * @CreateDate: 2022-05-13 16:22:42
     * @params
     * @return
     */
    public static  <T> IPage<T> IpageTypeConversion(IPage sourcePage, Class<T> c){
        IPage<T> resPage=new Page<>();
        resPage.setPages(sourcePage.getPages());
        resPage.setCurrent(sourcePage.getCurrent());
        resPage.setSize(sourcePage.getSize());
        resPage.setTotal(sourcePage.getTotal());
        List<Object> sourceList=sourcePage.getRecords();
        List<T> tartgetList=arrayTypeConversion(sourceList,c);
        resPage.setRecords(tartgetList);
        return resPage;
    }

    /**
     * @Description: Ipage类型转换，自备Records
     * @Author: HeZhipeng
     * @CreateDate: 2022-05-13 16:23:36
     * @params
     * @return
     */
    public static  <T> IPage<T> IpageTypeConversion(IPage sourcePage,List<T> redords,Class<T> c){
        IPage<T> resPage=new Page<>();
        resPage.setPages(sourcePage.getPages());
        resPage.setCurrent(sourcePage.getCurrent());
        resPage.setSize(sourcePage.getSize());
        resPage.setTotal(sourcePage.getTotal());
        List<Object> sourceList=sourcePage.getRecords();
        resPage.setRecords(redords);
        return resPage;
    }
}

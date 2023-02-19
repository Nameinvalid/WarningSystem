package com.Ice.WarningSystem.aop;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Aspect
@Slf4j
public class LogAopAspect {

    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void executeLogController() {}

    //起打印的作用
    private static final Logger logger = LoggerFactory.getLogger(LogAopAspect.class);

    private static ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Before(value = "executeLogController()")
    public void beforeLogController(JoinPoint joinPoint) throws Exception {


        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ApiOperation declaredAnnotation = method.getDeclaredAnnotation(ApiOperation.class);
        String value = declaredAnnotation.value();
        try {
            startTime.set(System.currentTimeMillis());
            Object[] args = joinPoint.getArgs();

            for (Object object : args) {
                if (null == object) {
                    continue;
                }
                for (Field field : object.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.getName().toLowerCase().contains("session")) {
                        continue;
                    }
                    if (field.getName().toLowerCase().contains("name") || field.getName().toLowerCase().contains("title")) {
                        Class aClass = object.getClass();
                        Field declaredField = aClass.getDeclaredField(field.getName());
                        declaredField.setAccessible(true);
                        if (declaredField.get(object) != null) {
                            String str = escapeSqlSpecialChars(declaredField.get(object).toString());
                            field.set(object, str);
                        }
                    }
                }
            }
            Stream<?> stream = ArrayUtils.isEmpty(args) ? Stream.empty() : Arrays.stream(args);
            List<Object> logArgs = stream
                    .filter(arg -> (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse)))
                    .collect(Collectors.toList());

            logger.info(">>>>> {}-{}-start", value, signature.toShortString());
            logger.info(">>>>> {}-params:{}", value, JSONObject.toJSONString(args));
        } catch (Exception e) {
            logger.info(">>>>> {}-{}-start", value, "error");
            logger.info(">>>>> {}-params:{}", value, "error");
        }



    }


    private static String escapeSqlSpecialChars(String str){
        if(StringUtils.isNotBlank(str)){
            str= str.replaceAll("\\\\", "\\\\\\\\");
            str= str.replaceAll("_", "\\\\_");
            str= str.replaceAll("%", "\\\\%");
            str= str.replaceAll("]","");
            str= str.replaceAll("\\[","");
        }
        return str;
    }


    @AfterReturning(pointcut = "executeLogController()", returning = "result")
    public void afterReturningController(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ApiOperation declaredAnnotation = method.getDeclaredAnnotation(ApiOperation.class);
        String value = declaredAnnotation.value();
        String res = JSONObject.toJSONString(result);
        if(res!=null&&res.length()>200){
            res=res.substring(0,200)+"...";
        }

        long costTime = System.currentTimeMillis() - startTime.get();
        startTime.remove();
        logger.info("<<<<< {}-return:{}", value, res);
        logger.info("<<<<< {}-{}-{}ms-return-end", value, signature.toShortString(),costTime);
    }

    @AfterThrowing(throwing = "exception", pointcut = "executeLogController()")
    public void afterThrowingLogController(JoinPoint joinPoint, Exception exception) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ApiOperation declaredAnnotation = method.getDeclaredAnnotation(ApiOperation.class);
        String value = declaredAnnotation.value();
        long costTime = System.currentTimeMillis() - startTime.get();
        startTime.remove();
        logger.error("error", exception);
        logger.info("<<<<< {}-exception:{}", value, JSONObject.toJSONString(exception));
        logger.info("<<<<< {}-{}-{}ms-exception-end", value, signature.toShortString(),costTime);
    }
}


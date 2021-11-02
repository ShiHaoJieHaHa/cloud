package own.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import own.utils.AtomicCounter;
import javax.servlet.http.HttpServletRequest;

//API访问统计

@Component
@Aspect
public class ApiControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ApiControllerAdvice.class);
    private ThreadLocal<Long> startTime = new ThreadLocal<>();
    //aop own.controller下所有接口都会被统计
    @Pointcut("execution(* own.controller..*.*(..))")
    public void pointCut(){
    }
    //调用方法前先执行
    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        logger.info("类名：{}", joinPoint.getSignature().getDeclaringType().getSimpleName());
        logger.info("方法名:{}", joinPoint.getSignature().getName() );
        // 计数
        AtomicCounter.getInstance().increase();
    }
    //正常调用接口返回此数据
    @AfterReturning(returning = "returnVal", pointcut = "pointCut()")
    public void doAfterReturning(JoinPoint joinPoint, Object returnVal) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logger.info("URI:[{}], 耗费时间:[{}] ms, 访问次数:{}", request.getRequestURI(), System.currentTimeMillis() - startTime.get(), AtomicCounter.getInstance().getValue());
    }
    //调用错误时
    @AfterThrowing(pointcut = "pointCut()")
    public void doAfterThrowing(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logger.info("接口访问失败，URI:[{}], 耗费时间:[{}] ms", request.getRequestURI(), System.currentTimeMillis() - startTime.get());
    }





}

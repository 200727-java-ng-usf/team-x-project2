package com.revature.web.aspects;


import com.revature.dtos.Principal;
import com.revature.exceptions.AuthenticationException;
import com.revature.web.security.Secured;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class SecurityAspect {

    private HttpServletRequest request;

    @Autowired
    public SecurityAspect(HttpServletRequest request) {
        this.request = request;
    }

    @Around("@annotation(com.revature.web.security.Secured)")
    public Object secureEndpoint(ProceedingJoinPoint pjp) throws Throwable {

        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        Secured securedAnno = method.getAnnotation(Secured.class);

        List<String> allowedRoles = Arrays.asList(securedAnno.allowedRoles());
        Principal principal = (Principal) request.getSession().getAttribute("principal");

        if (principal == null) {
            throw new AuthenticationException("You must log in before accessing this endpoint!");
        }

        if (!allowedRoles.contains(principal.getUserRole().toString())){
            throw new AuthenticationException(principal.getUsername() + " is not allowed to access this endpoint!");
        }
        Object target = pjp.proceed();

        System.out.println("An authenticated request was made by " + principal.getUsername());

        return target;
    }
}

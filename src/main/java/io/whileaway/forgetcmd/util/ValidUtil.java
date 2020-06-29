package io.whileaway.forgetcmd.util;


import io.whileaway.forgetcmd.rbac.RBACConstant;
import io.whileaway.forgetcmd.rbac.entites.Developer;
import io.whileaway.forgetcmd.util.enums.CommonErrorEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ValidUtil {

    private final HttpSession session;
    private final HttpServletRequest request;

    public ValidUtil(HttpSession session, HttpServletRequest request) {
        this.session = session;
        this.request = request;
    }

    public Developer getCurrentDeveloper () {
        Object currentDeveloper = session.getAttribute(RBACConstant.CURRENT_DEVELOPER);
        if (Objects.nonNull(currentDeveloper) && currentDeveloper instanceof Developer) {
            return (Developer) currentDeveloper;
        }
        return new Developer();
    }

    public Long getURITempleVariables (String key) {
        Map attribute = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Object rawValue = attribute.get(key);
        if (Objects.isNull(rawValue) || !(rawValue instanceof String))
            CommonErrorEnum.PARAM_ERROR.throwThis();
        String value = rawValue.toString();
        if ( value.matches("^[0-9]+$"))
            return  Long.valueOf(value);
        CommonErrorEnum.PARAM_ERROR.throwThis();
        return null;
    }

    public void pringSessionAndRequestInfo () {
        System.out.println("session id" + session.getId());
        System.out.println("request" + request.getMethod() + " " + request.getParameterMap().entrySet()
                .stream()
                .map( e-> e.getKey() + ":"+ Arrays.toString(e.getValue()))
                .collect(Collectors.joining(",")));
        System.out.println("request:" + request.getContextPath() +":"+ request.getServletPath() + ":" +  request.getPathInfo() +":" + request.getRequestURI());

    }
}

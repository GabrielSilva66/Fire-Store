package com.project.system.controller.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // Verifica o tipo de erro e redireciona para a página apropriada
        Object status = org.springframework.web.context.request.RequestContextHolder
                .currentRequestAttributes().getAttribute("javax.servlet.error.status_code", 0);

        if (status != null) {
            int statusCode = (Integer) status;
            if (statusCode == 404) {
                return "/error/404";
            } else if (statusCode == 500) {
                return "/error/500";
            }
        }

        return "error/error";
    }

//    @Override
//    public String getErrorPath() {
//        return "/error";
//    }
}

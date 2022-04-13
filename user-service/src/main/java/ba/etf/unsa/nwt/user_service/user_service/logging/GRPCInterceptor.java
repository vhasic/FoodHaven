package ba.etf.unsa.nwt.user_service.user_service.logging;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GRPCInterceptor implements HandlerInterceptor {
    @Autowired
    private GRPCService grpcService;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {

        String responseType = "";

        if(response.getStatus() == 200){
            responseType = "OK";
        }
        else if(response.getStatus() == 404){
            responseType = "ERROR - ResourceNotFound";
        }
        else if(response.getStatus() == 401){
            responseType = "ERROR - Unauthorized";
        }
        else if(response.getStatus() == 403){
            responseType = "ERROR - AccessDenied (Forbidden)";
        }
        else {
            responseType = "ERROR - WrongInput/Validation";
        }

        grpcService.save(request.getMethod(), request.getRequestURI(), responseType, "test");
    }
}

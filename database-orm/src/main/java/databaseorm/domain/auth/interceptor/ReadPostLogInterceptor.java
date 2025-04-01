package databaseorm.domain.auth.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ReadPostLogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        long endTime = System.currentTimeMillis();
        long startTime = (long)request.getAttribute("startTime");

        request.removeAttribute("startTime");

        System.out.println("글 상세 읽기 요청 처리 소요시간 : " + (endTime - startTime) + "ms");
    }
}

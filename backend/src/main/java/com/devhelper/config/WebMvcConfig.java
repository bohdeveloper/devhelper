package com.devhelper.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        // interceptor left for backward compatibility, but the real work is
        // performed by GlobalControllerAdvice.  We still keep it here as a
        // safety net; however we must not attempt to mutate the model after the
        // response has been committed, otherwise a downstream exception can
        // trigger the "response has already been committed" log that you saw.
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, @Nullable ModelAndView modelAndView) throws Exception {
                if (response.isCommitted()) {
                    // nothing to do; avoid writing to a closed response
                    return;
                }
                if (modelAndView != null) {
                    String viewName = modelAndView.getViewName();
                    if (viewName != null && !viewName.startsWith("redirect:")) {
                        modelAndView.addObject("currentUri", request.getRequestURI());
                    }
                }
            }
        });
    }
}

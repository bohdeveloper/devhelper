package com.devhelper.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Global advice that adds common model attributes to every controller method.
 *
 * In particular we expose the current request URI so that the sidebar can
 * determine which menu item should be marked as active.  Using a
 * {@code @ModelAttribute} here avoids potential errors in the MVC interceptor
 * when the response has already been committed (which was the source of the
 * "Cannot render error page ... response has already been committed" error
 * reported while navigating).  The advice runs before the controller handler
 * executes, guaranteeing the model is available and never requires writing to
 * the response after rendering has started.
 */
@ControllerAdvice(annotations = Controller.class)
public class GlobalControllerAdvice {

    @ModelAttribute
    public void addCurrentUri(Model model, HttpServletRequest request) {
        model.addAttribute("currentUri", request.getRequestURI());
    }
}

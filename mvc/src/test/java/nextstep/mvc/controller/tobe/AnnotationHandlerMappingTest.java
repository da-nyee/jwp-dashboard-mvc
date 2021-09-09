package nextstep.mvc.controller.tobe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nextstep.mvc.controller.tobe.handler.mapping.AnnotationHandlerMapping;
import nextstep.mvc.controller.tobe.handler.mapping.HandlerExecution;
import nextstep.mvc.view.JspView;
import nextstep.mvc.view.ModelAndView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnnotationHandlerMappingTest {

    private AnnotationHandlerMapping handlerMapping;

    @BeforeEach
    void setUp() {
        handlerMapping = new AnnotationHandlerMapping("samples");
        handlerMapping.initialize();
    }

    @Test
    void get() {
        // given
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);

        given(request.getAttribute("id")).willReturn("gugu");
        given(request.getRequestURI()).willReturn("/get-test");
        given(request.getMethod()).willReturn("GET");

        final HandlerExecution handlerExecution = (HandlerExecution) handlerMapping
            .getHandler(request);

        // when
        final ModelAndView modelAndView = handlerExecution.handle(request, response);
        JspView jspView = (JspView) modelAndView.getView();

        // then
        assertThat(modelAndView.getObject("id")).isEqualTo("gugu");
        assertThat(jspView.getName()).isEqualTo("/get-test");
    }

    @Test
    void post() {
        // given
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);

        given(request.getAttribute("id")).willReturn("gugu");
        given(request.getRequestURI()).willReturn("/post-test");
        given(request.getMethod()).willReturn("POST");

        final HandlerExecution handlerExecution = (HandlerExecution) handlerMapping
            .getHandler(request);

        // when
        final ModelAndView modelAndView = handlerExecution.handle(request, response);
        JspView jspView = (JspView) modelAndView.getView();

        // then
        assertThat(modelAndView.getObject("id")).isEqualTo("gugu");
        assertThat(jspView.getName()).isEqualTo("/post-test");
    }
}

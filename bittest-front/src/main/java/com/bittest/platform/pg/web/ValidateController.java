package com.bittest.platform.pg.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/validate")
public class ValidateController extends BaseController {

    /**
     * 到达指定的页面
     *
     * @return
     */
    @RequestMapping(value = "/validate")
    public ModelAndView list(Model model, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView("/validate/validate");
        return mv;
    }


}

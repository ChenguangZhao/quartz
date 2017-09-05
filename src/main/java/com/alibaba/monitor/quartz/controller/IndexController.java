package com.alibaba.monitor.quartz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/08/31
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/task/*")
    public String index() {
        return "default";
    }
}

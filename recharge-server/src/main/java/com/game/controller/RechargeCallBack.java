package com.game.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller(value = "/callback")
public class RechargeCallBack
{
    private static  final Gson gson = new Gson();

    @ResponseBody
    @RequestMapping(value = "/qqCallBack", method = RequestMethod.GET)
    public String qq(@RequestBody Map<String, String> params)
    {
        return "Hello spring boot";
    }
}

package com.system.controller;

import com.system.domain.Userlogin;
import com.system.domain.dto.resultDto;
import com.system.service.UserloginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Controller
public class LoginController {

    @Resource(name = "userloginServiceImpl")
    private UserloginService userloginService;

    //登录表单处理
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public resultDto logining(Userlogin userlogin) throws Exception {
        //Shiro实现登录
        UsernamePasswordToken token = new UsernamePasswordToken(userlogin.getUserAccount(),
                userlogin.getPassword());
        Subject subject = SecurityUtils.getSubject();
        //获取登录用户名返回前端
        resultDto resultDto = new resultDto();
        userlogin = userloginService.findByName(token.getUsername());
        //如果获取不到用户名就是登录失败，但登录失败的话，会直接抛出异常
        try {
            subject.login(token);
            if (subject.hasRole("admin")) {
                resultDto.setResult(true);
                resultDto.setMessage("登录成功,管理员"+userlogin.getUserName());
                resultDto.setData(userlogin);
                return resultDto;
            } else if (subject.hasRole("user")) {
                resultDto.setResult(true);
                resultDto.setMessage("登录成功,用户"+userlogin.getUserName());
                resultDto.setData(userlogin);
                return resultDto;
            }
        } catch (IncorrectCredentialsException e) {
            resultDto.setMessage("password error");
            resultDto.setResult(false);
            return resultDto;
        } catch (UnknownAccountException e1) {
            resultDto.setMessage("username error");
            resultDto.setResult(false);
            return resultDto;
        }
        resultDto.setResult(false);
        resultDto.setMessage("登录失败,检查后端");
        return resultDto;
    }



}

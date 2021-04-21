package com.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.domain.Userlogin;
import com.system.service.UserloginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("User")
public class CustomerInfoController {
    @Autowired
    private UserloginService userloginService;

    //用户信息列表
    @RequestMapping(value = "UserList",method = RequestMethod.GET)
    public List<Userlogin> getList(){
        return userloginService.getListUser();
    }

    //根据id查询用户信息
    @RequestMapping(value = "queryUserById",method = RequestMethod.GET)
    public Userlogin queryUserById(int userid){
        return userloginService.selectByPrimaryKey(userid);
    }

    //保存用户信息
    @RequestMapping(value = "saveUser", method = RequestMethod.POST)
    public String updateUser(Userlogin userlogin) throws Exception {
        try{
            userloginService.updateByPrimaryKeySelective(userlogin);
        }
        catch (Exception e){
            return "error";
        }
        return "ok";
    }

    //保存头像
    @RequestMapping(value = "saveImg", method = RequestMethod.POST)
    public String saveImg(Userlogin userlogin) throws Exception {
        try{
            userloginService.updateImg(userlogin);
        }
        catch (Exception e){
            return "error";
        }
        return "ok";
    }

    //根据id删除用户信息
    @RequestMapping(value = "deleteUserByid",method = RequestMethod.GET)
    public String deleteUserByName(Integer userid) throws Exception {
        try{
            userloginService.deleteByID(userid);
        }
        catch (Exception e){
            return "error";
        }
        return "ok";
    }

    //插入用户信息
    @RequestMapping(value = "insertUser", method = RequestMethod.POST)
    public String insertUser(Userlogin userlogin) throws Exception {
        try{
            Userlogin user=userloginService.findByName(userlogin.getUserAccount());
            if(user==null) {
                userlogin.setRole(1);
                userloginService.save(userlogin);
            }
            else{
                return "presence";
            }
        }
        catch (Exception e){
            return "error";
        }
        return "ok";
    }

    //模糊查询
    @RequestMapping(value = "selectKey",method = RequestMethod.GET)
    public List<Userlogin> selectKey(String query){
        Userlogin userlogin = new Userlogin();
        userlogin.setQueryKey(query);
        return userloginService.selectKey(query);
    }

    //pagehelper分页
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<Userlogin> getpage(int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Userlogin> list = userloginService.getListUser();
        PageInfo<Userlogin> getpageinfo = new PageInfo<Userlogin>(list);
        return  getpageinfo;
    }
}

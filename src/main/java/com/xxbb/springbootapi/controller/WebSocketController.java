package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.dao.IBaseDao;
import com.xxbb.springbootapi.entity.User;
import com.xxbb.springbootapi.entity.dto.JsonResult;
import com.xxbb.springbootapi.mapper.UserMapper;
import com.xxbb.springbootapi.wrapper.UserQuery;
import com.xxbb.springbootapi.wrapper.UserUpdate;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "WebSocket及时通讯")
@RequestMapping("WebSocket")
public class WebSocketController {
    //spring提供的推送方式
    @Autowired(required = false)
    private SimpMessagingTemplate messagingTemplate;

    /**
     * 广播模式
     *
     * @param requestMsg
     * @return
     */
    @MessageMapping("/broadcast")
    @SendTo("/topic/broadcast")
    public String broadcast(JsonResult requestMsg) {
        //这里是有return，如果不写@SendTo默认和/topic/broadcast一样
        return "server:" + requestMsg.getMessage();
    }

    /**
     * 订阅模式，只是在订阅的时候触发，可以理解为：访问——>返回数据
     *
     * @param id
     * @return
     */
    @SubscribeMapping("/subscribe/{id}")
    public String subscribe(@DestinationVariable Long id) {
        return "success";
    }

    /**
     * 用户模式
     *
     * @param requestMsg
     * @param principal
     */
    //@MessageMapping("/one")
    //@SendToUser("/queue/one") 如果存在return,可以使用这种方式
    public void one() {
        //注意为什么使用queue，主要目的是为了区分广播和队列的方式。实际采用topic，也没有关系。但是为了好理解
        messagingTemplate.convertAndSend("/queue/one", "服务器主动推送");
    }

    @Mapper
    public static interface IUserDao extends IBaseDao<User, UserQuery, UserUpdate, UserMapper> {

    }
}

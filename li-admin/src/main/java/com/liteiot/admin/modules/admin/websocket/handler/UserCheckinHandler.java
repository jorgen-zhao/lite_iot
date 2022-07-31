package com.liteiot.admin.modules.admin.websocket.handler;

import com.liteiot.admin.modules.admin.handler.WSPushHandler;
import com.liteiot.admin.modules.admin.websocket.dto.Message;
import com.liteiot.admin.modules.auth.util.user.JwtTokenUtil;
import com.liteiot.common.constant.AdminMarkConstant;
import com.liteiot.common.util.jwt.IJWTInfo;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class:  UserCheckinHandler
 * <p>
 * Author: zhaoyg
 * Date:   2022/4/1 11:42
 * Desc:   UserCheckinHandler
 */

@Component
@Slf4j
public class UserCheckinHandler {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Qualifier("simpleMapCache")
    @Autowired
    private ConcurrentHashMap<String, List<Channel>> simpleMapCache;

    @Autowired
    private WSPushHandler wsPushHandler;


    /**
     * 缓存校验通过的用户通道
     *
     * @param ctx
     * @param msg
     */
    public void cacheCheckedUser(ChannelHandlerContext ctx, Message msg) {
        ByteBuf byteBuf = msg.getData();
        // 此处接收到web传递的token前面3个乱码字符，手动去除
        String token = byteBuf.toString(StandardCharsets.UTF_8).substring(3);
        IJWTInfo infoFromToken;
        try {
            // 解析token，简单判断当前用户是否登录
            infoFromToken = jwtTokenUtil.getInfoFromToken(token);
            // 将用户信息放入缓存
            String key = infoFromToken.getUniqueName() + AdminMarkConstant.USER_CHECK_MARK + infoFromToken.getTokenId();
            log.info("{}登录保持连接, 缓存项: {}", infoFromToken.getUniqueName(), key);
            cacheChannel(key, ctx.channel());
            // ws建立连接时，推送系统消息总数给前端
            wsPushHandler.pushMsgCount();
        } catch (Exception e) {
            log.error("token验证失败, 拒绝接收", e);
            byteBuf.release();
            msg.getRemote().close();
        }
    }

    /**
     * 缓存为通道组
     *
     * @param key
     * @param channel
     */
    private void cacheChannel(String key, Channel channel) {
        log.info("{}:缓存通道{}", key, channel);
        List<Channel> list = simpleMapCache.get(key);
        if (CollectionUtils.isEmpty(list)) {
            list = new ArrayList<>();
        }
        list.add(channel);
        simpleMapCache.put(key, list);
    }

    /**
     * 通过通道移除缓存用户
     *
     * @param ctx
     */
    public void removeConnectUser(ChannelHandlerContext ctx) {
        // 通过channel 移除用户
        Channel channel = ctx.channel();
        ConcurrentHashMap.KeySetView<String, List<Channel>> keySet = simpleMapCache.keySet();
        for (String key : keySet) {
            Optional<Channel> any = simpleMapCache.get(key).stream().filter(item -> item == channel).findAny();
            if (any.isPresent()) {
                Channel o = any.get();
                keySet.remove(o);
                log.info("{}通道断开连接,从缓存中移除", o);
            }
            if (CollectionUtils.isEmpty(simpleMapCache.get(key))) {
                log.info("{}下已经没有通道连接,移除该通道缓存", key);
                simpleMapCache.remove(key);
            }
        }
    }
}

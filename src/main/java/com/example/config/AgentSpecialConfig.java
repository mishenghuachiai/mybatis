package com.example.config;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.Executor;

@Slf4j
@Component
public class AgentSpecialConfig {
    @Autowired
    StNacosConfigManager stNacosConfigManager;
    private Map<String,Object> agentSpecialUser = Collections.synchronizedMap(Maps.newHashMap());

    private final String ruleDataId = "checkphone.json";

    @PostConstruct
    private void init() {
        try {
            NacosConfigProperties properties = stNacosConfigManager.getNacosConfigProperties();
            ConfigService configService = stNacosConfigManager.getConfigService();
            configService.addListener(ruleDataId, properties.getGroup(), new RuleListener());
            String ruleSource = configService.getConfig(ruleDataId, properties.getGroup(), properties.getTimeout());
            log.info("特殊代理规则加载成功:{}", ruleSource);
            agentSpecialConfig(ruleSource);
        } catch (NacosException e) {
            log.error("agentSpecialHandler.init()", e);
        }
    }
    public Object checkAgentSpecialConfig(String telphone){
        if (CollectionUtils.isEmpty(agentSpecialUser)) {
            return false;
        }
        if(agentSpecialUser.containsKey(telphone)){
            return agentSpecialUser.get(telphone);
        }
        return null;
    }
    private void agentSpecialConfig(String source) {
        if(Objects.isNull(source)){
            return;
        }
        agentSpecialUser.clear();
        agentSpecialUser = JSON.parseObject(source);
    }
    class RuleListener implements Listener {

        @Override
        public Executor getExecutor() {
            return null;
        }

        @Override
        public void receiveConfigInfo(String configInfo) {
            log.debug("agentSpecialRuleListener收到的内容是{}", configInfo);
            agentSpecialConfig(configInfo);
        }
    }
}

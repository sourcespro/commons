package cn.sourcespro.commons.config;

import cn.sourcespro.commons.utils.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * lr-tree
 *
 * @author zhanghaowei
 * @date 2018/10/31
 */
@Configuration
public class SpringConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(SpringConfiguration.class);

    /**
     * 注册Spring Util
     */
    @Bean
    public SpringUtil springUtil(){
        return new SpringUtil();
    }

}

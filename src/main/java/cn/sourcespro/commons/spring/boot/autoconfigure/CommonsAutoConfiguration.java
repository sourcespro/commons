package cn.sourcespro.commons.spring.boot.autoconfigure;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.SpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * commons
 *
 * @author zhanghaowei
 * @since  2018/11/29
 */
@Configuration
@MapperScan("cn.sourcespro.commons.dao")
public class CommonsAutoConfiguration {

    /**
     * 注册Spring Util
     * @return util
     */
    @Bean
    @ConditionalOnMissingBean
    public SpringUtil springUtil(){
        return new SpringUtil();
    }

    /**
     * 注册IdWork
     * @return idWoker
     */
    @Bean
    @ConditionalOnMissingBean
    public IdWoker idWoker(){
        return new IdWoker();
    }



}

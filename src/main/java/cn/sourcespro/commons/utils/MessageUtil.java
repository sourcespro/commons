package cn.sourcespro.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 国际化工具类
 */
@Component
public class MessageUtil {

    private static final Logger logger = LoggerFactory.getLogger(MessageUtil.class);

    private static MessageSource messageSource;

    public MessageUtil(MessageSource messageSource) {
        MessageUtil.messageSource = messageSource;
    }

    /**
     * 获取单个国际化翻译值
     */
    public static String get(String msgKey) {
        try {
            return messageSource.getMessage(msgKey, null, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            logger.error("未找到消息配置文件", e);
            return msgKey;
        }
    }

    /**
     * 获取单个国际化翻译值
     * 带参数list
     */
    public static String get(String msgKey, List<Object> args) {
        try {
            return messageSource.getMessage(msgKey, args.toArray(), LocaleContextHolder.getLocale());
        } catch (Exception e) {
            logger.error("未找到消息配置文件", e);
            return msgKey;
        }
    }

    /**
     * 获取单个国际化翻译值
     * 带参数string
     */
    public static String get(String msgKey, String args0) {
        try {
            Object[] args = new Object[]{args0};
            return messageSource.getMessage(msgKey, args, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            logger.error("未找到消息配置文件", e);
            return msgKey;
        }
    }

}

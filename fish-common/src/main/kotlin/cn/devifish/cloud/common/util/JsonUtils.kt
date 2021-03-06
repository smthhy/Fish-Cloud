package cn.devifish.cloud.common.util

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.lang3.StringUtils
import kotlin.reflect.KClass

/**
 * JsonUtils
 * Json 转换工具 基于 jackson
 * 依赖Spring 环境 (非Spring 环境会自动创建ObjectMapper)
 *
 * @see com.fasterxml.jackson.databind.ObjectMapper
 * @author Devifish
 */
object JsonUtils {

    private val objectMapper: ObjectMapper by lazy {
        SpringUtils.getBean(ObjectMapper::class) ?: ObjectMapper()
    }

    fun toJson(data: Any): String = objectMapper.writeValueAsString(data)

    fun <T : Any> toObject(json: String?, clazz: Class<T>): T? {
        return if (StringUtils.isNoneBlank(json)) {
            objectMapper.readValue(json, clazz)
        } else null
    }

    fun <T : Any> toObject(json: String?, clazz: KClass<T>): T? = toObject(json, clazz.java)

}
package com.chigirh.bc.notice.infra.mysql.mapper.reader

import com.chigirh.bc.notice.infra.mysql.dto.DemoEntity
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 * Demo class.
 */
@Mapper
interface DemoReaderMapper {
    fun findByKey(@Param("key") key: String): DemoEntity?
}

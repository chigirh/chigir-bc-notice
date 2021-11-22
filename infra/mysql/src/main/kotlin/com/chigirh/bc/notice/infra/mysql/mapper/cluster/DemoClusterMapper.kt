package com.chigirh.bc.notice.infra.mysql.mapper.cluster

import com.chigirh.bc.notice.infra.mysql.dto.DemoEntity
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 * Demo class.
 */
@Mapper
interface DemoClusterMapper {
    fun insert(@Param("entities") entities: List<DemoEntity>)
}

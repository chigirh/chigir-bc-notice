package com.chigirh.bc.notice.repository

import com.chigirh.bc.notice.application.repository.demo.DemoRepository
import com.chigirh.bc.notice.domain.entity.demo.Demo
import com.chigirh.bc.notice.infra.mysql.dto.DemoEntity
import com.chigirh.bc.notice.infra.mysql.mapper.cluster.DemoClusterMapper
import com.chigirh.bc.notice.infra.mysql.mapper.reader.DemoReaderMapper
import org.springframework.stereotype.Repository

/**
 * Demo class.
 */
@Repository
class DemoRepositoryImpl(
    val demoClusterMapper: DemoClusterMapper,
    val demoReaderMapper: DemoReaderMapper,
) : DemoRepository {
    
    override fun fetchByKey(key: String): Demo? = demoReaderMapper.findByKey(key)?.toEntity()

    override fun create(entity: Demo) = demoClusterMapper.insert(listOf(entity.toDto()))

    private fun Demo.toDto() = DemoEntity(
        key = key,
        value = value,
    )

    private fun DemoEntity.toEntity() = Demo(
        key = key,
        value = value,
    )
}

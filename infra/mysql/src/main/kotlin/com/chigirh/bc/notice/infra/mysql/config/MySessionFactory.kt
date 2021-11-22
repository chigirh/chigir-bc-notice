package com.chigirh.bc.notice.infra.mysql.config

import org.apache.ibatis.session.Configuration
import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.boot.autoconfigure.MybatisProperties
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import javax.sql.DataSource

object MySessionFactory {
    internal fun create(
        dataSource: DataSource,
        mybatisProperties: MybatisProperties,
    ): SqlSessionFactory {
        val sqlSessionFactoryBean = SqlSessionFactoryBean()
        sqlSessionFactoryBean.setDataSource(dataSource)
        sqlSessionFactoryBean.vfs = SpringBootVFS::class.java

        // beanが上書きされないようにconfigurationを新たに生成
        val configuration = Configuration()
        configuration.defaultExecutorType = mybatisProperties.configuration.defaultExecutorType
        configuration.isMapUnderscoreToCamelCase = mybatisProperties.configuration.isMapUnderscoreToCamelCase

        // MapperLocationを設定
        sqlSessionFactoryBean.setMapperLocations(
            *PathMatchingResourcePatternResolver().getResources(
                mybatisProperties.mapperLocations[0]
            )
        )
        sqlSessionFactoryBean.setConfiguration(configuration)
        return sqlSessionFactoryBean.getObject()!!
    }
}
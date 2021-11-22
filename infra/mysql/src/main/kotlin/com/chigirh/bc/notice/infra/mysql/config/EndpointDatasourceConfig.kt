package com.chigirh.bc.notice.infra.mysql.config

import com.chigirh.bc.notice.infra.mysql.config.MySessionFactory.create
import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.annotation.MapperScan
import org.mybatis.spring.boot.autoconfigure.MybatisProperties
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.support.JdbcTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource


// Datasource for write.
@Configuration
@MapperScan(
    basePackages = ["com.chigirh.bc.notice.infra.mysql.mapper.cluster"],
    sqlSessionFactoryRef = ClusterDatasourceConstant.SESSION_FACTORY
)
class ClusterEndpointDatasourceConfig {
    @Primary
    @ConfigurationProperties("spring.datasource.cluster")
    @Bean(name = [ClusterDatasourceConstant.PROPERTIES])
    fun datasourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Primary
    @Bean(name = [ClusterDatasourceConstant.DATASOURCE])
    fun datasource(
        @Qualifier(ClusterDatasourceConstant.PROPERTIES) properties: DataSourceProperties,
    ): DataSource {
        return properties.initializeDataSourceBuilder().build()
    }

    @Primary
    @Bean(name = [ClusterDatasourceConstant.TX_MANAGER])
    fun txManager(
        @Qualifier(ClusterDatasourceConstant.DATASOURCE) dataSource: DataSource,
    ): PlatformTransactionManager {
        return JdbcTransactionManager(dataSource)
    }

    @Primary
    @Bean(name = [ClusterDatasourceConstant.SESSION_FACTORY])
    @Throws(Exception::class)
    fun sessionFactory(
        @Qualifier(ClusterDatasourceConstant.DATASOURCE) dataSource: DataSource,
        mybatisProperties: MybatisProperties,
    ): SqlSessionFactory {
        return create(dataSource, mybatisProperties)
    }
}

// Datasource for read only.
@Configuration
@MapperScan(
    basePackages = ["com.chigirh.bc.notice.infra.mysql.mapper.reader"],
    sqlSessionFactoryRef = ReaderDatasourceConstant.SESSION_FACTORY
)
class ReaderEndpointDatasourceConfig {
    @ConfigurationProperties("spring.datasource.reader")
    @Bean(name = [ReaderDatasourceConstant.PROPERTIES])
    fun datasourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean(name = [ReaderDatasourceConstant.DATASOURCE])
    fun datasource(
        @Qualifier(ReaderDatasourceConstant.PROPERTIES) properties: DataSourceProperties,
    ): DataSource {
        return properties.initializeDataSourceBuilder().build()
    }

    @Bean(name = [ReaderDatasourceConstant.TX_MANAGER])
    fun txManager(
        @Qualifier(ReaderDatasourceConstant.DATASOURCE) dataSource: DataSource,
    ): PlatformTransactionManager {
        val transactionManager = JdbcTransactionManager(dataSource)
        // Readerは読み取り専用に設定する
        transactionManager.isEnforceReadOnly = true
        return transactionManager
    }

    @Bean(name = [ReaderDatasourceConstant.SESSION_FACTORY])
    @Throws(java.lang.Exception::class)
    fun sessionFactory(
        @Qualifier(ReaderDatasourceConstant.DATASOURCE) dataSource: DataSource,
        mybatisProperties: MybatisProperties,
    ): SqlSessionFactory {
        return create(dataSource, mybatisProperties)
    }
}

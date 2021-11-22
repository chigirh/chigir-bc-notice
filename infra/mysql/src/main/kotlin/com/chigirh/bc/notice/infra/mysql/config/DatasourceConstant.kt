package com.chigirh.bc.notice.infra.mysql.config

/**
 * Constant for datasource creation.
 */
class ClusterDatasourceConstant {
    companion object {
        const val PROPERTIES = "clusterProperties"
        const val SESSION_FACTORY = "clusterSessionFactory"
        const val DATASOURCE = "clusterDatasource"
        const val TX_MANAGER = "clusterTxManager"
    }
}

class ReaderDatasourceConstant {
    companion object {
        const val PROPERTIES = "readerProperties"
        const val SESSION_FACTORY = "readerSessionFactory"
        const val DATASOURCE = "readerDatasource"
        const val TX_MANAGER = "readerTxManager"
    }
}
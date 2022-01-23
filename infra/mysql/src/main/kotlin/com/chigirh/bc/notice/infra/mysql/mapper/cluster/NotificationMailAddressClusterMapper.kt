package com.chigirh.bc.notice.infra.mysql.mapper.cluster

import com.chigirh.bc.notice.infra.mysql.dto.NotificationMailAddressEntity
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 * notification_mail_address mapper.
 */
@Mapper
interface NotificationMailAddressClusterMapper {
    fun insert(@Param("entities") entities: List<NotificationMailAddressEntity>): Int

    fun updateByKey(@Param("entity") entity: NotificationMailAddressEntity): Int

    fun deleteByKey(@Param("key") key: NotificationMailAddressEntity.Key): Int
}

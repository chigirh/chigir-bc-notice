package com.chigirh.bc.notice.infra.mysql.mapper.reader

import com.chigirh.bc.notice.infra.mysql.dto.NotificationMailAddressEntity
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import java.time.LocalDateTime

/**
 * notification_mail_address mapper.
 */
@Mapper
interface NotificationMailAddressReaderMapper {
    fun findByDateTime(@Param("dateTime") dateTime: LocalDateTime): List<NotificationMailAddressEntity>

    fun findByKey(@Param("key") key: NotificationMailAddressEntity.Key): NotificationMailAddressEntity?

    fun findByMailAddressAndDateTime(
        @Param("mailAddress") mailAddress: String,
        @Param("dateTime") dateTime: LocalDateTime,
    ): NotificationMailAddressEntity?

    fun findByBeforeKey(@Param("key") key: NotificationMailAddressEntity.Key): NotificationMailAddressEntity?

    fun findByAfterKey(@Param("key") key: NotificationMailAddressEntity.Key): NotificationMailAddressEntity?

}

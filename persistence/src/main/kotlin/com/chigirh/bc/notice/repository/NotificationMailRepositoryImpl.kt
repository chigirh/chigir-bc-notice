package com.chigirh.bc.notice.repository

import com.chigirh.bc.notice.application.repository.mail.NotificationMailRepository
import com.chigirh.bc.notice.common.util.DateUtil.toDateTime
import com.chigirh.bc.notice.domain.entity.mail.NotificationMail
import com.chigirh.bc.notice.infra.mysql.dto.NotificationMailAddressEntity
import com.chigirh.bc.notice.infra.mysql.mapper.cluster.NotificationMailAddressClusterMapper
import com.chigirh.bc.notice.infra.mysql.mapper.reader.NotificationMailAddressReaderMapper
import org.springframework.stereotype.Repository
import java.time.LocalDate

/**
 * com.chigirh.bc.notice.domain.entity.mail.NotificationMail repository impl.
 */
@Repository
class NotificationMailRepositoryImpl(
    val notificationMailAddressClusterMapper: NotificationMailAddressClusterMapper,
    val notificationMailAddressReaderMapper: NotificationMailAddressReaderMapper,
) : NotificationMailRepository {

    /**
     * Notification mail address insert.
     * <p>
     * If there is 'effectiveDate' valid data, update the 'expirationDate' with 'effectiveDate'.
     * Then insert.
     * <p>
     * If there is data with the same 'expirationDate', Insert it.
     * <p>
     * If there is valid data on the 'expirationDate' of the insert or update data,
     * update the 'effectiveDate' with 'expirationDate'.
     * 'effectiveDate' is the primary key, delete and insert.
     *
     */
    override fun create(model: NotificationMail) {

        val entity = NotificationMailAddressEntity(
            mailAddress = model.mailAddress,
            name = model.name,
            beginDate = model.effectiveDate.toDateTime(),
            endDate = model.expirationDate.toDateTime(),
        )

        val key = NotificationMailAddressEntity.Key(
            mailAddress = entity.mailAddress,
            beginDate = entity.beginDate,
        )

        // find by same 'expirationDate'
        val sameExpirationEntity = notificationMailAddressReaderMapper.findByKey(key)

        // update it
        if (sameExpirationEntity != null) {
            notificationMailAddressClusterMapper.updateByKey(entity)
            updateExpirationDateTheAfterData(entity)
            return
        }

        val validEntity = notificationMailAddressReaderMapper.findByMailAddressAndDateTime(
            mailAddress = entity.mailAddress,
            dateTime = entity.beginDate,
        )

        // if there is 'effectiveDate' valid data
        if (validEntity != null) {
            // update the 'expirationDate' with 'effectiveDate'
            val beforeEntity = NotificationMailAddressEntity(
                mailAddress = validEntity.mailAddress,
                name = validEntity.name,
                beginDate = validEntity.beginDate,
                endDate = entity.beginDate,
            )
            notificationMailAddressClusterMapper.updateByKey(beforeEntity)
        }
        notificationMailAddressClusterMapper.insert(listOf(entity))
        updateExpirationDateTheAfterData(entity)

    }

    override fun fetchByDate(date: LocalDate) =
        notificationMailAddressReaderMapper.findByDateTime(dateTime = date.toDateTime())
            .map { it.toModel() }
            .toList()

    override fun delete(model: NotificationMail) {
        val key = NotificationMailAddressEntity.Key(
            mailAddress = model.mailAddress,
            beginDate = model.effectiveDate.toDateTime(),
        )

        notificationMailAddressClusterMapper.deleteByKey(key)
    }

    private fun updateExpirationDateTheAfterData(entity: NotificationMailAddressEntity) {
        val validEntity = notificationMailAddressReaderMapper.findByMailAddressAndDateTime(
            mailAddress = entity.mailAddress,
            dateTime = entity.endDate,
        ) ?: return // if there is no valid data, no need to update
        val afterEntity = NotificationMailAddressEntity(
            mailAddress = validEntity.mailAddress,
            name = validEntity.name,
            beginDate = entity.endDate,
            endDate = validEntity.endDate,
        )
        notificationMailAddressClusterMapper.deleteByKey(
            NotificationMailAddressEntity.Key(
                mailAddress = validEntity.mailAddress,
                beginDate = validEntity.beginDate,
            ))
        notificationMailAddressClusterMapper.insert(listOf(afterEntity))
    }

    fun NotificationMailAddressEntity.toModel() = NotificationMail(
        mailAddress = mailAddress,
        name = name,
        effectiveDate = beginDate.toLocalDate(),
        expirationDate = endDate.toLocalDate(),
    )
}

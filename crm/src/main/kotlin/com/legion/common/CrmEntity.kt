package com.legion.common

import java.time.Instant
import java.util.*

interface CrmEntity {

    val id: UUID
    val createDate: Instant
    val updateDate: Instant?
    val removed: Boolean
}
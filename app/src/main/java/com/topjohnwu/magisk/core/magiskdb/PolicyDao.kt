package com.topjohnwu.magisk.core.magiskdb

import android.content.Context
import com.topjohnwu.magisk.core.Const
import com.topjohnwu.magisk.core.model.su.SuPolicy
import com.topjohnwu.magisk.core.model.su.toMap
import com.topjohnwu.magisk.core.model.su.toPolicy
import timber.log.Timber


class PolicyDao(
    private val context: Context
) : BaseDao() {

    override val table: String = Table.POLICY

    suspend fun update(policy: SuPolicy) = buildQuery<Replace> {
        values(policy.toMap())
    }.commit()

    suspend fun <R : Any> fetchAll(mapper: (SuPolicy) -> R) = buildQuery<Select> {
        condition {
            equals("uid/100000", Const.USER_ID)
        }
    }.query {
        it.toPolicyOrNull()?.let(mapper)
    }

    private fun Map<String, String>.toPolicyOrNull(): SuPolicy? {
        return runCatching { toPolicy(context.packageManager) }.getOrElse {
            Timber.e(it)
            null
        }
    }

}

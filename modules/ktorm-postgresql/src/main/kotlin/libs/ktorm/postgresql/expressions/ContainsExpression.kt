package libs.ktorm.postgresql.expressions

import me.liuwj.ktorm.expression.ScalarExpression
import me.liuwj.ktorm.schema.BooleanSqlType
import me.liuwj.ktorm.schema.ColumnDeclaring
import me.liuwj.ktorm.schema.SqlType

/**
 * Created at November 2019
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

data class ContainsExpression<T : Any>(
        val left: ScalarExpression<*>,
        val values: Collection<T>,
        override val sqlType: SqlType<Boolean> = BooleanSqlType,
        override val isLeafNode: Boolean = false,
        override val extraProperties: Map<String, Any> = emptyMap()
) : ScalarExpression<Boolean>()

infix fun <T : List<*>> ColumnDeclaring<T>.contains(values: T): ContainsExpression<T> {
    return ContainsExpression(left = asExpression(), values = listOf(values))
}

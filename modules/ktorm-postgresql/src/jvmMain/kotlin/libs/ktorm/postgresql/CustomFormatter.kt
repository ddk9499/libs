package libs.ktorm.postgresql

import libs.ktorm.postgresql.expressions.ContainsExpression
import me.liuwj.ktorm.database.Database
import me.liuwj.ktorm.expression.ScalarExpression
import me.liuwj.ktorm.support.postgresql.PostgreSqlFormatter

/**
 * Created at November 2019
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

class CustomFormatter(database: Database, beautifySql: Boolean, indentSize: Int) :
    PostgreSqlFormatter(database, beautifySql, indentSize) {

    override fun <T : Any> visitScalar(expr: ScalarExpression<T>): ScalarExpression<T> {
        val result = if (expr is ContainsExpression<*>) visitContains(expr)
        else super.visitScalar(expr)

        @Suppress("UNCHECKED_CAST")
        return result as ScalarExpression<T>
    }

    private fun <T : Any> visitContains(expr: ContainsExpression<T>): ContainsExpression<T> {
        visit(expr.left)

        write("@> ")
        write("ARRAY${expr.values.joinToString(", ", "[", "]")}")

        return expr
    }
}

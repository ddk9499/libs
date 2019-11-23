package libs.ktorm.postgresql

import me.liuwj.ktorm.database.Database
import me.liuwj.ktorm.expression.SqlFormatter
import me.liuwj.ktorm.support.postgresql.PostgreSqlDialect

/**
 * Created at November 2019
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

object CustomDialect : PostgreSqlDialect() {
    override fun createSqlFormatter(database: Database, beautifySql: Boolean, indentSize: Int): SqlFormatter {
        return CustomFormatter(database, beautifySql, indentSize)
    }
}

package libs.ktorm.postgresql.types

import me.liuwj.ktorm.schema.SqlType
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Types
import kotlin.reflect.KClass

/**
 * Created at November 2019
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

class EnumSqlType<ENUM : Enum<ENUM>>(private val klass: KClass<ENUM>) : SqlType<ENUM>(Types.VARCHAR, "enum") {

    override fun doGetResult(rs: ResultSet, index: Int): ENUM? {
        val value = rs.getString(index)
        return klass.java.enumConstants?.first { it.name == value }
    }

    override fun doSetParameter(ps: PreparedStatement, index: Int, parameter: ENUM) {
        ps.setString(index, parameter.name)
    }
}

object LongArraySqlType : SqlType<List<Long>>(Types.ARRAY, "longArray") {

    override fun doGetResult(rs: ResultSet, index: Int): List<Long>? =
        (rs.getArray(index).array as Array<*>)
            .filterIsInstance<Int>()
            .map { it.toLong() }

    override fun doSetParameter(ps: PreparedStatement, index: Int, parameter: List<Long>) {
        val array = ps.connection.createArrayOf("BIGINT", parameter.toTypedArray())
        println(array.baseTypeName)
        ps.setArray(index, array)
    }
}

package libs.ktorm.postgresql.types

import me.liuwj.ktorm.schema.BaseTable

/**
 * Created at November 2019
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

inline fun <reified ENUM : Enum<ENUM>, E : Any> BaseTable<E>.enum(name: String): BaseTable<E>.ColumnRegistration<ENUM> {
    return registerColumn(name, EnumSqlType(ENUM::class))
}

fun <E : Any> BaseTable<E>.longArray(name: String): BaseTable<E>.ColumnRegistration<List<Long>> {
    return registerColumn(name, LongArraySqlType)
}

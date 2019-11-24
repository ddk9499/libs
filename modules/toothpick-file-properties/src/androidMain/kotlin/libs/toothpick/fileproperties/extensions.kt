package libs.toothpick.fileproperties

/**
 * Created at November 2019
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

internal val String.isFloat get() = this.toFloatOrNull() != null

internal val String.isInt get() = this.toIntOrNull() != null

internal val String.quoted get() = this.replace("\"", "")

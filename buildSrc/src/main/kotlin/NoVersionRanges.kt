/**
 * Created at November 2019
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

import org.gradle.api.Project

fun Project.checkNoVersionRanges() {
    configurations.forEach {
        it.resolutionStrategy {
            eachDependency {
                val version = requested.version ?: return@eachDependency
                check('+' !in version) {
                    "Version ranges are forbidden because they would make builds non reproducible."
                }
                check("SNAPSHOT" !in version) {
                    "Snapshot versions are forbidden because they would make builds non reproducible."
                }
            }
        }
    }

}

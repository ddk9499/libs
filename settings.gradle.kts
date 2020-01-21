arrayOf(
    ":ktorm-postgresql",
    ":toothpick-file-properties",
    ":collections"
).forEach { include(":modules$it") }

arrayOf(
    ":paycom-base",
    ":paycom-android"
).forEach { include(":modules:paycom$it") }

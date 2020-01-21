arrayOf(
    ":ktorm-postgresql",
    ":toothpick-file-properties",
    ":collections"
).forEach { include(":modules$it") }

arrayOf(
    ":paycom-base",
    ":paycom-android",
    ":paycom-backend"
).forEach { include(":modules:paycom$it") }

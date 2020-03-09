arrayOf(
    ":ktorm-postgresql",
    ":toothpick-file-properties",
    ":toothpick-viewmodel-ext",
    ":collections"
).forEach { include(":modules$it") }

arrayOf(
    ":paycom-base",
    ":paycom-android",
    ":paycom-backend"
).forEach { include(":modules:paycom$it") }

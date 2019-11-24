arrayOf(
    ":ktorm-postgresql",
    ":toothpick-file-properties"
).forEach { include(":modules$it") }

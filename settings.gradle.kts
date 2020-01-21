arrayOf(
    ":ktorm-postgresql",
    ":toothpick-file-properties",
    ":collections"
).forEach { include(":modules$it") }

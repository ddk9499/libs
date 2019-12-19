arrayOf(
    ":ktorm-postgresql",
    ":toothpick-file-properties",
    ":collections",
    ":paycom-sdk"
).forEach { include(":modules$it") }

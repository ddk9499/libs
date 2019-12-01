# Libs (working title)

Libs is a collection of small libraries written in Kotlin, which help 
us don't write boilerplate code and solve routine tasks.

## All modules

- **[Collections:](modules/collections)** Currently has only BiMap implementation
- **[Ktorm PostgreSQL:](modules/ktorm-postgresql)** Some extensions for
Ktorm PostgreSQL dialect.
- **[Toothpick File Properties:](modules/toothpick-file-properties)** 
Toothpick module, for binding properties which saved in file.  


## Download

### Gradle instructions
Make sure you have `jcenter()` in the repositories defined in your project's
(root) `build.gradle` file (default for new Android Studio projects).

### All artifacts
<details>
<summary>
<b>Here are all the artifacts of this library. Just use the ones you need. (Click to expand)</b>
</summary>

```kts
implementation("uz.dkamaloff.libs:collections:$libs_version")
implementation("uz.dkamaloff.libs:ktorm-postgresql:$libs_version")
implementation("uz.dkamaloff.libs:toothpick-file-properties:$libs_version")
```

</details>

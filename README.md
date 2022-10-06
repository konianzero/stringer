# Stringer - your independent correspondent

RSS feed reader.
Read feeds from .rss file or web sources and output to console.

## Requirements

- JDK 17

## Run

- Build
    ```bash
    ./gradlew clean jar
    ```
  - Run
      ```bash
      java -jar build/libs/stringer-0.2.0.jar https://blogs.oracle.com/javamagazine/rss
      ```
    - Or get .rss from [https://blogs.oracle.com/javamagazine/rss](https://blogs.oracle.com/javamagazine/rss)
    ```bash
    java -jar build/libs/stringer-0.2.0.jar javamagazin.rss
    ```
- Run with specified (17) JDK, see [run.sh](run.sh)


## License
[MIT](https://choosealicense.com/licenses/mit/)

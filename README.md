# VersionChecker

A small, fast, efficient and flexible open source library that checks for your apps' updates on your own server.

## Documentation

Will be added as soon as possible!

## Build

Building with gradle is fairly straight forward:

```shell
git clone https://github.com/mafixmafix/VersionCheckerSample.git
cd VersionCheckerSample
./gradlew build
```

## Installation

You will need to include the ` version-checker-0.x.aar` in your application's runtime.

### Maven

```xml
<dependency>
  <groupId>tk.pallas.versionchecker</groupId>
  <artifactId>version-checker</artifactId>
  <version>0.1.0</version>
  <type>pom</type>
</dependency>
```

### Gradle

```groovy
// Add VersionChecker dependencies
dependencies {
  implementation 'tk.pallas.versionchecker:version-checker:0.1.0'
}
```

### Ivy

```xml
<dependency org='tk.pallas.versionchecker' name='version-checker' rev='0.1.0'/>
```

**Note**: library is available **JUST** on JCenter.

## Usage


## ProGuard / R8

Will be added as soon as possible!

## Contributing

Of course, Pull Requests are welcome.

Here are the few rules we'd like you to respect if you do so:

* Only edit the code related to the suggested change, so DON'T automatically format the classes you've edited.
* Use IntelliJ default formatting rules.
* Regarding licensing:
  * You must be the original author of the code you suggest.
  * You must give the copyright to "the VersionChecker Project"

## License

    Copyright 2019 Pallas

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

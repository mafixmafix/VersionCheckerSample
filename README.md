# VersionChecker

A small, fast, efficient and flexible open source library that checks for your apps' updates from REST APIs.

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

**Note**: library is available **ONLY** on JCenter.

## Usage

#### 1. Create *POJO* class from *JSON* response.

*JSON* response:
```json
{
    "data": {
        "id": 2,
        "first_name": "Janet",
        "last_name": "Weaver",
        "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg"
    }
}
```

*POJO* class:
```java
public class ResultModel {
    Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @NonNull
    @Override
    public String toString() {
        return data.toString();
    }

    public static class Data {
        Integer id;
        String first_name;
        String last_name;
        String avatar;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
```

*Note*:
* [reqres][reqres] used for fake REST-API.
* You can use [jsonschema2pojo][jsonschema2pojo] for model *JSON* to *POJO*
    
#### 2. Create `VersionChecker` instance in AppModule.
    
```java
@Module
public abstract class AppModule {

    @Provides
    static VersionChecker provideVersionChecker(VersionCheckerClient versionCheckerClient) {
        return VersionChecker.Builder.getInstance()
                .versionCheckerClient(versionCheckerClient)
                .build();
    }

    @Provides
    static VersionCheckerClient provideVersionCheckerClient(Context context, Condition<ResultModel> condition) {
        return VersionCheckerClient
                .Builder
                .getInstance()
                .appContext(context)
                .condition(condition)
                .result(ResultModel.class)
                .url("https://reqres.in/api/users/2") // Fake REST_API :)
                .build();
    }

    @Provides
    static Condition<ResultModel> provideCondition() {
        return new Condition<ResultModel>() {

            @Override
            public void whatHappen(IVersion<ResultModel> version) {
                if (version.getNetworkInfo().isConnected() && version.getTag().equals(MainActivity.TAG)) {
                    if (version.getResult() != null) {
                        Log.d("VersionChecker", version.getResult().toString());
                        ((MainActivity) version.getActivity()).openDialogFragment();
                    }
                }
            }
        };
    }

    @Singleton
    @Binds
    abstract Context bindContext(App app);
}
```

*Note*:
* Sample is based on *Dagger2* DI Framework.
* `VersionChecker` instance **MUST** be created in application scope.

#### 3. Invoke `start` method in `onResume` method of your activity.

```java
@Override
protected void onResume() {
    super.onResume();
    versionChecker.start(this, TAG);
}
```

*Note*:
* **DON'T** invoke `start` method in any other activity callbacks.(e.g.,`onCreate`)
* You can invoke `start` method in multiple activities!

#### 4. Enjoy!:smiley:

*Note*:
* See the sample for more information.

## Dependencies

* [Gson][Gson]
* [Volley][Volley]

**Note**: All dependencies will be removed in 1.x version.:wink:

## R8 / ProGuard

Will be added as soon as possible!

## Contributing

Of course, Pull Requests are welcome.

Here are the few rules we'd like you to respect if you do so:

* Only edit the code related to the suggested change, so **DON'T** automatically format the classes you've edited.
* Use IntelliJ default formatting rules.
* Regarding licensing:
  * You must be the original author of the code you suggest.
  * You must give the copyright to the **VersionChecker** Project.

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

[Gson]: https://github.com/google/gson
[volley]: https://github.com/google/volley
[reqres]: https://reqres.in/
[jsonschema2pojo]: http://www.jsonschema2pojo.org/

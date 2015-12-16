Marvel Api Client for Java [![Build Status](https://travis-ci.org/Karumi/MarvelApiClientAndroid.svg?branch=master)](https://travis-ci.org/Karumi/MarvelApiClientAndroid)
==========================


This is an implementation in Java for the Mavel Api. This library works with Java 7 and it is compatible with Android.

**IMPORTANT**: This library is under development.

Usage
-----

**Prerequisites**: You need obtain valid Marvel keys for use this library. You can obtain it from [Marvel] [1]  

To start using the library you just need to create a valid MarvelApiConfiguration:

```java
MarvelApiConfig marvelApiConfig = new MarvelApiConfig.Builder(publicKey, privateKey).debug().build();
```

Once you have configure you Marvel api, you can use this object to obtain information from Marvel Api.

###Characters Api

CharacterApiClient contains all operations that you can make over characters. If you want to do complex queries you can use CharactersQuery object.

```java
CharacterApiClient characterApiClient = new CharacterApiClient(marvelApiConfig);
CharactersQuery spider = CharactersQuery.Builder.create().withOffset(0).withLimit(10).build();
MarvelResponse<CharactersDto> all = characterApiClient.getAll(spider);
```

Add it to your project
----------------------

Include the library in your ``build.gradle``

```groovy
dependencies{
    compile 'com.karumi:marvelapiclient:0.0.1'
}
```

or to your ``pom.xml`` if you are using Maven

```xml
<dependency>
    <groupId>com.karumi</groupId>
    <artifactId>marvelapiclient</artifactId>
    <version>0.0.1</version>
    <type>jar</type>
</dependency>

```
Do you want to contribute?
--------------------------

Feel free to add any useful feature to the library, we will be glad to improve it with your help.

Keep in mind that your PRs **must** be validated by Travis-CI. Please, run a local build with ``./gradlew checkstyle assemble test`` before submiting your code.


Libraries used in this project
------------------------------

* [okhttp] [2]
* [retrofit] [3]
* [JUnit] [4]
* [Mockito] [5]
* [mockwebserver] [6]

License
-------

    Copyright 2015 Karumi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


    Data provided by Marvel. © 2014 Marvel

[1]: http://developer.marvel.com/documentation/authorization
[2]: https://github.com/square/okhttp
[3]: https://github.com/square/retrofit
[4]: https://github.com/junit-team/junit
[5]: https://github.com/mockito/mockito
[6]: https://github.com/square/okhttp/tree/master/mockwebserver
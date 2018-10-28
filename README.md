![Karumi logo][karumilogo] Marvel Api Client written in Java [![Build Status](https://travis-ci.org/Karumi/MarvelApiClientAndroid.svg?branch=master)](https://travis-ci.org/Karumi/MarvelApiClientAndroid) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.karumi/marvelapiclient/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.karumi/marvelapiclient)
==========================


This is an implementation written in Java for the [Mavel Api][marvelApi]. This library is implemented using Java 7 and it is fully compatible with Android.

**IMPORTANT**: This library is under development.

Usage
-----

**Prerequisites**: You need to obtain valid Marvel keys for using this library. You can obtain it from [Marvel] [marvelAuthorization]  

To start using the library you just need to create a valid MarvelApiConfiguration:

```java
MarvelApiConfig marvelApiConfig = new MarvelApiConfig.Builder(publicKey, privateKey).debug().build();
```

Once you have configured your Marvel api, you can use this object to obtain information from the Marvel Api.

### Characters Api

``CharacterApiClient`` contains all operations used to retrieve characters from the Marvel Api. If you want to perform complex queries you can use ``CharactersQuery`` object.

```java
CharacterApiClient characterApiClient = new CharacterApiClient(marvelApiConfig);
CharactersQuery spider = CharactersQuery.Builder.create().withOffset(0).withLimit(10).build();
MarvelResponse<CharactersDto> all = characterApiClient.getAll(spider);
```

### Comics Api

``ComicApiClient`` contains all operations used to retrieve comics from the Marvel Api. If you want to perform complex queries you can use ``ComicsQuery`` object.

```java
ComicApiClient comicApiClient = new ComicApiClient(marvelApiConfig);
ComicsQuery query = ComicsQuery.Builder.create().withOffset(0).withLimit(10).build();
MarvelResponse<ComicsDto> all = comicApiClient.getAll(query);
```

### Series Api

``SeriesApiClient`` contains all operations used to retrieve series from the Marvel Api. If you want to perform complex queries you can use ``SeriesQuery`` object.

```java
SeriesApiClient seriesApiClient = new SeriesApiClient(marvelApiConfig);
SeriesQuery query = SeriesQuery.Builder.create().withOffset(0).withLimit(10).build();
MarvelResponse<SeriesCollectionDto> all = seriesApiClient.getAll(query);
```

Add it to your project
----------------------

Include the library in your ``build.gradle``

```groovy
dependencies{
    compile 'com.karumi:marvelapiclient:0.0.4'
}
```

or to your ``pom.xml`` if you are using Maven

```xml
<dependency>
    <groupId>com.karumi</groupId>
    <artifactId>marvelapiclient</artifactId>
    <version>0.0.4</version>
    <type>jar</type>
</dependency>

```
Do you want to contribute?
--------------------------

Feel free to add any useful feature to the library, we will be glad to improve it with your help.

Keep in mind that your PRs **must** be validated by Travis-CI. Please, run a local build with ``./gradlew checkstyle assemble test`` before submiting your code.


Libraries used in this project
------------------------------

* [OkHttp] [okHttp]
* [Retrofit] [retrofit]
* [JUnit] [junit]
* [Mockito] [mockito]
* [mockwebserver] [mockwebserver]

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
    
[marvelApi]: http://developer.marvel.com/
[marvelAuthorization]: http://developer.marvel.com/documentation/authorization
[okHttp]: https://github.com/square/okhttp
[retrofit]: https://github.com/square/retrofit
[junit]: https://github.com/junit-team/junit
[mockito]: https://github.com/mockito/mockito
[mockwebserver]: https://github.com/square/okhttp/tree/master/mockwebserver
[karumilogo]: https://cloud.githubusercontent.com/assets/858090/11626547/e5a1dc66-9ce3-11e5-908d-537e07e82090.png

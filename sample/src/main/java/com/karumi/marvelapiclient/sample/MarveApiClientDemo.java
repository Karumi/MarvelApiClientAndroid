/*
 *   Copyright (C) 2015 Karumi.
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.karumi.marvelapiclient.sample;

import com.karumi.marvelapiclient.CharacterApiClient;
import com.karumi.marvelapiclient.ComicApiClient;
import com.karumi.marvelapiclient.MarvelApiConfig;
import com.karumi.marvelapiclient.MarvelApiException;
import com.karumi.marvelapiclient.SerieApiClient;
import com.karumi.marvelapiclient.model.CharacterDto;
import com.karumi.marvelapiclient.model.CharactersDto;
import com.karumi.marvelapiclient.model.CharactersQuery;
import com.karumi.marvelapiclient.model.ComicDto;
import com.karumi.marvelapiclient.model.ComicsDto;
import com.karumi.marvelapiclient.model.MarvelResponse;
import com.karumi.marvelapiclient.model.SerieDto;
import com.karumi.marvelapiclient.model.SeriesDto;

public class MarveApiClientDemo {
  public static void main(String[] args) {
    if (args.length < 3) {
      System.out.println("MarveApiClientDemo [publicKey] [privateKey] [operation]");
    }
    String publicKey = args[0];
    String privateKey = args[1];
    String operation = args[2];

    MarvelApiConfig marvelApiConfig =
        new MarvelApiConfig.Builder(publicKey, privateKey).debug().build();

    try {
      if (operation.equals("getCharacters")) {
        CharacterApiClient characterApiClient = new CharacterApiClient(marvelApiConfig);
        CharactersQuery spider =
            CharactersQuery.Builder.create().withOffset(0).withLimit(10).build();
        MarvelResponse<CharactersDto> all = characterApiClient.getAll(spider);
        System.out.println(all.toString());
      } else if (operation.equals("getCharacter")) {
        CharacterApiClient characterApiClient = new CharacterApiClient(marvelApiConfig);
        MarvelResponse<CharacterDto> character = characterApiClient.getCharacter("1011334");
        System.out.println(character.toString());
      } else if (operation.equals("getComics")) {
        ComicApiClient comicApiClient = new ComicApiClient(marvelApiConfig);
        MarvelResponse<ComicsDto> comics = comicApiClient.getAll(0, 10);
        System.out.println(comics.toString());
      } else if (operation.equals("getComic")) {
        ComicApiClient comicApiClient = new ComicApiClient(marvelApiConfig);
        MarvelResponse<ComicDto> comic = comicApiClient.getComic("42882");
        System.out.println(comic.toString());
      } else if (operation.equals("getSeries")) {
        SerieApiClient serieApiClient = new SerieApiClient(marvelApiConfig);
        MarvelResponse<SeriesDto> series = serieApiClient.getAll(0, 10);
        System.out.println(series.toString());
      } else if (operation.equals("getSerie")) {
        SerieApiClient serieApiClient = new SerieApiClient(marvelApiConfig);
        MarvelResponse<SerieDto> serie = serieApiClient.getSerie("15276");
        System.out.println(serie.toString());
      }
    } catch (MarvelApiException e) {
      System.out.println("An error calling the Marvel Api " + e);
      e.printStackTrace();
    }
  }
}

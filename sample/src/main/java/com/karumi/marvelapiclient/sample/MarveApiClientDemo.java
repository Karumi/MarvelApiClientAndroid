package com.karumi.marvelapiclient.sample;

import com.karumi.marvelapiclient.CharacterApiClient;
import com.karumi.marvelapiclient.MarvelApiClient;
import com.karumi.marvelapiclient.model.CharactersDto;
import com.karumi.marvelapiclient.model.CharactersQuery;
import com.karumi.marvelapiclient.model.MarvelResponse;

import java.io.IOException;

public class MarveApiClientDemo {
  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println("MarveApiClientDemo [publicKey] [privateKey]");
    }
    String publicKey = args[0];
    String privateKey = args[1];

    MarvelApiClient marvelApiClient = MarvelApiClient.with(publicKey, privateKey);
    CharacterApiClient characterApiClient = new CharacterApiClient(marvelApiClient);
    try {
      CharactersQuery spider = CharactersQuery.Builder.create().withOffset(0).withLimit(10).build();
      MarvelResponse<CharactersDto> all = characterApiClient.getAll(spider);
      System.out.println(all.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

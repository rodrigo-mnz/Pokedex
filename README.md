# Pokedex (Study Project)

Android app using PokeAPI [pokeapi](https://pokeapi.co) to retrieve and display information about Pokemon.

## Tech used

* MVVM Architecture: Separates concerns between Model (data), View (UI), and ViewModel (data preparation and communication).
* Jetpack Compose: Builds declarative and composable UI for a smooth and performant experience.
* Paging Compose: Efficiently handles large datasets by loading pokemon information in pages.
* Retrofit: Simplifies network calls to the PokeAPI for retrieving pokemon data.
* Kotlin: Modern language for concise and expressive code.
* Koin: Dependency injection framework for managing object lifecycles.
* Kotlin Flow: Asynchronous data streaming to handle data updates in a reactive way.
* Navigation Compose: Straightforward navigation with composable screens.
* Single Activity / No Fragments


## Solution

MVVM Architecture & Koin approach for the ease of maintenance and testability.

Navigation Compose for a 100% Compose approach, making it possible for a single activity and no fragments for study purposes (still evaluating the result though).

Flow & Paging Compose: I chose to use Paging Compose mostly because I'm not familiar with it and want to study it. Although it's a powerful framework for pagination, I'm still trying to find a reliable way to test it.

Basic cache with shared preferences: As we need to fetch Pokemon details for each Pokemon in the list to get the image, this is a must-have but still basic. I will try to use Room in the next iterations.

## TODO & Future improvements

* Map all pokemon types and colors and delegate the resposability properly.
* Improve the implementation of Paging Compose and find a realiable way to test it.
* Build previews for each screen.
* Build compose components for reusability.
* Improve instrumented tests, also create End-to-end UI tests (With screenshots maybe).
* Improve navigation.
* Code coverage.
* Wishlist.
* CI/CD.
* Pokemon search.

***

#### Note

- Compose performance it's notable worse in the debug variation still, for performance test the release variation.
- Although this is a challenge project, my goal is to keep it as a study project and keep improving it and trying new solutions :)

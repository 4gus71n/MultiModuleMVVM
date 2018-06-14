# MultiModuleMVVM
A boilerplate-less Kotlin MVVM with support for Instant Apps

## Description

This project is pretty much the same MVVMKotlin project that I have in my repo, but with support for Instant Apps. This App feeds from the Imgur public API. The idea is that we are going to have several modules:

- app: The one that is in charge of building and deploying the App

- core: (Or base) is the one that has all the dependencies and all the common Dagger Modules that are use across the children modules.

- postmenu: Is going to feature a list of Imgur posts

- postdetail: Is going to display the detail of those posts

## Current challenges

- DI: I need to make sure that there's no cross-module references, we don't want to rebuild all the modules from the projects if we just changed one. To keep track of this I'm going to be using the BuildTime Tracker Plugin (https://github.com/passy/build-time-tracker-plugin) to make sure that the building time only gets faster.

- ViewModel DI: The idea is that when we create a new feature module, the Dagger Component for that module should be pretty straightforward to wring, we should only specify the BaseComponent from which depends (that exposes all the features from the AppModule such as Repositories), and we should specify a Dagger Module for this module component which specifies how the ViewModel is created (which dependencies are injected). For now, I kinda "patch" this issue, I'd like to improve it in a way that you only need to declare a @Provides + @Map so the ViewModel entry is automatically injected into the ViewModelFactory. Right now, I'm creating the ViewModelFactory instance, right there in the Dagger Module, which is no good because we are going to have one different ViewModelFactory per each feature module. But this is kinda tricky beacuse remember that we don't want any cross-reference betweent the feature modules that could slow-down the building time.

Right now the project is working but I need to improve these things.

## Sources

https://github.com/NikitaKozlov/GradleBuildExperiment

https://proandroiddev.com/modules-modules-everywhere-cffa37449f58

https://github.com/MyDogTom/MultiModuleExample

https://medium.com/mindorks/writing-a-modular-project-on-android-304f3b09cb37

https://github.com/passy/build-time-tracker-plugin

https://medium.com/iadvize-engineering/android-multi-module-architecture-a1a7a291a47e

https://medium.com/@luigi.papino/dagger2-for-modular-architecture-332e1250a85f BESTTTTTTTTTTT

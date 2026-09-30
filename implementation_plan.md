# Tergoum (ተረጉም) - English & Amharic Dictionary & Translator
## Jetpack Compose Architecture & Implementation Plan

### 1. Executive Summary
Tergoum (ተረጉም) is a lightweight mobile dictionary and translation application for Android built with Kotlin and Jetpack Compose (Material 3). It bridges English and Amharic with instant offline accessibility, crystal-clear Ge'ez script typography (ፊደላት), phonetic romanization, and bidirectional translation.

### 2. Android Project Architecture
- Model: WordEntry, TranslationDirection
- Data: OfflineDictionary (65+ curated offline pairs across 8 categories), DictionaryRepository (Kotlin Coroutines Flow)
- ViewModel: DictionaryViewModel (StateFlow, search debounce, direction toggle, favorite bookmarking)
- Theme: TergoumTheme (Soft Ethio-Green, Deep Slate, Material 3 ColorScheme, Noto Sans Ethiopic)
- UI: SearchBarComponent, WordCard, BottomNavBar, SearchScreen, FavoritesScreen, FidelScreen
# StackOverflowUsersApp 
The StackOverflowUsersApp is a simple Kotlin-based Android application designed to fetch and display user data from the Stack Overflow API. The app aims to provide a user-friendly interface for users to browse and explore Stack Overflow user profiles. 

## 🛠️ Tech Stack
* Minimum SDK level: 26 📱
* Language: Kotlin
* Asynchronous programming: Kotlin Coroutines + flow 
* Serialization: KotlinX serialization for mapping the API responses
* Networking: OkHttp and Retrofit 📡
* Database management: AndroidX Room 📁
* Testing: JUnit 📊
* User interface: AndroidX Compose UI and Material 3 for the  🎨

## 📂 Architecture
The architecture is structured into two layers:
- **UI Layer:** 
  Follows the **MVVM pattern** consisting of Ui elements(Activity and composables) and the ViewModel, which handles  ui logic and manages the ui states.

- **Data layer:**
  Follows the **Repository pattern** where the repository mediates between different data sources. The user list is fetched from the API via network requests and the followed users are saved in a Room db.

## 🤔 Technical decisions
Some technical decisions made during the development:

**How to handle the followed users?**

I decided to use Room for persitence since it makes managing (select/filter/order) data easy as it uses SQL is under the hood.  
It also supports flows, meaning that when a user is folllowed/unfollowed the changes are automatically reflected in the UI.

**How to reduce the network requests for loading images?**

I needed to cache images to make the scrolling smoother and minimize the network requests every time that user scrolls the list or configuration change occurs.
I added a LruCache to the ImageLoader class to get images from memory. 
  


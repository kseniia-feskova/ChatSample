Description:
---
My first attempt at creating an app with Compose. 
The idea is simple - a primitive chat for two interlocutors with authorization. Firebase is used as the remote database. During the development process, a news screen was added for working with the API and Room.
The navigation logic and news loading logic are shown in the diagrams in Figma: [Logic diagrams](https://www.figma.com/file/8WbVt1cdh7tux2IYcdDMNR/Untitled?type=whiteboard&node-id=0-1&t=C0pntR3b8efcUr26-0)

Preview:
---
![Chat preview](https://github.com/kseniia-feskova/ChatSample/blob/96c4d20a2fc5755e5e4092c6fad10f2af13016bf/assets/preview_chat.gif)
Technologies:
---
1. Jetpack Compose, Navigation Component, Single Activity
2. Shared preferances: Kotpref
3. Locale database: Room
4. Remotedatabase: Firestore (Firebase)
5. Work with API: Retrofit
6. Asyncronus: Coroutines, RxJava
7. DI: Dagger
8. Tests: JUnit, Mockito

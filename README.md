# 프로젝트 제목

**간편한 채팅 앱: Chat~Connect**

## 프로젝트 설명 및 목적

ChattyConnect는 사용자들이 간단하게 회원 가입하고 로그인하여 다른 사용자들과 편리하게 채팅할 수 있는 안드로이드 앱입니다. 이 앱은 사용자들 간의 대화를 더욱 편리하게 만들어주는 목적으로 개발되었습니다.

## 주요 기능

### 1. 회원가입 및 로그인

파이어베이스를 활용하여 **회원가입 및 로그인 기능**을 구현했습니다. 사용자는 간편한 과정으로 앱에 가입하고 로그인할 수 있습니다.

### 2. 채팅목록에서 회원 이름 표시

파이어베이스에서 가져온 사용자 정보를 기반으로 **채팅목록에 회원들의 이름을 표시**하도록 구현했습니다. 이를 통해 사용자들은 누가 채팅에 참여하고 있는지 한눈에 확인할 수 있습니다.

### 3. 개인 채팅 창

사용자가 로그인한 아이디로 채팅에 참여하면, 상대방이 보낸 채팅 내용을 **개인적으로 볼 수 있는 개인 채팅 창**을 구현했습니다. 이를 통해 사용자들은 대화를 더욱 편리하게 이어나갈 수 있습니다.

### 4. 이미지 변경 버튼

사용자들은 **버튼 클릭 시 프로필 이미지가 변경**되는 기능을 이용하여 자신의 프로필을 원하는 이미지로 변경할 수 있습니다.

## 구현 이미지


*회원가입 및 로그인 화면*
![ㄴ](https://github.com/boradorying/chatingApp/assets/136980408/3fda8eff-1655-4af0-bb57-f1aad4b45f6a)
--------------------------------------------------------------------------------
------------------------------
*채팅목록 화면에서 회원 이름 표시* *개인 채팅 창**회원가입 및 로그인 화면*
![0809194057317543](https://github.com/boradorying/chatingApp/assets/136980408/439d2d1d-a3e8-4538-9038-f4c287e75806)







*이미지 변경 버튼 클릭 시 이미지 변경*

## 사용 기술 및 라이브러리

- **Firebase Authentication**: 회원가입 및 로그인 구현을 위해 사용
- **Firebase Realtime Database**: 채팅 데이터 저장 및 불러오기에 활용
- **Android Kotlin**: 안드로이드 앱을 개발하기 위한 주요 프로그래밍 언어로 사용되었습니다.
- **RecyclerView**: 채팅 목록 화면과 개인 채팅 창에서 아이템들을 효율적으로 표시하기 위해 사용되었습니다.

# FinalProject-ApartTalk
테킷 앱스쿨: 안드로이드2기 (아파트톡) - 6팀

## Intro
- - -
- 프로젝트명 : Apart Talk
- 기획 및 개발 기간 : 2024.5.13 ~ 2024.6.11
- 개발인원 : 6명
- 개발환경 : Android Studio
- 개발언어 : Kotlin
- 데이터베이스 : Firebase
- - -

## Member 👤

### 🧑‍💻 허성욱 (팀장)
### 🧑‍💻 김성현
### 🧑‍💻 이병진
### 🧑‍💻 송주석
### 🧑‍💻 황순혁
### 👩‍💻 최나연

- - -

📕 노션 : https://likelion.notion.site/94f49c40d6e2490285e929fe6f0bd4e2

📗 피그마 : https://www.figma.com/file/pIUiLuRGJIvtwiUlZ7okoi?embed_host=notion&kind=file&node-id=18%3A7&page-selector=0&t=4Nik8RPBJA3rkgRV-1&viewer=1

🖥️ 시연영상 : https://youtube.com/watch?v=BAE43OqXfcA&si=2dEYlG-eGlgSdPhB

- - -

# 1. 프로젝트 주제 소개 및 기획의도
대한민국 주택유형 중 가장많이 차지하고 있는 아파트를 대상으로 아파트 커뮤니티 활성화를 위한 앱입니다.

![스크린샷 2025-01-09 오후 12 51 45](https://github.com/user-attachments/assets/7339d2ee-ded6-43a5-96de-106ba25a9610)

# 2. 시장조사
기존에 출시된 아파트관련 어플 중 두가지를 찾아 어떠한 문제점이 있는지 알아보고 단점들을 보완하기 위해 시장조사를 진행했습니다.

![스크린샷 2025-01-09 오후 12 53 06](https://github.com/user-attachments/assets/8c91e269-eb2e-444f-9b77-0586a8df9ad3)

# 3. 개발목표
기존 어플 사용자들이 불편함을 느낀 부분들에 대해 최소화하며 아파트 관리 및 유지보수 보다는 커뮤니티를 중점적으로 생각하여 
'가족만큼 가까운 이웃' 이라는 슬로건으로 다가간다.

# 4. 프로젝트 아키텍처
 - MVVM 구조

![스크린샷 2025-01-09 오후 1 12 22](https://github.com/user-attachments/assets/4a0af0a9-d93b-404f-b7a8-79621995ea66)

# 5. 앱 기능 소개

![APARTTALK ppt 깃 001](https://github.com/user-attachments/assets/f63ab736-1967-415c-b149-5bb2a8784f5a)

![APARTTALK ppt 깃 002](https://github.com/user-attachments/assets/992bedb2-14d2-49db-b018-e3d502d5e255)

![APARTTALK ppt 깃 003](https://github.com/user-attachments/assets/9fe2bb2b-519d-4ad8-bb5e-00b870b407a0)

![APARTTALK ppt 깃 004](https://github.com/user-attachments/assets/8beaa4ef-2db6-43d0-8141-1e8c4adfe161)

![APARTTALK ppt 깃 005](https://github.com/user-attachments/assets/351582a3-eef8-4ed9-822f-fbb6050eb52d)

![APARTTALK ppt 깃 006](https://github.com/user-attachments/assets/ca6f8be3-8428-4a91-b44a-3453a1c5a291)

![APARTTALK ppt 깃 007](https://github.com/user-attachments/assets/efcc8ac3-d248-4989-9e8d-9d9cf7946b3e)

![APARTTALK ppt 깃 008](https://github.com/user-attachments/assets/97e018f8-df57-4056-90f7-59a2f353a923)

![APARTTALK ppt 깃 009](https://github.com/user-attachments/assets/157fd3f6-b64d-4978-989e-86a55575acfc)

![APARTTALK ppt 깃 010](https://github.com/user-attachments/assets/3990f627-a260-491e-96c5-229f8d1b095c)

![APARTTALK ppt 깃 011](https://github.com/user-attachments/assets/0f296984-f9c7-4f43-82d5-7620af8353da)

![APARTTALK ppt 깃 012](https://github.com/user-attachments/assets/7b490d78-4557-425c-a7df-33ae0fc94573)

![APARTTALK ppt 깃 013](https://github.com/user-attachments/assets/9bcdc93a-214d-48ed-aaf8-c4d513254c5f)

![APARTTALK ppt 깃 014](https://github.com/user-attachments/assets/dac69c04-97f6-4499-bcc5-98072b27533c)

![APARTTALK ppt 깃 015](https://github.com/user-attachments/assets/b4ab2384-46db-47d0-8d2f-bdf9b6ece8ee)

- - -

# 6. 트러블 슈팅

- 데이터 로드 속도 저하 문제
    - 문제 : 게시글과 댓글의 데이터가 많아지면서 데이터를 가져오는 속도가 점점 느려짐
    - 원인 추론 : 댓글 컬렉션을 게시글 컬렉션과 별도로 저장하여, 문서마다 게시글 번호를 검색하고 해당하는 번호를 가져오다 보니 시간이 많이 소요됨
    - 해결 방법 : 게시글 컬렉션 안에 댓글 컬렉션을 두어 데이터를 검색할 양을 줄였으며, 번호값 대신 해시값을 사용하여 더 빠르게 데이터를 불러올 수 있도록 최적화
    
- Activity 전환 및 상태 관리 문제
    - 문제 : 화면 전환 시 로그인 화면을 종료하지 않고 회원가입 화면으로 넘어가는 과정에서, 회원가입을 마치고 메인 화면으로 이동할 때 로그인 화면이 백스택에 남아 있어 앱 흐름에 문제가 발생
    - 해결 방법 : ActivityResultLauncher를 사용해 회원가입 화면에서 결과를 받아 로그인 화면을 종료하고, 로그인 화면이 백스택에 남지 않도록 처리함

- 배열 직렬화 문제
    - 문제 : Firebase는 배열 직렬화가 지원되지 않아 Serializing Arrays is not supported please use Lists instead라는 오류가 발생했습니다.
    - 원인 추론 : textInputLayout의 타입이 Editable이라, 이를 String 타입으로 변환하지 않으면 Firebase에 저장할 때 자동으로 배열 타입으로 변환되는 것으로 추정
    - 해결 방법 : textInputLayout의 데이터를 String 타입으로 변환하여 Firebase에 저장되도록 수정



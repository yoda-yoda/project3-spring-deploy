# Durcit: Reddit을 모티브로 한 게임 커뮤니티 플랫폼

**Durcit**은 Reddit에서 영감을 받아 만들어진 게임 커뮤니티 플랫폼으로, 게이머들이 활발하게 소통하고 콘텐츠를 공유할 수 있는 공간입니다. 다양한 기능을 통해 사용자가 더 편리하고 재미있게 커뮤니티 활동을 즐길 수 있도록 설계되었습니다.

---

## 🌟 주요 기능

### 1. 일반 기능
- **태그 시스템**: 게시글을 태그로 분류해 쉽게 탐색하고 발견할 수 있도록 지원합니다.
- **댓글 및 멘션**: 댓글 작성 시 다른 사용자를 멘션하여 소통할 수 있습니다.
- **채팅**: 실시간 메시징으로 원활한 의사소통을 지원합니다.
- **게시글**: 게임과 관련된 글, 경험, 미디어를 공유할 수 있습니다.
- **검색**: 게시글, 태그, 사용자 등을 손쉽게 검색할 수 있습니다.
- **프로필**: 사용자 프로필에 소개글과 프로필 이미지를 추가하고 수정할 수 있습니다.
- **피드**: 팔로우한 태그와 게시글을 기반으로 개인화된 피드를 제공합니다.
- **푸시알람** : 팔로잉한 사용자의 게시글, 채팅 도착, 댓글 등 다양한 상황에서 푸시 알림을 제공합니다.

---

### 2. 어드민 페이지 기능
별도의 어드민 페이지에서 플랫폼 관리를 지원합니다: **[http://admin.durcit.site](http://admin.durcit.site)**
- **로그**: 시스템 활동 기록을 확인하고 관리.
- **회원 관리**: 회원의 역할 변경(관리자, 매니저, 일반 회원), 회원 차단 및 차단 해제.
- **게시글 관리**: 게시글 숨기기(논리 삭제) 및 영구 삭제.
- **역할 변경**: 사용자 역할을 필요에 따라 조정 가능.

---

## 🚀 시작하기

### **프로젝트 URL**
- **메인 사이트**: [http://durcit.site](http://durcit.site)
- **어드민 페이지**: [http://admin.durcit.site](http://admin.durcit.site)

### **기술 스택**
- **프론트엔드**: React.js, Tailwind CSS
- **백엔드**: Spring Boot, JPA, WebSocket, Spring Security, JWT 인증, RabbitMQ
- **데이터베이스**: MySQL
- **클라우드 서비스**: AWS S3(파일 업로드), AWS EC2, AWS RDS, AWS Route 53
- **기타**: Nginx

---

### API 명세서

#### 1. Admin 인증 API

| **기능**       | **Method** | **URL**           | **Request Body**                                    | **Response**                                                                                   |
|----------------|------------|-------------------|---------------------------------------------------|-----------------------------------------------------------------------------------------------|
| 로그인          | POST       | /admins/login   | { "username": "string", "password": "string" }   | { "accessToken": "string", "refreshToken": "string" }                                       |

---

#### 2. 통계 API

| **기능**               | **Method** | **URL**                | **Request Body** | **Response**                                                     |
|------------------------|------------|------------------------|------------------|------------------------------------------------------------------|
| 대시보드 통계 조회      | GET        | /statistics          | -                | { "code": "GET_ADMIN_LOG_SUCCESS", "data": { ... } }           |
| 최근 통계 조회          | GET        | /statistics/recent   | -                | { "code": "GET_ADMIN_LOG_SUCCESS", "data": [ ... ] }           |

---

#### 3. 로그 API

| **기능**               | **Method** | **URL**                | **Request Body** | **Response**                                                     |
|------------------------|------------|------------------------|------------------|------------------------------------------------------------------|
| 로그 전체 조회 (페이징) | GET        | /logs                | -                | { "code": "GET_ADMIN_LOG_SUCCESS", "data": { "content": [ ... ] } } |
| 최근 로그 조회          | GET        | /logs/recent         | -                | { "code": "GET_ADMIN_LOG_SUCCESS", "data": [ ... ] }           |

---

#### 4. 역할 관리 API

| **기능**               | **Method** | **URL**                              | **Request Body** | **Response**                               |
|------------------------|------------|--------------------------------------|------------------|------------------------------------------|
| 역할을 ADMIN으로 변경  | PUT        | /role-updates-admin/{memberId}     | -                | { "code": "UPDATE_ROLE_SUCCESS" }       |
| 역할을 MANAGER로 변경  | PUT        | /role-updates-manager/{memberId}   | -                | { "code": "UPDATE_ROLE_SUCCESS" }       |
| 역할을 MEMBER로 변경   | PUT        | /role-updates-member/{memberId}    | -                | { "code": "UPDATE_ROLE_SUCCESS" }       |

---

#### 5. 사용자 관리 API

| **기능**            | **Method** | **URL**                | **Request Body** | **Response**                                                     |
|---------------------|------------|------------------------|------------------|------------------------------------------------------------------|
| 사용자 목록 조회     | GET        | /users               | -                | { "code": "GET_USER_PROFILE_SUCCESS", "data": [ ... ] }        |
| 사용자 차단          | PUT        | /block/{memberId}    | -                | { "code": "UPDATE_USER_BLOCK_STATUS" }                         |
| 사용자 차단 해제     | PUT        | /unblock/{memberId}  | -                | { "code": "UPDATE_USER_BLOCK_STATUS" }                         |
| 사용자 삭제          | DELETE     | /delete/{memberId}   | -                | { "code": "DELETE_USER_PERMANENT_SUCCESS" }                    |

---

#### 6. 게시글 관리 API

| **기능**            | **Method** | **URL**                              | **Request Body** | **Response**                                                     |
|---------------------|------------|--------------------------------------|------------------|------------------------------------------------------------------|
| 게시글 복원          | PUT        | /posts/{postId}/restore            | -                | { "code": "RECOVER_POST_SUCCESS" }                             |
| 게시글 목록 조회     | GET        | /posts                             | -                | { "code": "GET_POST_SUCCESS", "data": [ ... ] }                |
| 숨겨진 게시글 조회   | GET        | /posts/hide                        | -                | { "code": "GET_POST_SUCCESS", "data": [ ... ] }                |
| 게시글 숨기기        | DELETE     | /posts/{postId}/hide               | -                | { "code": "DELETE_POST_SUCCESS" }                              |
| 게시글 영구 삭제     | DELETE     | /posts/{postId}/permanent          | -                | { "code": "DELETE_POST_SUCCESS" }                              |



### Chat API 명세서

#### 공통 정보
- **Base URL**: /api/members

---

#### 1. WebSocket 기반 채팅 API

| **기능**        | **Method** | **URL**           | **Request Body**                                     | **Response**                                    |
|-----------------|------------|-------------------|----------------------------------------------------|------------------------------------------------|
| 채팅 메시지 전송 | WebSocket  | /chat/send      | { "roomId": "string", "senderId": "string", "message": "string" } | -                                              |

---

#### 2. REST 기반 채팅 API

| **기능**              | **Method** | **URL**                            | **Request Body**                                   | **Response**                                                                                   |
|-----------------------|------------|------------------------------------|--------------------------------------------------|-----------------------------------------------------------------------------------------------|
| 채팅방 생성           | POST       | /rooms                           | { "roomName": "string", "creatorId": "string" }| { "code": "CREATE_CHAT_ROOM_SUCCESS", "data": { "roomId": "string", "roomName": "string" } } |
| 사용자의 채팅방 조회  | GET        | /rooms                           | -                                                | { "code": "GET_CHAT_ROOM_SUCCESS", "data": [ { "roomId": "string", "roomName": "string" } ] }|
| 특정 채팅방 메시지 조회 | GET        | /rooms/{roomId}/messages         | -                                                | { "code": "GET_CHAT_SUCCESS", "data": [ { "messageId": "


### Comment API 명세서

#### 공통 정보
- **Base URL**: /api

---

#### 1. Comment API

| **기능**              | **Method** | **URL**                          | **Request Body**                                   | **Response**                                                                                     |
|-----------------------|------------|----------------------------------|--------------------------------------------------|-------------------------------------------------------------------------------------------------|
| 댓글 등록              | POST       | /members/comments             | { "postId": "number", "content": "string" }     | { "code": "CREATE_COMMENT_SUCCESS", "data": { "commentId": "number", "content": "string" } }  |
| 댓글 수정              | PUT        | /members/comments             | { "commentId": "number", "content": "string" } | { "code": "UPDATE_COMMENT_SUCCESS" }                                                         |
| 댓글 삭제              | DELETE     | /members/comments/{commentId} | -                                                | { "code": "DELETE_COMMENT_SUCCESS" }                                                         |
| 사용자의 댓글 조회      | GET        | /members/my-comments/{memberId} | -                                                | { "code": "GET_COMMENT_SUCCESS", "data": [ { "commentId": "number", "content": "string" } ] } |

---

#### 2. Mention API

| **기능**              | **Method** | **URL**             | **Request Body**  | **Response**                                                                                     |
|-----------------------|------------|---------------------|-------------------|-------------------------------------------------------------------------------------------------|
| 멘션 가능한 멤버 조회  | GET        | /members/mentions | query=string    | { "code": "GET_MENTION_POSSIBLE_MEMBERS_SUCCESS", "data": [ { "memberId": "number", "name": "string" } ] } |


### Feed & Post Facade API 명세서

#### 공통 정보
- **Base URL**: /api

---

#### 1. Feed API

| **기능**              | **Method** | **URL**                  | **Request Body** | **Response**                                                                                     |
|-----------------------|------------|--------------------------|------------------|-------------------------------------------------------------------------------------------------|
| 사용자 정보 조회       | GET        | /feeds/users/{userId}  | -                | { "code": "GET_USER_PROFILE_SUCCESS", "data": { "userId": "number", "userName": "string", ... } } |

---

#### 2. Post Facade API

| **기능**              | **Method** | **URL**                          | **Request Body**                                   | **Response**                                                                                     |
|-----------------------|------------|----------------------------------|--------------------------------------------------|-------------------------------------------------------------------------------------------------|
| 게시글 등록            | POST       | /members/posts                | { "title": "string", "content": "string", ... } | { "code": "CREATE_POST_SUCCESS", "data": "number" }                                            |
| 게시글 상세 조회        | POST       | /posts/{postId}               | memberId: "number"                              | { "code": "GET_POST_SUCCESS", "data": { "postId": "number", "title": "string", ... } }         |
| 게시글 수정            | PUT        | /members/posts/{postId}       | { "title": "string", "content": "string", ... } | { "code": "UPDATE_POST_SUCCESS" }                                                             |
| 게시글 삭제            | DELETE     | /members/posts/{postId}       | -                                                | { "code": "DELETE_POST_SUCCESS" }                                                             |


### Follow API 명세서

#### 공통 정보
- **Base URL**: /api/members

---

#### 1. 멤버 팔로우 API

| **기능**            | **Method** | **URL**                     | **Request Body**                         | **Response**                                                                                     |
|---------------------|------------|-----------------------------|------------------------------------------|-------------------------------------------------------------------------------------------------|
| 팔로우 토글          | POST       | /follows/toggle           | { "followeeId": "number" }             | { "code": "TOGGLE_MEMBER_FOLLOW_SUCCESS" }                                                    |
| 특정 멤버 팔로우 여부 | GET        | /follows                  | followeeId: number                     | { "code": "GET_MEMBER_FOLLOWER_SUCCESS", "data": true/false }                                 |
| 팔로워 목록 조회      | GET        | /follows/followers        | followeeId: number                     | { "code": "GET_MEMBER_FOLLOWER_SUCCESS", "data": [ { "memberId": "number", "name": "string" } ] } |
| 팔로잉 목록 조회      | GET        | /follows/followees        | followerId: number                     | { "code": "GET_MEMBER_FOLLOWEE_SUCCESS", "data": [ { "memberId": "number", "name": "string" } ] } |

---

#### 2. 태그 팔로우 API

| **기능**               | **Method** | **URL**                     | **Request Body**                             | **Response**                                                                                     |
|------------------------|------------|-----------------------------|----------------------------------------------|-------------------------------------------------------------------------------------------------|
| 태그 팔로우 토글        | POST       | /tag-follow/{memberId}/post | { "tagId": "number" }                      | { "code": "CREATE_TAG_FOLLOW_SUCCESS", "data": { "tagId": "number", "tagName": "string", ... } } |
| 멤버의 태그 팔로우 조회 | GET        | /tag-follow/{memberId}    | -                                            | { "code": "GET_TAG_FOLLOW_SUCCESS", "data": [ { "tagId": "number", "tagName": "string", ... } ] } |


### Post API 명세서

#### 공통 정보
- **Base URL**: /api

---

#### 1. Emoji API

| **기능**              | **Method** | **URL**         | **Request Body**                         | **Response**                                                                                     |
|-----------------------|------------|-----------------|------------------------------------------|-------------------------------------------------------------------------------------------------|
| 이모지 추가/토글      | WebSocket  | /addEmoji     | { "postId": "number", "emojiType": "string" } | { "code": "EMOJI_TOGGLE_SUCCESS", "data": { "postId": "number", "emojiType": "string" } }     |

---

#### 2. Like API

| **기능**               | **Method** | **URL**                      | **Request Body** | **Response**                                                                                     |
|------------------------|------------|------------------------------|------------------|-------------------------------------------------------------------------------------------------|
| 게시글 좋아요 개수 조회 | GET        | /members/likes/{postId}/count | -                | { "code": "GET_POST_LIKES_SUCCESS", "data": "number" }                                         |
| 게시글 좋아요 토글      | POST       | /members/likes/{postId}/toggle | -                | { "code": "TOGGLE_LIKE_SUCCESS", "data": true/false }                                         |
| 사용자가 좋아요한 게시글 조회 | GET   | /members/likes/{memberId} | -                | { "code": "GET_POST_LIKES_SUCCESS", "data": [ { "postId": "number", "title": "string", ... } ] } |

---

#### 3. Post API

| **기능**                      | **Method** | **URL**                               | **Request Body**                                   | **Response**                                                                                     |
|-------------------------------|------------|---------------------------------------|--------------------------------------------------|-------------------------------------------------------------------------------------------------|
| 전체 게시글 조회              | GET        | /                                   | -                                                | { "code": "GET_POST_SUCCESS", "data": [ { "postId": "number", "title": "string", ... } ] }     |
| 게시글 페이지네이션 조회       | GET        | /posts/pages                        | { "page": "number", "size": "number", "category": "string" } | { "code": "GET_POST_SUCCESS", "data": { "content": [ ... ], "pageable": { ... } } }            |
| 팔로우한 태그로 게시글 조회   | GET        | /posts/pages/tags                   | { "page": "number", "size": "number", "memberId": "number", "category": "string" } | { "code": "GET_POST_SUCCESS", "data": { "content": [ ... ], "pageable": { ... } } }            |
| 태그 검색으로 게시글 조회      | GET        | /posts/search/tags                  | tag=string&page=number&size=number              | { "code": "GET_POST_SUCCESS", "data": { "content": [ ... ], "pageable": { ... } } }            |
| 내 게시글 조회                | GET        | /members/my-posts                   | -                                                | { "code": "GET_POST_SUCCESS", "data": [ { "postId": "number", "title": "string", ... } ] }     |
| 특정 사용자의 피드 게시글 조회 | GET        | /feed/{userId}                      | -                                                | { "code": "GET_POST_SUCCESS", "data": [ { "postId": "number", "title": "string", ... } ] }     |

---

#### 4. (미완성/주석 처리된) 게시글 작성/수정/삭제 API

| **기능**                      | **Method** | **URL**                       | **Request Body**                                   | **Response**                                                                                     |
|-------------------------------|------------|-------------------------------|--------------------------------------------------|-------------------------------------------------------------------------------------------------|
| 게시글 작성                   | POST       | /members                   | { "title": "string", "content": "string", ... } | { "code": "CREATE_POST_SUCCESS", "data": { "postId": "number", "title": "string", ... } }      |
| 게시글 수정                   | PUT        | /members/{postId}          | { "title": "string", "content": "string", ... } | { "code": "UPDATE_POST_SUCCESS" }                                                             |
| 게시글 삭제                   | DELETE     | /members/{postId}          | -                                                | { "code": "DELETE_POST_SUCCESS" }                                                             |
| 게시글 상세 조회 및 조회수 증가 | GET        | /{postId}                  | -                                                | { "code": "GET_POST_SUCCESS", "data": { "postId": "number", "title": "string", ... } }         |


### Push Notification API 명세서

#### 공통 정보
- **Base URL**: /api/members/pushs

---

#### 1. Push API

| **기능**                     | **Method** | **URL**                     | **Request Body** | **Response**                                                                                     |
|------------------------------|------------|-----------------------------|------------------|-------------------------------------------------------------------------------------------------|
| 특정 멤버의 푸시 알림 조회    | GET        | /                         | memberId: string | { "code": "GET_PUSHS_SUCCESS", "data": [ { "pushId": "number", "message": "string", "status": "string" } ] } |
| 특정 푸시 알림 확인 처리      | PUT        | /{pushId}/confirm         | -                | { "code": "UPDATE_PUSH_SUCCESS" }                                                             |

---

### Search API 명세서

#### 공통 정보
- **Base URL**: /api/search

---

#### 1. Search API

| **기능**                 | **Method** | **URL**      | **Request Body**                              | **Response**                                                                                     |
|--------------------------|------------|--------------|-----------------------------------------------|-------------------------------------------------------------------------------------------------|
| 검색 결과 조회           | GET        | /          | query: string                               | { "code": "SEARCH_SUCCESS", "data": [ { "id": "number", "type": "string", "content": "string" } ] } |


---

### Security API 명세서

---

#### 1. 인증 (Auth) API

| **기능**                     | **Method** | **URL**         | **Request Body**                                 | **Response**                                                                                     |
|------------------------------|------------|-----------------|------------------------------------------------|-------------------------------------------------------------------------------------------------|
| 로그인                       | POST       | /auth/login   | { "username": "string", "password": "string" } | { "accessToken": "string", "refreshToken": "string" }                                         |
| 로그아웃                     | POST       | /auth/logout  | { "refreshToken": "string" }                  | {}                                                                                           |
| 회원가입                     | POST       | /auth/register | multipart/form-data                          | { "code": "CREATED_USER" }                                                                   |
| 이메일 인증                  | POST       | /auth/verify  | { "token": "string" }                         | { "code": "VERIFY_EMAIL_SUCCESS" }                                                           |

---

#### 2. 비밀번호 재설정 API

| **기능**                     | **Method** | **URL**                     | **Request Body**                 | **Response**                                                                                     |
|------------------------------|------------|-----------------------------|----------------------------------|-------------------------------------------------------------------------------------------------|
| 비밀번호 재설정 요청          | POST       | /api/members/passwords/request | -                              | { "code": "SEND_EMAIL_SUCCESS" }                                                              |
| 인증 코드 확인               | POST       | /api/members/passwords/verify | { "code": "string" }          | { "code": "VERIFY_EMAIL_SUCCESS" } or { "code": "VERIFY_EMAIL_FAIL" }                      |
| 비밀번호 변경                | POST       | /api/members/passwords/change  | { "newPassword": "string" }   | { "code": "PASSWORD_RESET_SUCCESS" }                                                         |

---

#### 3. 프로필 관리 API

| **기능**                     | **Method** | **URL**                                | **Request Body**                                 | **Response**                                                                                     |
|------------------------------|------------|----------------------------------------|------------------------------------------------|-------------------------------------------------------------------------------------------------|
| 프로필 조회                  | GET        | /api/members/profile                 | -                                              | { "code": "GET_USER_PROFILE_SUCCESS", "data": { "nickname": "string", "bio": "string", ... } }|
| 닉네임 변경                  | PUT        | /api/members/profile/nickname-updates| { "nickname": "string" }                     | { "code": "UPDATE_NICKNAME_SUCCESS", "data": { "nickname": "string", "bio": "string", ... } } |
| 닉네임 중복 확인             | GET        | /api/members/profile/nickname-checks | nickname=string                              | { "code": "CHECK_DUPLICATE_NICKNAME_SUCCESS", "data": true/false }                            |
| 자기소개 변경                | PUT        | /api/members/profile/bio-updates     | { "bio": "string" }                          | { "code": "UPDATE_BIO_SUCCESS", "data": { "nickname": "string", "bio": "string", ... } }      |
| 자기소개 조회                | GET        | /api/members/profile/bio             | -                                              | { "code": "GET_BIO_SUCCESS", "data": "string" }                                               |

---


### Upload API 명세서

#### 공통 정보
- **Base URL**: /api/members

---

#### 1. 프로필 이미지 업로드 API

| **기능**               | **Method** | **URL**                     | **Request Body**                             | **Response**                                                                                     |
|------------------------|------------|-----------------------------|----------------------------------------------|-------------------------------------------------------------------------------------------------|
| 프로필 이미지 업데이트 | PUT        | /profile                 | MultipartFile                              | { "code": "UPDATE_PROFILE_IMAGE_SUCCESS" }                                                    |

---

#### 2. 파일 업로드 API

| **기능**               | **Method** | **URL**                     | **Request Body**                             | **Response**                                                                                     |
|------------------------|------------|-----------------------------|----------------------------------------------|-------------------------------------------------------------------------------------------------|
| 파일 업로드            | POST       | /upload/files            | { "postId": "number", "files": [MultipartFile, ...] } | { "code": "UPLOAD_FILES_SUCCESS" }                                                            |
| 파일 업데이트          | PUT        | /upload/update           | { "postId": "number", "imageIdsToDelete": [number, ...], "newFiles": [MultipartFile, ...] } | { "code": "UPDATE_FILES_SUCCESS" }                                                            |

---




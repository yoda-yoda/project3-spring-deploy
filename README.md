# Admin API 명세서

## 공통 정보
- **Base URL**: `/api/admins`

---

## 1. Admin 인증 API

| **기능**       | **Method** | **URL**           | **Request Body**                                    | **Response**                                                                                   |
|----------------|------------|-------------------|---------------------------------------------------|-----------------------------------------------------------------------------------------------|
| 로그인          | POST       | `/admins/login`   | `{ "username": "string", "password": "string" }`   | `{ "accessToken": "string", "refreshToken": "string" }`                                       |

---

## 2. 통계 API

| **기능**               | **Method** | **URL**                | **Request Body** | **Response**                                                     |
|------------------------|------------|------------------------|------------------|------------------------------------------------------------------|
| 대시보드 통계 조회      | GET        | `/statistics`          | -                | `{ "code": "GET_ADMIN_LOG_SUCCESS", "data": { ... } }`           |
| 최근 통계 조회          | GET        | `/statistics/recent`   | -                | `{ "code": "GET_ADMIN_LOG_SUCCESS", "data": [ ... ] }`           |

---

## 3. 로그 API

| **기능**               | **Method** | **URL**                | **Request Body** | **Response**                                                     |
|------------------------|------------|------------------------|------------------|------------------------------------------------------------------|
| 로그 전체 조회 (페이징) | GET        | `/logs`                | -                | `{ "code": "GET_ADMIN_LOG_SUCCESS", "data": { "content": [ ... ] } }` |
| 최근 로그 조회          | GET        | `/logs/recent`         | -                | `{ "code": "GET_ADMIN_LOG_SUCCESS", "data": [ ... ] }`           |

---

## 4. 역할 관리 API

| **기능**               | **Method** | **URL**                              | **Request Body** | **Response**                               |
|------------------------|------------|--------------------------------------|------------------|------------------------------------------|
| 역할을 ADMIN으로 변경  | PUT        | `/role-updates-admin/{memberId}`     | -                | `{ "code": "UPDATE_ROLE_SUCCESS" }`       |
| 역할을 MANAGER로 변경  | PUT        | `/role-updates-manager/{memberId}`   | -                | `{ "code": "UPDATE_ROLE_SUCCESS" }`       |
| 역할을 MEMBER로 변경   | PUT        | `/role-updates-member/{memberId}`    | -                | `{ "code": "UPDATE_ROLE_SUCCESS" }`       |

---

## 5. 사용자 관리 API

| **기능**            | **Method** | **URL**                | **Request Body** | **Response**                                                     |
|---------------------|------------|------------------------|------------------|------------------------------------------------------------------|
| 사용자 목록 조회     | GET        | `/users`               | -                | `{ "code": "GET_USER_PROFILE_SUCCESS", "data": [ ... ] }`        |
| 사용자 차단          | PUT        | `/block/{memberId}`    | -                | `{ "code": "UPDATE_USER_BLOCK_STATUS" }`                         |
| 사용자 차단 해제     | PUT        | `/unblock/{memberId}`  | -                | `{ "code": "UPDATE_USER_BLOCK_STATUS" }`                         |
| 사용자 삭제          | DELETE     | `/delete/{memberId}`   | -                | `{ "code": "DELETE_USER_PERMANENT_SUCCESS" }`                    |

---

## 6. 게시글 관리 API

| **기능**            | **Method** | **URL**                              | **Request Body** | **Response**                                                     |
|---------------------|------------|--------------------------------------|------------------|------------------------------------------------------------------|
| 게시글 복원          | PUT        | `/posts/{postId}/restore`            | -                | `{ "code": "RECOVER_POST_SUCCESS" }`                             |
| 게시글 목록 조회     | GET        | `/posts`                             | -                | `{ "code": "GET_POST_SUCCESS", "data": [ ... ] }`                |
| 숨겨진 게시글 조회   | GET        | `/posts/hide`                        | -                | `{ "code": "GET_POST_SUCCESS", "data": [ ... ] }`                |
| 게시글 숨기기        | DELETE     | `/posts/{postId}/hide`               | -                | `{ "code": "DELETE_POST_SUCCESS" }`                              |
| 게시글 영구 삭제     | DELETE     | `/posts/{postId}/permanent`          | -                | `{ "code": "DELETE_POST_SUCCESS" }`                              |



# Chat API 명세서

## 공통 정보
- **Base URL**: `/api/members`

---

## 1. WebSocket 기반 채팅 API

| **기능**        | **Method** | **URL**           | **Request Body**                                     | **Response**                                    |
|-----------------|------------|-------------------|----------------------------------------------------|------------------------------------------------|
| 채팅 메시지 전송 | WebSocket  | `/chat/send`      | `{ "roomId": "string", "senderId": "string", "message": "string" }` | -                                              |

---

## 2. REST 기반 채팅 API

| **기능**              | **Method** | **URL**                            | **Request Body**                                   | **Response**                                                                                   |
|-----------------------|------------|------------------------------------|--------------------------------------------------|-----------------------------------------------------------------------------------------------|
| 채팅방 생성           | POST       | `/rooms`                           | `{ "roomName": "string", "creatorId": "string" }`| `{ "code": "CREATE_CHAT_ROOM_SUCCESS", "data": { "roomId": "string", "roomName": "string" } }` |
| 사용자의 채팅방 조회  | GET        | `/rooms`                           | -                                                | `{ "code": "GET_CHAT_ROOM_SUCCESS", "data": [ { "roomId": "string", "roomName": "string" } ] }`|
| 특정 채팅방 메시지 조회 | GET        | `/rooms/{roomId}/messages`         | -                                                | `{ "code": "GET_CHAT_SUCCESS", "data": [ { "messageId": "


# Comment API 명세서

## 공통 정보
- **Base URL**: `/api`

---

## 1. Comment API

| **기능**              | **Method** | **URL**                          | **Request Body**                                   | **Response**                                                                                     |
|-----------------------|------------|----------------------------------|--------------------------------------------------|-------------------------------------------------------------------------------------------------|
| 댓글 등록              | POST       | `/members/comments`             | `{ "postId": "number", "content": "string" }`     | `{ "code": "CREATE_COMMENT_SUCCESS", "data": { "commentId": "number", "content": "string" } }`  |
| 댓글 수정              | PUT        | `/members/comments`             | `{ "commentId": "number", "content": "string" }` | `{ "code": "UPDATE_COMMENT_SUCCESS" }`                                                         |
| 댓글 삭제              | DELETE     | `/members/comments/{commentId}` | -                                                | `{ "code": "DELETE_COMMENT_SUCCESS" }`                                                         |
| 사용자의 댓글 조회      | GET        | `/members/my-comments/{memberId}` | -                                                | `{ "code": "GET_COMMENT_SUCCESS", "data": [ { "commentId": "number", "content": "string" } ] }` |

---

## 2. Mention API

| **기능**              | **Method** | **URL**             | **Request Body**  | **Response**                                                                                     |
|-----------------------|------------|---------------------|-------------------|-------------------------------------------------------------------------------------------------|
| 멘션 가능한 멤버 조회  | GET        | `/members/mentions` | `query=string`    | `{ "code": "GET_MENTION_POSSIBLE_MEMBERS_SUCCESS", "data": [ { "memberId": "number", "name": "string" } ] }` |


# Feed & Post Facade API 명세서

## 공통 정보
- **Base URL**: `/api`

---

## 1. Feed API

| **기능**              | **Method** | **URL**                  | **Request Body** | **Response**                                                                                     |
|-----------------------|------------|--------------------------|------------------|-------------------------------------------------------------------------------------------------|
| 사용자 정보 조회       | GET        | `/feeds/users/{userId}`  | -                | `{ "code": "GET_USER_PROFILE_SUCCESS", "data": { "userId": "number", "userName": "string", ... } }` |

---

## 2. Post Facade API

| **기능**              | **Method** | **URL**                          | **Request Body**                                   | **Response**                                                                                     |
|-----------------------|------------|----------------------------------|--------------------------------------------------|-------------------------------------------------------------------------------------------------|
| 게시글 등록            | POST       | `/members/posts`                | `{ "title": "string", "content": "string", ... }` | `{ "code": "CREATE_POST_SUCCESS", "data": "number" }`                                            |
| 게시글 상세 조회        | POST       | `/posts/{postId}`               | `memberId: "number"`                              | `{ "code": "GET_POST_SUCCESS", "data": { "postId": "number", "title": "string", ... } }`         |
| 게시글 수정            | PUT        | `/members/posts/{postId}`       | `{ "title": "string", "content": "string", ... }` | `{ "code": "UPDATE_POST_SUCCESS" }`                                                             |
| 게시글 삭제            | DELETE     | `/members/posts/{postId}`       | -                                                | `{ "code": "DELETE_POST_SUCCESS" }`                                                             |


# Follow API 명세서

## 공통 정보
- **Base URL**: `/api/members`

---

## 1. 멤버 팔로우 API

| **기능**            | **Method** | **URL**                     | **Request Body**                         | **Response**                                                                                     |
|---------------------|------------|-----------------------------|------------------------------------------|-------------------------------------------------------------------------------------------------|
| 팔로우 토글          | POST       | `/follows/toggle`           | `{ "followeeId": "number" }`             | `{ "code": "TOGGLE_MEMBER_FOLLOW_SUCCESS" }`                                                    |
| 특정 멤버 팔로우 여부 | GET        | `/follows`                  | `followeeId: number`                     | `{ "code": "GET_MEMBER_FOLLOWER_SUCCESS", "data": true/false }`                                 |
| 팔로워 목록 조회      | GET        | `/follows/followers`        | `followeeId: number`                     | `{ "code": "GET_MEMBER_FOLLOWER_SUCCESS", "data": [ { "memberId": "number", "name": "string" } ] }` |
| 팔로잉 목록 조회      | GET        | `/follows/followees`        | `followerId: number`                     | `{ "code": "GET_MEMBER_FOLLOWEE_SUCCESS", "data": [ { "memberId": "number", "name": "string" } ] }` |

---

## 2. 태그 팔로우 API

| **기능**               | **Method** | **URL**                     | **Request Body**                             | **Response**                                                                                     |
|------------------------|------------|-----------------------------|----------------------------------------------|-------------------------------------------------------------------------------------------------|
| 태그 팔로우 토글        | POST       | `/tag-follow/{memberId}/post` | `{ "tagId": "number" }`                      | `{ "code": "CREATE_TAG_FOLLOW_SUCCESS", "data": { "tagId": "number", "tagName": "string", ... } }` |
| 멤버의 태그 팔로우 조회 | GET        | `/tag-follow/{memberId}`    | -                                            | `{ "code": "GET_TAG_FOLLOW_SUCCESS", "data": [ { "tagId": "number", "tagName": "string", ... } ] }` |


# Post API 명세서

## 공통 정보
- **Base URL**: `/api`

---

## 1. Emoji API

| **기능**              | **Method** | **URL**         | **Request Body**                         | **Response**                                                                                     |
|-----------------------|------------|-----------------|------------------------------------------|-------------------------------------------------------------------------------------------------|
| 이모지 추가/토글      | WebSocket  | `/addEmoji`     | `{ "postId": "number", "emojiType": "string" }` | `{ "code": "EMOJI_TOGGLE_SUCCESS", "data": { "postId": "number", "emojiType": "string" } }`     |

---

## 2. Like API

| **기능**               | **Method** | **URL**                      | **Request Body** | **Response**                                                                                     |
|------------------------|------------|------------------------------|------------------|-------------------------------------------------------------------------------------------------|
| 게시글 좋아요 개수 조회 | GET        | `/members/likes/{postId}/count` | -                | `{ "code": "GET_POST_LIKES_SUCCESS", "data": "number" }`                                         |
| 게시글 좋아요 토글      | POST       | `/members/likes/{postId}/toggle` | -                | `{ "code": "TOGGLE_LIKE_SUCCESS", "data": true/false }`                                         |
| 사용자가 좋아요한 게시글 조회 | GET   | `/members/likes/{memberId}` | -                | `{ "code": "GET_POST_LIKES_SUCCESS", "data": [ { "postId": "number", "title": "string", ... } ] }` |

---

## 3. Post API

| **기능**                      | **Method** | **URL**                               | **Request Body**                                   | **Response**                                                                                     |
|-------------------------------|------------|---------------------------------------|--------------------------------------------------|-------------------------------------------------------------------------------------------------|
| 전체 게시글 조회              | GET        | `/`                                   | -                                                | `{ "code": "GET_POST_SUCCESS", "data": [ { "postId": "number", "title": "string", ... } ] }`     |
| 게시글 페이지네이션 조회       | GET        | `/posts/pages`                        | `{ "page": "number", "size": "number", "category": "string" }` | `{ "code": "GET_POST_SUCCESS", "data": { "content": [ ... ], "pageable": { ... } } }`            |
| 팔로우한 태그로 게시글 조회   | GET        | `/posts/pages/tags`                   | `{ "page": "number", "size": "number", "memberId": "number", "category": "string" }` | `{ "code": "GET_POST_SUCCESS", "data": { "content": [ ... ], "pageable": { ... } } }`            |
| 태그 검색으로 게시글 조회      | GET        | `/posts/search/tags`                  | `tag=string&page=number&size=number`              | `{ "code": "GET_POST_SUCCESS", "data": { "content": [ ... ], "pageable": { ... } } }`            |
| 내 게시글 조회                | GET        | `/members/my-posts`                   | -                                                | `{ "code": "GET_POST_SUCCESS", "data": [ { "postId": "number", "title": "string", ... } ] }`     |
| 특정 사용자의 피드 게시글 조회 | GET        | `/feed/{userId}`                      | -                                                | `{ "code": "GET_POST_SUCCESS", "data": [ { "postId": "number", "title": "string", ... } ] }`     |

---

## 4. (미완성/주석 처리된) 게시글 작성/수정/삭제 API

| **기능**                      | **Method** | **URL**                       | **Request Body**                                   | **Response**                                                                                     |
|-------------------------------|------------|-------------------------------|--------------------------------------------------|-------------------------------------------------------------------------------------------------|
| 게시글 작성                   | POST       | `/members`                   | `{ "title": "string", "content": "string", ... }` | `{ "code": "CREATE_POST_SUCCESS", "data": { "postId": "number", "title": "string", ... } }`      |
| 게시글 수정                   | PUT        | `/members/{postId}`          | `{ "title": "string", "content": "string", ... }` | `{ "code": "UPDATE_POST_SUCCESS" }`                                                             |
| 게시글 삭제                   | DELETE     | `/members/{postId}`          | -                                                | `{ "code": "DELETE_POST_SUCCESS" }`                                                             |
| 게시글 상세 조회 및 조회수 증가 | GET        | `/{postId}`                  | -                                                | `{ "code": "GET_POST_SUCCESS", "data": { "postId": "number", "title": "string", ... } }`         |


# Push Notification API 명세서

## 공통 정보
- **Base URL**: `/api/members/pushs`

---

## 1. Push API

| **기능**                     | **Method** | **URL**                     | **Request Body** | **Response**                                                                                     |
|------------------------------|------------|-----------------------------|------------------|-------------------------------------------------------------------------------------------------|
| 특정 멤버의 푸시 알림 조회    | GET        | `/`                         | `memberId: string` | `{ "code": "GET_PUSHS_SUCCESS", "data": [ { "pushId": "number", "message": "string", "status": "string" } ] }` |
| 특정 푸시 알림 확인 처리      | PUT        | `/{pushId}/confirm`         | -                | `{ "code": "UPDATE_PUSH_SUCCESS" }`                                                             |

---

### 설명
1. **특정 멤버의 푸시 알림 조회**
    - 멤버 ID를 쿼리 파라미터로 전달하여 해당 멤버가 받은 모든 푸시 알림을 조회합니다.
    - 알림 데이터에는 `pushId`, `message`, `status` 등의 정보가 포함됩니다.

2. **특정 푸시 알림 확인 처리**
    - 푸시 알림의 ID(`pushId`)를 경로 파라미터로 전달하여 해당 푸시 알림을 확인 상태로 변경합니다.

---

### 주요 필드
- **PushResponse**
    - `pushId`: 알림 ID
    - `message`: 알림 메시지
    - `status`: 알림 상태 (예: "unread", "read")


# Search API 명세서

## 공통 정보
- **Base URL**: `/api/search`

---

## 1. Search API

| **기능**                 | **Method** | **URL**      | **Request Body**                              | **Response**                                                                                     |
|--------------------------|------------|--------------|-----------------------------------------------|-------------------------------------------------------------------------------------------------|
| 검색 결과 조회           | GET        | `/`          | `query: string`                               | `{ "code": "SEARCH_SUCCESS", "data": [ { "id": "number", "type": "string", "content": "string" } ] }` |

---

### 설명

1. **게시글 검색**
    - 검색어를 제목, 내용, 태그, 닉네임에서 조회합니다.
    - 삭제된 게시글(`delete = true`)은 조회되지 않습니다.
    - 반환값은 검색된 게시글 리스트를 `PostCardResponse` 형식으로 포함합니다.

2. **검색 결과 조회**
    - 검색어를 입력하여 다양한 종류의 결과(예: 게시글, 태그, 사용자)를 조회합니다.
    - 반환값은 검색된 결과 리스트를 `SearchResultResponse` 형식으로 포함합니다.

---

### 주요 필드

- **SearchRequest**
    - `query`: 검색어

- **PostCardResponse**
    - `postId`: 게시글 ID
    - `title`: 게시글 제목
    - `content`: 게시글 내용
    - 기타 게시글 관련 정보

- **SearchResultResponse**
    - `id`: 검색 결과 ID
    - `type`: 검색 결과 유형 (예: "post", "tag", "user")
    - `content`: 검색 결과 내용


# Security API 명세서

---

## 1. 인증 (Auth) API

| **기능**                     | **Method** | **URL**         | **Request Body**                                 | **Response**                                                                                     |
|------------------------------|------------|-----------------|------------------------------------------------|-------------------------------------------------------------------------------------------------|
| 로그인                       | POST       | `/auth/login`   | `{ "username": "string", "password": "string" }` | `{ "accessToken": "string", "refreshToken": "string" }`                                         |
| 로그아웃                     | POST       | `/auth/logout`  | `{ "refreshToken": "string" }`                  | `{}`                                                                                           |
| 회원가입                     | POST       | `/auth/register` | `multipart/form-data`                          | `{ "code": "CREATED_USER" }`                                                                   |
| 이메일 인증                  | POST       | `/auth/verify`  | `{ "token": "string" }`                         | `{ "code": "VERIFY_EMAIL_SUCCESS" }`                                                           |

---

## 2. 비밀번호 재설정 API

| **기능**                     | **Method** | **URL**                     | **Request Body**                 | **Response**                                                                                     |
|------------------------------|------------|-----------------------------|----------------------------------|-------------------------------------------------------------------------------------------------|
| 비밀번호 재설정 요청          | POST       | `/api/members/passwords/request` | -                              | `{ "code": "SEND_EMAIL_SUCCESS" }`                                                              |
| 인증 코드 확인               | POST       | `/api/members/passwords/verify` | `{ "code": "string" }`          | `{ "code": "VERIFY_EMAIL_SUCCESS" }` or `{ "code": "VERIFY_EMAIL_FAIL" }`                      |
| 비밀번호 변경                | POST       | `/api/members/passwords/change`  | `{ "newPassword": "string" }`   | `{ "code": "PASSWORD_RESET_SUCCESS" }`                                                         |

---

## 3. 프로필 관리 API

| **기능**                     | **Method** | **URL**                                | **Request Body**                                 | **Response**                                                                                     |
|------------------------------|------------|----------------------------------------|------------------------------------------------|-------------------------------------------------------------------------------------------------|
| 프로필 조회                  | GET        | `/api/members/profile`                 | -                                              | `{ "code": "GET_USER_PROFILE_SUCCESS", "data": { "nickname": "string", "bio": "string", ... } }`|
| 닉네임 변경                  | PUT        | `/api/members/profile/nickname-updates`| `{ "nickname": "string" }`                     | `{ "code": "UPDATE_NICKNAME_SUCCESS", "data": { "nickname": "string", "bio": "string", ... } }` |
| 닉네임 중복 확인             | GET        | `/api/members/profile/nickname-checks` | `nickname=string`                              | `{ "code": "CHECK_DUPLICATE_NICKNAME_SUCCESS", "data": true/false }`                            |
| 자기소개 변경                | PUT        | `/api/members/profile/bio-updates`     | `{ "bio": "string" }`                          | `{ "code": "UPDATE_BIO_SUCCESS", "data": { "nickname": "string", "bio": "string", ... } }`      |
| 자기소개 조회                | GET        | `/api/members/profile/bio`             | -                                              | `{ "code": "GET_BIO_SUCCESS", "data": "string" }`                                               |

---


# Upload API 명세서

## 공통 정보
- **Base URL**: `/api/members`

---

## 1. 프로필 이미지 업로드 API

| **기능**               | **Method** | **URL**                     | **Request Body**                             | **Response**                                                                                     |
|------------------------|------------|-----------------------------|----------------------------------------------|-------------------------------------------------------------------------------------------------|
| 프로필 이미지 업데이트 | PUT        | `/profile`                 | `MultipartFile`                              | `{ "code": "UPDATE_PROFILE_IMAGE_SUCCESS" }`                                                    |

---

## 2. 파일 업로드 API

| **기능**               | **Method** | **URL**                     | **Request Body**                             | **Response**                                                                                     |
|------------------------|------------|-----------------------------|----------------------------------------------|-------------------------------------------------------------------------------------------------|
| 파일 업로드            | POST       | `/upload/files`            | `{ "postId": "number", "files": [MultipartFile, ...] }` | `{ "code": "UPLOAD_FILES_SUCCESS" }`                                                            |
| 파일 업데이트          | PUT        | `/upload/update`           | `{ "postId": "number", "imageIdsToDelete": [number, ...], "newFiles": [MultipartFile, ...] }` | `{ "code": "UPDATE_FILES_SUCCESS" }`                                                            |

---

### 주요 설명

1. **프로필 이미지 업데이트**
    - 현재 로그인한 사용자의 프로필 이미지를 업데이트합니다.
    - `SecurityUtil`을 통해 현재 사용자의 ID를 가져와 작업을 수행합니다.

2. **파일 업로드**
    - 게시글과 연결된 파일을 업로드합니다.
    - 게시글 ID(`postId`)와 파일 리스트(`files`)를 요청 파라미터로 전달합니다.

3. **파일 업데이트**
    - 기존 파일을 삭제하거나 새로운 파일을 추가합니다.
    - 삭제할 파일의 ID 리스트(`imageIdsToDelete`)와 추가할 파일 리스트(`newFiles`)를 요청 파라미터로 전달합니다.

---
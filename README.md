### 공통 조건

1. 일정 작성, 수정, 조회 시 반환 받은 일정 정보에 `비밀번호`는 제외 되어있습니다.
2. 일정 수정, 삭제 시 선택한 일정의 `비밀번호`와 요청할 때 함께 보낸 `비밀번호`가 일치할 경우에만 가능합니다.

### 1단계

기능: 일정 작성

| 일정 |
| --- |
| 할일 제목 |
| 할일 내용 |
| 담당자 |
| 비밀번호 |
| 작성일 |

### 조건

1. `할일 제목`, `할일 내용`, `담당자`, `비밀번호`, `작성일`을 저장할 수 있습니다.
    1. 저장된 일정 정보를 반환 받아 확인할 수 있습니다.


### 2단계

기능: 선택한 일정 조회

### 조건

1. 선택한 일정의 정보를 조회할 수 있습니다.

### 3단계

기능: 일정 목록 조회

### 조건

1. 등록된 일정 전체를 조회할 수 있습니다.
2. 조회된 일정 목록은 `작성일` 기준 내림차순으로 정렬 되어있습니다.

### 4단계

기능: 선택한 일정 수정

### 조건

1. 선택한 일정의 `할일 제목`, `할일 내용`, `담당자`을 수정할 수 있습니다.
    1. 서버에 일정 수정을 요청할 때 `비밀번호`를 함께 전달합니다.
2. 수정된 일정의 정보를 반환 받아 확인할 수 있습니다.

### 5단계

기능: 선택한 일정 삭제

### 조건

1. 선택한 일정을 삭제할 수 있습니다.
    1. 서버에 일정 삭제를 요청할 때 `비밀번호`를 함께 전달합니다.
# 🧩 Spring Boot + MyBatis 회원 관리 시스템

이 프로젝트는 **Spring Boot + MyBatis + Oracle** 환경에서  
회원 가입, 조회, 수정, 삭제를 구현한 **회원 관리 실습 프로젝트**입니다.

단순 CRUD 구현을 넘어서,  
**트랜잭션 처리, 권한 테이블 조인, Soft Delete, 동적 SQL** 등  
실무에서 반드시 필요한 패턴들을 학습 목적으로 적용했습니다.

---

## 📌 기술 스택

- Java 17  
- Spring Boot 5.x  
- MyBatis  
- Oracle Database  
- JSP + Spring Form Tag  
- Maven  

---

## 📂 주요 기능

### ✅ 회원 가입
- 회원 정보 등록
- Oracle SEQUENCE를 이용한 PK 생성
- 기본 권한(`ROLE_USER`) 자동 부여
- 회원 / 권한 등록을 트랜잭션으로 처리

---

### ✅ 회원 조회 (권한 포함)
- `MYBATISMEMBER` ↔ `MYBATISMEMBERAUTH` INNER JOIN
- 1:N 관계를 `resultMap + collection`으로 매핑
- 탈퇴 회원 제외 (`ENABLED = '1'`)

```sql
SELECT m.no, m.id, m.name, a.auth
FROM MYBATISMEMBER m
INNER JOIN MYBATISMEMBERAUTH a
ON m.no = a.no
WHERE m.no = #{no}
AND m.enabled = '1'

✅ 회원 목록

- Soft Delete 적용
- 활성 회원만 조회

SELECT *
FROM MYBATISMEMBER
WHERE enabled = '1'
ORDER BY regdate DESC

✅ 회원 수정 (비밀번호 선택적 변경)

비밀번호는 변경을 원하는 경우에만 수정되도록
MyBatis 동적 SQL을 사용했습니다.

<update id="update">
    UPDATE MYBATISMEMBER
    <set>
        id = #{id}
        <if test="pw != null and pw != ''">
            , pw = #{pw}
        </if>
    </set>
    WHERE no = #{no}
      AND enabled = '1'
</update>

✅ 회원 삭제 (Soft Delete)

회원 데이터는 실제 삭제하지 않고
ENABLED 컬럼을 활용한 논리 삭제 방식으로 처리했습니다.

UPDATE MYBATISMEMBER
SET enabled = '0'
WHERE no = #{no}

🧠 설계 포인트
🔹 Soft Delete 사용 이유

- 실무에서 회원 데이터 물리 삭제는 거의 사용되지 않음

- 데이터 무결성 유지

- 복구 및 이력 관리 가능

🔹 비밀번호 처리 방식

- <form:password>는 보안상 값 재바인딩 불가

- 수정 시 비밀번호는 항상 빈 값으로 표시

- 변경 의도가 있을 때만 입력받는 구조

🔹 권한 설계

- 회원 : 권한 = 1 : N 구조

- 다중 권한 확장 가능

- authList 컬렉션으로 매핑

🗂️ 테이블 구조

MYBATISMEMBER

CREATE TABLE MYBATISMEMBER (
    NO NUMBER PRIMARY KEY,
    ID VARCHAR2(50) NOT NULL,
    PW VARCHAR2(50) NOT NULL,
    NAME VARCHAR2(100) NOT NULL,
    REGDATE DATE DEFAULT SYSDATE,
    MODDATE DATE DEFAULT SYSDATE,
    ENABLED CHAR(1) DEFAULT '1'
);

MYBATISMEMBERAUTH

CREATE TABLE MYBATISMEMBERAUTH (
    NO NUMBER,
    AUTH VARCHAR2(50)
);

🎯 정리

이 프로젝트는 다음을 목표로 했습니다.

MyBatis ResultMap & JOIN 이해

트랜잭션 처리 경험

Soft Delete 실무 패턴 적용

동적 SQL을 통한 안전한 UPDATE

“동작하는 코드”가 아닌 의도 있는 코드 작성



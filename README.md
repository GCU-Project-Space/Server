## 프로젝트 소개

이 프로젝트는 MSA(Microservice Architecture) 기반의 웹 애플리케이션으로, 학생들이 단체로 배달 음식을 주문하여, 배달비를 절약하고 할인 혜택을 누릴 수 있는 플랫폼을 제공합니다.

## 개발 요약
Spring Boot와 React를 활용하여 사용자, 가게, 주문, 결제 등 다양한 도메인 서버를 분리하여 개발하였습니다. 각 서버는 독립적으로 배포 및 관리되며, API Gateway를 통해 클라이언트와 통신합니다. 인프라 구축, CI/CD 파이프라인, DevOps 환경 경험 등 최신 백엔드/프론트엔드 기술을 적용하였습니다.


## 팀원

### 프론트엔드

| **박세렴** |
|:---------:|
| <img src="https://github.com/eileen4505.png" width="120"/> |
| [@eileen4505](https://github.com/eileen4505) |

---

### 백엔드

| **강민재** | **이도연** | **김호범** | **임지은** | **김동은** |
|:---------:|:---------:|:---------:|:---------:|:---------:|
| <img src="https://github.com/kmj02dev.png" width="120"/> | <img src="https://github.com/doup2001.png" width="120"/> | <img src="https://github.com/hobmk.png" width="120"/> | <img src="https://github.com/wldmsdl7.png" width="120"/> | <img src="https://github.com/ehddms0320.png" width="120"/> |
| [@kmj02dev](https://github.com/kmj02dev) | [@doup2001](https://github.com/doup2001) | [@hobmk](https://github.com/hobmk) | [@wldmsdl7](https://github.com/wldmsdl7) | [@ehddms0320](https://github.com/ehddms0320) |

---

## 역할

| 이름 | 역할/담당 | 역할 상세 | 주요 업무 및 기여 |  
|:---:|:---:|:---:|:---|  
| **강민재** | 팀장✨, 백엔드(주문 서버), 인프라 | 조장 / 백엔드 | 팀장, 인프라 및 CI/CD 파이프라인 구축, 주문 서버 개발, 프론트-백 연동, MSA 설계 |
| **박세렴** | 프론트엔드 | 프론트엔드 | 피그마 UI 디자인, 전체 프론트엔드 개발, 공통 레이아웃 및 styled-components 적용, 협업 및 피드백 |  
| **이도연** | 백엔드(결제 서버) | 백엔드 | 결제 서버 개발(토스페이먼츠 API 연동), 결제 UI 개발 및 연동, 아키텍처 제작, 게이트웨이 개발, CodeRabbit 도입, Notion 문서화 |  
| **김호범** | 백엔드(가게 서버) | 백엔드 | 가게 관리 서버 개발, 문서화, 빠른 피드백, MSA 구조 이해 및 구현 |  
| **임지은** | 백엔드(유저 서버) | 백엔드 | 유저 서버 개발, PPT 제작, 팀 내 소통 및 피드백, 추가 업무 지원 |  
| **김동은** | 백엔드(주문 서버) | 백엔드 | 주문 서버 개발, 협업 및 역할 분담, 브랜치 전략 적용 |  

---

## 프로젝트 주요 특징

- **MSA 구조**: 각 도메인별 서버와 DB를 분리하여 확장성과 유지보수성 강화
- **API Gateway**: 클라이언트와 백엔드 서버 간 통합 진입점 제공
- **CI/CD 자동화**: Github Actions를 통한 자동 배포 파이프라인 구축
- **DevOps/Cloud 경험**: AWS, GCP 등 클라우드 환경에서 서비스 운영 경험
- **문서화 및 협업**: Notion, Git을 활용한 체계적 문서화 및 협업

---

## 사용 기술 스택
- **Frontend**: React, styled-components, Figma
- **Backend**: Spring Boot, Spring Cloud Gateway, MySQL
- **CI/CD & Infra**: Github Actions, AWS, GCP
- **Collaboration**: Notion, Git, Github

---

## 아키텍처
![그림1](https://github.com/user-attachments/assets/e64fa7ce-669d-4b44-ad47-049f10fd82fd)

![아키텍처 다프론트엔드**: React
- **API Gateway**: Spring Cloud Gateway
- **백엔드**: Spring Boot (유저, 가게, 주문&모집, 결제 서버)
- **DB**: MySQL (도메인별 분리)
- **CI/CD**: Github Actions, AWS/GCP 배포
- **문서화**: Notion, PPT
